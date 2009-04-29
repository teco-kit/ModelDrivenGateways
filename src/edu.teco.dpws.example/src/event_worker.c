#include "stdsoap2.h"
#include "usbbridge.h"

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

static void *device;



void *
event_worker_loop() {

  /*struct READER_STRUCT reader;*/
   usbbridge bridge;
	printf("Starting working loop...\n");
   usbbridge_init(&bridge);
   usbbridge_set_eventdevice(&bridge,device);

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
#if 0
while (p = p_socket_recv(recv, send)) {
   struct p_acl_tuple *a;
   static int seq=0;

   if (shutdownFlag)
      return;

   if(seq!=p_pkt_get_seq(p))
      if (p_acl_findfirst_str(p, "xml", &a) >= 0) {
         uint8_t *buff;
         ssize_t len;
         len = p_acl_get_data(a, &buff);

         deliver_event(device, buff);
         /*
            read_init(&reader, buff);
            encoder_automata_init(fileno(stdout), &reader);
            encoder_automata_run();
            */


         if (curr_buf == 0)
         {
            memcpy(buffer2, buff, len);
            curr_buf = 1;
            global_packet_buffer = buffer2;
         }
         else
         {
            memcpy(buffer1, buff, len);
            curr_buf = 0;
            global_packet_buffer = buffer1;
         }

      }
   seq=p_pkt_get_seq(p);

   p_pkt_free(p);
   /*sleep(15);*/

}
#endif



printf("Leaving working loop...\n");
return 0;
}

int event_worker_init(void *_device) {
   device = _device;

#ifdef WIN32
   worker =
      CreateThread (NULL, 0, (DWORD WINAPI) event_worker_loop, NULL, 0,
                    &workerid);
   if (worker != NULL)
#else
      if (pthread_create(&worker, NULL, event_worker_loop, NULL) == 0)
#endif
      {
         return SOAP_OK;
      } else {
         return SOAP_ERR;
      }
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
