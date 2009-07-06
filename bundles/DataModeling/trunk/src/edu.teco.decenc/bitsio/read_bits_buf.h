#ifndef _READ_BITS_BUF_H_
#define _READ_BITS_BUF_H_
#include "read_bits.h"
#include <stdlib.h>
#include <alloca.h>
extern const size_t read_bits_bufreader_size;

struct READER_STRUCT *read_bits_bufreader_init(struct READER_STRUCT *,u_char *buf);

#define read_bits_bufreader_stack_new(buf)  read_bits_bufreader_init(alloca(read_bits_bufreader_size),buf)

#define read_bits_bufreader_new(buf) read_bits_bufreader_init(malloc(read_bits_bufreader_size),buf)
#define read_bits_bufreader_delete(w) free(w)

#endif
