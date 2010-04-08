#ifndef _READ_BITS_BUF_H_
#define _READ_BITS_BUF_H_
#include "read_bits.h"
#include <stdlib.h>
#include <alloca.h>
#include <stdint.h>
#include <stddef.h>
extern const size_t read_bits_bufreader_size;

struct READER_STRUCT *read_bits_bufreader_init(struct READER_STRUCT *,char *buf, size_t size);

#define read_bits_bufreader_stack_new(buf,size)  read_bits_bufreader_init((struct READER_STRUCT *)alloca(read_bits_bufreader_size),buf,size)

#define read_bits_bufreader_new(buf) read_bits_bufreader_init(malloc(read_bits_bufreader_size),buf,size)
#define read_bits_bufreader_delete(w) free(w)

#endif
