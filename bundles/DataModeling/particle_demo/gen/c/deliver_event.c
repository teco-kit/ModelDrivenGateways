
/* Generated file */   
#include "teco_inv.nsmap"
#include "dpws_device.h"
#include "../../bitsio/read_bits_buf.h"
#include "gsoap_encoder_automata_inv.h"

struct dpws_s *_device;

void
deliver_event (void *device, char * buf)
{
  struct soap soap;
  struct ws4d_subscription *subs, *next;
  struct READER_STRUCT reader;
#define soap_action_uri "http://www.teco.edu/SensorValues/SensorValuesEventOut"

  _device = (struct dpws_s *)device; 

  soap_init (&soap);
  soap_set_namespaces (&soap, teco_inv_namespaces);

  dpws_for_each_subs (subs, next, _device,
                      soap_action_uri)
  {
    char *deliverto = dpws_subsm_get_deliveryPush_address (_device, subs);

    if (!deliverto)
      continue;

    dpws_header_gen_oneway (&soap, NULL, deliverto,
                            soap_action_uri,
                            NULL, sizeof (struct SOAP_ENV__Header));

    printf ("Sending Event to %s\n", deliverto);

    if (   soap_connect(&soap, deliverto, soap_action_uri)
        || soap_envelope_begin_out(&soap)
        || soap_putheader(&soap)
        || soap_body_begin_out(&soap) )
    {
       soap_closesock(&soap);
       soap_print_fault (&soap, stderr);
    }
    read_init(&reader, buf);
    gsoap_automata_init_inv(&soap, &reader);
    gsoap_automata_run_inv();
    
    if (   soap_body_end_out(&soap)
        || soap_envelope_end_out(&soap)
        || soap_end_send(&soap) )
    {
       soap_closesock(&soap);
       soap_print_fault (&soap, stderr);
    }
    soap_end (&soap);
  }

  soap_done (&soap);
}
