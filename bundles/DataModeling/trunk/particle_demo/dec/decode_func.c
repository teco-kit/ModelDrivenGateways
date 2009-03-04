/* ========================================================================
 *
 *        Filename   : decode_func.c
 *        Description:
 *        Version    : 1.0
 *        (C)        : Dimitar Yordanov (domidimi [at] gmail [dot] com)
 *        ---- History ---------------------------------------------------
 *        05.04.2008 21:43    dy    initial version
 * ========================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "decode_func.h"

#define MAX_UBYTE_VAL  (1 <<  8) - 1
#define MAX_USHORT_VAL (1 << 16) - 1
#define MAX_UINT_VAL   (1 << 32) - 1

void decode_byte(int8_t val, u_char * result, int min, int max, int stepping) {
	if (min == max)
		*(int8_t *)result = val;
	if (min == 0 && max == MAX_UBYTE_VAL)
		*(uint8_t *)result = (uint8_t)val;
}

void decode_short(int16_t val, u_char * result, int min, int max, int stepping) {
	if (min == max)
		*(int16_t *) result = val;
	if (min == 0 && max == MAX_USHORT_VAL)
		*(uint16_t *)result = (uint16_t)val;
}

void decode_int(int32_t val, u_char * result, int min, int max, int stepping) {
	if (min == max)
		*(int32_t *) result = val;
	if (min == 0 && max == MAX_UINT_VAL)
		*(uint32_t *)result = (uint32_t)val;

}

void decode_float(float val, u_char *result, u_char *frac_result, int min,
		int max, double stepping, int frac) {
	if (min == max)
		*(float *) result = val;
}

void decode_double(double val, u_char *result, u_char *frac_result, int min,
		int max, double stepping, int frac) {
	if (min == max)
		*(double *) result = val;
}

void decode_string(char *val, char * result, int len) {
	if (len > 0)
		strncpy(result, val, len);
	else
		strcpy(result, val);
}
