/* ========================================================================
 *
 *        Filename   : type_str_func.c
 *        Description:
 *        Version    : 1.0
 *        (C)        : Dimitar Yordanov (domidimi [at] gmail [dot] com)
 *        ---- History ---------------------------------------------------
 *        05.04.2008 21:43    dy    initial version
 * ========================================================================
 */
#define _GNU_SOURCE
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <stdint.h>

#include "../bitsio/bits_io.h"

/* ========================================================================
 *
 *
 * ========================================================================*/
int str_read_char(char **res, void * reader)
{
	int  ret;
	char c;

	ret = read_bits(reader, (u_char *)&c, sizeof(char)*8);
	if (ret < 0)
		return ret;

	return asprintf(res, "%c", c);
}

/* ========================================================================
 *
 *
 * ========================================================================*/
int str_read_byte(char **res, int len, int isUnsigned, void * reader)
{
	int  ret;
	int8_t c;

	ret = read_bits(reader, (u_char *)&c, sizeof(char)*8);
	if (ret < 0)
		return ret;

	if (isUnsigned)
		return asprintf(res, "%hu", (uint8_t)c);
	else
		return asprintf(res, "%hd", c);
}
/* ========================================================================
 *
 *
 * ========================================================================*/
int str_read_short(char **res, int len, int isUnsigned, void * reader)
{
	int   ret;
	short s;

	ret = read_bits(reader, (u_char *)&s, sizeof(short)*8);
	if (ret < 0)
		return ret;

	if (isUnsigned)
		return asprintf(res, "%hu", (unsigned short)s);
	else
		return asprintf(res, "%hd", s);
}

/* ========================================================================
 *
 *
 * ========================================================================*/
int str_read_int(char **res, int len, int isUnsigned, void * reader)
{
	int ret;
	int i;

	if (len > 0)
	{
		ret = read_bits(reader, (u_char *)&i, len);
		if (ret < 0)
			return ret;
	}
	else
	{
		ret = read_bits(reader, (u_char *)&i, sizeof(int)*8);
		if (ret < 0)
			return ret;
	}

	if (isUnsigned)
		return asprintf(res, "%u", (unsigned int)i);
	else
		return asprintf(res, "%d", i);
}

/* ========================================================================
 *
 *
 * ========================================================================*/
int str_read_long(char **res, void *reader)
{
	int  ret;
	long l;

	ret = read_bits(reader, (u_char *)&l, sizeof(long)*8);
	if (ret < 0)
		return ret;

	return asprintf(res, "%ld", l);
}

/* ========================================================================
 *
 *
 * ========================================================================*/
int str_read_float(char **res, void *reader)
{
	int   ret;
	float f;

	ret = read_bits(reader, (u_char *)&f, sizeof(float)*8);
	if (ret < 0)
		return ret;

	return asprintf(res, "%f", f);
}

/* ========================================================================
 *
 *
 * ========================================================================*/
int str_read_double(char **res, int len, int fracLen, void *reader)
{
	int    ret;
	double d;


	if (len > 0)
	{
		int i,j;
		ret = read_bits(reader, (u_char *)&i, len);
		if (ret < 0)
			return ret;

		ret = read_bits(reader, (u_char *)&j, len);
		if (ret < 0)
			return ret;

		d = j;
		d /= (10^fracLen);
		d += i;
	}
	else
	{
		ret = read_bits(reader, (u_char *)&d, sizeof(double)*8);
		if (ret < 0)
			return ret;
	}

	return asprintf(res, "%f", d);
}

/* ========================================================================
 *
 *
 * ========================================================================*/
int str_read_string(char **res, int length, void *reader)
{
	int ret;
	int len;

	if (length > 0)
		return read_bits(reader, (u_char *)*res, length*8);

	ret = read_bits(reader, (u_char *)&len, sizeof(int)*8);
	if (ret < 0)
		return ret;

	*res = (char *)malloc(len);
	ret  = read_bits(reader, (u_char *)*res, len*8);

    return ret;
}
