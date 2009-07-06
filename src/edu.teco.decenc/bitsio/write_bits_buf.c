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

#include "bits_io.h"
#include "write_bits.h"

struct WRITER_STRUCT {
   int     pos;
   u_char *buf;
   u_char  lastByte;
   u_char  currBits;
};

/* ========================================================================
 *
 *
 * ========================================================================*/
struct WRITER_STRUCT * write_bits_bufwriter_init(struct WRITER_STRUCT *writer, u_char *buf)
{
	   if(writer)
	   {
		   writer->buf      = buf;
		   writer->pos      = 0;
		   writer->lastByte = 0;
		   writer->currBits = 0;
	   }
	   return writer;
}

const size_t write_bits_bufwriter_size=sizeof(struct WRITER_STRUCT);


/* ========================================================================
 *
 * @return the number of bytes written
 * ========================================================================*/
ssize_t write_bits(struct WRITER_STRUCT* writer, const u_char *bits, int bits_len)
{
   int      i;
   int      bytes;     /* = bits_len / 8; */
   int      rest_len;  /* = bits_len % 8; */
   ssize_t  ret;

   bytes    = bits_len >> 3;
   rest_len = bits_len % 8;
   ret      = 0;

   if (writer->currBits > 0)
   {
      u_char a_byte;

      for (i = 0; i < bytes; i++)
      {
         a_byte = (bits[i] >> writer->currBits) | (writer->lastByte);
         writer->lastByte = LOW_N_BITS_SHIFT(bits[i], writer->currBits);
         writer->buf[writer->pos] = a_byte;
         ret++;
         writer->pos++;
      }

      if (rest_len > 0)
      {
         /* set rest of the bits to zero */
    	 u_char last= LOW_N_BITS(bits[bytes], rest_len);

         if (rest_len + writer->currBits > 8)
         {
            a_byte = (last >> (rest_len + writer->currBits - 8)) |
                   (writer->lastByte);
            writer->lastByte =
               LOW_N_BITS_SHIFT(last,
                                (rest_len + writer->currBits - 8));
            writer->currBits = (rest_len + writer->currBits) - 8;
            writer->buf[writer->pos++] = a_byte;
            ret++;
         }
         else if (rest_len + writer->currBits == 8)
         {
            a_byte = bits[bytes] | writer->lastByte;

            writer->lastByte = 0;
            writer->currBits = 0;
            writer->buf[writer->pos++] = a_byte;
            ret++;
         }
         else
         {
            writer->lastByte |= bits[bytes] << (8 - writer->currBits - rest_len);
            writer->currBits += rest_len;
         }
      }

      return ret;
   }
   else
   {
      if (bytes > 0)
      {
         memcpy(writer->buf + writer->pos, &bits, bytes);
         ret        += bytes;
         writer->pos += bytes;
      }

      if ((bits_len % 8) > 0)
      {

         writer->lastByte = LOW_N_BITS(bits[bytes], rest_len) << (8 - rest_len);
         writer->currBits = rest_len;
      }

      return ret;
   }
}

/* ========================================================================
 *
 *
 * ========================================================================*/
ssize_t write_finish(struct WRITER_STRUCT* writer)
{
	ssize_t ret = 0;

   if (writer->currBits)
   {
      memcpy(writer->buf + writer->pos, &writer->lastByte, 1);
      ret = 1;
   }

   return ret;
}
