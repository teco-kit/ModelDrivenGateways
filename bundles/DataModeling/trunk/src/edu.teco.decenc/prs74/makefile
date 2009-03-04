CC = gcc
CFLAGS += -Wall -pedantic \
	-I../../edu.teco.automata.generator/src-gen/edu/teco/automata/generator/gen/c/ -I. -I`pwd` 

LIBS = ../bitsio/libbitsio.a

OBJS = test.o \
	    ../dec/decode_func.o \
       ../../edu.teco.automata.generator/src-gen/edu/teco/automata/generator/gen/c/decoder_send.o \
       ../../edu.teco.automata.generator/src-gen/edu/teco/automata/generator/gen/c/decoder_set.o
       
       
PROG = test

${PROG}: ${OBJS} 
	$(CC) $(CFLAGS) $? $(LDFLAGS) -o $@ ${LIBS}

clean:
	@rm -f *.o ${PROG} 
