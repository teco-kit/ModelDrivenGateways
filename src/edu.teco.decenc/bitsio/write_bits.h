#ifndef _WRITE_BITS_H_
#define _WRITE_BITS_H_
#include <unistd.h>

struct WRITER_STRUCT;


ssize_t write_bits  (struct WRITER_STRUCT *writer, const void *bits, size_t bits_len);
ssize_t write_finish(struct WRITER_STRUCT *writer);

ssize_t write_bit(struct WRITER_STRUCT *writer, uint8_t bit);

#define write_false(w) write_bit(w, 0)
#define write_true(w)  write_bit(w, 1)

#endif
