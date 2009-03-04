#ifndef _DECODER_SET_H
#define _DECODER_SET_H
struct SensorValues * get_SensorValues(void);

struct AcclX * SensorValues_getNext_AcclX(struct SensorValues *parent);

void AcclX_set_AcclValue(struct AcclX *parent, int16_t value);

void AcclX_set_AcclIdx(struct AcclX *parent, uint8_t value);

struct AcclY * SensorValues_getNext_AcclY(struct SensorValues *parent);

void AcclY_set_AcclValue(struct AcclY *parent, int16_t value);

void AcclY_set_AcclIdx(struct AcclY *parent, uint8_t value);

struct AcclZ * SensorValues_getNext_AcclZ(struct SensorValues *parent);

void AcclZ_set_AcclValue(struct AcclZ *parent, int16_t value);

void AcclZ_set_AcclIdx(struct AcclZ *parent, uint8_t value);

void SensorValues_set_Audio(struct SensorValues *parent, uint8_t value);

struct Light * SensorValues_getNext_Light(struct SensorValues *parent);

void Light_set_LightValue(struct Light *parent, uint8_t value);

void Light_set_LightIdx(struct Light *parent, uint8_t value);

struct AmbientLight * SensorValues_getNext_AmbientLight(
		struct SensorValues *parent);

void AmbientLight_set_LightValue(struct AmbientLight *parent, uint8_t value);

void AmbientLight_set_LightIdx(struct AmbientLight *parent, uint8_t value);

struct Force * SensorValues_getNext_Force(struct SensorValues *parent);

void Force_set_ForceValue(struct Force *parent, uint8_t value);

void Force_set_ForceIdx(struct Force *parent, uint8_t value);

struct Temperature * SensorValues_getNext_Temperature(
		struct SensorValues *parent);

void Temperature_set_TempValue(struct Temperature *parent, int8_t value);

void Temperature_set_TempIdx(struct Temperature *parent, uint8_t value);

void SensorValues_set_Voltage(struct SensorValues *parent, uint16_t value);

#endif

