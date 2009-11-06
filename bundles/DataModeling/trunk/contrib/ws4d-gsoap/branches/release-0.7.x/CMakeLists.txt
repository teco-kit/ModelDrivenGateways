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

# DPWS CMake file - 30.07.2007
#
# Top level cmake file of the dpws core library.
#
# author: S.Feldhorst <sascha.feldhorst@udo.edu>

# Name of the project
PROJECT(WS4D-gSOAP C)

# The required version of cmake. This is needed for full qt4-support.
CMAKE_MINIMUM_REQUIRED(VERSION 2.4.0 FATAL_ERROR)

IF(COMMAND CMAKE_POLICY)
	CMAKE_POLICY(SET CMP0003 NEW)
ENDIF(COMMAND CMAKE_POLICY)

# Enable ctest
INCLUDE(CTest)

# Read the current build version.
FILE(READ ${CMAKE_SOURCE_DIR}/buildversion VERSION)
MESSAGE(STATUS "Buildversion: ${VERSION}")

set(CPACK_PACKAGE_DESCRIPTION_SUMMARY "WS4D-gSOAP DPWS toolkit source tarball")
set(CPACK_PACKAGE_VENDOR "WS4D-gSOAP development team")
set(CPACK_PACKAGE_DESCRIPTION_FILE ${CMAKE_CURRENT_SOURCE_DIR}/README)
set(
CPACK_SOURCE_PACKAGE_FILE_NAME
"ws4d-gsoap-${VERSION}.src"
CACHE INTERNAL "tarball basename"
)
set(CPACK_SOURCE_GENERATOR TGZ TBZ2 ZIP)

set(CPACK_SOURCE_IGNORE_FILES
"~$"
".git"
".project"
".cdtproject"
".settings"
".cproject"
"Build-*"
"Install-*"
"CTestConfig.cmake"
"CTestCustom.cmake"
"\\\\.cvsignore$"
"^${PROJECT_SOURCE_DIR}.*/CVS/"
"^${PROJECT_SOURCE_DIR}/debian/"
"^${PROJECT_SOURCE_DIR}/old/"
)
include(CPack)

# Some cmake specific settings.
SET(CMAKE_COLOR_MAKEFILE ON) # For a colorful makefile output.
SET(CMAKE_MODULE_PATH ${CMAKE_MODULE_PATH} ${CMAKE_SOURCE_DIR}/cmake-modules/) # Include own modules.

IF(CMAKE_TOOLCHAIN_FILE)
	CMAKE_MINIMUM_REQUIRED(VERSION 2.5.0 FATAL_ERROR)
ENDIF(CMAKE_TOOLCHAIN_FILE)

# Search threading library.
FIND_PACKAGE(Threads REQUIRED)

# Check dependencies
SET(GSOAP_PATH "/usr/local" CACHE PATH "Path where gSOAP is installed")
FIND_PACKAGE(GSOAP REQUIRED)
SET(GSOAP_PATH ${GSOAP_PATH_FOUND} CACHE PATH "Path where gSOAP is installed" FORCE)

# Find host executables
IF(CMAKE_CROSSCOMPILING)
    SET(DPWS_HOSTEXEC_PATH "/usr/local" CACHE PATH "Path where gSOAP is installed")
    FIND_PACKAGE(DPWSHostExecutables REQUIRED)
    SET(DPWS_HOSTEXEC_PATH ${DPWS_HOSTEXEC_PATH_FOUND} CACHE PATH "Path where gSOAP is installed" FORCE)
ENDIF(CMAKE_CROSSCOMPILING)

FIND_PACKAGE(LATEX QUIET)

FIND_PACKAGE(Doxygen QUIET)

# Include the needed cmake-Files.
INCLUDE(WS4D-Misc) # Collection of own macros.

# Set the output paths.
SET(EXECUTABLE_OUTPUT_PATH ${CMAKE_BINARY_DIR}/bin)
SET(LIBRARY_OUTPUT_PATH ${CMAKE_BINARY_DIR}/lib)

SET(DPWS_WS_DIR
	${CMAKE_BINARY_DIR}/WS)

SET(DPWS_INCLUDE_DIR
	${CMAKE_SOURCE_DIR}/include)

SET(DPWS_GENERATED_INCLUDE_DIR
	${CMAKE_BINARY_DIR}/include)

SET(DPWS_INCLUDES
	${DPWS_INCLUDE_DIR}
	${DPWS_GENERATED_INCLUDE_DIR})

SET(DPWS_SRC_DIR
	${CMAKE_SOURCE_DIR}/src)

SET(LIBDPWS_SRC_DIR
	${DPWS_SRC_DIR}/libdpws)

FIND_PROGRAM(DPWS_XSLTPROC_BINARY NAMES xsltproc xsltproc.exe PATHS ${CMAKE_SOURCE_DIR}/bin)
IF(${DPWS_XSLTPROC_BINARY-NOTFOUND})
	MESSAGE(FATAL_ERROR "xsltproc not found! See http://www.gnu.org/software/patch/patch.html for details.")
