#ifndef _DECODER_STRUCT_H_
#define _DECODER_STRUCT_H_

struct Temperature {

	int8_t TempValue;

	uint8_t TempIdx;

};

struct Force {

	uint8_t ForceValue;

	uint8_t ForceIdx;

};

struct AmbientLight {

	uint8_t LightValue;

	uint8_t LightIdx;

};

struct Light {

	uint8_t LightValue;

	uint8_t LightIdx;

};

struct AcclZ {

	int16_t AcclValue;

	uint8_t AcclIdx;

};

struct AcclY {

	int16_t AcclValue;

	uint8_t AcclIdx;

};

struct AcclX {

	int16_t AcclValue;

	uint8_t AcclIdx;

};

struct SensorValues {
	struct AcclX AcclX_elem;
	size_t AcclX_count;

	struct AcclY AcclY_elem;
	size_t AcclY_count;

	struct AcclZ AcclZ_elem;
	size_t AcclZ_count;

	uint8_t Audio;

	size_t Audio_count;

	struct Light Light_elem;
	size_t Light_count;

	struct AmbientLight AmbientLight_elem;
	size_t AmbientLight_count;

	struct Force Force_elem;
	size_t Force_count;

	struct Temperature Temperature_elem;
	size_t Temperature_count;

	uint16_t Voltage;

	size_t Voltage_count;

};
#endif
