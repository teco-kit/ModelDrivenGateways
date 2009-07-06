#ifndef _READ_BITS_H_
#define _READ_BITS_H_

struct READER_STRUCT ;

void    read_init   (struct READER_STRUCT * reader, int fd);
ssize_t read_bits   (struct READER_STRUCT * reader, u_char *dst_buf, int bits_len);

static unsigned char read_bit( struct READER_STRUCT *reader)  __attribute__((__unused__));

static unsigned char read_bit( struct READER_STRUCT *reader)
{
   u_char val;
   read_bits(reader, &val, 1);
   return val;
}

#endif
