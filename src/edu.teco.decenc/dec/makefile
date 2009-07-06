CC = gcc
CFLAGS += -Wall -pedantic -I/usr/include/libxml2 -I. 

LIBS = ../strio/libstrio.a ../bitsio/libbitsio.a -lxml2

OBJS = decoder.o
        
       
PROG = decoder

${PROG}: ${OBJS} 
	$(CC) $(CFLAGS) $? $(LDFLAGS) -o $@ ${LIBS}

clean:
	@rm -f *.o ${PROG} 
