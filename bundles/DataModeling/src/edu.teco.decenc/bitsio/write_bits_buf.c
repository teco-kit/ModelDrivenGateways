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

#include "bits_io.h"
#include "write_bits_buf.h"

struct WRITER_STRUCT {
   int     pos;
   u_char *buf;
   u_char  lastByte;
   u_char  currBits;
};

static struct WRITER_STRUCT writer;

/* ========================================================================
 *
 *
 * ========================================================================*/
void write_init(u_char *buf)
{
   writer.buf      = buf;
   writer.pos      = 0;
   writer.lastByte = 0;
   writer.currBits = 0;
}

/* ========================================================================
 *
 * @return the number of bytes written
 * ========================================================================*/
ssize_t write_bits(u_char *bits, int bits_len)
{
   int      i;
   int      bytes;     /* = bits_len / 8; */
   int      rest_len;  /* = bits_len % 8; */
   ssize_t  ret;

   bytes    = bits_len >> 3;
   rest_len = bits_len % 8;
   ret      = 0;

   if (writer.currBits > 0)
   {
      u_char a_byte;

      for (i = 0; i < bytes; i++)
      {
         a_byte = (bits[i] >> writer.currBits) | (writer.lastByte);
         writer.lastByte = LOW_N_BITS_SHIFT(bits[i], writer.currBits);
         writer.buf[writer.pos] = a_byte;
         ret++;
         writer.pos++;
      }

      if (rest_len > 0)
      {
         /* set rest of the bits to zero */
         bits[bytes] = LOW_N_BITS(bits[bytes], rest_len);

         if (rest_len + writer.currBits > 8)
         {
            a_byte = (bits[bytes] >> (rest_len + writer.currBits - 8)) |
                   (writer.lastByte);
            writer.lastByte =
               LOW_N_BITS_SHIFT(bits[bytes],
                                (rest_len + writer.currBits - 8));
            writer.currBits = (rest_len + writer.currBits) - 8;
            writer.buf[writer.pos++] = a_byte;
            ret++;
         }
         else if (rest_len + writer.currBits == 8)
         {
            a_byte = bits[bytes] | writer.lastByte;

            writer.lastByte = 0;
            writer.currBits = 0;
            writer.buf[writer.pos++] = a_byte;
            ret++;
         }
         else
         {
            writer.lastByte |= bits[bytes] << (8 - writer.currBits - rest_len);
            writer.currBits += rest_len;
         }
      }

      return ret;
   }
   else
   {
      if (bytes > 0)
      {
         memcpy(writer.buf + writer.pos, &bits, bytes);
         ret        += bytes;
         writer.pos += bytes;
      }

      if ((bits_len % 8) > 0)
      {
         /* set rest of the bits to zero */
         bits[bytes]     = LOW_N_BITS(bits[bytes], rest_len);

         writer.lastByte = bits[bytes] << (8 - rest_len);
         writer.currBits = rest_len;
      }

      return ret;
   }
}

/* ========================================================================
 *
 *
 * ========================================================================*/
ssize_t write_finish( void )
{
   ssize_t ret = 0;

   if (writer.currBits)
   {
      memcpy(writer.buf + writer.pos, &writer.lastByte, 1);
      ret = 1;
   }

   writer.lastByte = 0;
   writer.currBits = 0;
   writer.pos      = 0;
   writer.buf      = 0;

   return ret;
}

/* ========================================================================
 *
 *
 * ========================================================================*/
ssize_t write_true( void )
{
   u_char val = 1;
   return write_bits(&val, 1);
}

/* ========================================================================
 *
 *
 * ========================================================================*/
ssize_t write_false( void )
{
   u_char val = 0;
   return write_bits(&val, 1);
}
