CC = gcc

LIBS = ../bitsio/libbitsio.a

OBJS = encoder.o \
       ../gen/c/encoder_automata.o \
       read_str_func.o 
CFLAGS +=-I../ -I/home/teco/include
CFLAGS +=-I../enc
PROG = encoder
LDFLAGS += -L/home/teco/lib

${PROG}: ${OBJS} 
	$(CC) $(CFLAGS) $^ $(LDFLAGS) -lparticle -o $@ ${LIBS}

clean:
	@rm -f *.o ${PROG} 
