#include <ws4d-gSOAP/dpwsH.h>
#include <ws4d-gSOAP/ws-addressing.h>
#include <stdsoap2.h>

#include <ws4d-gSOAP/dpws_device.h>

extern void (*send_buf)(struct dpws_s *, uint16_t , uint8_t , struct soap* , u_char* , ssize_t );
extern ssize_t (*rcv_buf)(struct dpws_s *device, uint16_t service_id, uint8_t op_id, struct soap* msg, char **buf);

#include "Conversion.h"

int AccelerationService_serve_request(struct soap *soap)
{
	soap_peek_element(soap);

	return soap->error = SOAP_NO_METHOD;
}
