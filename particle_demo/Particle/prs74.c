/**
* APPLICATION FILE PRS
* waits for information on when to transmit which sensor values and sends them.
*
* Author: Sabin Wendhack, wendhack@teco.edu
* Date: 2003-12-02
*
* Modified by Christian Decker, cdecker@teco.edu
* Date: 2003-12-04
*
* - adaption to new software architecture
*
* modified by cdecker (2004-01-09)
* - PRSSendAmbientlight fixed
*
* modified by sabin (2004-08-11)
* - default rates in PRSInit().
* - send aps type before sensorvalues.
* - crs type definition now in acltypes.
*
* modified by sabin (2004-11-22)
* - bug fixed: PRS_sensors_wanted wasn't cleared so PRSSensorsOffSelected() didn't work.
*
*/

/* dy */
#include "../bitsio/bits_io.h" 
#include "../bitsio/write_bits_buf.c"
#include "../gen/c/decoder_struct.h"
#include "../dec/decode_func.c"
#include "../gen/c/decoder_set.h"
#include "../gen/c/decoder_set.c"
#include "../dec/decoder_send.h"
#include "../gen/c/decoder_send.c"

// macros.
#define PRS_SENSORS_MAX    8           // space needed in PRS_buffer / space needed in ACL_buffer  (in bytes)

#define PRS_ACCL        0           // 6 / 12
#define PRS_ACCL_Z         1           // 3 / 6
#define PRS_AUDIO       2           // 3 / 6
#define PRS_LIGHT       3           // 3 / 6
#define PRS_AMBIENTLIGHT   4           // 3 / 6
#define PRS_FORCE       5           // 2 / 5
#define PRS_TEMP        6           // 2 / 5
#define  PRS_VOLTAGE       7           // 2 / 5

#define PRS_SENSOR_VALUES_MAX    24    // space needed in PRS_buffer for all used sensors.

#define PRS_SEND_TIMEOUT_MAX     5     // number of bit to set, i. e. 32 slots.

#define PRS_SLEEP_WANTED         1     // allow sleep here.
#define PRS_SLOTS_FOR_ONE_SLEEP  202      // sleep-time ca. 2630 - 2640 ms = 202 slots.
#define PRS_SLOTS_SLEEP_SAFE     13    // 13 slots for safe wake-up.
#define  PRS_RATE_SLEEP_POSSIBLE    8     // every 256 slots, as 128 < 202 + 13
#define PRS_CONTROL_MESSAGES_MAX 200      // number of slots it is not allowed to sleep after having received a control message. otap.

#define  PRS_ACCL_DEFAULT           6    // rates from 0 to 15, means 'send every slot' to 'send every 32768.'; rate 255 means 'don't send'.
#define  PRS_ACCL_Z_DEFAULT        6 
#define  PRS_AUDIO_DEFAULT          6
#define  PRS_AMBIENTLIGHT_DEFAULT   255
#define  PRS_LIGHT_DEFAULT         6 
#define  PRS_FORCE_DEFAULT          255 
#define  PRS_TEMP_DEFAULT          6 
#define  PRS_VOLTAGE_DEFAULT       6 

#define PRS_BITMASK_ACCL_AMBIENT_TEMP  0b01010011
#define PRS_BITMASK_AUDIO_FORCE        0b00100100
#define PRS_BITMASK_LIGHT           0b00001000


// globals.
unsigned int   PRS_sensors_included;
unsigned int   PRS_sensors_wanted;
unsigned int   PRS_sensor_rates[ PRS_SENSORS_MAX ];
unsigned long  PRS_slot_counter;
unsigned long  PRS_send_timeout;
unsigned long  PRS_last_slot_sent;
unsigned int   PRS_try_sleep;
unsigned int   PRS_control_messages_counter;

// functions.
void PRSInit();
void PRSRun();
void PRSSendSensorValues();
void PRSGoToSleep();
void PRSAddSensorValueToSend( unsigned int sensor );
void PRSSensorsOnSelected();
void PRSSensorsOffSelected();
void PRSSensorsOffAll();
void PRSGetAccl( unsigned int* pos );
void PRSSendAccl( void );
void PRSGetAcclZ( unsigned int* pos );
void PRSSendAcclZ( void );
void PRSGetAudio( unsigned int* pos );
void PRSSendAudio( void );
void PRSGetLight( unsigned int* pos );
void PRSSendLight( void );
void PRSGetAmbientlight( unsigned int* pos );
void PRSSendAmbientlight( void );
void PRSGetForce( unsigned int* pos );
void PRSSendForce( void );
void PRSGetTemp( unsigned int* pos );
void PRSSendTemp( void );
void PRSGetVoltage( unsigned int* pos );
void PRSSendVoltage( void );
int sendPacket( int  timeout );

