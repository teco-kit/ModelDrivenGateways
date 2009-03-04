/* Generated file */
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <limits.h>
#include <errno.h>

#include "../bitsio/bits_io.h"
#include "read_str_func.h"

static int out;
static int label = 0;
static void *reader;

void encoder_automata_init(int _out, void * _reader) {
	out = _out;
	reader = _reader;
	label = 0;
}

void encoder_automata_run() {
	int ret = 0;
	char *str = NULL;
	while (1) {
		switch (label) {

		case 0: {

			write(out, "<SensorValues>\n", 15);

		} /* stateEnd */

		case 1: {

			if (read_bit(reader)) { /* depth 1*/

				write(out, "<AcclX>\n", 8);

			} else {
				label = 5;
				continue;
			} /* AcclX */

		} /* stateEnd */

		case 2: {

			write(out, "<AcclValue>\n", 12);

			ret = str_read_short(&str, 0, 0, reader);

			if (ret < 0) {
				fprintf(stderr, "Error reading input data. Errno: %s\n",
						strerror(errno));
				return;
			}
			write(out, str, strlen(str));
			write(out, "\n", 1);
			free(str);

			write(out, "</AcclValue>\n", 13);

		} /* stateEnd */

		case 3: {

			write(out, "<AcclIdx>\n", 10);

			ret = str_read_byte(&str, 8, 1, reader);

			if (ret < 0) {
				fprintf(stderr, "Error reading input data. Errno: %s\n",
						strerror(errno));
				return;
			}
			write(out, str, strlen(str));
			write(out, "\n", 1);
			free(str);

			write(out, "</AcclIdx>\n", 11);

		} /* stateEnd */

		case 4: {

			write(out, "</AcclX>\n", 9);

			label = 1;
			continue;

		} /* case */

		case 5: {

			if (read_bit(reader)) { /* depth 1*/

				write(out, "<AcclY>\n", 8);

			} else {
				label = 9;
				continue;
			} /* AcclY */

		} /* stateEnd */

		case 6: {

			write(out, "<AcclValue>\n", 12);

			ret = str_read_short(&str, 0, 0, reader);

			if (ret < 0) {
				fprintf(stderr, "Error reading input data. Errno: %s\n",
						strerror(errno));
				return;
			}
			write(out, str, strlen(str));
			write(out, "\n", 1);
			free(str);

			write(out, "</AcclValue>\n", 13);

		} /* stateEnd */

		case 7: {

			write(out, "<AcclIdx>\n", 10);

			ret = str_read_byte(&str, 8, 1, reader);

			if (ret < 0) {
				fprintf(stderr, "Error reading input data. Errno: %s\n",
						strerror(errno));
				return;
			}
			write(out, str, strlen(str));
			write(out, "\n", 1);
			free(str);

			write(out, "</AcclIdx>\n", 11);

		} /* stateEnd */

		case 8: {

			write(out, "</AcclY>\n", 9);

			label = 5;
			continue;

		} /* case */

		case 9: {

			if (read_bit(reader)) { /* depth 1*/

				write(out, "<AcclZ>\n", 8);

			} else {
				label = 13;
				continue;
			} /* AcclZ */

		} /* stateEnd */

		case 10: {

			write(out, "<AcclValue>\n", 12);

			ret = str_read_short(&str, 0, 0, reader);

			if (ret < 0) {
				fprintf(stderr, "Error reading input data. Errno: %s\n",
						strerror(errno));
				return;
			}
			write(out, str, strlen(str));
			write(out, "\n", 1);
			free(str);

			write(out, "</AcclValue>\n", 13);

		} /* stateEnd */

		case 11: {

			write(out, "<AcclIdx>\n", 10);

			ret = str_read_byte(&str, 8, 1, reader);

			if (ret < 0) {
				fprintf(stderr, "Error reading input data. Errno: %s\n",
						strerror(errno));
				return;
			}
			write(out, str, strlen(str));
			write(out, "\n", 1);
			free(str);

			write(out, "</AcclIdx>\n", 11);

		} /* stateEnd */

		case 12: {

			write(out, "</AcclZ>\n", 9);

			label = 9;
			continue;

		} /* case */

		case 13: {

			/* loop /SensorValues/Audio */
			while (read_bit(reader)) { /* depth 1 */

				write(out, "<Audio>\n", 8);

				ret = str_read_byte(&str, 8, 1, reader);

				if (ret < 0) {
					fprintf(stderr, "Error reading input data. Errno: %s\n",
							strerror(errno));
					return;
				}
				write(out, str, strlen(str));
				write(out, "\n", 1);
				free(str);

				write(out, "</Audio>\n", 9);

			} /* Audio */

		} /* stateEnd */

		case 14: {

			if (read_bit(reader)) { /* depth 1*/

				write(out, "<Light>\n", 8);

			} else {
				label = 18;
				continue;
			} /* Light */

		} /* stateEnd */

		case 15: {

			write(out, "<LightValue>\n", 13);

			ret = str_read_byte(&str, 8, 1, reader);

			if (ret < 0) {
				fprintf(stderr, "Error reading input data. Errno: %s\n",
						strerror(errno));
				return;
			}
			write(out, str, strlen(str));
			write(out, "\n", 1);
			free(str);

			write(out, "</LightValue>\n", 14);

		} /* stateEnd */

		case 16: {

			write(out, "<LightIdx>\n", 11);

			ret = str_read_byte(&str, 8, 1, reader);

			if (ret < 0) {
				fprintf(stderr, "Error reading input data. Errno: %s\n",
						strerror(errno));
				return;
			}
			write(out, str, strlen(str));
			write(out, "\n", 1);
			free(str);

			write(out, "</LightIdx>\n", 12);

		} /* stateEnd */

		case 17: {

			write(out, "</Light>\n", 9);

			label = 14;
			continue;

		} /* case */

		case 18: {

			if (read_bit(reader)) { /* depth 1*/

				write(out, "<AmbientLight>\n", 15);

			} else {
				label = 22;
				continue;
			} /* AmbientLight */

		} /* stateEnd */

		case 19: {

			write(out, "<LightValue>\n", 13);

			ret = str_read_byte(&str, 8, 1, reader);

			if (ret < 0) {
				fprintf(stderr, "Error reading input data. Errno: %s\n",
						strerror(errno));
				return;
			}
			write(out, str, strlen(str));
			write(out, "\n", 1);
			free(str);

			write(out, "</LightValue>\n", 14);

		} /* stateEnd */

		case 20: {

			write(out, "<LightIdx>\n", 11);

			ret = str_read_byte(&str, 8, 1, reader);

			if (ret < 0) {
				fprintf(stderr, "Error reading input data. Errno: %s\n",
						strerror(errno));
				return;
			}
			write(out, str, strlen(str));
			write(out, "\n", 1);
			free(str);

			write(out, "</LightIdx>\n", 12);

		} /* stateEnd */

		case 21: {

			write(out, "</AmbientLight>\n", 16);

			label = 18;
			continue;

		} /* case */

		case 22: {

			if (read_bit(reader)) { /* depth 1*/

				write(out, "<Force>\n", 8);

			} else {
				label = 26;
				continue;
			} /* Force */

		} /* stateEnd */

		case 23: {

			write(out, "<ForceValue>\n", 13);

			ret = str_read_byte(&str, 8, 1, reader);

			if (ret < 0) {
				fprintf(stderr, "Error reading input data. Errno: %s\n",
						strerror(errno));
				return;
			}
			write(out, str, strlen(str));
			write(out, "\n", 1);
			free(str);

			write(out, "</ForceValue>\n", 14);

		} /* stateEnd */

		case 24: {

			write(out, "<ForceIdx>\n", 11);

			ret = str_read_byte(&str, 8, 1, reader);

			if (ret < 0) {
				fprintf(stderr, "Error reading input data. Errno: %s\n",
						strerror(errno));
				return;
			}
			write(out, str, strlen(str));
			write(out, "\n", 1);
			free(str);

			write(out, "</ForceIdx>\n", 12);

		} /* stateEnd */

		case 25: {

			write(out, "</Force>\n", 9);

			label = 22;
			continue;

		} /* case */

		case 26: {

			if (read_bit(reader)) { /* depth 1*/

				write(out, "<Temperature>\n", 14);

			} else {
				label = 30;
				continue;
			} /* Temperature */

		} /* stateEnd */

		case 27: {

			write(out, "<TempValue>\n", 12);

			ret = str_read_byte(&str, 0, 0, reader);

			if (ret < 0) {
				fprintf(stderr, "Error reading input data. Errno: %s\n",
						strerror(errno));
				return;
			}
			write(out, str, strlen(str));
			write(out, "\n", 1);
			free(str);

			write(out, "</TempValue>\n", 13);

		} /* stateEnd */

		case 28: {

			write(out, "<TempIdx>\n", 10);

			ret = str_read_byte(&str, 8, 1, reader);

			if (ret < 0) {
				fprintf(stderr, "Error reading input data. Errno: %s\n",
						strerror(errno));
				return;
			}
			write(out, str, strlen(str));
			write(out, "\n", 1);
			free(str);

			write(out, "</TempIdx>\n", 11);

		} /* stateEnd */

		case 29: {

			write(out, "</Temperature>\n", 15);

			label = 26;
			continue;

		} /* case */

		case 30: {

			/* loop /SensorValues/Voltage */
			while (read_bit(reader)) { /* depth 1 */

				write(out, "<Voltage>\n", 10);

				ret = str_read_short(&str, 16, 1, reader);

				if (ret < 0) {
					fprintf(stderr, "Error reading input data. Errno: %s\n",
							strerror(errno));
					return;
				}
				write(out, str, strlen(str));
				write(out, "\n", 1);
				free(str);

				write(out, "</Voltage>\n", 11);

			} /* Voltage */

		} /* stateEnd */

		case 31: {

			write(out, "</SensorValues>\n", 16);

		} /* case */

		default:
			return;
		}
	}
}
