#ifndef _WRITE_BITS_H_
#define _WRITE_BITS_H_
#include <unistd.h>

struct WRITER_STRUCT;

#define write_false(w) write_bits(w,(u_char *)"", 1)
#define write_true(w) write_bits(w, (u_char *)"\377", 1)

ssize_t write_bits  (struct WRITER_STRUCT *writer, const u_char *bits, int bits_len);
ssize_t write_finish(struct WRITER_STRUCT *writer);
#endif
