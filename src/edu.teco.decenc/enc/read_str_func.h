#ifndef _READ_STR_FUNC_H
#define _READ_STR_FUNC_H

int str_read_char(char **res, void*);
int str_read_byte(char **res, int len, int isUnsigned, void * reader);
int str_read_short(char **res, int len, int isUnsigned, void *);
int str_read_int(char **res, int len, int isUnsigned, void *);
int str_read_long(char **res, void *);
int str_read_float(char **res, void *);
int str_read_double(char **res, int, int, void *);
int str_read_string(char **res, int, void *);

#endif
