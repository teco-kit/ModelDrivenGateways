#ifndef _READ_BITS_H_
#define _READ_BITS_H_
#include <unistd.h>

struct READER_STRUCT ;

void    read_init   (struct READER_STRUCT * reader, int fd);
ssize_t read_bits   (struct READER_STRUCT * reader, char *dst_buf, int bits_len);

int read_bit( struct READER_STRUCT *reader);

#endif
