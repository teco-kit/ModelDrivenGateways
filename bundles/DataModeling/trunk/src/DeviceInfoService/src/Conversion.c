

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

#include <limits.h>
#include <errno.h>
#include <stdsoap2.h>

#include "DeviceInfo_operations.h"
#include "Conversion.h"

#ifndef SOAP_TYPE_string
#define SOAP_TYPE_string 0
#endif

void writeDeviceInfo(struct soap *soap,DeviceInfo * info)
{
	soap_element_begin_out(soap, "dvcinf:deviceinfo", 0, "");

	char * status_str = soap_strdup(soap, info->status);
	soap_outstring(soap, "dvcinf:status", -1, &status_str, "", SOAP_TYPE_string);

	soap_element_end_out(soap, "dvcinf:deviceinfo");
}

void writeStopDevice(struct soap *soap,char * buf,int len)
{
	soap_element_begin_out(soap, "dvcinf:stopinfo", 0, "");

	char * status_str = soap_strdup(soap, buf);
	soap_outstring(soap, "dvcinf:status", -1, &status_str, "", SOAP_TYPE_string);

	soap_element_end_out(soap, "dvcinf:stopinfo");
}
