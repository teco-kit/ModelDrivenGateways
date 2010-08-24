/*
 * sensorvalues_service.c
 *
 *  Created on: 02.03.2010
 *      Author: riedel
 */
#include <stdlib.h>
#include <sys/time.h>
#include <math.h>
#include <string.h>
#include <sens_types.h>
#include "sensorvalues_service.h"

static struct timeval timeoffset,uptime;
static sens_SensorConfigurationType sensorConfig={};
static sens_SensorConfigurationType allSensorConfig={};
static int time_set=0;

#define MAX(X,Y) ((X) > (Y) ? (X) : (Y))


void get_time(struct timeval* rel,struct timeval* abs)
{
	abs->tv_sec=rel->tv_sec-timeoffset.tv_sec;
	abs->tv_usec=rel->tv_usec-timeoffset.tv_usec;
}

uint16_t sec;

void init_time()
{
#ifdef DUMMY_MODE
	/* radomize local time for dummy*/
	gettimeofday(&timeoffset,NULL);
	gettimeofday(&uptime,NULL);
#endif
  // setup and start timer 3 (RTC)
  bit_set( PIC_T1CON, 3 );		//TIMER1 oszillator enable (is for timer3 as well)
  PIC_T3CON = 0b01110111;		//timer3: 2*8bit acces,1:8 prescale,no external sync, input from timer 1 oszi, enable timer
  								//(one step = 244,1us = 1/4096s)
  // configure interrupts
//  bit_clear(PIC_RCON, 7);			// interrupt priorities (IPEN)
//  bit_clear(PIC_IPR2, 1);			// timer3 priority bit -> high priority (TMR3P)
//  bit_set(PIC_INTCON, 6);			// enable peripheral interrupts (PEIE/GIEL)

}
//assert that it is called in time for overflow todo: use interrupt
void update_time()
{
	if(PIC_TMR3H&0xf0)
	{
		uint8_t overflow;
		overflow=PIC_TMR3H&0xf0;
		sec+=overflow>>4;
		PIC_TMR3H-=overflow; //don't only clear in case of TMR3L overflow
	}
}

void gettimeofday(struct timeval * t,void *dummy)
{
	uint8_t OTMR3H;
	do{
	OTMR3H=PIC_TMR3H;
	t->tv_sec=sec+(PIC_TMR3H>>4);
	t->tv_usec=(uint16_t)PIC_TMR3H<<12|(uint16_t)PIC_TMR3L<<4; //TODO not real u_sec->ticks
	}
	while(Timer3GetHi()!=OTMR3H);
}

void set_curtime(struct timeval* abs)
{
	gettimeofday(&timeoffset,NULL);

	timeoffset.tv_sec-=abs->tv_sec;
	timeoffset.tv_usec-=abs->tv_usec;
}

void init_sensors()
{
	/* initial sensors with defaults*/
#if defined(ACCL_SENSOR) || defined(DUMMY_MODE)
	sens_SensorConfigurationType_add_acceleration(&sensorConfig)->rate=1;
	sens_SensorConfigurationType_add_acceleration(&allSensorConfig)->rate=1;
#endif

#if defined(TEMPERATURE_SENSOR) || defined(DUMMY_MODE)
	sens_SensorConfigurationType_add_temperature(&sensorConfig)->rate=7;
	sens_SensorConfigurationType_add_temperature(&allSensorConfig)->rate=4;
#endif

#if defined(FORCE_SENSOR) || defined(DUMMY_MODE)
	sens_SensorConfigurationType_add_force(&allSensorConfig)->rate=1;
#endif

#if defined(AMBIENT_LIGHT_SENSOR) || defined(DUMMY_MODE)
	sens_SensorConfigurationType_add_ambientLight(&allSensorConfig)->rate=1;
#endif
}

