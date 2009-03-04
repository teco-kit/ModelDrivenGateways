#ifndef _WRITE_BITS_H_
#define _WRITE_BITS_H_

void    write_init  (int fd);
ssize_t write_bits  (u_char *bits, int bits_len);
ssize_t write_finish( void );
ssize_t write_true( void );
ssize_t write_false( void );

#endif
