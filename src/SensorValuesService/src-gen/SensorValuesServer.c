
enum operations {

	OP_GetSensorValues, OP_SensorValuesEvent, OP_Config

};

#include <ws4d-gSOAP/dpwsH.h>
#include <ws4d-gSOAP/ws-addressing.h>
#include <stdsoap2.h>

#include <ws4d-gSOAP/dpws_device.h>

extern void (*send_buf)(struct dpws_s *, uint16_t , uint8_t , struct soap* , u_char* , ssize_t );
extern ssize_t (*rcv_buf)(struct dpws_s *device, uint16_t service_id, uint8_t op_id, struct soap* msg, char **buf);

#include "Sample_bin2sax.h"

static int soap_serve_GetSensorValues(struct soap *soap) //TODO: pass device context
{
	int op_id = OP_GetSensorValues;
	int service_id = 0; //TODO
	struct dpws_s *device = NULL;

	(*send_buf)(device, service_id, op_id, soap, NULL, 0);

	/* prepare response */
	{
		const char* To = wsa_header_get_ReplyTo(soap);
		char * MessageId = wsa_header_get_MessageId(soap);
		char * Action = "http://www.teco.edu/SensorValues/GetSensorValuesOut";

		soap->omode |= SOAP_IO_CHUNK;

		if (!To || (0 == strcmp(To, wsa_anonymousURI))) {
			To = wsa_anonymousURI;
		} else {

		}

		if (To != wsa_anonymousURI) // no strcmp needed -> s.a.
		{
			struct soap *reply_soap = soap_copy(soap);
			if (reply_soap) {
				soap_copy_stream(reply_soap, soap);
				soap_clr_omode(reply_soap, SOAP_ENC_MIME | SOAP_ENC_DIME
						| SOAP_ENC_MTOM);
				soap->socket = SOAP_INVALID_SOCKET; /* prevents close */
				if (soap_connect(soap, To, Action)) /*Todo: can this be delayed ??*/
					return soap->error;
				soap_send_empty_response(reply_soap, SOAP_OK); /* HTTP ACCEPTED */
				soap_closesock(reply_soap);
				soap_end(reply_soap);
				soap_free(reply_soap);
				/*
				 data->fresponse = soap->fresponse;
				 soap->fresponse = soap_wsa_response;
				 response will be a POST */
			} else
				return soap->error;
		} else {
			if (soap_response(soap, SOAP_OK) != SOAP_OK)
				return soap->error;
		}

		wsa_header_gen_response(soap, NULL, To, Action, MessageId,
				sizeof(struct SOAP_ENV__Header));
	}

	if (soap_envelope_begin_out(soap) || soap_putheader(soap)
			|| soap_body_begin_out(soap)) {
		return soap->error;
	}

	{
		char * buf;
		ssize_t len = (*rcv_buf)(device, service_id, op_id, soap, &buf);

		if (len < 0) {
			soap->error = soap_receiver_fault(soap, "No reply from Node", NULL);
			return soap->error;
		}
		struct READER_STRUCT* reader = read_bits_bufreader_stack_new(buf, len);

		{
			Sample_bin2sax_run(reader, soap);
		}

	}

	if (soap_body_end_out(soap) || soap_envelope_end_out(soap)
			|| soap_end_send(soap)) {
		return soap->error;
	}

	return soap_closesock(soap);
}

#include "StatusControl_sax2bin.h"

#include "Status_bin2sax.h"

static int soap_serve_Config(struct soap *soap) //TODO: pass device context
{
	int op_id = OP_Config;
	int service_id = 0; //TODO
	struct dpws_s *device = NULL;

	{
		char sendbuffer[256];
		memset(sendbuffer, 0xff, sizeof(sendbuffer));
		struct WRITER_STRUCT* writer = write_bits_bufwriter_stack_new(sendbuffer,
				sizeof(sendbuffer));

		{
			if (StatusControl_sax2bin_run(soap, writer) < 0)
				return soap->error;
		}

		(*send_buf)(device, service_id, op_id, soap, sendbuffer, write_buf_finish(
				writer));
	}

	/* prepare response */
	{
		const char* To = wsa_header_get_ReplyTo(soap);
		char * MessageId = wsa_header_get_MessageId(soap);
		char * Action = "http://www.teco.edu/SensorValues/ConfigResponse";

		soap->omode |= SOAP_IO_CHUNK;

		if (!To || (0 == strcmp(To, wsa_anonymousURI))) {
			To = wsa_anonymousURI;
		} else {

		}

		if (To != wsa_anonymousURI) // no strcmp needed -> s.a.
		{
			struct soap *reply_soap = soap_copy(soap);
			if (reply_soap) {
				soap_copy_stream(reply_soap, soap);
				soap_clr_omode(reply_soap, SOAP_ENC_MIME | SOAP_ENC_DIME
						| SOAP_ENC_MTOM);
				soap->socket = SOAP_INVALID_SOCKET; /* prevents close */
				if (soap_connect(soap, To, Action)) /*Todo: can this be delayed ??*/
					return soap->error;
				soap_send_empty_response(reply_soap, SOAP_OK); /* HTTP ACCEPTED */
				soap_closesock(reply_soap);
				soap_end(reply_soap);
				soap_free(reply_soap);
				/*
				 data->fresponse = soap->fresponse;
				 soap->fresponse = soap_wsa_response;
				 response will be a POST */
			} else
				return soap->error;
		} else {
			if (soap_response(soap, SOAP_OK) != SOAP_OK)
				return soap->error;
		}

		wsa_header_gen_response(soap, NULL, To, Action, MessageId,
				sizeof(struct SOAP_ENV__Header));
	}

	if (soap_envelope_begin_out(soap) || soap_putheader(soap)
			|| soap_body_begin_out(soap)) {
		return soap->error;
	}

	{
		char * buf;
		ssize_t len = (*rcv_buf)(device, service_id, op_id, soap, &buf);

		if (len < 0) {
			soap->error = soap_receiver_fault(soap, "No reply from Node", NULL);
			return soap->error;
		}
		struct READER_STRUCT* reader = read_bits_bufreader_stack_new(buf, len);

		{
			Status_bin2sax_run(reader, soap);
		}

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

	/* Port SensorValues */

	if ((soap->action && !strcmp(soap->action,
			"http://www.teco.edu/SensorValues/GetSensorValuesIn"))

	)
		return soap_serve_GetSensorValues(soap);

	if ((soap->action && !strcmp(soap->action,
			"http://www.teco.edu/SensorValues/ConfigRequest"))

			|| (!soap->action && !soap_match_tag(soap, soap->tag,
					"sens:StatusControl"))

	)
		return soap_serve_Config(soap);

	return soap->error = SOAP_NO_METHOD;
}
