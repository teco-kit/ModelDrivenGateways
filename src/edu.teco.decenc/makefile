LIBDIRS = bitsio
        
CDIRS = prs74 dec enc
##-------------------------------------------------------------
default: all

##----------------------------------------------------------------------

all: $(LIBDIRS)
	for d in $^;                  \
	do                                  \
		(cd $$d && pwd && $(MAKE));      \
		retVal=$$?;                      \
		if [ ! $$retVal = 0 ];           \
		then                             \
			exit $$retVal;                \
		fi;                              \
	done;  
	                             
tests: $(LIBDIRS) $(CDIRS)
	for d in $^;                  \
	do                                  \
		(cd $$d && pwd && $(MAKE));      \
		retVal=$$?;                      \
		if [ ! $$retVal = 0 ];           \
		then                             \
			exit $$retVal;                \
		fi;                              \
	done;       

##---- clean up ---------------------------------------------------------------
clean: $(CDIRS) $(LIBDIRS)
	for d in $^;                       \
	do                                       \
		(cd $$d; make clean);                 \
	done;
