/* Generated file */
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <limits.h>
#include <errno.h>
#include <stdsoap2.h>

#include "../../bitsio/bits_io.h"
#include "../../enc/read_str_func.h"

static struct soap *soap = NULL;
static int label = 0;
static void *reader = NULL;

#ifndef SOAP_TYPE_string
#define SOAP_TYPE_string 0
#endif

void gsoap_automata_init(struct soap *_soap, void * _reader) {
	soap = _soap;
	reader = _reader;
	label = 0;
}

void gsoap_automata_run() {
	int ret = 0;
	char *str = NULL;
	while (1) {
		switch (label) {

		case 0: {

			soap_element_begin_out(soap, "teco1:SensorValues", 0, "");

		} /* stateEnd */

		case 1: {

			if (read_bit(reader)) { /* depth 1*/

				soap_element_begin_out(soap, "teco1:AcclX", 0, "");

			} else {
				label = 5;
				continue;
			} /* AcclX */

		} /* stateEnd */

		case 2: {

			char * AcclValue_str;

			ret = str_read_short(&str, 0, 0, reader);

			if (ret < 0) {
				fprintf(stderr, "Error reading input data. Errno: %s\n",
						strerror(errno));
				return;
			}
			AcclValue_str = soap_strdup(soap, str);
			soap_outstring(soap, "teco1:AcclValue", -1, &AcclValue_str, "",
					SOAP_TYPE_string);
			free(str);

		} /* stateEnd */

		case 3: {

			char * AcclIdx_str;

			ret = str_read_byte(&str, 8, 1, reader);

			if (ret < 0) {
				fprintf(stderr, "Error reading input data. Errno: %s\n",
						strerror(errno));
				return;
			}
			AcclIdx_str = soap_strdup(soap, str);
			soap_outstring(soap, "teco1:AcclIdx", -1, &AcclIdx_str, "",
					SOAP_TYPE_string);
			free(str);

		} /* stateEnd */

		case 4: {

			soap_element_end_out(soap, "teco1:AcclX");

			label = 1;
			continue;

		} /* case */

		case 5: {

			if (read_bit(reader)) { /* depth 1*/

				soap_element_begin_out(soap, "teco1:AcclY", 0, "");

			} else {
				label = 9;
				continue;
			} /* AcclY */

		} /* stateEnd */

		case 6: {

			char * AcclValue_str;

			ret = str_read_short(&str, 0, 0, reader);

			if (ret < 0) {
				fprintf(stderr, "Error reading input data. Errno: %s\n",
						strerror(errno));
				return;
			}
			AcclValue_str = soap_strdup(soap, str);
			soap_outstring(soap, "teco1:AcclValue", -1, &AcclValue_str, "",
					SOAP_TYPE_string);
			free(str);

		} /* stateEnd */

		case 7: {

			char * AcclIdx_str;

			ret = str_read_byte(&str, 8, 1, reader);

			if (ret < 0) {
				fprintf(stderr, "Error reading input data. Errno: %s\n",
						strerror(errno));
				return;
			}
			AcclIdx_str = soap_strdup(soap, str);
			soap_outstring(soap, "teco1:AcclIdx", -1, &AcclIdx_str, "",
					SOAP_TYPE_string);
			free(str);

		} /* stateEnd */

		case 8: {

			soap_element_end_out(soap, "teco1:AcclY");

			label = 5;
			continue;

		} /* case */

		case 9: {

			if (read_bit(reader)) { /* depth 1*/

				soap_element_begin_out(soap, "teco1:AcclZ", 0, "");

			} else {
				label = 13;
				continue;
			} /* AcclZ */

		} /* stateEnd */

		case 10: {

			char * AcclValue_str;

			ret = str_read_short(&str, 0, 0, reader);

			if (ret < 0) {
				fprintf(stderr, "Error reading input data. Errno: %s\n",
						strerror(errno));
				return;
			}
			AcclValue_str = soap_strdup(soap, str);
			soap_outstring(soap, "teco1:AcclValue", -1, &AcclValue_str, "",
					SOAP_TYPE_string);
			free(str);

		} /* stateEnd */

		case 11: {

			char * AcclIdx_str;

			ret = str_read_byte(&str, 8, 1, reader);

			if (ret < 0) {
				fprintf(stderr, "Error reading input data. Errno: %s\n",
						strerror(errno));
				return;
			}
			AcclIdx_str = soap_strdup(soap, str);
			soap_outstring(soap, "teco1:AcclIdx", -1, &AcclIdx_str, "",
					SOAP_TYPE_string);
			free(str);

		} /* stateEnd */

		case 12: {

			soap_element_end_out(soap, "teco1:AcclZ");

			label = 9;
			continue;

		} /* case */

		case 13: {

			/* loop /SensorValues/Audio */
			while (read_bit(reader)) { /* depth 1 */

				char * Audio_str;

				ret = str_read_byte(&str, 8, 1, reader);

				if (ret < 0) {
					fprintf(stderr, "Error reading input data. Errno: %s\n",
							strerror(errno));
					return;
				}
				Audio_str = soap_strdup(soap, str);
				soap_outstring(soap, "teco1:Audio", -1, &Audio_str, "",
						SOAP_TYPE_string);
				free(str);

			} /* Audio */

		} /* stateEnd */

		case 14: {

			if (read_bit(reader)) { /* depth 1*/

				soap_element_begin_out(soap, "teco1:Light", 0, "");

			} else {
				label = 18;
				continue;
			} /* Light */

		} /* stateEnd */

		case 15: {

			char * LightValue_str;

			ret = str_read_byte(&str, 8, 1, reader);

			if (ret < 0) {
				fprintf(stderr, "Error reading input data. Errno: %s\n",
						strerror(errno));
				return;
			}
			LightValue_str = soap_strdup(soap, str);
			soap_outstring(soap, "teco1:LightValue", -1, &LightValue_str, "",
					SOAP_TYPE_string);
			free(str);

		} /* stateEnd */

		case 16: {

			char * LightIdx_str;

			ret = str_read_byte(&str, 8, 1, reader);

			if (ret < 0) {
				fprintf(stderr, "Error reading input data. Errno: %s\n",
						strerror(errno));
				return;
			}
			LightIdx_str = soap_strdup(soap, str);
			soap_outstring(soap, "teco1:LightIdx", -1, &LightIdx_str, "",
					SOAP_TYPE_string);
			free(str);

		} /* stateEnd */

		case 17: {

			soap_element_end_out(soap, "teco1:Light");

			label = 14;
			continue;

		} /* case */

		case 18: {

			if (read_bit(reader)) { /* depth 1*/

				soap_element_begin_out(soap, "teco1:AmbientLight", 0, "");

			} else {
				label = 22;
				continue;
			} /* AmbientLight */

		} /* stateEnd */

		case 19: {

			char * LightValue_str;

			ret = str_read_byte(&str, 8, 1, reader);

			if (ret < 0) {
				fprintf(stderr, "Error reading input data. Errno: %s\n",
						strerror(errno));
				return;
			}
			LightValue_str = soap_strdup(soap, str);
			soap_outstring(soap, "teco1:LightValue", -1, &LightValue_str, "",
					SOAP_TYPE_string);
			free(str);

		} /* stateEnd */

		case 20: {

			char * LightIdx_str;

			ret = str_read_byte(&str, 8, 1, reader);

			if (ret < 0) {
				fprintf(stderr, "Error reading input data. Errno: %s\n",
						strerror(errno));
				return;
			}
			LightIdx_str = soap_strdup(soap, str);
			soap_outstring(soap, "teco1:LightIdx", -1, &LightIdx_str, "",
					SOAP_TYPE_string);
			free(str);

		} /* stateEnd */

		case 21: {

			soap_element_end_out(soap, "teco1:AmbientLight");

			label = 18;
			continue;

		} /* case */

		case 22: {

			if (read_bit(reader)) { /* depth 1*/

				soap_element_begin_out(soap, "teco1:Force", 0, "");

			} else {
				label = 26;
				continue;
			} /* Force */

		} /* stateEnd */

		case 23: {

			char * ForceValue_str;

			ret = str_read_byte(&str, 8, 1, reader);

			if (ret < 0) {
				fprintf(stderr, "Error reading input data. Errno: %s\n",
						strerror(errno));
				return;
			}
			ForceValue_str = soap_strdup(soap, str);
			soap_outstring(soap, "teco1:ForceValue", -1, &ForceValue_str, "",
					SOAP_TYPE_string);
			free(str);

		} /* stateEnd */

		case 24: {

			char * ForceIdx_str;

			ret = str_read_byte(&str, 8, 1, reader);

			if (ret < 0) {
				fprintf(stderr, "Error reading input data. Errno: %s\n",
						strerror(errno));
				return;
			}
			ForceIdx_str = soap_strdup(soap, str);
			soap_outstring(soap, "teco1:ForceIdx", -1, &ForceIdx_str, "",
					SOAP_TYPE_string);
			free(str);

		} /* stateEnd */

		case 25: {

			soap_element_end_out(soap, "teco1:Force");

			label = 22;
			continue;

		} /* case */

		case 26: {

			if (read_bit(reader)) { /* depth 1*/

				soap_element_begin_out(soap, "teco1:Temperature", 0, "");

			} else {
				label = 30;
				continue;
			} /* Temperature */

		} /* stateEnd */

		case 27: {

			char * TempValue_str;

			ret = str_read_byte(&str, 0, 0, reader);

			if (ret < 0) {
				fprintf(stderr, "Error reading input data. Errno: %s\n",
						strerror(errno));
				return;
			}
			TempValue_str = soap_strdup(soap, str);
			soap_outstring(soap, "teco1:TempValue", -1, &TempValue_str, "",
					SOAP_TYPE_string);
			free(str);

		} /* stateEnd */

		case 28: {

			char * TempIdx_str;

			ret = str_read_byte(&str, 8, 1, reader);

			if (ret < 0) {
				fprintf(stderr, "Error reading input data. Errno: %s\n",
						strerror(errno));
				return;
			}
			TempIdx_str = soap_strdup(soap, str);
			soap_outstring(soap, "teco1:TempIdx", -1, &TempIdx_str, "",
					SOAP_TYPE_string);
			free(str);

		} /* stateEnd */

		case 29: {

			soap_element_end_out(soap, "teco1:Temperature");

			label = 26;
			continue;

		} /* case */

		case 30: {

			/* loop /SensorValues/Voltage */
			while (read_bit(reader)) { /* depth 1 */

				char * Voltage_str;

				ret = str_read_short(&str, 16, 1, reader);

				if (ret < 0) {
					fprintf(stderr, "Error reading input data. Errno: %s\n",
							strerror(errno));
					return;
				}
				Voltage_str = soap_strdup(soap, str);
				soap_outstring(soap, "teco1:Voltage", -1, &Voltage_str, "",
						SOAP_TYPE_string);
				free(str);

			} /* Voltage */

		} /* stateEnd */

		case 31: {

			soap_element_end_out(soap, "teco1:SensorValues");

		} /* case */

		default:
			return;
		}
	}
}
