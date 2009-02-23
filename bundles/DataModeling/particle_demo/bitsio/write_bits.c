/* ========================================================================
 *
 *        Filename   : write_bits.c
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
#include "write_bits.h"

struct WRITER_STRUCT {
   int    fd;
   u_char lastByte;
   u_char currBits;
};

static struct WRITER_STRUCT writer;

/* ========================================================================
 *
 *
 * ========================================================================*/
void write_init(int fd)
{
   writer.fd       = fd;
   writer.lastByte = 0;
   writer.currBits = 0;
}

/* ========================================================================
 *
 *
 * ========================================================================*/
ssize_t write_bits(u_char *bits, int bits_len)
{
   int      i;
   int      bytes;    /* = bits_len / 8; */
   int      rest_len; /* = bits_len % 8; */
   ssize_t  ret , res;

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
         res = write(writer.fd, &a_byte, 1);
         if (res < 0)
         {
            fprintf(stderr, "1Error writing. Errno: %s\n", strerror(errno));
            return (-1);
         }
         ret += res;
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
            res = write(writer.fd, &a_byte, 1);
            if (res < 0)
            {
               fprintf(stderr, "2Error writing. Errno: %s\n", strerror(errno));
               return (-1);
            }
            ret += res;
         }
         else if (rest_len + writer.currBits == 8)
         {
            a_byte = bits[bytes] | writer.lastByte;

            writer.lastByte = 0;
            writer.currBits = 0;
            res = write(writer.fd, &a_byte, 1);
            if (res < 0)
            {
               fprintf(stderr, "3Error writing. Errno: %s\n", strerror(errno));
               return (-1);
            }
            ret += res;
         }
         else
         {
            writer.lastByte |= bits[bytes] << (8 - writer.currBits - rest_len);
            writer.currBits  += rest_len;
         }
      }

      return ret;
   }
   else
   {
      if (bytes > 0)
      {
         res = write(writer.fd, &bits, bytes);
         if (res < 0)
         {
            fprintf(stderr, "Error writing. Errno: %s\n", strerror(errno));
            fprintf(stderr, "FD %d, Bits\n", writer.fd);
            return (-1);
         }
         ret += res;
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
      write(writer.fd, &writer.lastByte, 1);
	  ret = 1;
   }

   writer.lastByte = 0;
   writer.currBits = 0;

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
