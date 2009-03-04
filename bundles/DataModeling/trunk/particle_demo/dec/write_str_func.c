/* ========================================================================
 *
 *        Filename   : type_func.c
 *        Description:
 *        Version    : 1.0
 *        (C)        : Dimitar Yordanov (domidimi [at] gmail [dot] com)
 *        ---- History ---------------------------------------------------
 *        05.04.2008 21:43    dy    initial version
 * ========================================================================
 */
#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>

#include "../bitsio/bits_io.h"
#include "decoder_automata.h"
#include "write_str_func.h"

/* ========================================================================
 *
 *
 * ========================================================================*/
ssize_t str_write_char(const char *str, struct STR_WRITER_ARGS *args)
{
	char c;

	if (sscanf(str, "%c", &c) != 1)
	{
		fprintf(stderr, "Error parsing char value\n");
		fprintf(stderr, "Input string: %s", str);
		return -1;
	}

	return write_bits((u_char *)&c, sizeof(char)*8);
}
/* ========================================================================
 *
 *
 * ========================================================================*/
ssize_t str_write_byte(const char *str, struct STR_WRITER_ARGS *args)
{
	short c;

	if (sscanf(str, "%hd", &c) != 1)
	{
		fprintf(stderr, "Error parsing char value\n");
		fprintf(stderr, "Input string: %s", str);
		return -1;
	}

	return write_bits((u_char *)&c, sizeof(u_char)*8);
}
/* ========================================================================
 *
 *
 * ========================================================================*/
ssize_t str_write_short(const char *str, struct STR_WRITER_ARGS *args)
{
	short s;

	if (sscanf(str, "%hd", &s) != 1)
	{
		fprintf(stderr, "Error parsing short value\n");
		fprintf(stderr, "Input string: %s", str);
		return -1;
	}

	return write_bits((u_char *)&s, sizeof(short)*8);
}

/* ========================================================================
 *
 *
 * ========================================================================*/
ssize_t str_write_int(const char *str, struct STR_WRITER_ARGS *args)
{
	int i;

	if (sscanf(str, "%d", &i) != 1)
	{
		fprintf(stderr, "Error parsing int value\n");
		fprintf(stderr, "Input string: %s", str);
		return -1;
	}

	if (args->min != args->max)
	{
		if (i < args->min || i > args->max)
		{
			fprintf(stderr, "Value not in range %d\n", i);
			return -1;
		}
		return write_bits((u_char *)&i, args->len);
	}

	return write_bits((u_char *)&i, sizeof(int)*8);
}

/* ========================================================================
 *
 *
 * ========================================================================*/
ssize_t str_write_long(const char *str, struct STR_WRITER_ARGS *args)
{
	long l;

	if (sscanf(str, "%ld", &l) != 1)
	{
		fprintf(stderr, "Error parsing long value\n");
		fprintf(stderr, "Input string: %s", str);
		return -1;
	}

	return write_bits((u_char *)&l, sizeof(long)*8);
}

/* ========================================================================
 *
 *
 * ========================================================================*/
ssize_t str_write_float(const char *str, struct STR_WRITER_ARGS *args)
{
	float f;

	if (sscanf(str, "%f", &f) != 1)
	{
		fprintf(stderr, "Error parsing int value\n");
		fprintf(stderr, "Input string: %s", str);
		return -1;
	}

	return write_bits((u_char *)&f, sizeof(float)*8);
}

/* ========================================================================
 *
 *
 * ========================================================================*/
ssize_t str_write_double(const char *str, struct STR_WRITER_ARGS *args)
{
	double d;

	if (sscanf(str, "%lf", &d) != 1)
	{
		fprintf(stderr, "Error parsing int value\n");
		fprintf(stderr, "Input string: %s", str);
		return -1;
	}

	if (args->min != args->max)
	{
		ssize_t ret;
		ssize_t ret2;
		int i = (int)d;
		if (d < args->min || d > args->max)
		{
			fprintf(stderr, "Value not in range %lf\n", d);
			return -1;
		}
		ret = write_bits((u_char *)&i, args->len);
		if (ret < 0)
			return ret;
		d -= i;
		i  = (int)(d*(10^args->frac));
		ret2 = write_bits((u_char *)&i, args->frac_len);
		if (ret2 < 0)
			return ret2;
	}

	return write_bits((u_char *)&d, sizeof(double)*8);
}

/* ========================================================================
 *
 *
 * ========================================================================*/
ssize_t str_write_string(const char *str, struct STR_WRITER_ARGS *args)
{
	ssize_t ret;
	ssize_t ret2;
	int     len = strlen(str);

	if (args->len > 0)
		return write_bits((u_char *)str, args->len*8);

	ret = write_bits((u_char *)&len, sizeof(int)*8);
	if (ret < 0)
		return ret;

	ret2 = write_bits((u_char *)str, strlen(str)*8);
	if (ret2 < 0)
		return ret2;

	return ret + ret2;
}