ELSE(${DPWS_XSLTPROC_BINARY-NOTFOUND})
	MESSAGE(STATUS "Using xsltproc: ${DPWS_XSLTPROC_BINARY}")
ENDIF(${DPWS_XSLTPROC_BINARY-NOTFOUND})

FIND_PROGRAM(GNU_PATCH_BINARY NAMES patch patch.exe PATHS ${CMAKE_SOURCE_DIR}/bin)
IF(${GNU_PATCH_BINARY-NOTFOUND})
	MESSAGE(FATAL_ERROR "GNU patch not found! See http://www.gnu.org/software/patch/patch.html for details.")
ELSE(${GNU_PATCH_BINARY-NOTFOUND})
	MESSAGE(STATUS "Using gnu patch: ${GNU_PATCH_BINARY}")
ENDIF(${GNU_PATCH_BINARY-NOTFOUND})

FIND_PROGRAM(GNU_SED_BINARY NAMES sed sed.exe PATHS ${CMAKE_SOURCE_DIR}/bin)
IF(${GNU_SED_BINARY-NOTFOUND})
	MESSAGE(FATAL_ERROR "GNU sed not found! See http://www.gnu.org/software/sed/ for details.")
ELSE(${GNU_SED_BINARY-NOTFOUND})
	MESSAGE(STATUS "Using sed: ${GNU_SED_BINARY}")
ENDIF(${GNU_SED_BINARY-NOTFOUND})

IF(CMAKE_CROSSCOMPILING)
    MESSAGE(STATUS "Doing a cross compile CC=${CMAKE_C_COMPILER}")
ENDIF(CMAKE_CROSSCOMPILING)

# Show the important directories.
MESSAGE(STATUS "Using WS dir: ${DPWS_WS_DIR}")
MESSAGE(STATUS "Using include dir: ${DPWS_INCLUDE_DIR}")
MESSAGE(STATUS "Using src dir: ${DPWS_SRC_DIR}")
MESSAGE(STATUS "Using executable dir: ${EXECUTABLE_OUTPUT_PATH}")
MESSAGE(STATUS "Using library dir: ${LIBRARY_OUTPUT_PATH}")

# Set a default build type for single-configuration
# CMake generators if no build type is set.
IF(NOT MSVC)
  IF(NOT CMAKE_CONFIGURATION_TYPES AND NOT CMAKE_BUILD_TYPE)
    MESSAGE(STATUS "Build type not set, defaulting to DEBUG!")
    SET(CMAKE_BUILD_TYPE Debug CACHE STRING
      "Choose the type of build, options are: None Debug Release RelWithDebInfo MinSizeRel."
      FORCE)
  ENDIF(NOT CMAKE_CONFIGURATION_TYPES AND NOT CMAKE_BUILD_TYPE)
ENDIF(NOT MSVC)

# Configuration Options

# Compiler warings (default: off)
SET (COMPILER_WARNINGS OFF CACHE BOOL "Toggle compiler warnings")
IF (COMPILER_WARNINGS)
    IF (CMAKE_COMPILER_IS_GNUCC AND NOT CMAKE_CROSSCOMPILING)
        ADD_DEFINITIONS(-Wall -Wextra)
    ELSE (CMAKE_COMPILER_IS_GNUCC AND NOT CMAKE_CROSSCOMPILING)
# TODO enable warnings for other compilers here
    ENDIF(CMAKE_COMPILER_IS_GNUCC AND NOT CMAKE_CROSSCOMPILING)
ENDIF(COMPILER_WARNINGS)

# Abort on assert() (default: off)
SET (ABORT_ON_ASSERT OFF CACHE BOOL "Abort programm on assert")
IF(ABORT_ON_ASSERT)
SET (CMAKE_C_FLAGS_DEBUG "${CMAKE_C_FLAGS_DEBUG} -DDEBUG -DABORT_ON_ASSERT")
ELSE(ABORT_ON_ASSERT)
SET (CMAKE_C_FLAGS_DEBUG "${CMAKE_C_FLAGS_DEBUG} -DDEBUG")
ENDIF(ABORT_ON_ASSERT)

# Generate PDF documentation (default: off)
SET (PDF_DOCU OFF CACHE BOOL "Toggle generation of pdf docu")

# Activate hosting service (default: on)
SET (WITH_HOSTING_SERVICE ON CACHE BOOL "Toggle generation of hosting_service")

# Activate hosting service (default: on)
SET (WITH_LCM ON CACHE BOOL "Toggle generation of life cycle manager")

# Activate gSOAP WS-Security support (default: off)
SET (WITH_WS_SECURITY OFF CACHE BOOL "Toggle gSOAP WS-Security support")

# tell cmake to process CMakeLists.txt in that subdirectory
ADD_SUBDIRECTORY(WS)
ADD_SUBDIRECTORY(src)
ADD_SUBDIRECTORY(include)
ADD_SUBDIRECTORY(cmake-modules)
ADD_SUBDIRECTORY(bin)
ADD_SUBDIRECTORY(doc)

MARK_AS_ADVANCED(
    EXECUTABLE_OUTPUT_PATH
    LIBRARY_OUTPUT_PATH
)
