
#ifndef ACCELERATION_OPERATIONS_H
#define ACCELERATION_OPERATIONS_H
enum Acceleration_operations {
	OP_GetAccelerationValuesEvent
};

typedef struct {
	float values[8];
	float timertick;
	float delta;
} ldcmessage;
#endif //ACCELERATION_OPERATIONS_H
