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
   u_char mask;

   bytes    = bits_len >> 3;
   rest_len = bits_len % 8;
   ret      = 0;


   mask = 0xff;
   i=0; //unroll to init mask
   {
         int cur;
         u_char a_byte;
         a_byte = bits[i];
         for(cur=writer.currBits;cur;cur--)
         {
            rotate_right(&a_byte,1);
            mask>>=1;
         }
         writer.buf[writer.pos]  |= a_byte & mask;
         writer.buf[writer.pos+1] = a_byte & ~mask;
	 if (bytes > 0 || (writer.currBits+rest_len) >= 8)
	 {
           ret++;
           writer.pos++;
	 }
   }

   for (i = 1; i < bytes+1; i++)
   {
         int cur;
         u_char a_byte;
         a_byte = bits[i];
         for(cur=writer.currBits;cur;cur--)
         {
            rotate_right(&a_byte,1);
         }
         writer.buf[writer.pos]  |= a_byte & mask;
         writer.buf[writer.pos+1] = a_byte & ~mask;
	 if (bytes > i || (writer.currBits+rest_len) > 8)
	 {
            ret++;
            writer.pos++;
	 }
   }

   writer.currBits = (writer.currBits+rest_len)%8 ;

   return ret;
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
