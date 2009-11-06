# Copyright (C) 2007  University of Rostock
#
# This program is free software; you can redistribute it and/or
# modify it under the terms of the GNU General Public License as
# published by the Free Software Foundation; either version 2 of the
# License, or (at your option) any later version.
#
# This program is distributed in the hope that it will be useful, but
# WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
# General Public License for more details.
# 
# You should have received a copy of the GNU General Public License
# along with this program; if not, write to the Free Software
# Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
# 02110-1301 USA.

# Find XSLT includes and library
#
# This module defines
#  XSLT_INCLUDE_DIR
#  XSLT_LIBRARIES, the libraries to link against to use XSLT.
#  XSLT_LIB_DIR, the location of the libraries
#  XSLT_FOUND, If false, do not try to use XSLT
#  XSLTSDK, (Windows Only) The root directory of Ogre
#
# Copyright © 2007, Matt Williams
#
# Redistribution and use is allowed according to the terms of the BSD license.
# For details see the accompanying COPYING-CMAKE-SCRIPTS file.
# 
# Retrieved from https://svn.sourceforge.net/svnroot/thermite/trunk/cmake/modules/FindXSLT.cmake

IF (XSLT_LIBRARIES AND XSLT_INCLUDE_DIR)
	SET(XSLT_FIND_QUIETLY TRUE) # Already in cache, be silent
ENDIF (XSLT_LIBRARIES AND XSLT_INCLUDE_DIR)

IF (WIN32) #Windows
# Windows stuff
ELSE (WIN32) #Unix
	INCLUDE(UsePkgConfig)

	SET(XSLT_LIB_FLAGS)
	SET(XSLT_CXX_FLAGS)
	PKGCONFIG(xslt XSLT_INCLUDE_DIR XSLT_LIB_DIR XSLT_LIB_FLAGS XSLT_CXX_FLAGS)

	STRING(REGEX REPLACE "-I" "" XSLT_INCLUDE_DIR ${XSLT_CXX_FLAGS})
	STRING(REGEX REPLACE "^-L[a-zA-Z/]* " "" XSLT_LIBRARIES ${XSLT_LIB_FLAGS})
	STRING(REGEX REPLACE "-l" "" XSLT_LIBRARIES ${XSLT_LIBRARIES})
ENDIF (WIN32)

#Do some preparation
SEPARATE_ARGUMENTS(XSLT_INCLUDE_DIR)
SEPARATE_ARGUMENTS(XSLT_LIBRARIES)

SET(XSLT_INCLUDE_DIR ${XSLT_INCLUDE_DIR} CACHE PATH "")
SET(XSLT_LIBRARIES ${XSLT_LIBRARIES} CACHE STRING "")
SET(XSLT_LIB_DIR ${XSLT_LIB_DIR} CACHE PATH "")

IF (XSLT_INCLUDE_DIR AND XSLT_LIBRARIES)
	SET(XSLT_FOUND TRUE)
ENDIF (XSLT_INCLUDE_DIR AND XSLT_LIBRARIES)

IF (XSLT_FOUND)
	IF (NOT XSLT_FIND_QUIETLY)
		MESSAGE(STATUS "Found XSLT libraries : ${XSLT_LIBRARIES} from ${XSLT_LIB_DIR}")
		MESSAGE(STATUS "Found XSLT includes  : ${XSLT_INCLUDE_DIR}")
	ENDIF (NOT XSLT_FIND_QUIETLY)
ELSE (XSLT_FOUND)
	IF (XSLT_FIND_REQUIRED)
		MESSAGE(FATAL_ERROR "Could not find XSLT")
	ENDIF (XSLT_FIND_REQUIRED)
ENDIF (XSLT_FOUND)

