CC = gcc
CFLAGS += -Wall -pedantic -I/usr/include/libxml2 -I.

LIBS = ../bitsio/libbitsio.a -lxml2

OBJS = read_str_func.o \
       ../../edu.teco.automata.generator/src-gen/edu/teco/automata/generator/gen/c/encoder_automata.o \
		 encoder.o
        
       
PROG = encoder

${PROG}: ${OBJS} 
	$(CC) $(CFLAGS) $? $(LDFLAGS) -o $@ ${LIBS}

clean:
	@rm -f *.o ${PROG} 
