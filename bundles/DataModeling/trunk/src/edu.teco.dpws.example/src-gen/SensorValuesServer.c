
enum operations {

	OP_GetSensorValues, OP_SensorValuesEvent, OP_ConfigureSensors

};

#include <soap.h>
#include <dpws_device.h>
#include <stdsoap2.h>

#include "../src/sendrcv.h"

#include "SensorValues_bin2sax.h"

static int soap_serve_GetSensorValues(struct soap *soap) //TODO: pass device context
{
	int op_id = OP_GetSensorValues;
	int service_id = 0; //TODO
	struct dpws_s *device = NULL;

	send_buf(device, service_id, op_id, soap, NULL, 0);

	/* create response header */
	{
		char* To = wsa_header_get_ReplyTo(soap);
		if (!To)
			To
					= "http://schemas.xmlsoap.org/ws/2004/08/addressing/role/anonymous";
		dpws_header_gen_response(soap, NULL, To,
				"http://www.teco.edu/SensorValues/GetSensorValuesOut",
				wsa_header_get_MessageId(soap), sizeof(struct SOAP_ENV__Header));
	}

	if (soap_response(soap, SOAP_OK) || soap_envelope_begin_out(soap)
			|| soap_putheader(soap) || soap_body_begin_out(soap)) {
		return soap->error;
	}

	{
		struct READER_STRUCT* reader = read_bits_bufreader_stack_new(
				(u_char *) rcv_buf(device, service_id, op_id, soap));

		{
			SensorValues_bin2sax_run(reader, soap);
		}

	}

	if (soap_body_end_out(soap) || soap_envelope_end_out(soap)
			|| soap_end_send(soap)) {
		return soap->error;
	}

	return soap_closesock(soap);
}

#include "SensorConfiguration_sax2bin.h"

static int soap_serve_ConfigureSensors(struct soap *soap) //TODO: pass device context
{
	int op_id = OP_ConfigureSensors;
	int service_id = 0; //TODO
	struct dpws_s *device = NULL;

	{
		u_char sendbuf[256];
		struct WRITER_STRUCT* writer = write_bits_bufwriter_stack_new(sendbuf);

		{
			if (SensorConfiguration_sax2bin_run(soap, writer) < 0)
				return soap->error;
		}

		send_buf(device, service_id, op_id, soap, sendbuf, write_finish(writer));
	}

	/* create response header */
	{
		char* To = wsa_header_get_ReplyTo(soap);
		if (!To)
			To
					= "http://schemas.xmlsoap.org/ws/2004/08/addressing/role/anonymous";
		dpws_header_gen_response(soap, NULL, To,
				"http://www.teco.edu/SensorValues/ConfigureSensorsOut",
				wsa_header_get_MessageId(soap), sizeof(struct SOAP_ENV__Header));
	}

	if (soap_response(soap, SOAP_OK) || soap_envelope_begin_out(soap)
			|| soap_putheader(soap) || soap_body_begin_out(soap)) {
		return soap->error;
	}

	if (soap_body_end_out(soap) || soap_envelope_end_out(soap)
			|| soap_end_send(soap)) {
		return soap->error;
	}

	return soap_closesock(soap);
}

int SensorValues_serve_request(struct soap *soap) //TODO: pass device context
{
	soap_peek_element(soap);

	if ((!soap->action && !soap_match_tag(soap, soap->tag,
			"sens:GetSensorValues")) || (soap->action && !strcmp(soap->action,
			"http://www.teco.edu/SensorValues/GetSensorValuesIn")))
		return soap_serve_GetSensorValues(soap);

	if ((!soap->action && !soap_match_tag(soap, soap->tag,
			"sens:ConfigureSensors")) || (soap->action && !strcmp(soap->action,
			"http://www.teco.edu/SensorValues/ConfigureSensorsIn")))
		return soap_serve_ConfigureSensors(soap);
	return soap->error = SOAP_NO_METHOD;
}
