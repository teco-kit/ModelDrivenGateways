#include "stdsoap2.h"
#include "../src-gen/SensorValues_operations.h"
#include "sendrcv.h"

#ifdef WITH_USBBRIDGE
#include "usbbridge.h"
#else
#include <libparticle.h>
#endif

#ifndef WIN32
#include <pthread.h>
#endif

/*#include "../../bitsio/read_bits_buf.h"*/
int shutdownFlag = 0; /* Flag indicates system shutdown. */

#ifdef WIN32                    /* The working thread. */
DWORD workerid;
HANDLE worker;
#else
pthread_t worker;
#endif


#ifndef WITH_USBBRIDGE
static void *device;
static int s_recv,s_send;
static char buffer1[64],buffer2[64];
static char *global_packet_buffer=buffer1;


void send_buf(struct dpws_s *device, int service_id, int op_id, struct soap* soap, u_char* buf, ssize_t length)
{
	if (op_id==OP_SensorValues_ConfigureSensors)
	{
		struct p_packet *p=p_pkt_alloc();
		p_acl_add_str(p,"XML",buf,length,0);
		p_socket_send(s_send,p);
		p_pkt_free(p);
	}
	//TODO: dispatch by  device,
	//TODO: fill in service, operation and message context
	//TODO: save context for true calls
}

char *rcv_buf(struct dpws_s *device, int service_id,int op_id, struct soap* soap)
{
	//TODO dispatch by  device, service, operation and message context
	if (op_id==OP_SensorValues_GetSensorValues || op_id==OP_SensorValues_SensorValuesEvent)
		return  global_packet_buffer;
	else return NULL;
}
#endif

void *
event_worker_loop() {
#ifdef WITH_USBBRIDGE
  /*struct READER_STRUCT reader;*/
   usbbridge bridge;
	printf("Starting working loop...\n");


   while( true )
   {
      if (shutdownFlag)
         break;

      if( usbbridge_step( &bridge ) )
      {
         printf("Bridge error...\n");
         //ERROR( bridge.error );
         break;
      }
    }
#else
   {
	   struct p_packet *p;
while (!shutdownFlag)
{

if (p_socket_select(s_recv, 100))  while((p = p_socket_recv(s_recv, s_send))) {
   struct p_acl_tuple *a;
   static int seq=0;

   if (shutdownFlag)
      return 0;

   if(seq!=p_pkt_get_seq(p))
      if (p_acl_findfirst_str(p, "xml", &a) >= 0) {
         uint8_t *buff;
         ssize_t len;
         len = p_acl_get_data(a, &buff);
         {
        	 extern void  SensorValues_SensorValuesEvent_event(void *, char *);
        	 SensorValues_SensorValuesEvent_event(device, (char *)buff);
         }

         {

         if (global_packet_buffer == buffer1)
         {
            memcpy(buffer2, buff, len);
            global_packet_buffer = buffer2;
         }
         else
         {
            memcpy(buffer1, buff, len);
            global_packet_buffer = buffer1;
         }
         }

      }
   seq=p_pkt_get_seq(p);

   p_pkt_free(p);
   /*sleep(15);*/
}
}
}
#endif

printf("Leaving working loop...\n");
return 0;
}

int event_worker_init(void *_device) {
#ifndef WITH_USBBRIDGE
   device = _device;
#endif


#if WITH_USBBRIDGE
   if( usbbridge_init(&bridge))
   {
	 printf("cannot open usb bridge...\n");
     return SOAP_ERR;
   }

   usbbridge_set_eventdevice(&bridge,_device);
#else
   if(0>(s_recv=p_socket_open(0,0,5555))||0>(s_send=p_socket_open(0,0,5556)))
   {
		 	 printf("cannot bind libparticle  ...\n");
		     return SOAP_ERR;
    }
   p_socket_set_option(s_recv,SOCKET_BLOCKING,0);
   printf("listening for particles on:\n");
   p_describe_socket(s_recv);
   printf("sending to particles on:\n");
   p_describe_socket(s_send);
   fflush(stdout);
#endif

#ifdef WIN32
   worker =
      CreateThread (NULL, 0, (DWORD WINAPI) event_worker_loop, NULL, 0,
                    &workerid);
   if (worker == NULL)
#else
      if (pthread_create(&worker, NULL, event_worker_loop, NULL))
#endif
       return SOAP_ERR;

   return SOAP_OK;
}

int event_worker_shutdown() {
   shutdownFlag = 1;
#ifdef WIN32
   WaitForSingleObject (worker, INFINITE);
#else
   pthread_join(worker, NULL);
#endif
   return 0;
}
