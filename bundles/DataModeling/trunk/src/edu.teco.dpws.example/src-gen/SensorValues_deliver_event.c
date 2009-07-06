/* Generated file */
//#include "SensorValues.nsmap"
#include <dpws_device.h>
#include <bitsio/read_bits_buf.h>
#include <soap.h>
#include "../src/sendrcv.h"

struct dpws_s *_device;

#include "SensorValues_bin2sax.h"

void SensorValues_SensorValuesEvent_event(void *_device, char * buf) {
	struct soap soap;
	struct ws4d_subscription *subs, *next;

	struct dpws_s *device = (struct dpws_s *) _device;

	soap_init(&soap);
	soap_set_namespaces(&soap, device->hosting_handle->namespaces); //TODO:Check if correct
	//soap_set_namespaces (&soap, namespaces);
	{
		char soap_action_uri[] =
				"http://www.teco.edu/SensorValues/SensorValuesEventOut";
dpws_for_each_subs	(subs, next, _device,
			soap_action_uri)
	{
		char *deliverto = dpws_subsm_get_deliveryPush_address (device, subs);

		if (!deliverto)
		continue;

		dpws_header_gen_oneway (&soap, NULL, deliverto,
				soap_action_uri,
				NULL, sizeof (struct SOAP_ENV__Header));

		printf ("Sending Event to %s\n", deliverto);

		if ( soap_connect(&soap, deliverto, soap_action_uri)
				|| soap_envelope_begin_out(&soap)
				|| soap_putheader(&soap)
				|| soap_body_begin_out(&soap) )
		{
			soap_closesock(&soap);
			soap_print_fault (&soap, stderr);
		}

		{
			struct READER_STRUCT* reader=read_bits_bufreader_stack_new((u_char *) buf);

			{
				SensorValues_bin2sax_run(reader,&soap);
			}

		}
		if ( soap_body_end_out(&soap)
				|| soap_envelope_end_out(&soap)
				|| soap_end_send(&soap) )
		{
			soap_closesock(&soap);
			soap_print_fault (&soap, stderr);
		}
		soap_end (&soap);
	}
}
soap_done (&soap);
return;
}
