#include <stdio.h>
#include <stdlib.h>
int readfile(const char* filename, unsigned char* bytes);
int writefile(const char* filename, unsigned char* bytes, size_t len);
int comparefiles(const char* filename1, const char* filename2);
int readintfromfile(const char* filename);
int writeinttofile(int val, const char* filename);
