#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>

#include "../bitsio/bits_io.h"
#include "decode_func.h"
#include "decoder_struct.h"
#include "decoder_set.h"

static struct SensorValues SensorValues_struct;

struct SensorValues * get_SensorValues(void) {
	return &SensorValues_struct;
}

struct AcclX * SensorValues_get_AcclX(struct SensorValues *parent) {
	parent->AcclX_count = 1;
	return &(parent->AcclX_elem);
}

void AcclX_set_AcclValue(struct AcclX *parent, int16_t value) {

	decode_short(value, (u_char *) &(parent->AcclValue), 0, 0, 0);

}

void AcclX_set_AcclIdx(struct AcclX *parent, uint8_t value) {

	decode_byte(value, (u_char *) &(parent->AcclIdx), 0, 255, 0);

}

struct AcclY * SensorValues_get_AcclY(struct SensorValues *parent) {
	parent->AcclY_count = 1;
	return &(parent->AcclY_elem);
}

void AcclY_set_AcclValue(struct AcclY *parent, int16_t value) {

	decode_short(value, (u_char *) &(parent->AcclValue), 0, 0, 0);

}

void AcclY_set_AcclIdx(struct AcclY *parent, uint8_t value) {

	decode_byte(value, (u_char *) &(parent->AcclIdx), 0, 255, 0);

}

struct AcclZ * SensorValues_get_AcclZ(struct SensorValues *parent) {
	parent->AcclZ_count = 1;
	return &(parent->AcclZ_elem);
}

void AcclZ_set_AcclValue(struct AcclZ *parent, int16_t value) {

	decode_short(value, (u_char *) &(parent->AcclValue), 0, 0, 0);

}

void AcclZ_set_AcclIdx(struct AcclZ *parent, uint8_t value) {

	decode_byte(value, (u_char *) &(parent->AcclIdx), 0, 255, 0);

}

void SensorValues_set_Audio(struct SensorValues *parent, uint8_t value) {

	parent->Audio_count = 1;

	decode_byte(value, (u_char *) &(parent->Audio), 0, 255, 0);

}

struct Light * SensorValues_get_Light(struct SensorValues *parent) {
	parent->Light_count = 1;
	return &(parent->Light_elem);
}

void Light_set_LightValue(struct Light *parent, uint8_t value) {

	decode_byte(value, (u_char *) &(parent->LightValue), 0, 255, 0);

}

void Light_set_LightIdx(struct Light *parent, uint8_t value) {

	decode_byte(value, (u_char *) &(parent->LightIdx), 0, 255, 0);

}

struct AmbientLight * SensorValues_get_AmbientLight(struct SensorValues *parent) {
	parent->AmbientLight_count = 1;
	return &(parent->AmbientLight_elem);
}

void AmbientLight_set_LightValue(struct AmbientLight *parent, uint8_t value) {

	decode_byte(value, (u_char *) &(parent->LightValue), 0, 255, 0);

}

void AmbientLight_set_LightIdx(struct AmbientLight *parent, uint8_t value) {

	decode_byte(value, (u_char *) &(parent->LightIdx), 0, 255, 0);

}

struct Force * SensorValues_get_Force(struct SensorValues *parent) {
	parent->Force_count = 1;
	return &(parent->Force_elem);
}

void Force_set_ForceValue(struct Force *parent, uint8_t value) {

	decode_byte(value, (u_char *) &(parent->ForceValue), 0, 255, 0);

}

void Force_set_ForceIdx(struct Force *parent, uint8_t value) {

	decode_byte(value, (u_char *) &(parent->ForceIdx), 0, 255, 0);

}

struct Temperature * SensorValues_get_Temperature(struct SensorValues *parent) {
	parent->Temperature_count = 1;
	return &(parent->Temperature_elem);
}

void Temperature_set_TempValue(struct Temperature *parent, int8_t value) {

	decode_byte(value, (u_char *) &(parent->TempValue), 0, 0, 0);

}

void Temperature_set_TempIdx(struct Temperature *parent, uint8_t value) {

	decode_byte(value, (u_char *) &(parent->TempIdx), 0, 255, 0);

}

void SensorValues_set_Voltage(struct SensorValues *parent, uint16_t value) {

	parent->Voltage_count = 1;

	decode_short(value, (u_char *) &(parent->Voltage), 0, 65535, 0);

}

