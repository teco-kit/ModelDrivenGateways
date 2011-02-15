

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

#include <limits.h>
#include <errno.h>
#include <stdsoap2.h>

#include "DataLogging_operations.h"
#include "Conversion.h"

#ifndef SOAP_TYPE_string
#define SOAP_TYPE_string 0
#endif

void writeEvent(struct soap *soap,char * buf,int len)
{
	loggingpage * page = (loggingpage*) buf;
	char * str = NULL;

	soap_element_begin_out(soap, "log:series", 0, "");

	asprintf(&str, "%d", page->samplecount);
	char * count_str = soap_strdup(soap, str);
	soap_outstring(soap, "log:count", -1, &count_str, "", SOAP_TYPE_string);
	free(str);
	str = NULL;

	float delta = page->prevsamplecount * (1/(float)page->rate);
	asprintf(&str, "PT%fS", delta);
	char * delta_str = soap_strdup(soap, str);
	soap_outstring(soap, "log:delta", -1, &delta_str, "", SOAP_TYPE_string);
	free(str);
	str = NULL;

	int i;
	for(i=0;i<page->samplecount;i++)
	{
		soap_element_begin_out(soap, "log:sample", 0, "");
		delta = i * (1/(float)page->rate);
		asprintf(&str, "PT%fS", delta);
		char * sampledelta_str = soap_strdup(soap, str);
		soap_outstring(soap, "log:delta", -1, &sampledelta_str, "", SOAP_TYPE_string);
		free(str);
		str = NULL;
		soap_element_begin_out(soap, "log:accl", 0, "");
		asprintf(&str, "%f", page->samples[i].values[0]);
		char * x_str = soap_strdup(soap, str);
		soap_outstring(soap, "log:x", -1, &x_str, "", SOAP_TYPE_string);
		free(str);
		str = NULL;

		asprintf(&str, "%f", page->samples[i].values[1]);
		char * y_str = soap_strdup(soap, str);
		soap_outstring(soap, "log:y", -1, &y_str, "", SOAP_TYPE_string);
		free(str);

		asprintf(&str, "%f", page->samples[i].values[2]);
		char * z_str = soap_strdup(soap, str);
		soap_outstring(soap, "log:z", -1, &z_str, "", SOAP_TYPE_string);
		free(str);

		soap_element_end_out(soap, "log:accl");
		soap_element_end_out(soap, "log:sample");
	}
	soap_element_end_out(soap, "log:series");
}

int readLoggingInfo(struct soap *soap, LoggingInfo * info)
{
	if (soap_element_begin_in(soap, "log:logginginfo", 0, NULL) != SOAP_OK)
	{
		soap->error = soap_sender_fault(soap,"tag name or namespace mismatch: LoggingInfo expected",NULL);
		return 0;
	}
	if (soap_element_begin_in(soap, "log:rate", 0, NULL) != SOAP_OK)
	{
		soap->error = soap_sender_fault(soap,"tag name or namespace mismatch: rate expected",NULL);
		return 0;
	}

	const char* str = soap_value(soap);
	info->rate = strdup(str);

	if (soap_element_end_in(soap, "log:rate") != SOAP_OK) {
		soap->error = soap_sender_fault(soap,"tag name or namespace mismatch: rate expected",NULL);
		return 0;
	}

	if (soap_element_end_in(soap, "log:logginginfo") != SOAP_OK) {
		soap->error = soap_sender_fault(soap,"tag name or namespace mismatch: LoggingInfo expected",NULL);
		return 0;
	}
	return 1;
}

void writeSessionCount(struct soap *soap,sessioninfo * info)
{
	soap_element_begin_out(soap, "log:sessioninfo", 0, "");

	char * str = NULL;
	asprintf(&str, "%d", info->count);
	char * count_str = soap_strdup(soap, str);
	soap_outstring(soap, "log:count", -1, &count_str, "", SOAP_TYPE_string);
	free(str);

	soap_element_end_out(soap, "log:sessioninfo");
}
