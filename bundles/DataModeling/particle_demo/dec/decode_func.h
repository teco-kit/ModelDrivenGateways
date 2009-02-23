#ifndef _DECODE_FUNC_H
#define _DECODE_FUNC_H

void decode_byte(int8_t val, u_char * result, int min, int max, int stepping);
void decode_short(int16_t val, u_char * result, int min, int max, int stepping);
void decode_int(int32_t val, u_char * result, int min, int max, int stepping);
void decode_float(float val, u_char *result, int min, int max, double stepping, int frac, );
void decode_double(float val, u_char *result, int min, int max, double stepping,int frac,);
void decode_string(char *val, char * result, int len);

#endif
