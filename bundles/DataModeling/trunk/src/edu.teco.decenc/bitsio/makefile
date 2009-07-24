CFLAGS += -Wall -pedantic -g -O0

OBJ = read_bits.o write_bits.o  

OBJ_BUF = read_bits_buf.o write_bits_buf.o  

LIB = libbitsio.a 
LIB_BUF = libbitsio_buf.a 

all: ${LIB} ${LIB_BUF}

${LIB}: ${OBJ}
	ar rvu $@ ${OBJ}
	ranlib $@

${LIB_BUF}: ${OBJ_BUF}
	ar rvu $@ ${OBJ_BUF}
	ranlib $@
	
clean:
	rm -f ${OBJ} ${OBJ_BUF} ${LIB} 
