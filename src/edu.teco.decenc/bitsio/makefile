CFLAGS += -Wall -pedantic -g3 -O0

OBJ = read_bits.o write_bits.o  

OBJ_BUF = read_bits_buf.o write_bits_buf.o  

LIB = libbitsio.a 
LIB_BUF = libbitsio_buf.a 

all: ${LIB_BUF} ${LIB}

${LIB_BUF}: ${OBJ_BUF}
	ar r $@ $?
	ranlib $@
	
${LIB}: ${OBJ}
	ar r $@ $?
	ranlib $@
clean:
	rm -f ${OBJ} ${OBJ_BUF} ${LIB} ${LIB_BUF}
