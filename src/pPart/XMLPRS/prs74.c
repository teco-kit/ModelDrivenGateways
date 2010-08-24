/**
* APPLICATION FILE PRS
* waits for information on when to transmit which sensor values and sends them.
*
* Authors: Sabin Wendhack <wendhack@teco.edu>, Christian Decker <cdecker@teco.edu>, 
* Till Riedel <riedel@teco.edu>
*
*/
#define NDEBUG
#define CCSC

#include <bitsio/write_bits_buf.c>
#include <bitsio/read_bits_buf.c>

#define __attribute__(X)
#include <sens_types.h>

//#include "debug_dom.h"
#include <Sample_dom2bin.c>

#include <SensorValues_operations.h>

// macros.

#include "prs74.h"

#define PRS_SEND_TIMEOUT_MAX     5     // number of bit to set, i. e. 32 slots.

#define PRS_SLEEP_WANTED         1     // allow sleep here.
#define PRS_SLOTS_FOR_ONE_SLEEP  202      // sleep-time ca. 2630 - 2640 ms = 202 slots.
#define PRS_SLOTS_SLEEP_SAFE     13    // 13 slots for safe wake-up.
#define  PRS_RATE_SLEEP_POSSIBLE    8     // every 256 slots, as 128 < 202 + 13

#define PRS_BITMASK_ACCL_AMBIENT_TEMP  0b01010011
#define PRS_BITMASK_AUDIO_FORCE        0b00100100


// globals.
unsigned int   PRS_sensors_included; //assert(sizeof(PRS_sensors_included)*8>PRS_SENSORS_MAX)

unsigned int   PRS_sensors_wanted;
unsigned int   PRS_sensor_rates[ PRS_SENSORS_MAX ]= PRS_SENSORS_DEFAULT;

unsigned long  PRS_slot_counter=0;
unsigned long  PRS_send_timeout;
unsigned long  PRS_last_slot_sent;
unsigned int   PRS_try_sleep;
//unsigned int   PRS_control_messages_counter;

// functions.
void PRSInit();
void PRSRun();
void PRSSendSensorValues();
//void PRSGoToSleep();
void PRSAddSensorValueToSend( unsigned int sensor );
void PRSSetAccl( void );
void PRSSetAcclZ( void );
void PRSSetAudio( void );
void PRSSetAmbientlight( void );
void PRSSetForce( void );
void PRSSetTemp( void );

int sendPacket( int  timeout );

/* dy */
static sens_SSimpSample s;

void PRSInit()
{

   enable_interrupts( GLOBAL );
   ACLStart();
   PRS_sensors_included = 0;
#ifdef ACCL_SENSOR
   bit_set( PRS_sensors_included, PRS_ACCL  );
#endif
#ifdef AUDIO_SENSOR
   bit_set( PRS_sensors_included, PRS_AUDIO );
#endif
#ifdef LIGHT_SENSOR
   bit_set( PRS_sensors_included, PRS_LIGHT );
#endif
#ifdef AMBIENT_LIGHT_SENSOR
   bit_set( PRS_sensors_included, PRS_AMBIENTLIGHT );
#endif
#ifdef FORCE_SENSOR
   bit_set( PRS_sensors_included, PRS_FORCE );
#endif
#ifdef TEMPERATURE_SENSOR
   bit_set( PRS_sensors_included, PRS_TEMP );
#endif

#ifdef DUMMY_MODE 
#ifdef TEMPERATURE_SENSOR
#error dummy mode
#endif
   {
     int i;
     for(i=0;i<PRS_SENSORS_MAX;i++)
       bit_set( PRS_sensors_included, i );
   }
#endif
   /* PRS_sensors_included = 0; */

   // rest of init.
   PRS_slot_counter = 0;
   PRS_send_timeout = PRS_SEND_TIMEOUT_MAX;
   PRS_last_slot_sent = 0;

   PRS_try_sleep = 0;

   {
     int i;
   for( i = 0; i < 8; ++i )
   {
      if( PRS_sensor_rates[ i ] < 16 ) bit_set( PRS_sensors_wanted, i );
      else bit_clear( PRS_sensors_wanted, i );
   }
   }
}

void PRSRun()
{
   ++PRS_slot_counter;
}


void PRSSendSensorValues()
{
   unsigned int i;
   unsigned int timeout;
   unsigned int sensor_mask = 0;
   unsigned int dummy;

   if( PRS_last_slot_sent != PRS_slot_counter )
   {
      unsigned long slot_counter_changes;
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

      memset(&s,0,sizeof(s));
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

     // if( PRS_SLEEP_WANTED && PRS_try_sleep && !PRS_control_messages_counter ) PRSGoToSleep();
   }
}

int sendPacket( int  timeout )
{
   ssize_t data_len;
   char buf[64];

   {
     struct WRITER_STRUCT writer;

     write_bits_bufwriter_init(&writer,buf,64);
     Sample_dom2bin_run(&s, &writer);
     data_len=write_buf_finish(&writer);
   }

   {
     uint16_t svc=0;
     uint8_t op=OP_SensorValues_SensorValuesEvent;

     ACLAddNewType(ACL_TYPE_ALPHA_ARG('S','V','C'));
     ACLAddDataN(&svc,sizeof(svc));

     ACLAddNewType(ACL_TYPE_ALPHA_ARG('O','P','?'));
     ACLAddDataN(&op,sizeof(op));

     ACLAddNewType(ACL_TYPE_ALPHA_ARG('X','M','L'));
     ACLAddDataN(buf,data_len);

     ACLSendPacket(20);
   while(ACLSendingBusy());
   }

#ifdef DUMMMY_MODE
   {
     int i;
     for(i=0;i<sizeof(s);i+=48)
     {
       ACLAddNewType(ACL_TYPE_ALPHA_ARG('D','O','M'));
       ACLAddDataN(((char *)&s)+i,((sizeof(s)-i)<48)?(sizeof(s)-i):48);
       ACLSendPacket(20);
       while(ACLSendingBusy());
     }
   }
#endif


   return 0;
}

#ifndef REAL_TIME_CLOCK
unsigned int RTCGetTimeFromServer()
{
   return 0;
}
void RTCAddTimeStampACL() {}
#endif

#include "prs74_set.c"
