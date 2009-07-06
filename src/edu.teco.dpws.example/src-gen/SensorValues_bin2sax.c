
/* Generated file */
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <limits.h>
#include <errno.h>
#include <stdsoap2.h>

#include <bitsio/read_bits.h>
#include <strio/str_func.h>

#ifndef SOAP_TYPE_string
#define SOAP_TYPE_string 0
#endif

static int gsoap_automata(struct READER_STRUCT *reader, struct soap *soap,
		int *label);

int SensorValues_bin2sax_run(struct READER_STRUCT *reader, struct soap *soap) {
	int ret;
	int label = 0;
	while ((ret = gsoap_automata(reader, soap, &label)) > 0)
		;

	return ret;
}

// return:  0 if EOF, 0<for fault, and  read bits else (TODO)
static int gsoap_automata(struct READER_STRUCT *reader, struct soap *soap,
		int *_label) {
#define label (*_label)

	int ret = 0;
	char *str = NULL;

	while (1) {
		switch (label) {

		// Start State


		case 0://stateComplexBegin(SensorValues)
		{
			label = 1; //assert 1==label+1

			/* lowerbound=1 upperbound=1*/

			soap_element_begin_out(soap, "sens:SensorValues", 0, "");

			//push SensorValues: next 


			break;
		} /* stateEnd */

		case 1://stateComplexBegin(AcclX)
		{
			label = 2; //assert 2==label+1

			/* lowerbound=0 upperbound=1*/

			if (read_bit(reader)) { /* depth 1*/

				soap_element_begin_out(soap, "sens:AcclX", 0, "");

				//push AcclX: next 5


			} else {
				label = 5;
				continue;
			} /* AcclX */

			break;
		} /* stateEnd */

		case 2: //stateBegin(AcclValue)
		{
			label = 3; //assert 3==label+1


			{

				ret = str_read_int16_t(reader, &str, 0);
				;

				if (ret < 0) {
					fprintf(stderr, "Error reading input data. Errno: %s\n",
							strerror(errno));
					return -1;
				}

				{
					char * AcclValue_str = soap_strdup(soap, str);
					soap_outstring(soap, "sens:AcclValue", -1, &AcclValue_str,
							"", SOAP_TYPE_string);
				}

				free(str);

			}

			break;
		} /* stateEnd */

		case 3: //stateBegin(AcclIdx)
		{
			label = 4; //assert 4==label+1


			{

				ret = str_read_uint8_t(reader, &str, 8);
				;

				if (ret < 0) {
					fprintf(stderr, "Error reading input data. Errno: %s\n",
							strerror(errno));
					return -1;
				}

				{
					char * AcclIdx_str = soap_strdup(soap, str);
					soap_outstring(soap, "sens:AcclIdx", -1, &AcclIdx_str, "",
							SOAP_TYPE_string);
				}

				free(str);

			}

			break;
		} /* stateEnd */

		case 4: //stateComplexEnd(AcclX) : 0..1
		{

			label = 1; /* constLoopEnd1 /SensorValues/AcclX/ */

			soap_element_end_out(soap, "sens:AcclX");

			continue; /* constLoopEnd2 /SensorValues/AcclX/ */

		} /* case */
			//pop AcclX


		case 5://stateComplexBegin(AcclY)
		{
			label = 6; //assert 6==label+1

			/* lowerbound=0 upperbound=1*/

			if (read_bit(reader)) { /* depth 1*/

				soap_element_begin_out(soap, "sens:AcclY", 0, "");

				//push AcclY: next 9


			} else {
				label = 9;
				continue;
			} /* AcclY */

			break;
		} /* stateEnd */

		case 6: //stateBegin(AcclValue)
		{
			label = 7; //assert 7==label+1


			{

				ret = str_read_int16_t(reader, &str, 0);
				;

				if (ret < 0) {
					fprintf(stderr, "Error reading input data. Errno: %s\n",
							strerror(errno));
					return -1;
				}

				{
					char * AcclValue_str = soap_strdup(soap, str);
					soap_outstring(soap, "sens:AcclValue", -1, &AcclValue_str,
							"", SOAP_TYPE_string);
				}

				free(str);

			}

			break;
		} /* stateEnd */

		case 7: //stateBegin(AcclIdx)
		{
			label = 8; //assert 8==label+1


			{

				ret = str_read_uint8_t(reader, &str, 8);
				;

				if (ret < 0) {
					fprintf(stderr, "Error reading input data. Errno: %s\n",
							strerror(errno));
					return -1;
				}

				{
					char * AcclIdx_str = soap_strdup(soap, str);
					soap_outstring(soap, "sens:AcclIdx", -1, &AcclIdx_str, "",
							SOAP_TYPE_string);
				}

				free(str);

			}

			break;
		} /* stateEnd */

		case 8: //stateComplexEnd(AcclY) : 0..1
		{

			label = 5; /* constLoopEnd1 /SensorValues/AcclY/ */

			soap_element_end_out(soap, "sens:AcclY");

			continue; /* constLoopEnd2 /SensorValues/AcclY/ */

		} /* case */
			//pop AcclY


		case 9://stateComplexBegin(AcclZ)
		{
			label = 10; //assert 10==label+1

			/* lowerbound=0 upperbound=1*/

			if (read_bit(reader)) { /* depth 1*/

				soap_element_begin_out(soap, "sens:AcclZ", 0, "");

				//push AcclZ: next 13


			} else {
				label = 13;
				continue;
			} /* AcclZ */

			break;
		} /* stateEnd */

		case 10: //stateBegin(AcclValue)
		{
			label = 11; //assert 11==label+1


			{

				ret = str_read_int16_t(reader, &str, 0);
				;

				if (ret < 0) {
					fprintf(stderr, "Error reading input data. Errno: %s\n",
							strerror(errno));
					return -1;
				}

				{
					char * AcclValue_str = soap_strdup(soap, str);
					soap_outstring(soap, "sens:AcclValue", -1, &AcclValue_str,
							"", SOAP_TYPE_string);
				}

				free(str);

			}

			break;
		} /* stateEnd */

		case 11: //stateBegin(AcclIdx)
		{
			label = 12; //assert 12==label+1


			{

				ret = str_read_uint8_t(reader, &str, 8);
				;

				if (ret < 0) {
					fprintf(stderr, "Error reading input data. Errno: %s\n",
							strerror(errno));
					return -1;
				}

				{
					char * AcclIdx_str = soap_strdup(soap, str);
					soap_outstring(soap, "sens:AcclIdx", -1, &AcclIdx_str, "",
							SOAP_TYPE_string);
				}

				free(str);

			}

			break;
		} /* stateEnd */

		case 12: //stateComplexEnd(AcclZ) : 0..1
		{

			label = 9; /* constLoopEnd1 /SensorValues/AcclZ/ */

			soap_element_end_out(soap, "sens:AcclZ");

			continue; /* constLoopEnd2 /SensorValues/AcclZ/ */

		} /* case */
			//pop AcclZ


		case 13: //stateBegin(Audio)
		{
			label = 14; //assert 14==label+1


			/* loop /SensorValuesAudio label=label */
			while (read_bit(reader))

			{

				ret = str_read_uint8_t(reader, &str, 8);
				;

				if (ret < 0) {
					fprintf(stderr, "Error reading input data. Errno: %s\n",
							strerror(errno));
					return -1;
				}

				{
					char * Audio_str = soap_strdup(soap, str);
					soap_outstring(soap, "sens:Audio", -1, &Audio_str, "",
							SOAP_TYPE_string);
				}

				free(str);

			} /* Audio */

			break;
		} /* stateEnd */

		case 14://stateComplexBegin(Light)
		{
			label = 15; //assert 15==label+1

			/* lowerbound=0 upperbound=1*/

			if (read_bit(reader)) { /* depth 1*/

				soap_element_begin_out(soap, "sens:Light", 0, "");

				//push Light: next 18


			} else {
				label = 18;
				continue;
			} /* Light */

			break;
		} /* stateEnd */

		case 15: //stateBegin(LightValue)
		{
			label = 16; //assert 16==label+1


			{

				ret = str_read_uint8_t(reader, &str, 8);
				;

				if (ret < 0) {
					fprintf(stderr, "Error reading input data. Errno: %s\n",
							strerror(errno));
					return -1;
				}

				{
					char * LightValue_str = soap_strdup(soap, str);
					soap_outstring(soap, "sens:LightValue", -1,
							&LightValue_str, "", SOAP_TYPE_string);
				}

				free(str);

			}

			break;
		} /* stateEnd */

		case 16: //stateBegin(LightIdx)
		{
			label = 17; //assert 17==label+1


			{

				ret = str_read_uint8_t(reader, &str, 8);
				;

				if (ret < 0) {
					fprintf(stderr, "Error reading input data. Errno: %s\n",
							strerror(errno));
					return -1;
				}

				{
					char * LightIdx_str = soap_strdup(soap, str);
					soap_outstring(soap, "sens:LightIdx", -1, &LightIdx_str,
							"", SOAP_TYPE_string);
				}

				free(str);

			}

			break;
		} /* stateEnd */

		case 17: //stateComplexEnd(Light) : 0..1
		{

			label = 14; /* constLoopEnd1 /SensorValues/Light/ */

			soap_element_end_out(soap, "sens:Light");

			continue; /* constLoopEnd2 /SensorValues/Light/ */

		} /* case */
			//pop Light


		case 18://stateComplexBegin(AmbientLight)
		{
			label = 19; //assert 19==label+1

			/* lowerbound=0 upperbound=1*/

			if (read_bit(reader)) { /* depth 1*/

				soap_element_begin_out(soap, "sens:AmbientLight", 0, "");

				//push AmbientLight: next 22


			} else {
				label = 22;
				continue;
			} /* AmbientLight */

			break;
		} /* stateEnd */

		case 19: //stateBegin(LightValue)
		{
			label = 20; //assert 20==label+1


			{

				ret = str_read_uint8_t(reader, &str, 8);
				;

				if (ret < 0) {
					fprintf(stderr, "Error reading input data. Errno: %s\n",
							strerror(errno));
					return -1;
				}

				{
					char * LightValue_str = soap_strdup(soap, str);
					soap_outstring(soap, "sens:LightValue", -1,
							&LightValue_str, "", SOAP_TYPE_string);
				}

				free(str);

			}

			break;
		} /* stateEnd */

		case 20: //stateBegin(LightIdx)
		{
			label = 21; //assert 21==label+1


			{

				ret = str_read_uint8_t(reader, &str, 8);
				;

				if (ret < 0) {
					fprintf(stderr, "Error reading input data. Errno: %s\n",
							strerror(errno));
					return -1;
				}

				{
					char * LightIdx_str = soap_strdup(soap, str);
					soap_outstring(soap, "sens:LightIdx", -1, &LightIdx_str,
							"", SOAP_TYPE_string);
				}

				free(str);

			}

			break;
		} /* stateEnd */

		case 21: //stateComplexEnd(AmbientLight) : 0..1
		{

			label = 18; /* constLoopEnd1 /SensorValues/AmbientLight/ */

			soap_element_end_out(soap, "sens:AmbientLight");

			continue; /* constLoopEnd2 /SensorValues/AmbientLight/ */

		} /* case */
			//pop AmbientLight


		case 22://stateComplexBegin(Force)
		{
			label = 23; //assert 23==label+1

			/* lowerbound=0 upperbound=1*/

			if (read_bit(reader)) { /* depth 1*/

				soap_element_begin_out(soap, "sens:Force", 0, "");

				//push Force: next 26


			} else {
				label = 26;
				continue;
			} /* Force */

			break;
		} /* stateEnd */

		case 23: //stateBegin(ForceValue)
		{
			label = 24; //assert 24==label+1


			{

				ret = str_read_uint8_t(reader, &str, 8);
				;

				if (ret < 0) {
					fprintf(stderr, "Error reading input data. Errno: %s\n",
							strerror(errno));
					return -1;
				}

				{
					char * ForceValue_str = soap_strdup(soap, str);
					soap_outstring(soap, "sens:ForceValue", -1,
							&ForceValue_str, "", SOAP_TYPE_string);
				}

				free(str);

			}

			break;
		} /* stateEnd */

		case 24: //stateBegin(ForceIdx)
		{
			label = 25; //assert 25==label+1


			{

				ret = str_read_uint8_t(reader, &str, 8);
				;

				if (ret < 0) {
					fprintf(stderr, "Error reading input data. Errno: %s\n",
							strerror(errno));
					return -1;
				}

				{
					char * ForceIdx_str = soap_strdup(soap, str);
					soap_outstring(soap, "sens:ForceIdx", -1, &ForceIdx_str,
							"", SOAP_TYPE_string);
				}

				free(str);

			}

			break;
		} /* stateEnd */

		case 25: //stateComplexEnd(Force) : 0..1
		{

			label = 22; /* constLoopEnd1 /SensorValues/Force/ */

			soap_element_end_out(soap, "sens:Force");

			continue; /* constLoopEnd2 /SensorValues/Force/ */

		} /* case */
			//pop Force


		case 26://stateComplexBegin(Temperature)
		{
			label = 27; //assert 27==label+1

			/* lowerbound=0 upperbound=1*/

			if (read_bit(reader)) { /* depth 1*/

				soap_element_begin_out(soap, "sens:Temperature", 0, "");

				//push Temperature: next 30


			} else {
				label = 30;
				continue;
			} /* Temperature */

			break;
		} /* stateEnd */

		case 27: //stateBegin(TempValue)
		{
			label = 28; //assert 28==label+1


			{

				ret = str_read_int8_t(reader, &str, 0);
				;

				if (ret < 0) {
					fprintf(stderr, "Error reading input data. Errno: %s\n",
							strerror(errno));
					return -1;
				}

				{
					char * TempValue_str = soap_strdup(soap, str);
					soap_outstring(soap, "sens:TempValue", -1, &TempValue_str,
							"", SOAP_TYPE_string);
				}

				free(str);

			}

			break;
		} /* stateEnd */

		case 28: //stateBegin(TempIdx)
		{
			label = 29; //assert 29==label+1


			{

				ret = str_read_uint8_t(reader, &str, 8);
				;

				if (ret < 0) {
					fprintf(stderr, "Error reading input data. Errno: %s\n",
							strerror(errno));
					return -1;
				}

				{
					char * TempIdx_str = soap_strdup(soap, str);
					soap_outstring(soap, "sens:TempIdx", -1, &TempIdx_str, "",
							SOAP_TYPE_string);
				}

				free(str);

			}

			break;
		} /* stateEnd */

		case 29: //stateComplexEnd(Temperature) : 0..1
		{

			label = 26; /* constLoopEnd1 /SensorValues/Temperature/ */

			soap_element_end_out(soap, "sens:Temperature");

			continue; /* constLoopEnd2 /SensorValues/Temperature/ */

		} /* case */
			//pop Temperature


		case 30: //stateBegin(Voltage)
		{
			label = 31; //assert 31==label+1


			/* loop /SensorValuesVoltage label=label */
			while (read_bit(reader))

			{

				ret = str_read_uint16_t(reader, &str, 16);
				;

				if (ret < 0) {
					fprintf(stderr, "Error reading input data. Errno: %s\n",
							strerror(errno));
					return -1;
				}

				{
					char * Voltage_str = soap_strdup(soap, str);
					soap_outstring(soap, "sens:Voltage", -1, &Voltage_str, "",
							SOAP_TYPE_string);
				}

				free(str);

			} /* Voltage */

			break;
		} /* stateEnd */

		case 31: //stateComplexEnd(SensorValues) : 1..1
		{

			label = 32; // Complex End


			soap_element_end_out(soap, "sens:SensorValues");

			break;

		} /* case */
			//pop SensorValues


		default: //StopState


			return 0;

		}
	}

	return 1;
}

