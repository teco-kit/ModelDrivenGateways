/* ========================================================================
 *
 *        Filename   : bits_io.c
 *        Description:
 *        Version    : 1.0
 *        (C)        : Dimitar Yordanov (domidimi [at] gmail [dot] com)
 *        ---- History ---------------------------------------------------
 *        05.04.2008 21:43    dy    initial version
 * ========================================================================
 */
/*#include <stdio.h>*/

#include "bits_io.h"
/* #include "read_bits.h"  */
/* #include "write_bits.h" */

u_char LOW_N_BITS(u_char a_byte, int n) 
{
   return (a_byte & ((1 << (n)) - 1));
}

u_char UP_N_BITS(u_char a_byte, int n) 
{
  return (a_byte & ~((1 << (8 - n)) - 1));
}

u_char LOW_N_BITS_SHIFT(u_char a_byte, int n) 
{
   return ((a_byte & ((1 << (n)) - 1)) << (8 - n));
}
   
u_char UP_N_BITS_SHIFT(u_char a_byte, int n) 
{
   return (a_byte & ~((1 << (8 - n)) - 1)) >> (8 - n);
}

#if 0
/* ========================================================================
 *
 *
 * ========================================================================*/
int main(void)
{
   int  i   = 0xf3ffffff;
   u_char bit  = 1;
   u_char bit2 = 3;
   int fd = open("output2.bin",
                 O_RDWR  | O_CREAT | O_TRUNC,
                 S_IRWXU | S_IRWXG);
   if (fd == -1)
   {
      fprintf(stderr, "Error openong output file\n");
      return (-1);
   }
   write_init(fd);
   write_bits((u_char *) &bit, 1);
   write_bits((u_char *) &i, 4*8);
   write_bits((u_char *) &bit, 1);
   write_bits((u_char *) &bit, 1);
   write_bits((u_char *) &bit, 1);
   write_bits((u_char *) &bit, 1);
   write_bits((u_char *) &bit, 1);
   write_bits((u_char *) &bit, 1);
   write_bits((u_char *) &bit2, 2);
   write_bits((u_char *) &bit, 1);
   write_bits((u_char *) &bit, 1);
   bit = 12;
   write_bits((u_char *) &bit, 4);
   write_finish();


   bit = 0;
   i   = 0;
   lseek(fd, 0, SEEK_SET);
   read_init(fd);
   read_bits(&bit, 1);
   fprintf(stderr, "Read %x\n", bit);
   read_bits(&i, 4*8);
   fprintf(stderr, "Read %x\n", i);
   read_bits(&bit, 1);
   fprintf(stderr, "Read %x\n", bit);
   read_bits(&bit, 1);
   fprintf(stderr, "Read %x\n", bit);
   read_bits(&bit, 1);
   fprintf(stderr, "Read %x\n", bit);
   read_bits(&bit, 1);
   fprintf(stderr, "Read %x\n", bit);
   read_bits(&bit, 1);
   fprintf(stderr, "Read %x\n", bit);
   read_bits(&bit, 1);
   fprintf(stderr, "Read %x\n", bit);
   read_bits(&bit, 2);
   fprintf(stderr, "Read %x\n", bit);
   read_bits(&bit, 1);
   fprintf(stderr, "Read %x\n", bit);
   read_bits(&bit, 1);
   fprintf(stderr, "Read %x\n", bit);
   read_bits(&bit, 4);
   fprintf(stderr, "Read %d\n", bit);

   return 0;
}
#endif

