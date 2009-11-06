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

#include "sis.nsmap"
#include "dpws_client.h"

#include "ws4d_eprllist.h"

struct soap client;
struct dpws_s dpws;

int
main (int argc, char **argv)
{
  ws4d_alloc_list alist;

  struct ws4d_epr *device = NULL, *service = NULL;
  
  struct ws4d_abs_eprlist services;
  
  ws4d_qnamelist service_type_list;
  char *deviceaddr = NULL, *operation = NULL, *host = NULL, *XAddrs = NULL;

  WS4D_ALLOCLIST_INIT (&alist);

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
            case 'o':
              if (strlen (option) > 2)
                {
                  ++option;
                  operation = option;
                }
              else
                {
                  --argc;
                  ++argv;
                  operation = argv[1];
                }
              printf ("\nOperation is \"%s\"\n", operation);
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
      printf ("\nNo device id or address was specified!\n");
      fflush (NULL);
      exit (1);
    }

  if (host == NULL)
    {
      printf ("\nNo host was specified!\n");
      fflush (NULL);
      exit (1);
    }

  /* initialize client soap handle */
  soap_init (&client);
  soap_omode (&client, SOAP_XML_INDENT);

  /* initialize stack */
  if (dpws_init (&dpws, host) != SOAP_OK)
    {
      fprintf (stderr, "\nCould not initialize dpws handle\n");
      fflush (NULL);
      dpws_done (&dpws);
      exit (1);
    }

  /* allocate and prepare device to resolve */
  device = ws4d_epr_alloc (1, &alist);
  ws4d_epr_set_Addrs (device, deviceaddr);

  XAddrs = (char *) dpws_resolve_addr (&dpws, device, NULL, 10000);
  if (XAddrs != NULL)
    {
      fprintf (stderr, "\nDevice %s found at addr %s\n",
               ws4d_epr_get_Addrs (device), XAddrs);
    }
  else
    {
      fprintf (stderr, "\nDevice %s cannot be found\n",
               ws4d_epr_get_Addrs (device));
      fflush (NULL);
      exit (1);
    }
    
  ws4d_qnamelist_init (&service_type_list);
  ws4d_qnamelist_addstring
    ("\"http://schemas.example.org/SimpleService\":SimpleService",
     &service_type_list, &alist);

  ws4d_eprlist_init(&services, ws4d_eprllist_init, NULL);
  if (dpws_find_services (&dpws, device, &service_type_list,
                         10000, &services) == SOAP_OK)
    {
	  service = ws4d_eprlist_get_first(&services);
      printf ("\nDevice offers SimpleService at %s\n",
              ws4d_epr_get_Addrs (service));
    }
  else
    {
      printf ("\nSimpleService not found on %s\n",
              ws4d_epr_get_Addrs (device));
      exit (1);
    }

  if (!strcmp (operation, "oneway"))
    {
      struct sis1__OneWayType sis1__OneWay;
      struct __sis1__OneWay _param_1;

      soap_set_namespaces (&client, sis_namespaces);
      dpws_header_gen_oneway (&client, NULL,
                              ws4d_epr_get_Addrs (service),
                              "http://schemas.example.org/SimpleService/OneWay",
                              NULL, sizeof (struct SOAP_ENV__Header));

      sis1__OneWay.Param = 1;
      sis1__OneWay.__size = 0;
      sis1__OneWay.__any = NULL;
      sis1__OneWay.__anyAttribute = NULL;

      if (soap_send___sis1__OneWay
          (&client, ws4d_epr_get_Addrs (service), NULL,
           &sis1__OneWay) == SOAP_OK)
        {
          printf ("\nSuccessfully called %s-operation on %s with param %d\n",
                  operation, ws4d_epr_get_Addrs (service),
                  sis1__OneWay.Param);
        }
      else
        {
          printf ("\nError: calling %s-operation on %s\n", operation,
                  ws4d_epr_get_Addrs (service));
        }

      if (soap_recv___sis1__OneWay (&client, &_param_1) != 202)
        {
          printf ("\nError: got not response from %s\n",
                  ws4d_epr_get_Addrs (service));
        }

    }
  else if (!strcmp (operation, "twoway"))
    {
      struct sis1__TwoWayType sis1__TwoWayRequest;
      struct sis1__TwoWayResponseType sis1__TwoWayResponse;

      sis1__TwoWayRequest.X = 1;
      sis1__TwoWayRequest.Y = 2;
      sis1__TwoWayRequest.__size = 0;
      sis1__TwoWayRequest.__any = NULL;
      sis1__TwoWayRequest.__anyAttribute = NULL;

      soap_set_namespaces (&client, sis_namespaces);
      dpws_header_gen_request (&client, NULL,
                               ws4d_epr_get_Addrs (service),
                               "http://schemas.example.org/SimpleService/TwoWayRequest",
                               NULL, NULL, sizeof (struct SOAP_ENV__Header));

      if (soap_call___sis1__TwoWay
          (&client, ws4d_epr_get_Addrs (service), NULL,
           &sis1__TwoWayRequest, &sis1__TwoWayResponse) == SOAP_OK)
        {
          printf
            ("\nSuccessfully called %s-operation on %s with param X=%d, Y=%d,",
             operation, ws4d_epr_get_Addrs (service),
             sis1__TwoWayRequest.X, sis1__TwoWayRequest.Y);
          printf (" Sum is %d\n", sis1__TwoWayResponse.Sum);
        }
      else
        {
          printf ("\nError: calling %s-operation on %s\n", operation,
                  ws4d_epr_get_Addrs (service));
        }
    }
  else
    {
      printf ("\nError: operation %s not supported\n", operation);
    }

  soap_end (&client);
  soap_done (&client);
  dpws_done (&dpws);
  
  ws4d_eprlist_done(&services);
  ws4d_epr_free(1, device);
  
  ws4d_alloclist_done(&alist);

  exit (0);
}