/* dy */
static struct SensorValues *sensorVals;

void PRSInit()
{
   unsigned int i;
   unsigned int dummy1;
   unsigned int dummy2;

   PIC_TBLPTRL = 3;
   PIC_TBLPTRH = 0;
   PIC_TBLPTRU = 48;

#asm
   TBLRD*;
   nop
   nop
#endasm

   dummy1 = PIC_TABLAT;
   PIC_TBLPTRU = 0;

   // set watchdogtimer postscaler to  128, disabling hardwarecontrolled wdt to enable software control.
   ACLStop();
   disable_interrupts( GLOBAL );

   PIC_TBLPTRL = 3;
   PIC_TBLPTRH = 0;
   PIC_TBLPTRU = 48;

   PIC_TABLAT = 14;

#asm
   TBLWT*;
#endasm

   bit_set( PIC_EECON1, 7 );
   bit_set( PIC_EECON1, 6 );
   bit_set( PIC_EECON1, 2 );

   PIC_EECON2 = 0x55;
   PIC_EECON2 = 0xAA;

   bit_set( PIC_EECON1, 1 );

#asm
   nop
   nop
   nop
#endasm

   enable_interrupts( GLOBAL );
   ACLStart();
   bit_clear( PIC_WDTCON, 0 );

   PIC_TBLPTRL = 3;
   PIC_TBLPTRH = 0;
   PIC_TBLPTRU = 48;

#asm
   TBLRD*;
   nop
   nop
#endasm

   dummy2 = PIC_TABLAT;
   PIC_TBLPTRU = 0;

   bit_clear( PIC_WDTCON, 0 );

   // check wdt-config-bits.
   /*    while( ACLSendingBusy());
         if( !ACLSendingBusy())
         {
         ACLAddNewType( 79, 56 );
         ACLAddData( dummy1 );
         ACLAddData( dummy2 );
         ACLAddData( PIC_WDTCON );
         ACLSendPacket( 20 );
         }
         */
   PRS_sensors_included = 0;
#ifdef ACCL_SENSOR
   PRS_sensors_included |= 0b00000001;
#endif
#ifdef ACCL_Z_SENSOR
   PRS_sensors_included |= 0b00000010;
#endif
#ifdef AUDIO_SENSOR
   PRS_sensors_included |= 0b00000100;
#endif
#ifdef LIGHT_SENSOR
   PRS_sensors_included |= 0b00001000;
#endif
#ifdef AMBIENT_LIGHT_SENSOR
   PRS_sensors_included |= 0b00010000;
#endif
#ifdef FORCE_SENSOR
   PRS_sensors_included |= 0b00100000;
#endif
#ifdef TEMPERATURE_SENSOR
   PRS_sensors_included |= 0b01000000;
#endif
#ifdef VOLTAGE_SENSOR
   PRS_sensors_included |= 0b10000000;    // measure voltage supply.
#endif
   /* PRS_sensors_included = 0; */

   // rest of init.
   ACLSubscribe( ACL_TYPE_APS_HI, ACL_TYPE_APS_LO );
   ACLSubscribe( ACL_TYPE_CRS_HI, ACL_TYPE_CRS_LO );
   ACLSubscribe( ACL_TYPE_ACM_HI, ACL_TYPE_ACM_LO );
   PRS_slot_counter = 0;
   PRS_send_timeout = PRS_SEND_TIMEOUT_MAX;
   PRS_last_slot_sent = 0;
   for( i = 0; i < PRS_SENSORS_MAX; ++i )
   {
      PRS_sensor_rates[ i ] = 255;
   }
   PRS_sensor_rates[ PRS_ACCL ] = PRS_ACCL_DEFAULT;
   PRS_sensor_rates[ PRS_ACCL_Z ] = PRS_ACCL_Z_DEFAULT;
   PRS_sensor_rates[ PRS_AUDIO ] = PRS_AUDIO_DEFAULT;
   PRS_sensor_rates[ PRS_AMBIENTLIGHT ] = PRS_AMBIENTLIGHT_DEFAULT;
   PRS_sensor_rates[ PRS_LIGHT ] = PRS_LIGHT_DEFAULT;
   PRS_sensor_rates[ PRS_FORCE ] = PRS_FORCE_DEFAULT;
   PRS_sensor_rates[ PRS_TEMP ] = PRS_TEMP_DEFAULT;
   PRS_sensor_rates[ PRS_VOLTAGE ] = PRS_VOLTAGE_DEFAULT;
   PRS_try_sleep = 0;
   for( i = 0; i < 8; ++i )
   {
      if( PRS_sensor_rates[ i ] < 16 ) bit_set( PRS_sensors_wanted, i );
      else bit_clear( PRS_sensors_wanted, i );
   }
}

