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

#include "acs_inv1.nsmap"
#include "dpws_client.h"
#include <signal.h>

#include "ws4d_eprllist.h"

struct soap client, handler;
struct dpws_s dpws;
struct ws4d_epr *service = NULL;
char *id = NULL;

ws4d_alloc_list alist;

#define TemperatureEventListener_URI_LEN 255

void
client_exit ()
{
  printf ("\nTemperatureEventListener: shutting down...\n");

  if (dpws_subs_unsubscribe
      (&client, service, id) != SOAP_OK)
    {
      soap_print_fault (&client, stderr);
      exit (1);
    }

  soap_end (&client);
  soap_done (&client);
  soap_end (&handler);
  soap_done (&handler);
  dpws_done (&dpws);

  ws4d_alloclist_done (&alist);

  exit (0);
}


int
main (int argc, char **argv)
{
#ifndef WIN32
  struct sigaction sa;
#endif

  struct ws4d_epr device;
  struct ws4d_abs_eprlist services;
  ws4d_qnamelist service_type_list;

  char *deviceaddr = NULL, *host = NULL, *XAddrs = NULL;

  char handler_uri[DPWS_URI_MAX_LEN + 1] = "http://host:0/";

  struct ws4d_delivery_type *delivery = NULL;
  struct ws4d_filter_type *filter = NULL;

  struct ws4d_dur *duration = NULL;

  WS4D_ALLOCLIST_INIT (&alist);

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
              break;
            case 'd':
              if (strlen (option) > 2)
                {
                  ++option;
                  deviceaddr = option;
                }
              else
                {
                  --argc;
                  ++argv;
                  deviceaddr = argv[1];
                }
              printf ("\nDevice device id or address is \"%s\"\n",
                      deviceaddr);
              fflush (NULL);
              break;
            default:
              fprintf (stderr, "\nBad option %s", argv[1]);
            }
        }
      --argc;
      ++argv;
    }

  if (deviceaddr == NULL)
    {
      printf
        ("\nTemperatureEventListener: No device id or address was specified!\n");
      fflush (NULL);
      exit (1);
    }

  if (host == NULL)
    {
      printf ("\nTemperatureEventListener: No host was specified!\n");
      fflush (NULL);
      exit (1);
    }


  /* initialize client soap handle */
  soap_init (&client);
  soap_omode (&client, SOAP_XML_INDENT);
  soap_set_namespaces (&client, acs_inv1_namespaces);

  soap_init (&handler);
  soap_omode (&handler, SOAP_XML_INDENT);
  soap_set_namespaces (&handler, acs_inv1_namespaces);


  /* initialize stack */
  if (dpws_init (&dpws, host) != SOAP_OK)
    {
      fprintf (stderr,
               "\nTemperatureEventListener: Could not initialize dpws handle\n");
      fflush (NULL);
      dpws_done (&dpws);
      exit (1);
    }


  /* bind listener handle to an address */
  dpws_handle_init (&dpws, &handler);
  if (dpws_handle_bind (&dpws, &handler, handler_uri, DPWS_URI_MAX_LEN, 100)
      == SOAP_INVALID_SOCKET)
    {
      fprintf (stderr,
               "\nTemperatureEventListener: error calling dpws_handle_bind");
      fflush (NULL);
      exit (1);
    }


  /* allocate and prepare device to resolve */
  ws4d_epr_init(&device);
  ws4d_epr_set_Addrs (&device, deviceaddr);


  /* resolve address */
  XAddrs = (char *) dpws_resolve_addr (&dpws, &device, NULL, 10000);
  if (XAddrs != NULL)
    {
      fprintf (stderr,
               "\nTemperatureEventListener: Device %s found at addr %s\n",
               ws4d_epr_get_Addrs (&device), XAddrs);
    }
  else
    {
      fprintf (stderr,
               "\nTemperatureEventListener: Device %s cannot be found\n",
               ws4d_epr_get_Addrs (&device));
      fflush (NULL);
      exit (1);
    }


  /* prepare service type list */
  ws4d_qnamelist_init (&service_type_list);
  ws4d_qnamelist_addstring
    ("\"http://www.ws4d.org/axis2/tutorial/AirConditioner\":AirConditionerInterface",
     &service_type_list, &alist);


  /* look up service with matching service types on device */
  ws4d_eprlist_init (&services, ws4d_eprllist_init, NULL);
  if (dpws_find_services (&dpws, &device, &service_type_list,
                         10000, &services) == SOAP_OK)
    {
      service = ws4d_eprlist_get_first (&services);
      printf
        ("\nTemperatureEventListener: Device offers AirConditionerService at %s\n",
         ws4d_epr_get_Addrs (service));
    }
  else
    {
      printf
        ("\nTemperatureEventListener: AirConditionerService not found on %s\n",
         ws4d_epr_get_Addrs (&device));
      exit (1);
    }


  /* prepare duration */
  duration = soap_malloc (&client, sizeof (struct ws4d_dur));
  if (!duration)
    {
      exit (1);
    }
  duration->hours = 1;


  /* prepare filter and delivery mode */
  delivery =
    dpws_gen_delivery_push (&client, dpws_handle_get_paddr (&handler));

  /* TODO: test dpws_subscribe with wrong parameters, there is a bug on the device side */

  filter =
    dpws_gen_filter_action (&client,
                            "http://www.ws4d.org/axis2/tutorial/AirConditioner/TemperatureEventOut");


  /* Subscribing to Airconditioner Service */
  printf
    ("\nTemperatureEventListener: Subscribe to Airconditioner Service ... ");
  /* TODO: fix wrong type of 4. parameter */
  id =
    dpws_subscribe (&client, service, NULL, &duration, delivery, filter);
  if (!id)
    {
      soap_print_fault (&client, stderr);
      exit (1);
    }

  printf ("OK (%s %s)\n", ws4d_subsproxy_getsubsman(service, id), id);

  /* install signal handler for SIGINT or Ctrl-C */
#ifdef WIN32
  signal (SIGINT, client_exit);
#else
  memset (&sa, 0, sizeof (sa));
  sa.sa_handler = client_exit;
  sigaction (SIGINT, &sa, NULL);
#endif

  printf
    ("\nTemperatureEventListener: ready to receive events ... (Ctrl-C for shut down)\n");

  for (;;)
    {

      printf ("\nTemperatureEventListener: waiting for events\n");

      if (soap_accept (&handler))
        {

          printf ("\nTemperatureEventListener: processing event from %s:%d\n",
                  inet_ntoa (handler.peer.sin_addr),
                  ntohs (handler.peer.sin_port));

          if (acs_inv1_serve (&handler))
            {
              soap_print_fault (&handler, stderr);
            }

          /* clean up soaps internaly allocated memory */
          soap_end (&handler);
        }
    }

  return 0;
}


int
__acsinv1__TemperatureEvent (struct soap *soap,
                             struct acsinv1__ACStateType *acsinv1__ACState)
{
  printf ("\nGot TemperatureEvent: ");
  if (acsinv1__ACState)
    {
      printf ("CurrentTemp: %d, TargetTemp: %d\n",
              acsinv1__ACState->CurrentTemp, acsinv1__ACState->TargetTemp);
    }
  else
    {
      printf ("no ACState transmitted!\n");
    }

  return SOAP_OK;
}
