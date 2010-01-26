CPPFLAGS+=-I.. 
CXXFLAGS+=-O0 -g3
LDFLAGS+=-L../bitsio
LDLIBS+=-lgtest -lgtest_main
all: bitsio_test


bitsio_test: ../bitsio/libbitsio_buf.a
bitsio_test: LDLIBS+=-lbitsio_buf