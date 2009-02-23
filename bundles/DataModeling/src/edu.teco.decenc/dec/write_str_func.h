#ifndef WRITE_STR_FUNC_H
#define WRITE_STR_FUNC_H


struct STR_WRITER_ARGS
{
	int len;
	int min;
	int max;
	int frac;
	int frac_len;
	int stepping;
};

typedef ssize_t STR_WRITER_FUNC(const char*, struct STR_WRITER_ARGS *);

struct STR_WRITER_STRUCT
{
	STR_WRITER_FUNC *func;
	struct STR_WRITER_ARGS args;
};

STR_WRITER_FUNC str_write_char;
STR_WRITER_FUNC str_write_byte;
STR_WRITER_FUNC str_write_short;
STR_WRITER_FUNC str_write_int;
STR_WRITER_FUNC str_write_long;
STR_WRITER_FUNC str_write_float;
STR_WRITER_FUNC str_write_double;
STR_WRITER_FUNC str_write_string;

#endif
