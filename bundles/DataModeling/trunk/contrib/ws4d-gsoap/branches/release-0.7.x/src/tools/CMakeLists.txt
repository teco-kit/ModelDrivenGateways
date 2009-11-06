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

IF(COMMAND CMAKE_POLICY)
	CMAKE_POLICY(SET CMP0003 NEW)
ENDIF(COMMAND CMAKE_POLICY)

INCLUDE_DIRECTORIES( ${GSOAP_INCLUDE_DIR} ${DPWS_INCLUDES})

# stdsoap2.c
GSOAP_SET_RUNTIME_FLAGS("-DWITH_NONAMESPACES -DWITH_UDP")

SET(DPWS_DEVICE_METADATATOC ${CMAKE_CURRENT_SOURCE_DIR}/deviceMetadataToC.xslt CACHE PATH "")
SET(DPWS_DEVICE_METADATATOH ${CMAKE_CURRENT_SOURCE_DIR}/deviceMetadataToH.xslt CACHE PATH "")
SET(DPWS_HOSTED_METADATATOC ${CMAKE_CURRENT_SOURCE_DIR}/hostedMetadataToC.xslt CACHE PATH "")
SET(DPWS_HOSTED_METADATATOH ${CMAKE_CURRENT_SOURCE_DIR}/hostedMetadataToH.xslt CACHE PATH "")

# embedwsdl
IF(NOT CMAKE_CROSSCOMPILING)

	ADD_EXECUTABLE(embedwsdl
		embedwsdl.c)
		
	GET_TARGET_PROPERTY(EMBEDWSDL_BINARY embedwsdl LOCATION)
    SET(DPWS_EMBEDWSDL ${EMBEDWSDL_BINARY} CACHE PATH "")
    
ELSE(NOT CMAKE_CROSSCOMPILING)

	ADD_EXECUTABLE(embedwsdl
		embedwsdl.c)

	FIND_PROGRAM(DPWS_EMBEDWSDL
 		NAMES embedwsdl
		PATHS ${DPWS_EMBEDWSDL_PATH}
		NO_DEFAULT_PATH
		NO_CMAKE_ENVIRONMENT_PATH
		NO_CMAKE_PATH
		NO_SYSTEM_ENVIRONMENT_PATH
		NO_CMAKE_SYSTEM_PATH)

	IF(NOT DPWS_EMBEDWSDL)
		SET(ERROR_MSG "${ERROR_MSG} can't find host executable embedwsdl in ${DPWS_EMBEDWSDL_PATH}")
	ENDIF(NOT DPWS_EMBEDWSDL)
  
ENDIF(NOT CMAKE_CROSSCOMPILING)

ADD_CUSTOM_TARGET(tool_embedwsdl DEPENDS ${DPWS_EMBEDWSDL})

# supervisor
SET_SOURCE_FILES_PROPERTIES(supervisor.c
	PROPERTIES COMPILE_FLAGS "-DWITH_MUTEXES")

ADD_EXECUTABLE(supervisor
	supervisor.c ${GSOAP_STDSOAP2_SOURCE})

TARGET_LINK_LIBRARIES(supervisor
	dpwsS
	dpwsCmt
	ws4d_listLmt
	${CMAKE_THREAD_LIBS_INIT})

ADD_DEPENDENCIES(supervisor
	dpwsS
	dpwsCmt
	ws4d_listLmt)


# resolve
ADD_EXECUTABLE(resolve
	resolve.c ${GSOAP_STDSOAP2_SOURCE})

TARGET_LINK_LIBRARIES(resolve
	dpwsS
	dpwsC
	ws4d_listL)
	
ADD_DEPENDENCIES(resolve
	dpwsS
	dpwsC
	ws4d_listL)


# probe
ADD_EXECUTABLE(probe
	probe.c ${GSOAP_STDSOAP2_SOURCE})

TARGET_LINK_LIBRARIES(probe
	dpwsS
	dpwsC
	ws4d_listL)

ADD_DEPENDENCIES(probe
	dpwsS
	dpwsC
	ws4d_listL)


# dprobe
ADD_EXECUTABLE(dprobe
	dprobe.c ${GSOAP_STDSOAP2_SOURCE})

TARGET_LINK_LIBRARIES(dprobe
	dpwsS
	dpwsC
	ws4d_listL)

ADD_DEPENDENCIES(dprobe
	dpwsS
	dpwsC
	ws4d_listL)


# describe
ADD_EXECUTABLE(describe
	describe.c ${GSOAP_STDSOAP2_SOURCE})

TARGET_LINK_LIBRARIES(describe
	dpwsS
	dpwsC
	ws4d_listL)
	
ADD_DEPENDENCIES (describe
	dpwsS
	dpwsC
	ws4d_listL)


# subscribe
ADD_EXECUTABLE(subscribe
	subscribe.c ${GSOAP_STDSOAP2_SOURCE})

TARGET_LINK_LIBRARIES(subscribe
	dpwsS
	dpwsC
	ws4d_listL)

ADD_DEPENDENCIES (subscribe
	dpwsS
	dpwsC
	ws4d_listL)


# unsubscribe
ADD_EXECUTABLE(unsubscribe
	unsubscribe.c ${GSOAP_STDSOAP2_SOURCE})

TARGET_LINK_LIBRARIES(unsubscribe
	dpwsS
	dpwsC
	ws4d_listL)

ADD_DEPENDENCIES (unsubscribe
	dpwsS
	dpwsC
	ws4d_listL)


# subsstatus
ADD_EXECUTABLE(subsstatus
	subsstatus.c ${GSOAP_STDSOAP2_SOURCE})

TARGET_LINK_LIBRARIES(subsstatus
	dpwsS
	dpwsC
	ws4d_listL)

ADD_DEPENDENCIES (subsstatus
	dpwsS
	dpwsC
	ws4d_listL)

INSTALL(TARGETS embedwsdl describe dprobe probe resolve subscribe subsstatus supervisor unsubscribe
	RUNTIME DESTINATION bin)

FILE(WRITE ${EXECUTABLE_OUTPUT_PATH}/HostExecutables.cmake "SET(DPWS_EMBEDWSDL_PATH ${CMAKE_INSTALL_PREFIX}/bin CACHE PATH \"\")\n")
FILE(APPEND ${EXECUTABLE_OUTPUT_PATH}/HostExecutables.cmake "MARK_AS_ADVANCED(DPWS_EMBEDWSDL_PATH)")

INSTALL(FILES ${EXECUTABLE_OUTPUT_PATH}/HostExecutables.cmake DESTINATION share/ws4d-gSOAP)

IF(CMAKE_CROSSCOMPILING)
	INSTALL(PROGRAMS ${DPWS_EMBEDWSDL} DESTINATION bin RENAME embedwsdl_host)
ENDIF(CMAKE_CROSSCOMPILING)	

INSTALL(FILES deviceMetadataToC.xslt deviceMetadataToH.xslt hostedMetadataToC.xslt hostedMetadataToH.xslt invert.xslt DESTINATION share/ws4d-gSOAP)

MARK_AS_ADVANCED(
	DPWS_EMBEDWSDL
	DPWS_DEVICE_METADATATOC
	DPWS_DEVICE_METADATATOH
	DPWS_HOSTED_METADATATOC
	DPWS_HOSTED_METADATATOH
)
