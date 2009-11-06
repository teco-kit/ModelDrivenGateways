PROJECT(ZLIB)

INCLUDE (
  ${CMAKE_ROOT}/Modules/CheckIncludeFile.cmake)
CHECK_INCLUDE_FILE(
  "unistd.h" HAVE_UNISTD_H)
IF(HAVE_UNISTD_H)
  ADD_DEFINITIONS(-DHAVE_UNISTD_H)
ENDIF(HAVE_UNISTD_H)



# source files for zlib
SET(ZLIB_SRCS
	adler32.c   
	gzio.c      
	inftrees.c  
	uncompr.c
	compress.c  
#	infblock.c
#	infutil.c   
	zutil.c
	crc32.c     
#	infcodes.c 
	deflate.c   
	inffast.c  
	inflate.c   
	trees.c
)

OPTION(ZLIB_BUILD_SHARED
  "Build ZLIB shared" ON)
IF(WIN32)
  IF(ZLIB_BUILD_SHARED)
    SET(ZLIB_DLL 1)
  ENDIF(ZLIB_BUILD_SHARED)
ENDIF(WIN32)

IF(ZLIB_DLL)
  #ADD_DEFINITION(-DZLIB_DLL)
  ADD_DEFINITIONS(-DZLIB_DLL)
ENDIF(ZLIB_DLL)


ADD_LIBRARY(zlib ${ZLIB_SRCS})

#ADD_EXECUTABLE(example example.c)

#TARGET_LINK_LIBRARIES(example zlib)
