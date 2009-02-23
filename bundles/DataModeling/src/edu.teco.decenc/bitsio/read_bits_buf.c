/* ========================================================================
 *
 *        Filename   : read_bits_buf.c
 *        Description:
 *        Version    : 1.0
 *        (C)        : Dimitar Yordanov (domidimi [at] gmail [dot] com)
 *        ---- History ---------------------------------------------------
 *        05.04.2008 21:43    dy    initial version
 * ========================================================================
 */
#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <string.h>
#include <errno.h>

#include "bits_io.h"
#include "read_bits_buf.h"


/* ========================================================================
 *
 *
 * ========================================================================*/
void read_init(struct READER_STRUCT * reader, u_char *src_buf)
{
   reader->src_buf  = src_buf;
   reader->pos      = 0;
   reader->lastByte = 0;
   reader->currBits = 0;
}

/* ========================================================================
 *
 *
 * ========================================================================*/
ssize_t read_bits(void * memory, u_char *dst_buf, int bits_len)
{
   int     bytes;    /* = bits_len / 8;*/
   int     rest_len; /* = bits_len % 8; */
   u_char  a_byte;
   struct READER_STRUCT *reader = (struct READER_STRUCT *)memory;
   ssize_t ret, res;

   bytes    = bits_len >> 3;
   rest_len = bits_len % 8;
   ret      = 0;

   if (reader->currBits > 0)
   {
      if (reader->currBits >= bits_len)
      {
         a_byte  = UP_N_BITS_SHIFT(reader->lastByte, rest_len);
         *dst_buf = a_byte;
         reader->lastByte <<= rest_len;
         reader->currBits  -= rest_len;
      }
      else
      {
         for(;bytes > 0; bytes--)
         {
            u_char tmp_byte;

            a_byte = reader->src_buf[reader->pos];
            ret ++; reader->pos++;
            tmp_byte = LOW_N_BITS_SHIFT(a_byte, reader->currBits);
            a_byte   = UP_N_BITS_SHIFT(a_byte, (8 - reader->currBits)) |
                                       reader->lastByte;
            memcpy(dst_buf++, &a_byte, 1);
            reader->lastByte = tmp_byte;
         }
         if (rest_len > 0)
         {
            if (reader->currBits >= rest_len)
            {
               a_byte = UP_N_BITS_SHIFT(reader->lastByte, rest_len);
               memcpy(dst_buf, &a_byte, 1);
               reader->lastByte <<= rest_len;
               reader->currBits  -= rest_len;
            }
            else
            {
               u_char tmp_byte;

               a_byte = reader->src_buf[reader->pos];
               ret ++; reader->pos++;
               tmp_byte = LOW_N_BITS_SHIFT(a_byte, (8 - rest_len + reader->currBits));

               a_byte = UP_N_BITS_SHIFT(a_byte, (rest_len - reader->currBits)) |
                      (reader->lastByte >> (8 - rest_len));

               memcpy(dst_buf, &a_byte, 1);
               reader->lastByte = tmp_byte;
               reader->currBits = (8 - rest_len + reader->currBits);
            }
         }
      }

      return ret;
   }
   else
   {
      if (bytes > 0)
      {
         memcpy(dst_buf, reader->src_buf + reader->pos, bytes);
         ret += bytes; reader->pos += bytes;
      }

      if (rest_len > 0)
      {
         a_byte = reader->src_buf[reader->pos];
         ret ++; reader->pos++;
         reader->lastByte = LOW_N_BITS_SHIFT(a_byte, (8 - rest_len));

         a_byte = UP_N_BITS_SHIFT(a_byte, rest_len);
         memcpy(dst_buf, &a_byte, 1);

         reader->currBits = 8 - rest_len;
      }

      return ret;
   }
}

/* ========================================================================
 *
 *
 * ========================================================================*/
u_char read_bit( void * reader )
{
   u_char val;
   read_bits(reader, &val, 1);
   return val;
}