int get_sensor_values(sens_SSimpSample *s, int16_t eventCount) /* gets called for event and get */
{
	int has_data=0;
	sens_DateTime *t;

	memset(s,sizeof(sens_SSimpSample),0);

  //  t	= sens_SSimpSample_add_timeStamp(s);
//	gettimeofday(t,NULL);


    if(	    (allSensorConfig.ambientLight.len && eventCount<0 )	||
    		(sensorConfig.ambientLight.len    && 0==(eventCount%(1L<<sensorConfig.ambientLight.element[0].rate))
    				))
    {
#ifdef DUMMY_MODE
        sens_TSL2550Sample *light;
        light	= sens_SSimpSample_add_light(s);
        light->daylight=100+rand()*10;
        light->infrared=100-light->daylight;
#else
#ifdef AMBIENT_LIGHT_SENSOR
{
	sens_TSL2550Sample *light; 
	light = sens_SSimpSample_add_light(s);

	AmbientLightSensorPrepare();
	AmbientLightVisibleSensorGet( &light->daylight );
	AmbientLightIRSensorGet( &light->infrared );
}
#endif
#endif
        has_data=1;
    }

	if( (allSensorConfig.force.len && eventCount<0)	||
		(sensorConfig.force.len    && 0==(eventCount%(1L<<sensorConfig.force.element[0].rate))))
	{
#ifdef DUMMY_MODE
	  sens_SSimpSample_add_force(s)->value = rand()*100;
#else
#ifdef FORCE_SENSOR
{
   sens_FSR152Sample *force;
   force = sens_SSimpSample_add_force(s);
   ForceSensorPrepare();
   ForceSensorGet( &force->value );
}

#endif
#endif
	  has_data=1;
	}

	if( (eventCount<0 && allSensorConfig.temperature.len)	||(sensorConfig.temperature.len && 0==(eventCount%(1L<<sensorConfig.temperature.element[0].rate))))
	{
#ifdef DUMMY_MODE
		sens_SSimpSample_add_temperature(s)->value=
			23;
#else
#ifdef TEMPERATURE_SENSOR
		{
		sens_TC74Sample *temp;
		temp = sens_SSimpSample_add_temperature(s);
		TemperatureSensorPrepare();
		TemperatureSensorGet( &temp->value );
		}
#endif
#endif
		has_data=1;
	}

    if( (eventCount<0 && allSensorConfig.acceleration.len)	|| (sensorConfig.acceleration.len && 0==(eventCount%(1L<<sensorConfig.acceleration.element[0].rate))))
	{
#ifdef DUMMY_MODE
#ifndef M_PI
#define M_PI		3.14159265358979323846
#define M_PI_2		1.57079632679489661923
#endif
		int period=10000; //in us
		double x;
		x=((double)(t->tv_usec%period))/period*2*M_PI;
		x=1;

		{
		sens_ADXL210Sample *accl;
	         accl	= sens_SSimpSample_add_accelleration(s);

		accl->x = sin(x)*1000;
		accl->y = sin(x+M_PI_2)*1000;
		accl->z = sin(x+M_PI)*1000;
		}
#else
#ifdef ACCL_SENSOR
   {
  	sens_ADXL210Sample *accl;
        accl = sens_SSimpSample_add_accelleration(s);

        AcclXSensorPrepare();
        AcclXSensorGetGravity(&(accl->x));
        AcclYSensorPrepare();
        AcclYSensorGetGravity(&(accl->y));
#ifdef ACCL_Z_SENSOR
        //TODO change to optional
        AcclZSensorPrepare();
        AcclZSensorGetGravity( &(accl->z) );
#endif
   }
#endif
#endif
		has_data=1;
	}

    return (!has_data);
}

#define config_rate(SENSOR,s)\
{\
	sens_SSimpRateConfig *c,*c_a;\
	c=sens_SensorConfigurationType_next_##SENSOR(s,NULL);\
	c_a=sens_SensorConfigurationType_next_##SENSOR(&allSensorConfig,NULL);\
       if(c!=NULL && c_a!=NULL) sens_SensorConfigurationType_add_##SENSOR(&sensorConfig)->rate=MAX(c->rate,c_a->rate);\
}

int configure_sensors(sens_SSimpControl *in, sens_SSimpStatus *out)
{

	gettimeofday(&(out->currentTime),NULL);

	get_time(&out->currentTime,&out->currentTime);
	get_time(&out->upTime,&uptime);

	/*set new time*/
	if(in->newTime.len)
	{
	  set_curtime(in->newTime.element);
	  time_set=1;
	}

	/*set new sensor config*/
	if(in->sensorConfig.len)
	{
		config_rate(acceleration,in->sensorConfig.element);
		config_rate(ambientLight,in->sensorConfig.element);
		config_rate(audio,in->sensorConfig.element);
		config_rate(force,in->sensorConfig.element);
		config_rate(temperature,in->sensorConfig.element);
	}

	/*return current/new values*/
	memcpy(&out->sensorConfig,&sensorConfig,sizeof(sens_SensorConfigurationType));
	memcpy(&out->allSensorConfig,&allSensorConfig,sizeof(sens_SensorConfigurationType));

#ifdef DUMMY_MODE
	out->batteryVoltage=1200;
#else
	VoltageSensorPrepare();
	VoltageSensorGet(&out->batteryVoltage);
#endif

	return 0;
}
