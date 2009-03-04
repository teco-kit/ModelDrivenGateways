.SILENT:

CDIRS = bitsio \
        dec    \
        enc    \
        prs74

##-------------------------------------------------------------
default: all

##----------------------------------------------------------------------
all: $(CDIRS)
	for d in $(CDIRS);                  \
	do                                  \
		(cd $$d && pwd && $(MAKE));      \
		retVal=$$?;                      \
		if [ ! $$retVal = 0 ];           \
		then                             \
			exit $$retVal;                \
		fi;                              \
	done;                               


##---- clean up ---------------------------------------------------------------
clean: $(CDIRS)
	for d in $(CDIRS);                       \
	do                                       \
		(cd $$d; make clean);                 \
	done;                                    
