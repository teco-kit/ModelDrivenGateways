#include "acs1.nsmap"
#include "dpws_device.h"
#include "acs_metadata.h"
#include "acs_wsdl.h"
#include "config.h"
#include <signal.h>

struct soap service;
struct dpws_s device;

void
service_exit ()
{
#ifdef DEBUG
  printf ("\nAirconditioner: shutting down...\n");
#endif

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
              printf ("\nAirconditioner: Set interface to \"%s\"\n", interface);
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
              printf ("\nAirconditioner: Set uuid to \"%s\"\n", uuid);
#endif
              break;
            default:
              fprintf (stderr, "\nAirconditioner: Bad option %s\n", argv[1]);
              printf ("\n%s -i [interface address] -u urn:uuid[uuid]\n", argv[0]);
	      exit(1);
            }
        }
      --argc;
      ++argv;
    }

  if (interface == NULL)
    {
      fprintf (stderr, "\nAirconditioner: No interface addrss was specified!\n");
      exit (1);
    }

  /* initialize soap handle */
  soap_init (&service);
#ifdef DEBUG
  soap_omode (&service, SOAP_XML_INDENT);
#endif
  soap_set_namespaces (&service, acs1_namespaces);

  /* initialize device and services */
  if (dpws_init (&device, interface)
      || acs_setup_HostingService (&device, &service, uuid, 100)
      || acs_setup_AirConditioner (&device, &service, AIRCONDITIONER_WSDL,
                                   100))
    {
      fprintf (stderr, "\nAirconditioner: Can't init device and services\n");
      dpws_done (&device);
      exit (1);
    }

  /* Set Metadata */
  acs_set_Metadata (&device);
  acs_set_wsdl (&device);

  /* Update Metadata */
  if (dpws_update_Metadata (&device))
    {
      fprintf (stderr, "\nAirconditioner: Can't init metadata\n");
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
      fprintf (stderr, "\nAirconditioner: Can't activate device\n");
      dpws_done (&device);
      exit (1);
    }

#ifdef DEBUG
  printf ("\nAirconditioner: ready to serve... (Ctrl-C for shut down)\n");
#endif

  for (;;)
    {
      struct soap *handle = NULL, *soap_set[] = SOAP_HANDLE_SET (&service);
      int (*serve_requests[]) (struct soap * soap) =
        SOAP_SERVE_SET (acs1_serve_request);

#ifdef DEBUG
      printf ("\nAirconditioner: waiting for request\n");
#endif

      /* waiting for new messages */
      handle = dpws_maccept (&device, 100000, 1, soap_set);

      if (handle)
        {

#ifdef DEBUG
          printf ("\nAirconditioner: processing request from %s:%d\n",
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
    }

  return -1;
}

int temp = 23;

int __acs1__GetStatus(struct soap *soap,
                      struct _acs1__EmptyMessage *acs1__GetStatus,
                      struct acs1__ACStateType *acs1__ACState)
{
  /* fill response message */
  acs1__ACState->CurrentTemp = temp;
  acs1__ACState->TargetTemp = temp;

  /* create response header */
  return dpws_header_gen_response (soap, NULL, wsa_header_get_ReplyTo (soap),
                                   "http://www.ws4d.org/axis2/tutorial/AirConditioner/GetStatusOut",
                                   wsa_header_get_MessageId (soap),
                                   sizeof (struct SOAP_ENV__Header));
}


int
__acs1__SetTargetTemperature (struct soap *soap,
                              int acs1__TargetTemperature,
                              struct acs1__ACStateType *acs1__ACState)
{
  /* process request message */
  temp = acs1__TargetTemperature;

  /* fill response message */
  acs1__ACState->CurrentTemp = temp;
  acs1__ACState->TargetTemp = temp;

  /* create response header */
  return dpws_header_gen_response (soap, NULL, wsa_header_get_ReplyTo (soap),
                                   "http://www.ws4d.org/axis2/tutorial/AirConditioner/SetTargetTemperatureOut",
                                   wsa_header_get_MessageId (soap),
                                   sizeof (struct SOAP_ENV__Header));
}

