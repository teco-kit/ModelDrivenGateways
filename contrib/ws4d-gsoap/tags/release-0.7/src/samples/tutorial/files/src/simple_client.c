#include "acs1.nsmap"
#include "dpws_client.h"

#include "ws4d_eprllist.h"

struct soap client;
struct dpws_s dpws;

int
main (int argc, char **argv)
{
  ws4d_alloc_list alist;

  struct acs1__ACStateType ACState;

  struct ws4d_epr device, *service = NULL;
  struct ws4d_abs_eprlist services;
  ws4d_qnamelist service_type_list;

  char *deviceaddr = NULL, *interface = NULL, *XAddrs = NULL;

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
              printf ("\nsimple_client: Set interface to \"%s\"\n", interface);
#endif
              break;
            case 'd': /* set device address with option -d */
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
#ifdef DEBUG
              printf ("\nsimple_client: Set device address to \"%s\"\n", deviceaddr);
#endif
              break;
            default:
              fprintf (stderr, "\nsimple_client: Bad option %s\n", argv[1]);
              printf ("\n%s -i [interface address] -d [device address]\n", argv[0]);
              exit(1);
            }
        }
      --argc;
      ++argv;
    }
    
  if (interface == NULL)
    {
      fprintf (stderr, "\nsimple_client: No interface address was specified!\n");
      exit (1);
    }
    
  if (deviceaddr == NULL)
    {
      fprintf (stderr, "\nsimple_client: No device address was specified!\n");
      exit (1);
    }

  WS4D_ALLOCLIST_INIT (&alist);

  /* initialize soap handle */
  soap_init (&client);
#ifdef DEBUG
  soap_omode (&client, SOAP_XML_INDENT);
#endif

  /* initialize WS4D-gSOAP */
  if (dpws_init (&dpws, interface) != SOAP_OK)
    {
      fprintf (stderr, "\nsimple_client: could not initialize dpws handle\n");
      dpws_done (&dpws);
      exit (1);
    }

  /* prepare device epr to resolve */
  ws4d_epr_init (&device);
  ws4d_epr_set_Addrs (&device, deviceaddr);

  /* resolve address */
  XAddrs = (char *) dpws_resolve_addr(&dpws, &device, NULL, 10000);
  if (XAddrs != NULL)
    {
#ifdef DEBUG
      printf("\nsimple_client: device %s found at addr %s\n",
             ws4d_epr_get_Addrs(&device), XAddrs);
#endif
    }
  else
    {
      fprintf(stderr, "\nsimple_client: device %s cannot be found\n",
              ws4d_epr_get_Addrs(&device));
      exit(1);
    }

  /* prepare service type list */
  ws4d_qnamelist_init (&service_type_list);
  ws4d_qnamelist_addstring
    ("\"http://www.ws4d.org/axis2/tutorial/AirConditioner\":AirConditionerInterface",
     &service_type_list, &alist);

  /* look up service with matching service types on device */
  ws4d_eprlist_init(&services, ws4d_eprllist_init, NULL);
  if (dpws_find_services(&dpws, &device, &service_type_list, 10000, &services)
      == SOAP_OK)
    {
      /* use first service */
      service = ws4d_eprlist_get_first(&services);
#ifdef DEBUG
      printf("\nsimple_client: device offers AirConditionerService at %s\n",
             ws4d_epr_get_Addrs(service));
#endif
    }
  else
    {
      fprintf(stderr,
              "\nsimple_client: AirConditionerService not found on %s\n",
              ws4d_epr_get_Addrs(&device));
      exit(1);
    }

  /* prepare soap handel to use service */
  soap_set_namespaces(&client, acs1_namespaces);
  dpws_header_gen_request(&client, NULL, ws4d_epr_get_Addrs(service),
      "http://www.ws4d.org/axis2/tutorial/AirConditioner/GetStatusIn", NULL,
      NULL, sizeof(struct SOAP_ENV__Header));

  /* call GetStatus operation */
  if (soap_call___acs1__GetStatus(&client, ws4d_epr_get_Addrs (service),
                                  NULL, NULL, &ACState) == SOAP_OK)
    {
#ifdef DEBUG
      printf("\nsimple_client: got status from %s\n",
             ws4d_epr_get_Addrs(service));
#endif

      printf ("\nCurrentTemp: %d\nTargetTemp: %d\n", ACState.CurrentTemp, ACState.TargetTemp);
    }
  else
    {
      fprintf(stderr, "\nsimple_client: error calling GetStatus on %s\n",
              ws4d_epr_get_Addrs(service));
    }

  /* clean up */
  soap_end(&client);
  soap_done(&client);
  dpws_done(&dpws);

  ws4d_eprlist_done(&services);
  ws4d_epr_reset(&device);

  ws4d_alloclist_done(&alist);

  exit (0);
}

