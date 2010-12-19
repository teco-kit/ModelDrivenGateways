

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

#include <limits.h>
#include <errno.h>
#include <stdsoap2.h>

#include "Conversion.h"

#ifndef SOAP_TYPE_string
#define SOAP_TYPE_string 0
#endif

void writeSOAPBuf(struct soap *soap,char * buf)
{
	float x=0.0f,y=0.0f,z=0.0f;

	memcpy(&x,&buf[0],sizeof(float));
	memcpy(&y,&buf[sizeof(float)],sizeof(float));
	memcpy(&z,&buf[2*sizeof(float)],sizeof(float));

	writeSOAPValues(soap,x,y,z);
}

void writeSOAPValues(struct soap *soap,float x,float y,float z)
{
	char * str = NULL;

	soap_element_begin_out(soap, "acs:Sample", 0, "");



	struct timeval t;
	struct tm res;
	char out[64];
	gettimeofday(&t,NULL);
	strftime(out, sizeof(out), "%Y-%m-%dT%H:%M:%S",localtime_r(&t.tv_sec, &res));
	asprintf(&str, "%s.%06uZ", out, (uint32_t)((((uint64_t) t.tv_usec) * 1000000) >> 16));
	char * TimeStamp_str = soap_strdup(soap, str);
	soap_outstring(soap, "acs:TimeStamp", -1, &TimeStamp_str, "", SOAP_TYPE_string);
	free(str);
	str = NULL;

	asprintf(&str, "%f", x);
	char * x_str = soap_strdup(soap, str);
	soap_outstring(soap, "acs:x", -1, &x_str, "", SOAP_TYPE_string);
	free(str);
	str = NULL;

	asprintf(&str, "%f", y);
	char * y_str = soap_strdup(soap, str);
	soap_outstring(soap, "acs:y", -1, &y_str, "", SOAP_TYPE_string);
	free(str);

	asprintf(&str, "%f", z);
	char * z_str = soap_strdup(soap, str);
	soap_outstring(soap, "acs:z", -1, &z_str, "", SOAP_TYPE_string);
	free(str);


	soap_element_end_out(soap, "acs:Sample");
}
