
OBJ = read_bits_buf.o write_bits.o
LIB = libbitsio.a 
${LIB}: ${OBJ}
	ar rvu $@ ${OBJ}
	ranlib $@
	
clean:
	@rm -f *.o ${LIB} 
        