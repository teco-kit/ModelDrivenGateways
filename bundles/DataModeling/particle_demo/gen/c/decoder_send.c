#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "../bitsio/bits_io.h"
#include "decoder_struct.h"
#include "decoder_set.h"

ssize_t send_data(u_char * send_buffer, size_t max_size) {
	ssize_t bytes_to_send;
	int AcclX_it;
	int AcclY_it;
	int AcclZ_it;
	int Audio_it;
	int Light_it;
	int AmbientLight_it;
	int Force_it;
	int Temperature_it;
	int Voltage_it;

	struct SensorValues *SensorValues_elem;
	SensorValues_elem = get_SensorValues();
	bytes_to_send = 0;
	if (send_buffer)
		write_init(send_buffer);

	for (AcclX_it = 0; AcclX_it < SensorValues_elem->AcclX_count; AcclX_it++) {
		bytes_to_send += write_true();

		bytes_to_send += write_bits(
				(u_char*) &SensorValues_elem->AcclX_elem.AcclValue, 16);

		bytes_to_send += write_bits(
				(u_char*) &SensorValues_elem->AcclX_elem.AcclIdx, 8);

	}

	bytes_to_send += write_false();

	SensorValues_elem->AcclX_count = 0;

	for (AcclY_it = 0; AcclY_it < SensorValues_elem->AcclY_count; AcclY_it++) {
		bytes_to_send += write_true();

		bytes_to_send += write_bits(
				(u_char*) &SensorValues_elem->AcclY_elem.AcclValue, 16);

		bytes_to_send += write_bits(
				(u_char*) &SensorValues_elem->AcclY_elem.AcclIdx, 8);

	}

	bytes_to_send += write_false();

	SensorValues_elem->AcclY_count = 0;

	for (AcclZ_it = 0; AcclZ_it < SensorValues_elem->AcclZ_count; AcclZ_it++) {
		bytes_to_send += write_true();

		bytes_to_send += write_bits(
				(u_char*) &SensorValues_elem->AcclZ_elem.AcclValue, 16);

		bytes_to_send += write_bits(
				(u_char*) &SensorValues_elem->AcclZ_elem.AcclIdx, 8);

	}

	bytes_to_send += write_false();

	SensorValues_elem->AcclZ_count = 0;

	for (Audio_it = 0; Audio_it < SensorValues_elem->Audio_count; Audio_it++) {
		bytes_to_send += write_true();

		bytes_to_send += write_bits((u_char*) &SensorValues_elem->Audio, 8);

	}
	bytes_to_send += write_false();
	SensorValues_elem->Audio_count = 0;

	for (Light_it = 0; Light_it < SensorValues_elem->Light_count; Light_it++) {
		bytes_to_send += write_true();

		bytes_to_send += write_bits(
				(u_char*) &SensorValues_elem->Light_elem.LightValue, 8);

		bytes_to_send += write_bits(
				(u_char*) &SensorValues_elem->Light_elem.LightIdx, 8);

	}

	bytes_to_send += write_false();

	SensorValues_elem->Light_count = 0;

	for (AmbientLight_it = 0; AmbientLight_it
			< SensorValues_elem->AmbientLight_count; AmbientLight_it++) {
		bytes_to_send += write_true();

		bytes_to_send += write_bits(
				(u_char*) &SensorValues_elem->AmbientLight_elem.LightValue, 8);

		bytes_to_send += write_bits(
				(u_char*) &SensorValues_elem->AmbientLight_elem.LightIdx, 8);

	}

	bytes_to_send += write_false();

	SensorValues_elem->AmbientLight_count = 0;

	for (Force_it = 0; Force_it < SensorValues_elem->Force_count; Force_it++) {
		bytes_to_send += write_true();

		bytes_to_send += write_bits(
				(u_char*) &SensorValues_elem->Force_elem.ForceValue, 8);

		bytes_to_send += write_bits(
				(u_char*) &SensorValues_elem->Force_elem.ForceIdx, 8);

	}

	bytes_to_send += write_false();

	SensorValues_elem->Force_count = 0;

	for (Temperature_it = 0; Temperature_it
			< SensorValues_elem->Temperature_count; Temperature_it++) {
		bytes_to_send += write_true();

		bytes_to_send += write_bits(
				(u_char*) &SensorValues_elem->Temperature_elem.TempValue, 8);

		bytes_to_send += write_bits(
				(u_char*) &SensorValues_elem->Temperature_elem.TempIdx, 8);

	}

	bytes_to_send += write_false();

	SensorValues_elem->Temperature_count = 0;

	for (Voltage_it = 0; Voltage_it < SensorValues_elem->Voltage_count; Voltage_it++) {
		bytes_to_send += write_true();

		bytes_to_send += write_bits((u_char*) &SensorValues_elem->Voltage, 16);

	}
	bytes_to_send += write_false();
	SensorValues_elem->Voltage_count = 0;

	bytes_to_send += write_finish();

	return bytes_to_send;

}

