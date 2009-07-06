#ifndef _WRITE_BITS_BUF_H_
#define _WRITE_BITS_BUF_H_
#include "write_bits.h"
#include <stdlib.h>
#include <alloca.h>

extern const size_t write_bits_bufwriter_size;

struct WRITER_STRUCT *write_bits_bufwriter_init(struct WRITER_STRUCT *,u_char *buf);

#define write_bits_bufwriter_stack_new(buf)  write_bits_bufwriter_init(alloca(write_bits_bufwriter_size),buf)

#define write_bits_bufwriter_new(w,buf) write_bits_bufwriter_init(malloc(write_bits_bufwriter_size),buf)
#define write_bits_bufwriter_delete(w) free(w)

#endif
