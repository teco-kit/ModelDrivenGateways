
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

static int gsoap_automata();
int SensorConfiguration_sax2bin_run(struct soap *soap,
		struct WRITER_STRUCT *writer) {
	int ret;
	int label = 0;

	while ((ret = gsoap_automata(soap, writer, &label)) > 0)
		;

	return ret;
}

// return:  0 if EOF, 0<for fault, and  read bits else (TODO)
static int gsoap_automata(struct soap *soap, struct WRITER_STRUCT *writer,
		int *_label) {
#define label (*_label)

	for (;;) {
		switch (label) {

		// Start State


		case 0://stateComplexBegin(SensorConfiguration)
		{
			label = 1; //assert 1==label+1

			/* lowerbound=1 upperbound=1*/

			if (!(soap_element_begin_in(soap, "sens:SensorConfiguration", 0,
					NULL) == SOAP_OK)) {
				soap->error
						= soap_receiver_fault(
								soap,
								"tag name or namespace mismatch",
								"< errorcode xmlns='http://tempuri.org' > 123 < /errorcode > < errorinfo xmlns='http://tempuri.org' > &lt;SensorConfiguration&gt; expected </errorinfo>");
				//      soap->error=SOAP_TAG_MISMATCH;
				return -1;
			}

			//push SensorConfiguration: next 


			break;
		} /* stateEnd */

		case 1://stateComplexBegin(sensors)
		{
			label = 2; //assert 2==label+1

			/* lowerbound=1 upperbound=1*/

			if (!(soap_element_begin_in(soap, "sens:sensors", 0, NULL)
					== SOAP_OK)) {
				soap->error
						= soap_receiver_fault(
								soap,
								"tag name or namespace mismatch",
								"< errorcode xmlns='http://tempuri.org' > 123 < /errorcode > < errorinfo xmlns='http://tempuri.org' > &lt;sensors&gt; expected </errorinfo>");
				//      soap->error=SOAP_TAG_MISMATCH;
				return -1;
			}

			//push sensors: next 


			break;
		} /* stateEnd */

		case 2://stateComplexBegin(AcclX)
		{
			label = 3; //assert 3==label+1

			/* lowerbound=0 upperbound=1*/

			if ((soap_element_begin_in(soap, "sens:AcclX", 0, NULL) == SOAP_OK)) { /* depth 2*/

				//push AcclX: next 5


				write_true(writer);
			} else {
				label = 5; //TODO: xx
				write_false(writer);
				continue;
			} /* AcclX */

			break;
		} /* stateEnd */

		case 3: //stateBegin(rate)
		{
			label = 4; //assert 4==label+1


			if (!(soap_element_begin_in(soap, "sens:rate", 0, NULL) == SOAP_OK)) {
				soap->error
						= soap_receiver_fault(
								soap,
								"tag name or namespace mismatch",
								"< errorcode xmlns='http://tempuri.org' > 123 < /errorcode > < errorinfo xmlns='http://tempuri.org' > &lt;rate&gt; expected </errorinfo>");
				// soap->error=SOAP_TAG_MISMATCH;
				return -1;
			}

			{

				{
					const char *str = soap_value(soap);

					str_write_int16_t(writer, str);

				}

				if (soap_element_end_in(soap, "sens:rate") != SOAP_OK) {
					soap->error
							= soap_receiver_fault(
									soap,
									"tag name or namespace mismatch",
									"< errorcode xmlns='http://tempuri.org' > 123 < /errorcode > < errorinfo xmlns='http://tempuri.org' > &lt;rate&gt; expected </errorinfo>");
					//soap->error=SOAP_TAG_MISMATCH;
					return -1;
				}

				//stateNameEnd rate


			}

			break;
		} /* stateEnd */

		case 4: //stateComplexEnd(AcclX) : 0..1
		{

			label = 2; /* constLoopEnd1 /SensorConfiguration/sensors/AcclX/ */

			if (soap_element_end_in(soap, "sens:AcclX") != SOAP_OK) {
				soap->error
						= soap_receiver_fault(
								soap,
								"tag name or namespace mismatch",
								"< errorcode xmlns='http://tempuri.org' > 123 < /errorcode > < errorinfo xmlns='http://tempuri.org' > &lt;AcclX&gt; expected </errorinfo>");
				//soap->error=SOAP_TAG_MISMATCH;
				return -1;
			}

			//stateNameEnd AcclX


			continue; /* constLoopEnd2 /SensorConfiguration/sensors/AcclX/ */

		} /* case */
			//pop AcclX


		case 5://stateComplexBegin(AcclY)
		{
			label = 6; //assert 6==label+1

			/* lowerbound=0 upperbound=1*/

			if ((soap_element_begin_in(soap, "sens:AcclY", 0, NULL) == SOAP_OK)) { /* depth 2*/

				//push AcclY: next 8


				write_true(writer);
			} else {
				label = 8; //TODO: xx
				write_false(writer);
				continue;
			} /* AcclY */

			break;
		} /* stateEnd */

		case 6: //stateBegin(rate)
		{
			label = 7; //assert 7==label+1


			if (!(soap_element_begin_in(soap, "sens:rate", 0, NULL) == SOAP_OK)) {
				soap->error
						= soap_receiver_fault(
								soap,
								"tag name or namespace mismatch",
								"< errorcode xmlns='http://tempuri.org' > 123 < /errorcode > < errorinfo xmlns='http://tempuri.org' > &lt;rate&gt; expected </errorinfo>");
				// soap->error=SOAP_TAG_MISMATCH;
				return -1;
			}

			{

				{
					const char *str = soap_value(soap);

					str_write_int16_t(writer, str);

				}

				if (soap_element_end_in(soap, "sens:rate") != SOAP_OK) {
					soap->error
							= soap_receiver_fault(
									soap,
									"tag name or namespace mismatch",
									"< errorcode xmlns='http://tempuri.org' > 123 < /errorcode > < errorinfo xmlns='http://tempuri.org' > &lt;rate&gt; expected </errorinfo>");
					//soap->error=SOAP_TAG_MISMATCH;
					return -1;
				}

				//stateNameEnd rate


			}

			break;
		} /* stateEnd */

		case 7: //stateComplexEnd(AcclY) : 0..1
		{

			label = 5; /* constLoopEnd1 /SensorConfiguration/sensors/AcclY/ */

			if (soap_element_end_in(soap, "sens:AcclY") != SOAP_OK) {
				soap->error
						= soap_receiver_fault(
								soap,
								"tag name or namespace mismatch",
								"< errorcode xmlns='http://tempuri.org' > 123 < /errorcode > < errorinfo xmlns='http://tempuri.org' > &lt;AcclY&gt; expected </errorinfo>");
				//soap->error=SOAP_TAG_MISMATCH;
				return -1;
			}

			//stateNameEnd AcclY


			continue; /* constLoopEnd2 /SensorConfiguration/sensors/AcclY/ */

		} /* case */
			//pop AcclY


		case 8://stateComplexBegin(AcclZ)
		{
			label = 9; //assert 9==label+1

			/* lowerbound=0 upperbound=1*/

			if ((soap_element_begin_in(soap, "sens:AcclZ", 0, NULL) == SOAP_OK)) { /* depth 2*/

				//push AcclZ: next 11


				write_true(writer);
			} else {
				label = 11; //TODO: xx
				write_false(writer);
				continue;
			} /* AcclZ */

			break;
		} /* stateEnd */

		case 9: //stateBegin(rate)
		{
			label = 10; //assert 10==label+1


			if (!(soap_element_begin_in(soap, "sens:rate", 0, NULL) == SOAP_OK)) {
				soap->error
						= soap_receiver_fault(
								soap,
								"tag name or namespace mismatch",
								"< errorcode xmlns='http://tempuri.org' > 123 < /errorcode > < errorinfo xmlns='http://tempuri.org' > &lt;rate&gt; expected </errorinfo>");
				// soap->error=SOAP_TAG_MISMATCH;
				return -1;
			}

			{

				{
					const char *str = soap_value(soap);

					str_write_int16_t(writer, str);

				}

				if (soap_element_end_in(soap, "sens:rate") != SOAP_OK) {
					soap->error
							= soap_receiver_fault(
									soap,
									"tag name or namespace mismatch",
									"< errorcode xmlns='http://tempuri.org' > 123 < /errorcode > < errorinfo xmlns='http://tempuri.org' > &lt;rate&gt; expected </errorinfo>");
					//soap->error=SOAP_TAG_MISMATCH;
					return -1;
				}

				//stateNameEnd rate


			}

			break;
		} /* stateEnd */

		case 10: //stateComplexEnd(AcclZ) : 0..1
		{

			label = 8; /* constLoopEnd1 /SensorConfiguration/sensors/AcclZ/ */

			if (soap_element_end_in(soap, "sens:AcclZ") != SOAP_OK) {
				soap->error
						= soap_receiver_fault(
								soap,
								"tag name or namespace mismatch",
								"< errorcode xmlns='http://tempuri.org' > 123 < /errorcode > < errorinfo xmlns='http://tempuri.org' > &lt;AcclZ&gt; expected </errorinfo>");
				//soap->error=SOAP_TAG_MISMATCH;
				return -1;
			}

			//stateNameEnd AcclZ


			continue; /* constLoopEnd2 /SensorConfiguration/sensors/AcclZ/ */

		} /* case */
			//pop AcclZ


		case 11://stateComplexBegin(Audio)
		{
			label = 12; //assert 12==label+1

			/* lowerbound=0 upperbound=1*/

			if ((soap_element_begin_in(soap, "sens:Audio", 0, NULL) == SOAP_OK)) { /* depth 2*/

				//push Audio: next 14


				write_true(writer);
			} else {
				label = 14; //TODO: xx
				write_false(writer);
				continue;
			} /* Audio */

			break;
		} /* stateEnd */

		case 12: //stateBegin(rate)
		{
			label = 13; //assert 13==label+1


			if (!(soap_element_begin_in(soap, "sens:rate", 0, NULL) == SOAP_OK)) {
				soap->error
						= soap_receiver_fault(
								soap,
								"tag name or namespace mismatch",
								"< errorcode xmlns='http://tempuri.org' > 123 < /errorcode > < errorinfo xmlns='http://tempuri.org' > &lt;rate&gt; expected </errorinfo>");
				// soap->error=SOAP_TAG_MISMATCH;
				return -1;
			}

			{

				{
					const char *str = soap_value(soap);

					str_write_int16_t(writer, str);

				}

				if (soap_element_end_in(soap, "sens:rate") != SOAP_OK) {
					soap->error
							= soap_receiver_fault(
									soap,
									"tag name or namespace mismatch",
									"< errorcode xmlns='http://tempuri.org' > 123 < /errorcode > < errorinfo xmlns='http://tempuri.org' > &lt;rate&gt; expected </errorinfo>");
					//soap->error=SOAP_TAG_MISMATCH;
					return -1;
				}

				//stateNameEnd rate


			}

			break;
		} /* stateEnd */

		case 13: //stateComplexEnd(Audio) : 0..1
		{

			label = 11; /* constLoopEnd1 /SensorConfiguration/sensors/Audio/ */

			if (soap_element_end_in(soap, "sens:Audio") != SOAP_OK) {
				soap->error
						= soap_receiver_fault(
								soap,
								"tag name or namespace mismatch",
								"< errorcode xmlns='http://tempuri.org' > 123 < /errorcode > < errorinfo xmlns='http://tempuri.org' > &lt;Audio&gt; expected </errorinfo>");
				//soap->error=SOAP_TAG_MISMATCH;
				return -1;
			}

			//stateNameEnd Audio


			continue; /* constLoopEnd2 /SensorConfiguration/sensors/Audio/ */

		} /* case */
			//pop Audio


		case 14://stateComplexBegin(Light)
		{
			label = 15; //assert 15==label+1

			/* lowerbound=0 upperbound=1*/

			if ((soap_element_begin_in(soap, "sens:Light", 0, NULL) == SOAP_OK)) { /* depth 2*/

				//push Light: next 17


				write_true(writer);
			} else {
				label = 17; //TODO: xx
				write_false(writer);
				continue;
			} /* Light */

			break;
		} /* stateEnd */

		case 15: //stateBegin(rate)
		{
			label = 16; //assert 16==label+1


			if (!(soap_element_begin_in(soap, "sens:rate", 0, NULL) == SOAP_OK)) {
				soap->error
						= soap_receiver_fault(
								soap,
								"tag name or namespace mismatch",
								"< errorcode xmlns='http://tempuri.org' > 123 < /errorcode > < errorinfo xmlns='http://tempuri.org' > &lt;rate&gt; expected </errorinfo>");
				// soap->error=SOAP_TAG_MISMATCH;
				return -1;
			}

			{

				{
					const char *str = soap_value(soap);

					str_write_int16_t(writer, str);

				}

				if (soap_element_end_in(soap, "sens:rate") != SOAP_OK) {
					soap->error
							= soap_receiver_fault(
									soap,
									"tag name or namespace mismatch",
									"< errorcode xmlns='http://tempuri.org' > 123 < /errorcode > < errorinfo xmlns='http://tempuri.org' > &lt;rate&gt; expected </errorinfo>");
					//soap->error=SOAP_TAG_MISMATCH;
					return -1;
				}

				//stateNameEnd rate


			}

			break;
		} /* stateEnd */

		case 16: //stateComplexEnd(Light) : 0..1
		{

			label = 14; /* constLoopEnd1 /SensorConfiguration/sensors/Light/ */

			if (soap_element_end_in(soap, "sens:Light") != SOAP_OK) {
				soap->error
						= soap_receiver_fault(
								soap,
								"tag name or namespace mismatch",
								"< errorcode xmlns='http://tempuri.org' > 123 < /errorcode > < errorinfo xmlns='http://tempuri.org' > &lt;Light&gt; expected </errorinfo>");
				//soap->error=SOAP_TAG_MISMATCH;
				return -1;
			}

			//stateNameEnd Light


			continue; /* constLoopEnd2 /SensorConfiguration/sensors/Light/ */

		} /* case */
			//pop Light


		case 17://stateComplexBegin(AmbientLight)
		{
			label = 18; //assert 18==label+1

			/* lowerbound=0 upperbound=1*/

			if ((soap_element_begin_in(soap, "sens:AmbientLight", 0, NULL)
					== SOAP_OK)) { /* depth 2*/

				//push AmbientLight: next 20


				write_true(writer);
			} else {
				label = 20; //TODO: xx
				write_false(writer);
				continue;
			} /* AmbientLight */

			break;
		} /* stateEnd */

		case 18: //stateBegin(rate)
		{
			label = 19; //assert 19==label+1


			if (!(soap_element_begin_in(soap, "sens:rate", 0, NULL) == SOAP_OK)) {
				soap->error
						= soap_receiver_fault(
								soap,
								"tag name or namespace mismatch",
								"< errorcode xmlns='http://tempuri.org' > 123 < /errorcode > < errorinfo xmlns='http://tempuri.org' > &lt;rate&gt; expected </errorinfo>");
				// soap->error=SOAP_TAG_MISMATCH;
				return -1;
			}

			{

				{
					const char *str = soap_value(soap);

					str_write_int16_t(writer, str);

				}

				if (soap_element_end_in(soap, "sens:rate") != SOAP_OK) {
					soap->error
							= soap_receiver_fault(
									soap,
									"tag name or namespace mismatch",
									"< errorcode xmlns='http://tempuri.org' > 123 < /errorcode > < errorinfo xmlns='http://tempuri.org' > &lt;rate&gt; expected </errorinfo>");
					//soap->error=SOAP_TAG_MISMATCH;
					return -1;
				}

				//stateNameEnd rate


			}

			break;
		} /* stateEnd */

		case 19: //stateComplexEnd(AmbientLight) : 0..1
		{

			label = 17; /* constLoopEnd1 /SensorConfiguration/sensors/AmbientLight/ */

			if (soap_element_end_in(soap, "sens:AmbientLight") != SOAP_OK) {
				soap->error
						= soap_receiver_fault(
								soap,
								"tag name or namespace mismatch",
								"< errorcode xmlns='http://tempuri.org' > 123 < /errorcode > < errorinfo xmlns='http://tempuri.org' > &lt;AmbientLight&gt; expected </errorinfo>");
				//soap->error=SOAP_TAG_MISMATCH;
				return -1;
			}

			//stateNameEnd AmbientLight


			continue; /* constLoopEnd2 /SensorConfiguration/sensors/AmbientLight/ */

		} /* case */
			//pop AmbientLight


		case 20://stateComplexBegin(Force)
		{
			label = 21; //assert 21==label+1

			/* lowerbound=0 upperbound=1*/

			if ((soap_element_begin_in(soap, "sens:Force", 0, NULL) == SOAP_OK)) { /* depth 2*/

				//push Force: next 23


				write_true(writer);
			} else {
				label = 23; //TODO: xx
				write_false(writer);
				continue;
			} /* Force */

			break;
		} /* stateEnd */

		case 21: //stateBegin(rate)
		{
			label = 22; //assert 22==label+1


			if (!(soap_element_begin_in(soap, "sens:rate", 0, NULL) == SOAP_OK)) {
				soap->error
						= soap_receiver_fault(
								soap,
								"tag name or namespace mismatch",
								"< errorcode xmlns='http://tempuri.org' > 123 < /errorcode > < errorinfo xmlns='http://tempuri.org' > &lt;rate&gt; expected </errorinfo>");
				// soap->error=SOAP_TAG_MISMATCH;
				return -1;
			}

			{

				{
					const char *str = soap_value(soap);

					str_write_int16_t(writer, str);

				}

				if (soap_element_end_in(soap, "sens:rate") != SOAP_OK) {
					soap->error
							= soap_receiver_fault(
									soap,
									"tag name or namespace mismatch",
									"< errorcode xmlns='http://tempuri.org' > 123 < /errorcode > < errorinfo xmlns='http://tempuri.org' > &lt;rate&gt; expected </errorinfo>");
					//soap->error=SOAP_TAG_MISMATCH;
					return -1;
				}

				//stateNameEnd rate


			}

			break;
		} /* stateEnd */

		case 22: //stateComplexEnd(Force) : 0..1
		{

			label = 20; /* constLoopEnd1 /SensorConfiguration/sensors/Force/ */

			if (soap_element_end_in(soap, "sens:Force") != SOAP_OK) {
				soap->error
						= soap_receiver_fault(
								soap,
								"tag name or namespace mismatch",
								"< errorcode xmlns='http://tempuri.org' > 123 < /errorcode > < errorinfo xmlns='http://tempuri.org' > &lt;Force&gt; expected </errorinfo>");
				//soap->error=SOAP_TAG_MISMATCH;
				return -1;
			}

			//stateNameEnd Force


			continue; /* constLoopEnd2 /SensorConfiguration/sensors/Force/ */

		} /* case */
			//pop Force


		case 23://stateComplexBegin(Temperature)
		{
			label = 24; //assert 24==label+1

			/* lowerbound=0 upperbound=1*/

			if ((soap_element_begin_in(soap, "sens:Temperature", 0, NULL)
					== SOAP_OK)) { /* depth 2*/

				//push Temperature: next 26


				write_true(writer);
			} else {
				label = 26; //TODO: xx
				write_false(writer);
				continue;
			} /* Temperature */

			break;
		} /* stateEnd */

		case 24: //stateBegin(rate)
		{
			label = 25; //assert 25==label+1


			if (!(soap_element_begin_in(soap, "sens:rate", 0, NULL) == SOAP_OK)) {
				soap->error
						= soap_receiver_fault(
								soap,
								"tag name or namespace mismatch",
								"< errorcode xmlns='http://tempuri.org' > 123 < /errorcode > < errorinfo xmlns='http://tempuri.org' > &lt;rate&gt; expected </errorinfo>");
				// soap->error=SOAP_TAG_MISMATCH;
				return -1;
			}

			{

				{
					const char *str = soap_value(soap);

					str_write_int16_t(writer, str);

				}

				if (soap_element_end_in(soap, "sens:rate") != SOAP_OK) {
					soap->error
							= soap_receiver_fault(
									soap,
									"tag name or namespace mismatch",
									"< errorcode xmlns='http://tempuri.org' > 123 < /errorcode > < errorinfo xmlns='http://tempuri.org' > &lt;rate&gt; expected </errorinfo>");
					//soap->error=SOAP_TAG_MISMATCH;
					return -1;
				}

				//stateNameEnd rate


			}

			break;
		} /* stateEnd */

		case 25: //stateComplexEnd(Temperature) : 0..1
		{

			label = 23; /* constLoopEnd1 /SensorConfiguration/sensors/Temperature/ */

			if (soap_element_end_in(soap, "sens:Temperature") != SOAP_OK) {
				soap->error
						= soap_receiver_fault(
								soap,
								"tag name or namespace mismatch",
								"< errorcode xmlns='http://tempuri.org' > 123 < /errorcode > < errorinfo xmlns='http://tempuri.org' > &lt;Temperature&gt; expected </errorinfo>");
				//soap->error=SOAP_TAG_MISMATCH;
				return -1;
			}

			//stateNameEnd Temperature


			continue; /* constLoopEnd2 /SensorConfiguration/sensors/Temperature/ */

		} /* case */
			//pop Temperature


		case 26://stateComplexBegin(Voltage)
		{
			label = 27; //assert 27==label+1

			/* lowerbound=0 upperbound=1*/

			if ((soap_element_begin_in(soap, "sens:Voltage", 0, NULL)
					== SOAP_OK)) { /* depth 2*/

				//push Voltage: next 29


				write_true(writer);
			} else {
				label = 29; //TODO: xx
				write_false(writer);
				continue;
			} /* Voltage */

			break;
		} /* stateEnd */

		case 27: //stateBegin(rate)
		{
			label = 28; //assert 28==label+1


			if (!(soap_element_begin_in(soap, "sens:rate", 0, NULL) == SOAP_OK)) {
				soap->error
						= soap_receiver_fault(
								soap,
								"tag name or namespace mismatch",
								"< errorcode xmlns='http://tempuri.org' > 123 < /errorcode > < errorinfo xmlns='http://tempuri.org' > &lt;rate&gt; expected </errorinfo>");
				// soap->error=SOAP_TAG_MISMATCH;
				return -1;
			}

			{

				{
					const char *str = soap_value(soap);

					str_write_int16_t(writer, str);

				}

				if (soap_element_end_in(soap, "sens:rate") != SOAP_OK) {
					soap->error
							= soap_receiver_fault(
									soap,
									"tag name or namespace mismatch",
									"< errorcode xmlns='http://tempuri.org' > 123 < /errorcode > < errorinfo xmlns='http://tempuri.org' > &lt;rate&gt; expected </errorinfo>");
					//soap->error=SOAP_TAG_MISMATCH;
					return -1;
				}

				//stateNameEnd rate


			}

			break;
		} /* stateEnd */

		case 28: //stateComplexEnd(Voltage) : 0..1
		{

			label = 26; /* constLoopEnd1 /SensorConfiguration/sensors/Voltage/ */

			if (soap_element_end_in(soap, "sens:Voltage") != SOAP_OK) {
				soap->error
						= soap_receiver_fault(
								soap,
								"tag name or namespace mismatch",
								"< errorcode xmlns='http://tempuri.org' > 123 < /errorcode > < errorinfo xmlns='http://tempuri.org' > &lt;Voltage&gt; expected </errorinfo>");
				//soap->error=SOAP_TAG_MISMATCH;
				return -1;
			}

			//stateNameEnd Voltage


			continue; /* constLoopEnd2 /SensorConfiguration/sensors/Voltage/ */

		} /* case */
			//pop Voltage


		case 29: //stateComplexEnd(sensors) : 1..1
		{

			label = 30; // Complex End


			if (soap_element_end_in(soap, "sens:sensors") != SOAP_OK) {
				soap->error
						= soap_receiver_fault(
								soap,
								"tag name or namespace mismatch",
								"< errorcode xmlns='http://tempuri.org' > 123 < /errorcode > < errorinfo xmlns='http://tempuri.org' > &lt;sensors&gt; expected </errorinfo>");
				//soap->error=SOAP_TAG_MISMATCH;
				return -1;
			}

			//stateNameEnd sensors


			break;

		} /* case */
			//pop sensors


		case 30: //stateComplexEnd(SensorConfiguration) : 1..1
		{

			label = 31; // Complex End


			if (soap_element_end_in(soap, "sens:SensorConfiguration")
					!= SOAP_OK) {
				soap->error
						= soap_receiver_fault(
								soap,
								"tag name or namespace mismatch",
								"< errorcode xmlns='http://tempuri.org' > 123 < /errorcode > < errorinfo xmlns='http://tempuri.org' > &lt;SensorConfiguration&gt; expected </errorinfo>");
				//soap->error=SOAP_TAG_MISMATCH;
				return -1;
			}

			//stateNameEnd SensorConfiguration


			break;

		} /* case */
			//pop SensorConfiguration


		default: //StopState


			return 0;

		}
	}

	return 1;
}