void PRSRun()
{

   /* unsigned int length; */
   /* unsigned int i;      */
   /* unsigned int min;    */
   /* unsigned int dummy1; */
   /* unsigned int dummy2; */

   ++PRS_slot_counter;
/*

   if( PRS_control_messages_counter ) --PRS_control_messages_counter;

   if( ACLAnyDataIsNewNow())
   {
      if( ACLFoundReceivedType( ACL_TYPE_ACM_HI, ACL_TYPE_ACM_LO )) PRS_control_messages_counter = PRS_CONTROL_MESSAGES_MAX;
   }
   if( ACLAckedDataIsNewNow())
   {
      if( ACLFoundReceivedType( ACL_TYPE_APS_HI, ACL_TYPE_APS_LO ))
      {
         if( ACLFoundReceivedType( ACL_TYPE_CRS_HI, ACL_TYPE_CRS_LO ))
         {
            length = ACLGetReceivedDataLength( ACL_TYPE_CRS_HI, ACL_TYPE_CRS_LO );

            for( i = 0; i < PRS_SENSORS_MAX; ++i )
            {
               PRS_sensor_rates[ i ] = 255;
            }

            for( i = 0; i < length; ++i )
            {
               dummy1 = ACLGetReceivedByte( ACL_TYPE_CRS_HI, ACL_TYPE_CRS_LO, i );
               dummy2 = ACLGetReceivedByte( ACL_TYPE_CRS_HI, ACL_TYPE_CRS_LO, ++i );
               PRS_sensor_rates[ dummy1 ] = dummy2;
            }

            PRS_send_timeout = 255;
            min = 255;
            for( i = 0; i < PRS_SENSORS_MAX; ++i )
            {
               dummy1 = PRS_sensor_rates[ i ];
               if( dummy1 < min ) min = dummy1;
            }

            if( min > PRS_SEND_TIMEOUT_MAX ) PRS_send_timeout = PRS_SEND_TIMEOUT_MAX;
            else PRS_send_timeout = min;

            for( i = 0; i < PRS_SENSORS_MAX; ++i )
            {
               if( PRS_sensor_rates[ i ] < 16 ) bit_set( PRS_sensors_wanted, i );
               else bit_clear( PRS_sensors_wanted, i );
            }

            PRSSensorsOnSelected();
            PRSSensorsOffSelected();

            PRS_slot_counter = 0;
            PRS_try_sleep = 1;
         }
      }
   }
*/
   /* dy */
   sensorVals = get_SensorValues();
}


void PRSSendSensorValues()
{
   unsigned int i;
   unsigned int timeout;
   unsigned int sensor_mask = 0;
   unsigned long slot_counter_changes = 0;
   unsigned int dummy;

   if( PRS_last_slot_sent != PRS_slot_counter )
   {
      PRS_last_slot_sent = PRS_slot_counter;
      slot_counter_changes = PRS_last_slot_sent ^ ( PRS_last_slot_sent - 1 );

      for( i = 0; i < PRS_SENSORS_MAX; ++i )
      {
         dummy = PRS_sensor_rates[ i ];
         if( dummy < 16 )
         {
            if( bit_test( slot_counter_changes, dummy )) bit_set( sensor_mask, i );
         }
      }

      if( sensor_mask ) 
      {
         bit_set( timeout, PRS_send_timeout );


         for( i = 0; i < PRS_SENSORS_MAX; ++i )
         {
            if( bit_test( sensor_mask, i ))
               PRSAddSensorValueToSend( i );
         }

         sendPacket( timeout );
         PRS_try_sleep = 1;

      }

      if( PRS_SLEEP_WANTED && PRS_try_sleep && !PRS_control_messages_counter ) PRSGoToSleep();
   }
}

