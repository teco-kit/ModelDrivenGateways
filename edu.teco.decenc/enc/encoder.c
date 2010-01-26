/* ========================================================================
 *
 *        Filename   : encoder.c
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

#include "../bitsio/bits_io.h"
#include "../bitsio/read_bits.h"
#include "encoder_automata.h"

/* ========================================================================
 *
 *
 * ========================================================================*/
int main(void)
{
   int                  fd;
   struct READER_STRUCT reader;

   fd = open("output.bin", O_RDONLY);
   if (fd < 0)
   {
      fprintf(stderr, "Error opening input file. Errno: %s\n",
              strerror(errno));
      return (-1);
   }

   read_init(&reader, fd);
   encoder_automata_init(fileno(stdout), (void *)&reader);

   encoder_automata_run();

   return 0;
}
