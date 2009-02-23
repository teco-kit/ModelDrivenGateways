#ifndef _READ_BITS_BUF_H_
#define _READ_BITS_BUF_H_

struct READER_STRUCT {
   u_char *src_buf;
   int     pos;
   u_char  lastByte;
   u_char  currBits;
};

void    read_init   (struct READER_STRUCT * reader, u_char *src_buf);
ssize_t read_bits   (void * reader, u_char *dst_buf, int bits_len);
u_char  read_bit    ( void * reader );

#endif
