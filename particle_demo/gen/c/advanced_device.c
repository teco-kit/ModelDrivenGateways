
/* Generated file */  
#include "teco.nsmap"
#include "dpws_device.h"
#include "teco_metadata.h"
#include "teco_wsdl.h"
#include "config.h"
#include <signal.h>

#include "../../bitsio/read_bits_buf.h"
#include "event_worker.h"

struct soap service;
struct dpws_s device;

void
service_exit ()
{
#ifdef DEBUG
  printf ("\nSensor Monitor: shutting down...\n");
#endif

  event_worker_shutdown();

  dpws_deactivate_hosting_service (&device);
  soap_done (&service);
  dpws_done (&device);

  exit (0);
}

int
main (int argc, char **argv)
{
#ifndef WIN32
  struct sigaction sa;
#endif
  char *interface = NULL;
  char *uuid = NULL;

  /* parsing command line options */
  while (argc > 1)
    {
      if (argv[1][0] == '-')
        {
          char *option = &argv[1][1];

          switch (option[0])
            {
            case 'i': /* set interface with option -i */
              if (strlen (option) > 2)
                {
                  ++option;
                  interface = option;
                }
              else
                {
                  --argc;
                  ++argv;
                  interface = argv[1];
                }
#ifdef DEBUG
              printf ("\nSensor Monitor: Set interface to \"%s\"\n", interface);
#endif
              break;
            case 'u': /* set id with option -u */
              if (strlen (option) > 2)
                {
                  ++option;
                  uuid = option;
                }
              else
                {
                  --argc;
                  ++argv;
                  uuid = argv[1];
                }
#ifdef DEBUG
              printf ("\nSensor Monitor: Set uuid to \"%s\"\n", uuid);
#endif
              break;
            default:
              fprintf (stderr, "\nSensor Monitor: Bad option %s\n", argv[1]);
              printf ("\n%s -i [interface address] -u urn:uuid[uuid]\n", argv[0]);
         exit(1);
            }
        }
      --argc;
      ++argv;
    }

  if (interface == NULL)
    {
      fprintf (stderr, "\nSensor Monitor: No interface addrss was specified!\n");
      exit (1);
    }

  /* initialize soap handle */
  soap_init (&service);
#ifdef DEBUG
  soap_omode (&service, SOAP_XML_INDENT);
#endif
  soap_set_namespaces (&service, teco_namespaces);

  /* initialize device and services */
  if (dpws_init (&device, interface)
      || teco_setup_HostingService (&device, &service, uuid, 100)
      || teco_setup_ParticleSensor (&device, &service, SENSORVALUES_WSDL,
                                                100))
    {
      fprintf (stderr, "\nSensor Monitor: Can't init device and services\n");
      dpws_done (&device);
      exit (1);
    }

  /* Set Metadata */
  teco_set_Metadata (&device);
  teco_set_wsdl (&device);

  /* Update Metadata */
  if (dpws_update_Metadata (&device))
    {
      fprintf (stderr, "\nSensor Monitor: Can't init metadata\n");
      dpws_done (&device);
      exit (1);
    }

  /* install signal handler for SIGINT or Ctrl-C */
#ifdef WIN32
  signal (SIGINT, service_exit);
#else
  memset (&sa, 0, sizeof (sa));
  sa.sa_handler = service_exit;
  sigaction (SIGINT, &sa, NULL);
#endif

  /* Tell hosting service to start advertising its hosted services */
  if (dpws_activate_hosting_service (&device))
    {
      fprintf (stderr, "\nSensor Monitor: Can't activate device\n");
      dpws_done (&device);
      exit (1);
    }

  /* activate eventing. */
  if (dpws_activate_eventsource (&device, &service))
    {
      printf ("\nSensor Monitor: Can't activate eventing\n");
      dpws_done (&device);
      exit (0);
    }

  if (event_worker_init(&device))
  {
      printf ("\nSensor Monitor: Can't init event worker\n");
      dpws_done (&device);
      exit (0);
  }


#ifdef DEBUG
  printf ("\nSensor Monitor: ready to serve... (Ctrl-C for shut down)\n");
#endif

  for (;;)
    {
      struct soap *handle = NULL, *soap_set[] = SOAP_HANDLE_SET (&service);
      int (*serve_requests[]) (struct soap * soap) =
        SOAP_SERVE_SET (teco_serve_request);

#ifdef DEBUG
      printf ("\nSensor Monitor: waiting for request\n");
#endif

      /* waiting for new messages */
      handle = dpws_maccept (&device, 100000, 1, soap_set);

      if (handle)
        {

#ifdef DEBUG
          printf ("\nSensor Monitor: processing request from %s:%d\n",
                  inet_ntoa (handle->peer.sin_addr),
                  ntohs (handle->peer.sin_port));
#endif

          /* dispatch messages */
          if (dpws_mserve (handle, 1, serve_requests))
            {
              soap_print_fault (handle, stderr);
            }

          /* clean up soaps internaly allocated memory */
          soap_end (handle);
        }
      
      dpws_check_subscriptions(&device);
    }

  return -1;
}


int __teco1__GetSensorValues(struct soap* soap, 
                                         struct _teco1__EmptyMessage    *teco_GetSensorValues, 
                                         struct teco1__SensorValuesType *teco__SensorValues)
{
  struct READER_STRUCT reader;

  /* create response header */
  dpws_header_gen_response (soap, NULL, wsa_header_get_ReplyTo (soap),
                            "http://www.teco.edu/SensorValues/GetSensorValuesOut",
                            wsa_header_get_MessageId (soap),
                            sizeof (struct SOAP_ENV__Header));
                            
   if ( soap_response(soap, SOAP_OK)
       || soap_envelope_begin_out(soap)
       || soap_putheader(soap)
       || soap_body_begin_out(soap) )
   {
      soap_closesock(soap);
      soap_print_fault (soap, stderr);
   }
   else
   {
      read_init(&reader, get_global_packet_buf());
      gsoap_automata_init(soap, (void*)&reader);
      gsoap_automata_run();
   }
    
   if ( soap_body_end_out(soap)
       || soap_envelope_end_out(soap)
       || soap_end_send(soap) )
   {
      soap_closesock(soap);
      soap_print_fault (soap, stderr);
   }
                            
   return SOAP_OK;
}


