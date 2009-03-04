#ifndef DECODER_AUTOMATA_H
#define DECODER_AUTOMATA_H

#include "write_str_func.h"

int getElementDecoder(char * xmlElement, struct STR_WRITER_STRUCT * result);
void automata_init ( int );
void automata_finish( void );
#endif