int sendPacket( int  timeout )
{
   ssize_t data_len;

   if (LLSendingBusy()) return 0;

   // now copy the ACL to the LL payload
   LL_payload_send[0] = 160; // 160 117
   LL_payload_send[1] = 117;
   data_len = send_data(LL_payload_send + 3, LL_PAYLOAD_SIZE);
   LL_payload_send[2] = data_len; // 
   LL_payload_length  =  3 + data_len;
   if (LLSendPacket(timeout))
      return 1;
   else 
      return 0;
}

void PRSGoToSleep()
{
   unsigned int   min = 255;
   unsigned long  diff_sensor_send = 0;
   unsigned int   i;
   unsigned long  slot_counter;
   unsigned int   dummy1;
   // measuring sleep time.
   /* unsigned int   stunde;
      unsigned int   minute;
      unsigned int   sekunde;
      unsigned long  millisekunde;
      unsigned int   stunde2;
      unsigned int   minute2;
      unsigned int   sekunde2;
      unsigned long  millisekunde2;
      */

   RTCUpdate();

   for( i = 0; i < PRS_SENSORS_MAX; ++i )
   {
      dummy1 = PRS_sensor_rates[ i ];
      if( dummy1 < min ) min = dummy1;
   }

   if( min >= PRS_RATE_SLEEP_POSSIBLE )
   {
      while( ACLSendingBusy()) { SSimpLEDAmberOn(); SSimpLEDAmberOff(); }

      if( min <= 15 )
      {
         bit_set( diff_sensor_send, min );
         slot_counter = PRS_slot_counter;

         diff_sensor_send -= ( slot_counter & ( diff_sensor_send - 1 ));
      }

      if(( min > 15 ) || ( diff_sensor_send > ( PRS_SLOTS_FOR_ONE_SLEEP + PRS_SLOTS_SLEEP_SAFE )))  // 15: no sensor value or incorrect sensor rate requested.
      {
         PRSSensorsOffAll();
         ACLStop();
         // measuring sleep time.
         //          RTCGetClock( &stunde, &minute, &sekunde, &millisekunde );
         bit_set( PIC_WDTCON, 0 );
         restart_wdt();
         sleep();

#asm
        nop
        nop
        nop
#endasm

        bit_clear( PIC_WDTCON, 0 );
        RTCUpdate();
        // measuring sleep time.
        //          RTCGetClock( &stunde2, &minute2, &sekunde2, &millisekunde2 );
        ACLStart();
        PRSSensorsOnSelected();
        PRS_slot_counter += PRS_SLOTS_FOR_ONE_SLEEP;
        delay_ms( 1 );

        // measuring sleep time.
        /*          if( !ACLSendingBusy())
                    {
                    ACLAddNewType( 78, 56 );
                    ACLAddData( stunde );
                    ACLAddData( minute );
                    ACLAddData( sekunde );
                    ACLAddData( hi( millisekunde ));
                    ACLAddData( lo( millisekunde ));
                    ACLAddData( stunde2 );
                    ACLAddData( minute2 );
                    ACLAddData( sekunde2 );
                    ACLAddData( hi( millisekunde2 ));
                    ACLAddData( lo( millisekunde2 ));
                    ACLSendPacket( 20 );
                    delay_ms( 20 );
                    }
                     */       }
      else PRS_try_sleep = 0;
   }
   else PRS_try_sleep = 0;
}

void PRSAddSensorValueToSend( unsigned int sensor )
{

   switch( sensor )
   {
      case PRS_ACCL:          
         PRSSendAccl(); 
         break;
      case PRS_ACCL_Z:     
         PRSSendAcclZ();
         break;
      case PRS_AUDIO:         
         PRSSendAudio();
         break;
      case PRS_LIGHT:         
         PRSSendLight();
         break;
      case PRS_AMBIENTLIGHT:  
         PRSSendAmbientlight();
         break;
      case PRS_FORCE:         
         PRSSendForce();
         break;
      case PRS_TEMP:       
         PRSSendTemp();
         break;
      case PRS_VOLTAGE:    
         PRSSendVoltage();
         break;
      default:
         break;
   }

}

