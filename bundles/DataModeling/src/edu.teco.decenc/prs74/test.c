/* ========================================================================
 *
 *        Filename   : test.c
 *        Description:
 *        Version    : 1.0
 *        (C)        : Dimitar Yordanov (domidimi [at] gmail [dot] com)
 *        ---- History ---------------------------------------------------
 *        05.04.2008 21:43    dy    initial version
 * ========================================================================
 */

#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <stdint.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <string.h>
#include <errno.h>

#include "../types.h"
#include "../bitsio/bits_io.h"
#include "../bitsio/write_bits.h"
#include "../dec/decoder_send.h"
#include "decoder_set.h"

static void sendSensorValues(void);

/* ========================================================================
 *
 *
 * ========================================================================*/
int main(void)
{
	int fd;
	fd = open("output.bin",
			  O_WRONLY | O_CREAT | O_TRUNC,
			  S_IRWXU | S_IRWXG);
	if (fd == -1)
	{
		fprintf(stderr, "Error opening output file\n");
		return (-1);
	}

	write_init(fd);
	sendSensorValues();
	write_finish();

	return 0;
}

/* ========================================================================
 *
 *
 * ========================================================================*/
static void sendSensorValues(void)
{
	struct SensorValues *sensorVals = NULL;
	struct AcclX        *acclX;
	struct AcclY        *acclY;
	struct AcclZ        *acclZ;
	struct Light        *light;
	struct AmbientLight *aLight;
	struct Force        *force;
	struct Temperature  *temp;

	sensorVals = get_SensorValues();

	acclX = SensorValues_get_AcclX(sensorVals);
	AcclX_set_AcclValue(acclX, 50);
	AcclX_set_AcclIdx(acclX, 0);


	acclY = SensorValues_get_AcclY(sensorVals);
	AcclY_set_AcclValue(acclY, 60);
	AcclY_set_AcclIdx(acclY, 0);

	acclZ = SensorValues_get_AcclZ(sensorVals);
	AcclZ_set_AcclValue(acclZ, 60);
	AcclZ_set_AcclIdx(acclZ, 0);

	SensorValues_set_Audio(sensorVals, 40);

	light = SensorValues_get_Light(sensorVals);
	Light_set_LightValue(light, 200);
	Light_set_LightIdx(light, 0);

	aLight = SensorValues_get_AmbientLight(sensorVals);
	AmbientLight_set_LightValue(aLight, 30);
	AmbientLight_set_LightIdx(aLight, 0);

	force = SensorValues_get_Force(sensorVals);
    Force_set_ForceValue(force, 67);
    Force_set_ForceIdx(force, 0);

    temp = SensorValues_get_Temperature(sensorVals);
    Temperature_set_TempValue(temp, 22);
    Temperature_set_TempIdx(temp, 0);

    SensorValues_set_Voltage(sensorVals, 40);


    send_data(NULL, 0);
}
