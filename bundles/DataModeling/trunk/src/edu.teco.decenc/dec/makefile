CC = gcc
CFLAGS += -Wall -pedantic -I/usr/include/libxml2 -I. 

LIBS = ../bitsio/libbitsio.a -lxml2

OBJS = write_str_func.o \
       ../../edu.teco.automata.generator/src-gen/edu/teco/automata/generator/gen/c/decoder_automata.o \
		 decoder.o
        
       
PROG = decoder

${PROG}: ${OBJS} 
	$(CC) $(CFLAGS) $? $(LDFLAGS) -o $@ ${LIBS}

clean:
	@rm -f *.o ${PROG} 
