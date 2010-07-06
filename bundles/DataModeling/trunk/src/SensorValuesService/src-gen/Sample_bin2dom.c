
#include "Sample_bin2dom.h"
static int dom_automata(struct READER_STRUCT *reader, void **stack, int *label);

int Sample_bin2dom_run(struct READER_STRUCT *reader, sens_SSimpSample *dom) {
	int ret;
	int label = 0;
	void * stack[20] = { dom };
	memset(dom, 0, sizeof(*dom));
	while ((ret = dom_automata(reader, stack, &label)) > 0)
		;

	return ret;
}

static void *stack_pop(void ***stack) {
	void *ret = *(*stack);
	**stack = 0;
	(*stack)--;
	return ret;
}

#define pop() stack_pop(&stack)

static void *stack_push(void ***stack, void * X) {
	return *(++(*stack)) = X;
}
#define push(X) stack_push(&stack,X)

#define top() *stack

// return:  0 if EOF, 0<for fault, and  read bits else (TODO)
static int dom_automata(struct READER_STRUCT *reader, void **stack, int *_label) {
#define label (*_label)

	while (1) {
		switch (label) {

		// Start State


		case 0://stateComplexBegin(Sample)
		{
			label = 1; //assert 1==label+1

			/* lowerbound=1 upperbound=1*/

			//push(pop()): preinitialized stack


			//push Sample: next 


			break;
		} /* stateEnd */

		case 1: //stateBegin(TimeStamp)
		{
			label = 2; //assert 2==label+1


			/* loop /SampleTimeStamp label=label */
			while (read_bit(reader))

			{

				push(sens_SSimpSample_add_timeStamp(((sens_SSimpSample *)top())));

				{
					uint32_t c;
					read_bits(reader, (u_char *) &c, 32);

					struct timeval t = { c, 0 };
					memcpy(((sens_DateTime*) top()), &t, sizeof(t));
				}

				pop();

			} /* TimeStamp */

			break;
		} /* stateEnd */

		case 2://stateComplexBegin(Accelleration)
		{
			label = 3; //assert 3==label+1

			/* lowerbound=0 upperbound=1*/

			if (read_bit(reader)) { /* depth 1*/

				push(sens_SSimpSample_add_accelleration(((sens_SSimpSample *)top())));

				//push Accelleration: next 8


			} else {
				label = 8;
				continue;
			} /* Accelleration */

			break;
		} /* stateEnd */

		case 3: //stateBegin(x)
		{
			label = 4; //assert 4==label+1


			{

				push(&(((sens_ADXL210Sample*)top())->x));

				{
					uint16_t c;
					read_bits(reader, (u_char *) &c, 16);
					*(int16_t*) ((sens_XType*) top()) = (int16_t)(((c) * 1)
							+ (-32768));
				}

				pop();

			}

			break;
		} /* stateEnd */

		case 4: //stateBegin(y)
		{
			label = 5; //assert 5==label+1


			{

				push(&(((sens_ADXL210Sample*)top())->y));

				{
					uint16_t c;
					read_bits(reader, (u_char *) &c, 16);
					*(int16_t*) ((sens_YType*) top()) = (int16_t)(((c) * 1)
							+ (-32768));
				}

				pop();

			}

			break;
		} /* stateEnd */

		case 5: //stateBegin(z)
		{
			label = 6; //assert 6==label+1


			{

				push(&(((sens_ADXL210Sample*)top())->z));

				{
					uint16_t c;
					read_bits(reader, (u_char *) &c, 16);
					*(int16_t*) ((sens_ZType*) top()) = (int16_t)(((c) * 1)
							+ (-32768));
				}

				pop();

			}

			break;
		} /* stateEnd */

		case 6: //stateBegin(index)
		{
			label = 7; //assert 7==label+1


			/* loop /Sample/Accellerationindex label=label */
			while (read_bit(reader))

			{

				push(sens_ADXL210Sample_add_index(((sens_ADXL210Sample *)top())));

				{
					uint8_t c;
					read_bits(reader, (u_char *) &c, 8);
					*(int8_t*) ((sens_Byte*) top()) = (int8_t)(((c) * 1)
							+ (-128));
				}

				pop();

			} /* index */

			break;
		} /* stateEnd */

		case 7: //stateComplexEnd(Accelleration) : 0..1
		{

			label = 2; /* constLoopEnd1 /Sample/Accelleration/ */

			pop();

			continue; /* constLoopEnd2 /Sample/Accelleration/ */

		} /* case */
			//pop Accelleration


		case 8://stateComplexBegin(Audio)
		{
			label = 9; //assert 9==label+1

			/* lowerbound=0 upperbound=1*/

			if (read_bit(reader)) { /* depth 1*/

				push(sens_SSimpSample_add_audio(((sens_SSimpSample *)top())));

				//push Audio: next 12


			} else {
				label = 12;
				continue;
			} /* Audio */

			break;
		} /* stateEnd */

		case 9: //stateBegin(volume)
		{
			label = 10; //assert 10==label+1


			{

				push(&(((sens_SP101Sample*)top())->volume));

				{
					uint8_t c;
					read_bits(reader, (u_char *) &c, 8);
					*(uint8_t*) ((sens_UnsignedByte*) top())
							= (((c) * 1) + (0));
				}

				pop();

			}

			break;
		} /* stateEnd */

		case 10: //stateBegin(index)
		{
			label = 11; //assert 11==label+1


			/* loop /Sample/Audioindex label=label */
			while (read_bit(reader))

			{

				push(sens_SP101Sample_add_index(((sens_SP101Sample *)top())));

				{
					uint8_t c;
					read_bits(reader, (u_char *) &c, 8);
					*(int8_t*) ((sens_Byte*) top()) = (int8_t)(((c) * 1)
							+ (-128));
				}

				pop();

			} /* index */

			break;
		} /* stateEnd */

		case 11: //stateComplexEnd(Audio) : 0..1
		{

			label = 8; /* constLoopEnd1 /Sample/Audio/ */

			pop();

			continue; /* constLoopEnd2 /Sample/Audio/ */

		} /* case */
			//pop Audio


		case 12://stateComplexBegin(Light)
		{
			label = 13; //assert 13==label+1

			/* lowerbound=0 upperbound=1*/

			if (read_bit(reader)) { /* depth 1*/

				push(sens_SSimpSample_add_light(((sens_SSimpSample *)top())));

				//push Light: next 17


			} else {
				label = 17;
				continue;
			} /* Light */

			break;
		} /* stateEnd */

		case 13: //stateBegin(daylight)
		{
			label = 14; //assert 14==label+1


			{

				push(&(((sens_TSL2550Sample*)top())->daylight));

				{
					uint16_t c;
					read_bits(reader, (u_char *) &c, 16);
					*(uint16_t*) ((sens_UnsignedShort*) top()) = (((c) * 1)
							+ (0));
				}

				pop();

			}

			break;
		} /* stateEnd */

		case 14: //stateBegin(infrared)
		{
			label = 15; //assert 15==label+1


			{

				push(&(((sens_TSL2550Sample*)top())->infrared));

				{
					uint16_t c;
					read_bits(reader, (u_char *) &c, 16);
					*(uint16_t*) ((sens_UnsignedShort*) top()) = (((c) * 1)
							+ (0));
				}

				pop();

			}

			break;
		} /* stateEnd */

		case 15: //stateBegin(index)
		{
			label = 16; //assert 16==label+1


			/* loop /Sample/Lightindex label=label */
			while (read_bit(reader))

			{

				push(sens_TSL2550Sample_add_index(((sens_TSL2550Sample *)top())));

				{
					uint8_t c;
					read_bits(reader, (u_char *) &c, 8);
					*(int8_t*) ((sens_Byte*) top()) = (int8_t)(((c) * 1)
							+ (-128));
				}

				pop();

			} /* index */

			break;
		} /* stateEnd */

		case 16: //stateComplexEnd(Light) : 0..1
		{

			label = 12; /* constLoopEnd1 /Sample/Light/ */

			pop();

			continue; /* constLoopEnd2 /Sample/Light/ */

		} /* case */
			//pop Light


		case 17://stateComplexBegin(Force)
		{
			label = 18; //assert 18==label+1

			/* lowerbound=0 upperbound=1*/

			if (read_bit(reader)) { /* depth 1*/

				push(sens_SSimpSample_add_force(((sens_SSimpSample *)top())));

				//push Force: next 21


			} else {
				label = 21;
				continue;
			} /* Force */

			break;
		} /* stateEnd */

		case 18: //stateBegin(value)
		{
			label = 19; //assert 19==label+1


			{

				push(&(((sens_FSR152Sample*)top())->value));

				{
					uint8_t c;
					read_bits(reader, (u_char *) &c, 8);
					*(uint8_t*) ((sens_UnsignedByte*) top())
							= (((c) * 1) + (0));
				}

				pop();

			}

			break;
		} /* stateEnd */

		case 19: //stateBegin(index)
		{
			label = 20; //assert 20==label+1


			/* loop /Sample/Forceindex label=label */
			while (read_bit(reader))

			{

				push(sens_FSR152Sample_add_index(((sens_FSR152Sample *)top())));

				{
					uint8_t c;
					read_bits(reader, (u_char *) &c, 8);
					*(int8_t*) ((sens_Byte*) top()) = (int8_t)(((c) * 1)
							+ (-128));
				}

				pop();

			} /* index */

			break;
		} /* stateEnd */

		case 20: //stateComplexEnd(Force) : 0..1
		{

			label = 17; /* constLoopEnd1 /Sample/Force/ */

			pop();

			continue; /* constLoopEnd2 /Sample/Force/ */

		} /* case */
			//pop Force


		case 21://stateComplexBegin(Temperature)
		{
			label = 22; //assert 22==label+1

			/* lowerbound=0 upperbound=1*/

			if (read_bit(reader)) { /* depth 1*/

				push(sens_SSimpSample_add_temperature(((sens_SSimpSample *)top())));

				//push Temperature: next 25


			} else {
				label = 25;
				continue;
			} /* Temperature */

			break;
		} /* stateEnd */

		case 22: //stateBegin(value)
		{
			label = 23; //assert 23==label+1


			{

				push(&(((sens_TC74Sample*)top())->value));

				{
					uint8_t c;
					read_bits(reader, (u_char *) &c, 8);
					*(int8_t*) ((sens_ValueType*) top()) = (int8_t)(((c) * 1)
							+ (-128));
				}

				pop();

			}

			break;
		} /* stateEnd */

		case 23: //stateBegin(index)
		{
			label = 24; //assert 24==label+1


			/* loop /Sample/Temperatureindex label=label */
			while (read_bit(reader))

			{

				push(sens_TC74Sample_add_index(((sens_TC74Sample *)top())));

				{
					uint8_t c;
					read_bits(reader, (u_char *) &c, 8);
					*(int8_t*) ((sens_Byte*) top()) = (int8_t)(((c) * 1)
							+ (-128));
				}

				pop();

			} /* index */

			break;
		} /* stateEnd */

		case 24: //stateComplexEnd(Temperature) : 0..1
		{

			label = 21; /* constLoopEnd1 /Sample/Temperature/ */

			pop();

			continue; /* constLoopEnd2 /Sample/Temperature/ */

		} /* case */
			//pop Temperature


		case 25: //stateComplexEnd(Sample) : 1..1
		{

			label = 26; // Complex End


			pop();

			break;

		} /* case */
			//pop Sample


		default: //StopState


			return 0;

		}
	}

	return 1;
}

