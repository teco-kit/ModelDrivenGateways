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


#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <stdio.h>


#include <bitsio/write_bits.h>
#include <bitsio/read_bits.h>

/* ========================================================================
 *
 *
 * ========================================================================*/
#define str_read_inttype(TYPE,FORMAT)\
static int str_read_##TYPE(void *reader, char **res, int len) __attribute__((__unused__));\
static int str_read_##TYPE(void *reader, char **res, int len)\
{\
	int  ret;\
	TYPE c;\
\
	ret = read_bits(reader, (u_char *)&c, sizeof(TYPE)*8);\
	if (ret < 0)\
		return ret;\
\
		return asprintf(res, "%"#FORMAT, c);\
}

str_read_inttype(int8_t,d)
str_read_inttype(uint8_t,u)
str_read_inttype(int16_t,d)
str_read_inttype(uint16_t,u)
str_read_inttype(int32_t,ld)
str_read_inttype(uint32_t,lu)

#define str_write_inttype(TYPE,FORMAT)\
static ssize_t str_write_##TYPE(void *writer,const char *str) __attribute__((__unused__));\
static ssize_t str_write_##TYPE(void *writer,const char *str)\
{\
	TYPE c;\
\
	if (sscanf(str, "%"#FORMAT, &c) != 1)\
	{\
		fprintf(stderr, "Error parsing char value\n");\
		fprintf(stderr, "Input string: %s", str);\
		return -1;\
	}\
\
	return write_bits(writer,(u_char *)&c, sizeof(TYPE)*8);\
}

str_write_inttype(int8_t,hhd)
str_write_inttype(uint8_t,hhu)
str_write_inttype(int16_t,hd)
str_write_inttype(uint16_t,hu)
str_write_inttype(int32_t,ld)
str_write_inttype(uint32_t,lu)

static ssize_t str_read_string(void *reader, char **res, uint8_t length) __attribute__((__unused__));
static ssize_t str_read_string(void *reader, char **res, uint8_t length) {
	int ret;

	if (length > 0) {
		*res = (char *) malloc(length);
		return read_bits(reader, (u_char *) *res, length * 8);
	} else /*pascal type string*/
	{
		ret = read_bits(reader, (u_char *) &length, sizeof(length) * 8);
		*res = (char *) malloc(length);
		if (ret < 0)
			return ret;
		else
			return ret + read_bits(reader, (u_char *) *res, length * 8);
	}
}

static ssize_t str_write_string(void *writer, const char *str, uint8_t length) __attribute__((__unused__));
static ssize_t str_write_string(void *writer, const char *str, uint8_t length) {
	ssize_t ret;

	if (length > 0) {
		return write_bits(writer,(u_char *) str, length * 8);
	} else /*pascal type string*/
	{
		length = strlen(str);
		ret = write_bits(writer,(u_char *) &length, sizeof(int) * 8);
		if (ret < 0)
			return ret;
		{
			ssize_t ret2 = write_bits(writer,(u_char *) str, length * 8);
			if (ret2 < 0)
				return ret2;
			else
				return ret + ret2;
		}
	}
}
