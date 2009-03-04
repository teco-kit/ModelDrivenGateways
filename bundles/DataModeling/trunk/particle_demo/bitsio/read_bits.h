#ifndef _READ_BITS_H_
#define _READ_BITS_H_

struct READER_STRUCT {
   int    fd;
   u_char lastByte;
   u_char currBits;
};

void    read_init   (struct READER_STRUCT * reader, int fd);
ssize_t read_bits   (struct READER_STRUCT * reader, u_char *dst_buf, int bits_len);
u_char  read_bit    ( struct READER_STRUCT * reader );

#endif
