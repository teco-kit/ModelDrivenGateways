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

#include "acs1.nsmap"
#include "dpws_client.h"

#include "ws4d_eprllist.h"

struct soap client;
struct dpws_s dpws;

#define ACS_NO_TEMPERATURE -1
#define ACS_MAX_DEVICES 20

int
main (int argc, char **argv)
{
  ws4d_alloc_list alist;

  struct acs1__ACStateType ACState;

  struct ws4d_epr *device = NULL, *iter = NULL;
  struct ws4d_abs_eprlist devices, services;
  int ret = 0;

  char *deviceaddr = NULL, *host = NULL, *XAddrs = NULL;

  int TargetTemperature = ACS_NO_TEMPERATURE;

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
            case 't':
              if (strlen (option) > 2)
                {
                  ++option;
                  TargetTemperature = atoi (option);
                }
              else
                {
                  --argc;
                  ++argv;
                  TargetTemperature = atoi (argv[1]);
                }
              break;
            default:
              fprintf (stderr, "\nBad option %s", argv[1]);
            }
        }
      --argc;
      ++argv;
    }

  if (host == NULL)
    {
      printf ("\nNo host was specified!\n");
      fflush (NULL);
      exit (1);
    }

  if (TargetTemperature != ACS_NO_TEMPERATURE)
    {
      printf ("\nGoing to set TargetTemperature to %d\n", TargetTemperature);
      fflush (NULL);
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

  ws4d_eprlist_init (&devices, ws4d_eprllist_init, NULL);

  /* allocate and prepare device to eprs */
  if (deviceaddr == NULL)
    {
      /* no device was specified, so we will probe for devices */
      ws4d_qnamelist type_list;

      /* init type list */
      ws4d_qnamelist_init (&type_list);
      ws4d_qnamelist_addstring
        ("\"http://www.ws4d.org/axis2/tutorial/AirConditioner\":AirConditioner",
         &type_list, &alist);

      printf ("\nProbing for acs devices for 10 seconds ... ");
      fflush (NULL);
      ret = dpws_probe (&dpws, &type_list, NULL, 10000, 100, NULL,
                        NULL, &devices);
      if (ret != SOAP_OK)
        {
          if (ret == SOAP_EOF)
            {
              fprintf (stderr, "No devices found!\n");
            }
          else
            {
              fprintf (stderr, "Could not probe for devices!\n");
            }
          fflush (NULL);
          dpws_done (&dpws);
          exit (1);
        }
      printf ("done\n");
      fflush (NULL);
    }
  else
    {
      /* initialize epr with given device address */
      device = ws4d_eprlist_alloc (&devices);
      ws4d_epr_set_Addrs (device, deviceaddr);

      ws4d_eprlist_add (&devices, device);
    }

  ws4d_eprlist_foreach (device, iter, &devices)
  {
    if (ws4d_epr_isvalid (device))
      {
        struct ws4d_epr *service = NULL;
        ws4d_qnamelist service_type_list;

        /* resolve address */
        XAddrs = (char *) dpws_resolve_addr (&dpws, device, NULL, 10000);
        if (XAddrs != NULL)
          {
            fprintf (stderr, "\n\nDevice %s found at addr %s\n",
                     ws4d_epr_get_Addrs (device), XAddrs);
          }
        else
          {
            fprintf (stderr, "\nDevice %s cannot be found\n",
                     ws4d_epr_get_Addrs (device));
            fflush (NULL);
            continue;
          }

        /* prepare service type list */
        ws4d_qnamelist_init (&service_type_list);
        ws4d_qnamelist_addstring
          ("\"http://www.ws4d.org/axis2/tutorial/AirConditioner\":AirConditionerInterface",
           &service_type_list, &alist);

        /* look up service with matching service types on device */
        ws4d_eprlist_init (&services, ws4d_eprllist_init, NULL);
        if (dpws_find_services (&dpws, device, &service_type_list,
                               10000, &services) == SOAP_OK)
          {
            service = ws4d_eprlist_get_first (&services);
            printf ("\nDevice offers AirConditionerService at %s\n",
                    ws4d_epr_get_Addrs (service));
          }
        else
          {
            printf ("\nAirConditionerService not found on %s\n",
                    ws4d_epr_get_Addrs (device));
            continue;
          }

        /* If temperature was not specified at command line call GetStatus */
        if (TargetTemperature == ACS_NO_TEMPERATURE)
          {

            /* prepare soap handel to use service */
            soap_set_namespaces (&client, acs1_namespaces);
            dpws_header_gen_request (&client, NULL,
                                     ws4d_epr_get_Addrs (service),
                                     "http://www.ws4d.org/axis2/tutorial/AirConditioner/GetStatusIn",
                                     NULL, NULL,
                                     sizeof (struct SOAP_ENV__Header));

            /* call GetStatus operation */
            if (soap_call___acs1__GetStatus (&client,
                                             ws4d_epr_get_Addrs
                                             (service), NULL, NULL,
                                             &ACState) == SOAP_OK)
              {
                printf ("\nSuccessfully got status from %s\n",
                        ws4d_epr_get_Addrs (service));

                printf ("\t+ CurrentTemp: %d, TargetTemp: %d\n",
                        ACState.CurrentTemp, ACState.TargetTemp);
              }
            else
              {
                printf ("\nError: calling GetStatus on %s\n",
                        ws4d_epr_get_Addrs (service));
              }

          }
        else
          /* If temperature was specified at command line call SetTargetTemperature */
          {

            /* prepare soap handel to use service */
            soap_set_namespaces (&client, acs1_namespaces);
            dpws_header_gen_request (&client, NULL,
                                     ws4d_epr_get_Addrs (service),
                                     "http://www.ws4d.org/axis2/tutorial/AirConditioner/SetTargetTemperatureIn",
                                     NULL, NULL,
                                     sizeof (struct SOAP_ENV__Header));


            /* call GetStatus operation */
            if (soap_call___acs1__SetTargetTemperature (&client,
                                                        ws4d_epr_get_Addrs
                                                        (service), NULL,
                                                        TargetTemperature,
                                                        &ACState) == SOAP_OK)
              {
                printf ("\nSuccessfully set TargetTemperature on %s\n",
                        ws4d_epr_get_Addrs (service));

                printf ("\t+ CurrentTemp: %d, TargetTemp: %d\n",
                        ACState.CurrentTemp, ACState.TargetTemp);
              }
            else
              {
                printf ("\nError: calling SetTargetTemperature on %s\n",
                        ws4d_epr_get_Addrs (service));
              }
          }

        /* clean up */
        ws4d_eprlist_done (&services);
        soap_end (&client);

      }
  }

  /* clean up */
  ws4d_eprlist_done (&devices);
  soap_done (&client);
  dpws_done (&dpws);
  ws4d_alloclist_done (&alist);

  exit (0);
}
