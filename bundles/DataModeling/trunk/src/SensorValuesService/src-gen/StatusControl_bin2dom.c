
#include "StatusControl_bin2dom.h"
static int dom_automata(struct READER_STRUCT *reader, void **stack, int *label);

int StatusControl_bin2dom_run(struct READER_STRUCT *reader,
		sens_SSimpControl *dom) {
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


		case 0://stateComplexBegin(StatusControl)
		{
			label = 1; //assert 1==label+1

			/* lowerbound=1 upperbound=1*/

			//push(pop()): preinitialized stack


			//push StatusControl: next 


			break;
		} /* stateEnd */

		case 1://stateComplexBegin(SensorConfig)
		{
			label = 2; //assert 2==label+1

			/* lowerbound=0 upperbound=1*/

			if (read_bit(reader)) { /* depth 1*/

				push(sens_SSimpControl_add_sensorConfig(((sens_SSimpControl *)top())));

				//push SensorConfig: next 21


			} else {
				label = 21;
				continue;
			} /* SensorConfig */

			break;
		} /* stateEnd */

		case 2://stateComplexBegin(Acceleration)
		{
			label = 3; //assert 3==label+1

			/* lowerbound=0 upperbound=1*/

			if (read_bit(reader)) { /* depth 2*/

				push(sens_SensorConfigurationType_add_acceleration(((sens_SensorConfigurationType *)top())));

				//push Acceleration: next 5


			} else {
				label = 5;
				continue;
			} /* Acceleration */

			break;
		} /* stateEnd */

		case 3: //stateBegin(rate)
		{
			label = 4; //assert 4==label+1


			{

				push(&(((sens_SSimpRateConfig*)top())->rate));

				{
					uint8_t c;
					read_bits(reader, (u_char *) &c, 8);
					*(int8_t*) ((sens_Byte*) top()) = (int8_t)(((c) * 1)
							+ (-128));
				}

				pop();

			}

			break;
		} /* stateEnd */

		case 4: //stateComplexEnd(Acceleration) : 0..1
		{

			label = 2; /* constLoopEnd1 /StatusControl/SensorConfig/Acceleration/ */

			pop();

			continue; /* constLoopEnd2 /StatusControl/SensorConfig/Acceleration/ */

		} /* case */
			//pop Acceleration


		case 5://stateComplexBegin(Audio)
		{
			label = 6; //assert 6==label+1

			/* lowerbound=0 upperbound=1*/

			if (read_bit(reader)) { /* depth 2*/

				push(sens_SensorConfigurationType_add_audio(((sens_SensorConfigurationType *)top())));

				//push Audio: next 8


			} else {
				label = 8;
				continue;
			} /* Audio */

			break;
		} /* stateEnd */

		case 6: //stateBegin(rate)
		{
			label = 7; //assert 7==label+1


			{

				push(&(((sens_SSimpRateConfig*)top())->rate));

				{
					uint8_t c;
					read_bits(reader, (u_char *) &c, 8);
					*(int8_t*) ((sens_Byte*) top()) = (int8_t)(((c) * 1)
							+ (-128));
				}

				pop();

			}

			break;
		} /* stateEnd */

		case 7: //stateComplexEnd(Audio) : 0..1
		{

			label = 5; /* constLoopEnd1 /StatusControl/SensorConfig/Audio/ */

			pop();

			continue; /* constLoopEnd2 /StatusControl/SensorConfig/Audio/ */

		} /* case */
			//pop Audio


		case 8://stateComplexBegin(Light)
		{
			label = 9; //assert 9==label+1

			/* lowerbound=0 upperbound=1*/

			if (read_bit(reader)) { /* depth 2*/

				push(sens_SensorConfigurationType_add_light(((sens_SensorConfigurationType *)top())));

				//push Light: next 11


			} else {
				label = 11;
				continue;
			} /* Light */

			break;
		} /* stateEnd */

		case 9: //stateBegin(rate)
		{
			label = 10; //assert 10==label+1


			{

				push(&(((sens_SSimpRateConfig*)top())->rate));

				{
					uint8_t c;
					read_bits(reader, (u_char *) &c, 8);
					*(int8_t*) ((sens_Byte*) top()) = (int8_t)(((c) * 1)
							+ (-128));
				}

				pop();

			}

			break;
		} /* stateEnd */

		case 10: //stateComplexEnd(Light) : 0..1
		{

			label = 8; /* constLoopEnd1 /StatusControl/SensorConfig/Light/ */

			pop();

			continue; /* constLoopEnd2 /StatusControl/SensorConfig/Light/ */

		} /* case */
			//pop Light


		case 11://stateComplexBegin(AmbientLight)
		{
			label = 12; //assert 12==label+1

			/* lowerbound=0 upperbound=1*/

			if (read_bit(reader)) { /* depth 2*/

				push(sens_SensorConfigurationType_add_ambientLight(((sens_SensorConfigurationType *)top())));

				//push AmbientLight: next 14


			} else {
				label = 14;
				continue;
			} /* AmbientLight */

			break;
		} /* stateEnd */

		case 12: //stateBegin(rate)
		{
			label = 13; //assert 13==label+1


			{

				push(&(((sens_SSimpRateConfig*)top())->rate));

				{
					uint8_t c;
					read_bits(reader, (u_char *) &c, 8);
					*(int8_t*) ((sens_Byte*) top()) = (int8_t)(((c) * 1)
							+ (-128));
				}

				pop();

			}

			break;
		} /* stateEnd */

		case 13: //stateComplexEnd(AmbientLight) : 0..1
		{

			label = 11; /* constLoopEnd1 /StatusControl/SensorConfig/AmbientLight/ */

			pop();

			continue; /* constLoopEnd2 /StatusControl/SensorConfig/AmbientLight/ */

		} /* case */
			//pop AmbientLight


		case 14://stateComplexBegin(Force)
		{
			label = 15; //assert 15==label+1

			/* lowerbound=0 upperbound=1*/

			if (read_bit(reader)) { /* depth 2*/

				push(sens_SensorConfigurationType_add_force(((sens_SensorConfigurationType *)top())));

				//push Force: next 17


			} else {
				label = 17;
				continue;
			} /* Force */

			break;
		} /* stateEnd */

		case 15: //stateBegin(rate)
		{
			label = 16; //assert 16==label+1


			{

				push(&(((sens_SSimpRateConfig*)top())->rate));

				{
					uint8_t c;
					read_bits(reader, (u_char *) &c, 8);
					*(int8_t*) ((sens_Byte*) top()) = (int8_t)(((c) * 1)
							+ (-128));
				}

				pop();

			}

			break;
		} /* stateEnd */

		case 16: //stateComplexEnd(Force) : 0..1
		{

			label = 14; /* constLoopEnd1 /StatusControl/SensorConfig/Force/ */

			pop();

			continue; /* constLoopEnd2 /StatusControl/SensorConfig/Force/ */

		} /* case */
			//pop Force


		case 17://stateComplexBegin(Temperature)
		{
			label = 18; //assert 18==label+1

			/* lowerbound=0 upperbound=1*/

			if (read_bit(reader)) { /* depth 2*/

				push(sens_SensorConfigurationType_add_temperature(((sens_SensorConfigurationType *)top())));

				//push Temperature: next 20


			} else {
				label = 20;
				continue;
			} /* Temperature */

			break;
		} /* stateEnd */

		case 18: //stateBegin(rate)
		{
			label = 19; //assert 19==label+1


			{

				push(&(((sens_SSimpRateConfig*)top())->rate));

				{
					uint8_t c;
					read_bits(reader, (u_char *) &c, 8);
					*(int8_t*) ((sens_Byte*) top()) = (int8_t)(((c) * 1)
							+ (-128));
				}

				pop();

			}

			break;
		} /* stateEnd */

		case 19: //stateComplexEnd(Temperature) : 0..1
		{

			label = 17; /* constLoopEnd1 /StatusControl/SensorConfig/Temperature/ */

			pop();

			continue; /* constLoopEnd2 /StatusControl/SensorConfig/Temperature/ */

		} /* case */
			//pop Temperature


		case 20: //stateComplexEnd(SensorConfig) : 0..1
		{

			label = 1; /* constLoopEnd1 /StatusControl/SensorConfig/ */

			pop();

			continue; /* constLoopEnd2 /StatusControl/SensorConfig/ */

		} /* case */
			//pop SensorConfig


		case 21: //stateBegin(NewTime)
		{
			label = 22; //assert 22==label+1


			/* loop /StatusControlNewTime label=label */
			while (read_bit(reader))

			{

				push(sens_SSimpControl_add_newTime(((sens_SSimpControl *)top())));

				{
					uint32_t c;
					read_bits(reader, (u_char *) &c, 32);

					struct timeval t = { c, 0 };
					memcpy(((sens_DateTime*) top()), &t, sizeof(t));
				}

				pop();

			} /* NewTime */

			break;
		} /* stateEnd */

		case 22: //stateComplexEnd(StatusControl) : 1..1
		{

			label = 23; // Complex End


			pop();

			break;

		} /* case */
			//pop StatusControl


		default: //StopState


			return 0;

		}
	}

	return 1;
}

