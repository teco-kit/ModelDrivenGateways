/* ========================================================================
 *
 *        Filename   : write_bits_buf.c
 *        Description:
 *        Version    : 1.0
 *        (C)        : Dimitar Yordanov (domidimi [at] gmail [dot] com)
 *        ---- History ---------------------------------------------------
 *        05.04.2008 21:43    dy    initial version
 * ========================================================================
 */
#include <stdio.h>
#include <string.h>
#include <errno.h>
#include <stdlib.h>
#include <assert.h>

#include "bits_io.h"
#include "write_bits.h"

struct WRITER_STRUCT {
	int pos;
	u_char *buf;
	u_char lastByte;
	u_char currBits;
};

/* ========================================================================
 *
 *
 * ========================================================================*/
struct WRITER_STRUCT * write_bits_bufwriter_init(struct WRITER_STRUCT *writer,
		u_char *buf) {
	if (writer) {
		writer->buf = buf;
		writer->pos = 0;
		writer->lastByte = 0;
		writer->currBits = 0;
	}
	return writer;
}

const size_t write_bits_bufwriter_size = sizeof(struct WRITER_STRUCT);

/* ========================================================================
 *
 * @return the number of bytes written
 * ========================================================================*/
ssize_t write_bits(struct WRITER_STRUCT* writer, const u_char *buf,
		int bits_len) {

	int old_pos = writer->pos;

	{
		int i;
		if (writer->currBits == 0) {
			i=bits_len/8;
			memcpy(&writer->buf[writer->pos], &buf[0], i);
			writer->pos+=i;
		}
		else
		for (i = 0; i < (bits_len / 8); i++) /*complete bytes*/
		{
			int len = 8;
			u_char upper = (writer->lastByte);
			u_char lower = LOW_N_BITS(buf[i], len) >> writer->currBits;
			assert((0 == (lower & upper)) && "no overlap");

			writer->buf[writer->pos++] = upper | lower;

			writer->lastByte = LOW_N_BITS(buf[i], len)
					<< (8 - writer->currBits); /*move up and automatically clear left bits*/
			writer->currBits = (len + writer->currBits) % 8;

		}

		/*unroll for last bits*/
		if (((bits_len % 8) + writer->currBits) >= 8) {
			int len = bits_len % 8;
			u_char upper = (writer->lastByte);
			u_char lower = LOW_N_BITS(buf[i], len) >> writer->currBits;
			assert((0 == (lower & upper)) && "no overlap");

			writer->buf[writer->pos++] = upper | lower;

			writer->lastByte = LOW_N_BITS(buf[i], len)
					<< (8 - writer->currBits); /*move up and automatically clear left bits*/
			writer->currBits = (len + writer->currBits) % 8;

		} else { /*if( (bits_len % 8) + writer->currBits) <8)*/
			int len = bits_len % 8;
			u_char upper = (writer->lastByte);
			u_char lower = LOW_N_BITS(buf[i], len) << (8 - (writer->currBits
					+ len));

			writer->lastByte = upper | lower;
			writer->currBits = (len + writer->currBits) % 8;
		}

	}

	return old_pos - writer->pos;
}

/* ========================================================================
 *
 *
 * ========================================================================*/
ssize_t write_finish(struct WRITER_STRUCT* writer) {
	ssize_t ret = 0;

	if (writer->currBits) {
		memcpy(writer->buf + writer->pos, &writer->lastByte, 1);
		*(uint32_t *) &writer->buf[writer->pos + 1] = 0xdeadbeaf; /*DEBUG*/
		ret = 1;
	}

	return ret;
}

ssize_t write_buf_finish(struct WRITER_STRUCT* writer) {
	return writer->pos + write_finish(writer);
}