void PRSSensorsOnSelected()
{
   unsigned int dummy;

   dummy = PRS_sensors_included & PRS_sensors_wanted;
   if( bit_test( dummy, PRS_ACCL )) AcclSensorOn();
   if( bit_test( dummy, PRS_ACCL_Z )) AcclZSensorOn();
   if( bit_test( dummy, PRS_AUDIO )) AudioSensorOn();
   if( bit_test( dummy, PRS_LIGHT )) LightSensorOn();
   if( bit_test( dummy, PRS_AMBIENTLIGHT )) AmbientLightSensorOn();
   if( bit_test( dummy, PRS_FORCE )) ForceSensorOn();
   if( bit_test( dummy, PRS_TEMP )) TemperatureSensorOn();
}

void PRSSensorsOffSelected()
{
   if( !( PRS_sensors_wanted & PRS_BITMASK_ACCL_AMBIENT_TEMP )) AcclSensorOff();
   if( !( PRS_sensors_wanted & PRS_BITMASK_AUDIO_FORCE )) AudioSensorOff();
   if( !( PRS_sensors_wanted & PRS_BITMASK_LIGHT )) LightSensorOff();
}

void PRSSensorsOffAll()
{
   /* unsigned int dummy; */

   AcclSensorOff();
   AcclZSensorOff();
   AudioSensorOff();
   LightSensorOff();
   AmbientLightSensorOff();
   ForceSensorOff();
   TemperatureSensorOff();
}

#ifdef ACCL_SENSOR
void PRSGetAccl( unsigned int* pos )
{
   unsigned long dummy1;
   signed long dummy2;

   AcclSensorPrepare();

   do
   {
      dummy1=rf_slotcounter;
      AcclXSensorGetGravity( &dummy2);

   } while (rf_slotcounter!=dummy1);

   *pos = hi( dummy2 );
   *( pos + 1 ) = lo( dummy2 );

   do
   {
      dummy1=rf_slotcounter;
      AcclYSensorGetGravity( &dummy2 );
   } while (rf_slotcounter!=dummy1);

   *( pos + 2 ) = hi( dummy2 );
   *( pos + 3 ) = lo( dummy2 );
}

void PRSSendAccl( void )
{

   /* dy */
   uint32_t  pos;
   signed long   value;
   struct AcclX *acclX;
   struct AcclY *acclY;

   PRSGetAccl(&pos);

   value=(((unsigned long)(pos))<<8) + *(&pos+1);
   acclX = SensorValues_get_AcclX(sensorVals);
   AcclX_set_AcclValue(acclX, value);
   AcclX_set_AcclIdx(acclX, 0);

   value=(((unsigned long)(*(&pos+2)))<<8) + *(&pos+3);
   acclY = SensorValues_get_AcclY(sensorVals);
   AcclY_set_AcclValue(acclY, value);
   AcclY_set_AcclIdx(acclY, 0);
}
#else
void PRSGetAccl( unsigned int* pos ) {}
void PRSSendAccl( void ) {}
#endif

#ifdef ACCL_Z_SENSOR
void PRSGetAcclZ( unsigned int* pos )
{
   unsigned long dummy1;
   signed long dummy2;

   AcclZSensorPrepare();

   do
   {
      dummy1=rf_slotcounter;
      AcclZSensorGetGravity( &dummy2 );
   } while (rf_slotcounter!=dummy1);

   *pos = hi( dummy2 );
   *( pos + 1 ) = lo( dummy2 );
}

void PRSSendAcclZ( void )
{
   /* dy */
   signed   long   value;
   struct   AcclZ *acclZ;
   unsigned long     pos;

   PRSGetAcclZ( &pos );

   value=(((unsigned long)(pos))<<8) + *(&pos+1);
   acclZ = SensorValues_get_AcclZ(sensorVals);
   AcclZ_set_AcclValue(acclZ, value);
   AcclZ_set_AcclIdx(acclZ, 0);
}
#else
void PRSGetAcclZ( unsigned int* pos ) {}
void PRSSendAcclZ( void ) {}
#endif

#ifdef AUDIO_SENSOR
void PRSGetAudio( unsigned int* pos )
{
   unsigned int dummy1;

   AudioSensorPrepare();
   AudioSensorGetVolume( &dummy1 );

   *pos = dummy1;
}

void PRSSendAudio( void )
{
   /* dy */
   unsigned int pos;


   PRSGetAudio( &pos );
   SensorValues_set_Audio(sensorVals, pos);
}
#else
void PRSGetAudio( unsigned int* pos ) {}
void PRSSendAudio( void ) {}
#endif

