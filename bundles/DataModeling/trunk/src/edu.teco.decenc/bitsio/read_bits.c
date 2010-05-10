/* ========================================================================
 *
 *        Filename   : read_bits.c
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
#include "read_bits.h"
#include "bits_io.h"


struct READER_STRUCT {
   int    fd;
   u_char lastByte;
   u_char currBits;
};
/* ========================================================================
 *
 *
 * ========================================================================*/
void read_init(struct READER_STRUCT * reader, int fd)
{
   reader->fd       = fd;
   reader->lastByte = 0;
   reader->currBits = 0;
}

/* ========================================================================
 *
 *
 * ========================================================================*/
ssize_t read_bits(struct READER_STRUCT * reader, void *dst_buf, size_t bits_len)
{
   int     bytes;    /* = bits_len / 8;*/
   int     rest_len; /* = bits_lenz % 8; */
   u_char  a_byte;
   ssize_t ret, res;

   bytes    = bits_len >> 3;
   rest_len = bits_len % 8;
   ret      = 0;

   if (reader->currBits > 0)
   {
      if (reader->currBits >= bits_len)
      {
         a_byte = UP_N_BITS_SHIFT(reader->lastByte, rest_len);
         memcpy(dst_buf, &a_byte, 1);
         reader->lastByte <<= rest_len;
         reader->currBits  -= rest_len;
      }
      else
      {
         for(;bytes > 0; bytes--)
         {
            u_char tmp_byte;

            res = read(reader->fd, &a_byte, 1);
            if (res < 0)
            {
               fprintf(stderr, "Error reading. Errno: %s\n", strerror(errno));
               return (-1);
            }
            ret += res;
            tmp_byte = LOW_N_BITS_SHIFT(a_byte, reader->currBits);
            a_byte = UP_N_BITS_SHIFT(a_byte, (8 - reader->currBits)) |
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

               res = read(reader->fd, &a_byte, 1);
               if (res < 0)
               {
                  fprintf(stderr, "Error reading. Errno: %s\n", strerror(errno));
                  return (-1);
               }
               ret += res;
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
         res = read(reader->fd, dst_buf, bytes);
         if (res < 0)
         {
            fprintf(stderr, "Error reading. Errno: %s\n", strerror(errno));
            return (-1);
         }
         ret += res;
      }

      if (rest_len > 0)
      {
         res = read(reader->fd, &a_byte, 1);
         if (res < 0)
         {
            fprintf(stderr, "Error reading. Errno: %s\n", strerror(errno));
            return (-1);
         }
         ret += res;
         reader->lastByte = LOW_N_BITS_SHIFT(a_byte, (8 - rest_len));

         a_byte = UP_N_BITS_SHIFT(a_byte, rest_len);
         memcpy(dst_buf, &a_byte, 1);

         reader->currBits = 8 - rest_len;
      }

      return ret;
   }

}
