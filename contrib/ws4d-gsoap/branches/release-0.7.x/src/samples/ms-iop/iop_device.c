/* <one line to give the program's name and a brief idea of what it does.>
 * Copyright (C) 2007  University of Rostock
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 */

#include "dpws.nsmap"
#include "dpws_device.h"
#include "msiop_wsdl.h"
#include "msiop_metadata.h"
#include <signal.h>
#include <time.h>

struct soap hosting, eventsource, simpleservice1, simpleservice2,
  attachmentservice;
struct dpws_s device;

void
service_exit ()
{
  printf ("\niop_device: shutting down...\n");
  dpws_deactivate_hosting_service (&device);
  soap_done (&hosting);
  soap_done (&eventsource);
  soap_done (&simpleservice1);
  /*soap_done(&simpleservice2); */
  soap_done (&attachmentservice);
  dpws_done (&device);
  exit (0);
}

int sis_serve_request (struct soap *soap);
int ats_serve_request (struct soap *soap);

int
main (int argc, char **argv)
{
#ifndef WIN32
  struct sigaction sa;
#endif
  char *host = NULL;
  char *uuid = NULL;

  /* parsing command line options */
  while (argc > 1)
    {
      if (argv[1][0] == '-')
        {
          char *option = &argv[1][1];
          switch (option[0])
            {
            case 'h':
              if (strlen (option) > 2)
                {
                  ++option;
                  host = option;
                }
              else
                {
                  --argc;
                  ++argv;
                  host = argv[1];
                }
              printf ("\niop_device: Set host to \"%s\"\n", host);
              break;
            case 'u':
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
              printf ("\niop_device: Set uuid to \"%s\"\n", uuid);
              break;
            default:
              fprintf (stderr, "\niop_device: Bad option %s\n", argv[1]);
            }
        }
      --argc;
      ++argv;
    }

  if (host == NULL)
    {
      printf ("\niop_device: No host was specified!\n");
      exit (0);
    }

  /* initialize hosting handle */
  soap_init (&hosting);
  soap_omode (&hosting, SOAP_XML_INDENT);
  soap_set_namespaces (&hosting, dpws_namespaces);
  wsa_register_handle (&hosting);

  /* initialize eventsource handle */
  soap_init (&eventsource);
  soap_omode (&eventsource, SOAP_XML_INDENT);

  /* initialize simpleservice1 handle */
  soap_init (&simpleservice1);
  soap_omode (&simpleservice1, SOAP_XML_INDENT);

  /* initialize simpleservice2 handle */
  /* soap_init (&simpleservice2); */
  /* soap_omode (&simpleservice2, SOAP_XML_INDENT); */

  /* initialize attachmentservice handle */
  soap_init1 (&attachmentservice, SOAP_ENC_MTOM);
  //soap_omode (&attachmentservice, SOAP_XML_INDENT);

  /* initialize device and services */
  if (dpws_init (&device, host)
      || msiop_setup_HostingService (&device, &hosting, uuid, 100)
      || setup_SimpleService (&device, &simpleservice1,
                              "SimpleService.wsdl", 100)
      || setup_AttachmentService (&device, &attachmentservice,
                                  "AttachmentService.wsdl", 100)
      || setup_EventingService (&device, &eventsource,
                                "EventingService.wsdl", 100))
    {
      printf ("\niop_device: Can't init device and services\n");
      dpws_done (&device);
      exit (0);
    }

  /* Set Metadata */
  msiop_set_Metadata (&device);
  msiop_set_wsdl (&device);

  /* Update Metadata */
  if (dpws_update_Metadata (&device))
    {
      printf ("\niop_device: Can't init metadata\n");
      dpws_done (&device);
      exit (0);
    }

  /* activate eventing. */
  if (dpws_activate_eventsource (&device, &eventsource))
    {
      printf ("\nAirconditioner: Can't activate eventing\n");
      dpws_done (&device);
      exit (0);
    }

  /* install signal handler for SIGINT or Ctrl-C */
#ifdef WIN32
  signal (SIGINT, service_exit);
  
#else   /* 
 */
  memset (&sa, 0, sizeof (sa));
  
sa.sa_handler = service_exit;
  
sigaction (SIGINT, &sa, NULL);
  
#endif
    printf ("\niop_device: ready to serve... (Ctrl-C for shut down)\n");

  dpws_activate_hosting_service (&device);

  for (;;)
    {
      struct soap *handle = NULL;
#ifdef SOAP_HANDLE_SET
      struct soap *soap_set[] =
        SOAP_HANDLE_SET (&hosting, &simpleservice1, &attachmentservice,
                         &eventsource);
#else
	  struct soap *soap_set[] =
	  {&hosting, &simpleservice1, &attachmentservice, &eventsource, NULL, NULL, NULL};
#endif

#ifdef SOAP_SERVE_SET
      int (*serve_requests[]) (struct soap * soap) =
        SOAP_SERVE_SET (sis_serve_request, ats_serve_request);
#else
      int (*serve_requests[]) (struct soap * soap) =
	  {sis_serve_request, ats_serve_request, NULL, NULL, NULL};
#endif

      printf ("\niop_device: waiting for request\n");

      handle = dpws_maccept (&device, 10000, 4, soap_set);

      if (handle)
        {
          /* struct sockaddr_in addr; */
          /* socklen_t len; */
          /* char saddr[255]; */
          /* char daddr[255]; */

          /* len = sizeof (addr); */
          /* getsockname (handle->socket, (struct sockaddr *) &addr, &len); */

          /* strcpy (saddr, inet_ntoa (handle->peer.sin_addr)); */
          /* strcpy (daddr, inet_ntoa (addr.sin_addr)); */

          /* printf ("\niop_device: request from %s:%d to %s:%d\n", */
          /*         saddr, ntohs (handle->peer.sin_port), */
          /*         daddr, ntohs (addr.sin_port)); */

          if (dpws_mserve (handle, 2, serve_requests))
            {
              soap_print_fault (handle, stderr);
            }

          /* clean up soaps internaly allocated memory */
          soap_end (handle);
        }

      dpws_check_subscriptions (&device);

    }

  return -1;
}
