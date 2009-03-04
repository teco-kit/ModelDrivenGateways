#ifndef _WRITE_BITS_BUF_H_
#define _WRITE_BITS_BUF_H_

void write_init(u_char *buf);
ssize_t write_bits(u_char *bits, int bits_len);
ssize_t write_finish( void );

#endif
