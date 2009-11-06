/* describe - retrieve description metadata from dpws devices
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
#include "dpws_client.h"
#include "ws4d_targetcache.h"
#include "ws4d_servicecache.h"

#include "ws4d_eprllist.h"

struct dpws_s dpws;
struct soap client;

int
main (int argc, char **argv)
{
  ws4d_alloc_list alist;
  char *host = NULL, *device = NULL, *service = NULL, *operation = NULL;
  int thisdevice = 0, thismodel = 0, relationship = 0, thisservice = 0, ret;

  struct ws4d_epr *targetdevice = NULL;
  struct ws4d_abs_eprlist services;

  struct _wsm__Metadata metadata;

  struct ws4d_thisDevice ThisDevice;
  struct ws4d_thisModel ThisModel;

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
              fflush (NULL);
              break;
            case 'd':
              if (strlen (option) > 2)
                {
                  ++option;
                  device = option;
                }
              else
                {
                  --argc;
                  ++argv;
                  device = argv[1];
                }
              fflush (NULL);
              break;
            case 's':
              if (strlen (option) > 2)
                {
                  ++option;
                  service = option;
                }
              else
                {
                  --argc;
                  ++argv;
                  service = argv[1];
                }
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
              while (operation[0] != '\0')
                {
                  switch (operation[0])
                    {
                    case 'd':  /* get ThisDevice */
                      thisdevice = 1;
                      break;
                    case 'm':  /* get ThisModel */
                      thismodel = 1;
                      break;
                    case 'r':  /* get Relationship */
                      relationship = 1;
                      break;
                    case 's':  /* get service description */
                      thisservice = 1;
                      break;
                    default:
                      fprintf (stderr, "Unknown operation %1s", operation);
                      exit (1);
                    }
                  operation++;
                }
              fflush (NULL);
              break;
            default:
              fprintf (stderr, "\nBad option %s", argv[1]);
              exit (1);
            }
        }
      --argc;
      ++argv;
    }

  if (host == NULL)
    {
      fprintf (stderr, "\nNo host was specified!");
      fflush (NULL);
      exit (1);
    }

  if (operation == NULL)
    {
      fprintf (stderr, "\nNo operation was specified!");
      fflush (NULL);
      exit (1);
    }

  /* initialize stack */
  if (dpws_init (&dpws, host) != SOAP_OK)
    {
      fprintf (stderr, "\nCould not initialize dpws handle");
      fflush (NULL);
      dpws_done (&dpws);
      exit (1);
    }

  targetdevice = ws4d_epr_alloc (1, &alist);
  ws4d_epr_set_Addrs (targetdevice, device);

  soap_init (&client);
  soap_omode (&client, SOAP_XML_INDENT);

  dpws_handle_init (&dpws, &client);

  ret = dpws_metadata_get (&dpws, &client, targetdevice, &metadata, 10000);
  if (ret != SOAP_OK)
    {
      fprintf (stderr, "\nCan't get metadata form %s\n", device);
      fflush (NULL);
      exit (1);
    }

  if (thisdevice == 1)
    {                           /* get ThisDevice */
      if (device == NULL)
        {
          fprintf (stderr, "\nNo device was specified!");
          fflush (NULL);
          exit (1);
        }

      if (!dpws_metadata_getThisDevice (&metadata, &alist, &ThisDevice))
        {
          printf ("Device description:\n");
          if ((ThisDevice.__sizeFriendlyName > 0)
              && ws4d_locstring_get (ThisDevice.FriendlyName,
                                           ThisDevice.__sizeFriendlyName,
                                           "de"))
            {
              printf (" + FriendlyName (de): %s\n",
                      ws4d_locstring_get (ThisDevice.FriendlyName,
                                                ThisDevice.
                                                __sizeFriendlyName, "de"));
            }

          if (ThisDevice.FirmwareVersion)
            {
              printf (" + FirmwareVersion: %s\n", ThisDevice.FirmwareVersion);
            }

          if (ThisDevice.SerialNumber)
            {
              printf (" + SerialNumber: %s\n", ThisDevice.SerialNumber);
            }
        }
    }

  if (thismodel == 1)           /* get ThisModel */
    {
      if (device == NULL)
        {
          fprintf (stderr, "\nNo device was specified!");
          fflush (NULL);
          exit (1);
        }

      if (!dpws_metadata_getThisModel (&metadata, &alist, &ThisModel))
        {
          printf ("Model description:\n");

          if ((ThisModel.__sizeManufacturer > 0)
              && ws4d_locstring_get (ThisModel.Manufacturer,
                                           ThisModel.__sizeManufacturer,
                                           "de"))
            {
              printf (" + Manufacturer (de): %s\n",
                      ws4d_locstring_get (ThisModel.Manufacturer,
                                                ThisModel.__sizeManufacturer,
                                                "de"));
            }

          if (ThisModel.ManufacturerUrl)
            {
              printf (" + ManufacturerUrl: %s\n", ThisModel.ManufacturerUrl);
            }

          if ((ThisModel.__sizeModelName > 0)
              && ws4d_locstring_get (ThisModel.ModelName,
                                           ThisModel.__sizeModelName, "de"))
            {
              printf (" + ModelName (de): %s\n",
                      ws4d_locstring_get (ThisModel.ModelName,
                                                ThisModel.__sizeModelName,
                                                "de"));
            }

          if (ThisModel.ModelNumber)
            {
              printf (" + ModelNumber: %s\n", ThisModel.ModelNumber);
            }

          if (ThisModel.ModelUrl)
            {
              printf (" + ModelUrl: %s\n", ThisModel.ModelUrl);
            }

        }
    }

  if (relationship == 1)        /* get Relationship */
    {
      if (device == NULL)
        {
          fprintf (stderr, "\nNo device was specified!");
          fflush (NULL);
          exit (1);
        }

      ws4d_eprlist_init (&services, ws4d_eprllist_init, NULL);
      if (!dpws_metadata_getservices
          (&dpws, targetdevice, &metadata, &services))
        {
          register struct ws4d_epr *elem, *iter;

          printf ("Services:\n");

          ws4d_eprlist_foreach (elem, iter, &services)
          {
            if (elem && ws4d_epr_isvalid (elem))
              {
                printf (" + %s\n", ws4d_serviceep_copyid (elem, &alist));
                printf ("    + Address: %s\n", ws4d_epr_get_Addrs (elem));
                printf ("    + Types: %s\n",
                        ws4d_serviceep_gettypestr (elem, &alist));
              }
          }
        }
      ws4d_eprlist_done (&services);
    }

  if (thisservice == 1)         /* get service description */
    {
      if (device == NULL)
        {
          fprintf (stderr, "\nNo device was specified!");
          fflush (NULL);
          exit (1);
        }
      if (service == NULL)
        {
          fprintf (stderr, "\nNo service was specified!");
          fflush (NULL);
          exit (1);
        }

      ws4d_eprlist_init (&services, ws4d_eprllist_init, NULL);
      if (!dpws_metadata_getservices
          (&dpws, targetdevice, &metadata, &services))
        {
          register struct ws4d_epr *elem, *iter;
          int found = 0;

          ws4d_eprlist_foreach (elem, iter, &services)
          {
            if (elem && ws4d_epr_isvalid (elem)
                && ws4d_epr_get_Addrs (elem)
                && !strcmp (ws4d_epr_get_Addrs (elem), service))
              {
                char *types = NULL, *id = NULL;

                found = 1;

                printf ("Service description of %s:\n",
                        ws4d_epr_get_Addrs (elem));

                types = ws4d_serviceep_gettypestr (elem, &alist);
                id = ws4d_serviceep_copyid (elem, &alist);

                if (types)
                  {
                    printf (" + Types: %s\n", types);
                  }

                if (id)
                  {
                    printf (" + Id: %s\n", id);
                  }
              }
          }

          if (!found)
            {
              fprintf (stderr, "Service %s not found on device %s !\n",
                       service, device);
              fflush (NULL);
              exit (1);
            }
        }
      ws4d_eprlist_done (&services);
    }

  fflush (NULL);
  exit (0);
}