#ifdef LIGHT_SENSOR
void PRSGetLight( unsigned int* pos )
{
   byte dummy1;

   LightSensorPrepare();
   LightSensorGet( &dummy1 );

   *pos = dummy1;
}

void PRSSendLight( void )
{
   /* dy */
   struct   Light *light;
   byte            pos;

   PRSGetLight( &pos );

   light = SensorValues_get_Light(sensorVals);

   Light_set_LightValue(light, pos);
   Light_set_LightIdx(light, 0);
}
#else
void PRSGetLight( unsigned int* pos ) {}
void PRSSendLight( void ) {}
#endif

#ifdef AMBIENT_LIGHT_SENSOR
void PRSGetAmbientlight( unsigned int* pos )
{
   unsigned long dummy1;
   unsigned long dummy2;

   AmbientLightSensorPrepare();


   AmbientLightVisibleSensorGet( &dummy1 );
   AmbientLightIRSensorGet( &dummy2 );

   //AmbientLightVisibleSensorPackInACL( dummy1 );
   //AmbientLightIRSensorPackInACL( dummy2);

   *pos = hi( dummy1 );
   *( pos + 1 ) = lo( dummy1 );
   *( pos + 2 ) = hi( dummy2 );
   *( pos + 3 ) = lo( dummy2 );
}

void PRSSendAmbientlight( void )
{
   /*dy*/
   unsigned long t;
   struct AmbientLight *aLight;
   uint32_t pos;

   PRSGetAmbientlight( &pos );

   t = 0;
   t = (*(&pos+2));
   t = t << 8;
   t = t | ( 0x00FF & (long)(*(&pos+3)));

   /* AmbientLightIRSensorPackInACL(t); */

   aLight = SensorValues_get_AmbientLight(sensorVals);
   AmbientLight_set_LightValue(aLight, t);
   AmbientLight_set_LightIdx(aLight, 0);

   /* t = (pos);                            */
   /* t = t << 8;                            */
   /* t = t | ( 0x00FF & (long)(*(&pos+1)));  */

   /* AmbientLightVisibleSensorPackInACL(t); */

}
#else
void PRSGetAmbientlight( unsigned int* pos ) {}
void PRSSendAmbientlight( void ) {}
#endif

#ifdef FORCE_SENSOR
void PRSGetForce( unsigned int* pos )
{
   unsigned int dummy1;

   ForceSensorPrepare();
   ForceSensorGet( &dummy1 );

   *pos = dummy1;
}

void PRSSendForce( void )
{
   /* dy */
   struct Force *force;
   unsigned int pos;

   PRSGetForce( &pos );
   force = SensorValues_get_Force(sensorVals);
   Force_set_ForceValue(force, pos);
   Force_set_ForceIdx(force, 0);
}
#else
void PRSGetForce( unsigned int* pos ) {}
void PRSSendForce( void ) {}
#endif

#ifdef TEMPERATURE_SENSOR
void PRSGetTemp( unsigned int* pos )
{
   signed int dummy1;

   TemperatureSensorPrepare();
   TemperatureSensorGet( &dummy1 );

   *pos = dummy1;
}

void PRSSendTemp( void )
{
   /* dy */
   struct Temperature  *temp;
   signed int pos;

   PRSGetTemp( &pos );
   temp = SensorValues_get_Temperature(sensorVals);
   Temperature_set_TempValue(temp, pos);
   Temperature_set_TempIdx(temp, 0);
}
#else
void PRSGetTemp( unsigned int* pos ) {}
void PRSSendTemp( void ) {}
#endif

#ifdef VOLTAGE_SENSOR
void PRSGetVoltage( unsigned int* pos )
{
   unsigned long dummy1;

   VoltageSensorPrepare();
   VoltageSensorGet( &dummy1 );

   *pos = hi( dummy1 );
   *( pos + 1 ) = lo( dummy1 );
}

void PRSSendVoltage( void )
{
   /* dy */
   unsigned long dummy;
   unsigned long pos;

   PRSGetVoltage( &pos );

   dummy = ((long)(pos) << 8 ) | ( 255 & (long)(*( &pos + 1 )));
   SensorValues_set_Voltage(sensorVals, dummy);
}
#else
void PRSGetVoltage( unsigned int* pos ) {}
void PRSSendVoltage( void ) {}
#endif

#ifndef REAL_TIME_CLOCK
unsigned int RTCGetTimeFromServer()
{
   return 0;
}
void RTCAddTimeStampACL() {}
#endif

