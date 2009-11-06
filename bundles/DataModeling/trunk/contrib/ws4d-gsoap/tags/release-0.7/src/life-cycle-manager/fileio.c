#include "fileio.h"
#include <string.h>

#ifdef WIN32
#  define SNPRINTF _snprintf
#  pragma warning(disable : 4996) // Remove snprintf Secure Warnings
#else
#define SNPRINTF snprintf
#endif

#define BYTESCHUNK 4096

int readfile(const char* filename, unsigned char* bytes) {
	FILE* f = fopen(filename, "rb");
	int res = 0, nread;
	if (!f) return -3;
	while ((nread = fread(bytes+res, 1, BYTESCHUNK, f)) == BYTESCHUNK) {
		res += nread;
	}
	if (nread >= 0 && feof(f))
		res += nread;
	else res = -2;
	fclose(f);
	return res;	
}

int writefile(const char* filename, unsigned char* bytes, size_t len) {
	FILE* f = fopen(filename, "wb");	
	int nwritten = 0;
	if (!f) return -1;
	while (1) {
		int n = len - nwritten;
		if (n > BYTESCHUNK) {
			fwrite(bytes+nwritten, 1, BYTESCHUNK, f);
			nwritten += BYTESCHUNK;
		} else {
			fwrite(bytes+nwritten, 1, n, f);
			fclose(f);
			return 0;	
		}
	}
}

int comparefiles(const char* filename1, const char* filename2) {
	FILE* f1 = fopen(filename1, "rb");	
	FILE* f2 = fopen(filename2, "rb");	
	int res = 0, nread1, nread2;
	unsigned char bytes1[BYTESCHUNK], bytes2[BYTESCHUNK], *b1, *b2;
	while ((nread1 = fread(bytes1, 1, BYTESCHUNK, f1)) == (nread2 = fread(bytes2, 1, BYTESCHUNK, f2))) {
		unsigned char *b1 = bytes1, *b2 = bytes2;
		if (nread1 == 0) {
			res = 0;
			goto end;
		}
		while ((b1 < bytes1 + nread1) && *b1++ == *b2++);
		if (b1 < bytes1 + nread1) {
			res += (b1 - bytes1);
			goto end;
		}
		else res += nread1;
	}
	b1 = bytes1;
	b2 = bytes2;
	while ((b1 < bytes1 + nread1) && (b2 < bytes2 + nread2) && *b1++ == *b2++);
	res += (b1 - bytes1);
end:
	fclose(f1);
	fclose(f2);
	return res;	
}

int readintfromfile(const char* filename) {
	FILE* f = fopen(filename, "r");
	char buf[100];
	int n, val;
	if (!f || (n = fread(buf, 1, 99, f)) <= 0) {
		val = 0;
	} else {
		buf[n] = '\0';
		sscanf(buf, "%d", &val);
	}
	if (f) fclose(f);
	return val;
}

int writeinttofile(int val, const char* filename) {
	FILE* f = fopen(filename, "w");
	char buf[100];
	sprintf(buf, "%d", val);
	fwrite(buf, 1, strlen(buf), f);
	fclose(f);
	return val;
}
