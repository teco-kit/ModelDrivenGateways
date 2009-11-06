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

SET(wsdl2h_OPTS -cgmy)

# Note: This directory also contains additional XML-schemata, but
# 	these can't be generated with wsdl2h. That's why they are
#	not listed here.
SET(xsd_FILES 
	${CMAKE_CURRENT_SOURCE_DIR}/wsa
	${CMAKE_CURRENT_SOURCE_DIR}/wsd
	${CMAKE_CURRENT_SOURCE_DIR}/wsdp
	${CMAKE_CURRENT_SOURCE_DIR}/mex
	${CMAKE_CURRENT_SOURCE_DIR}/wse
	${CMAKE_CURRENT_SOURCE_DIR}/wst
)
ADD_SUFFIX(xsd_FILES .xsd)

SET(gsoap_GENERATED_FILES 
	${CMAKE_CURRENT_BINARY_DIR}/wsa
	${CMAKE_CURRENT_BINARY_DIR}/wsd
	${CMAKE_CURRENT_BINARY_DIR}/wsdp
	${CMAKE_CURRENT_BINARY_DIR}/mex
	${CMAKE_CURRENT_BINARY_DIR}/wse
	${CMAKE_CURRENT_BINARY_DIR}/wst
)
ADD_SUFFIX(gsoap_GENERATED_FILES .h)
SET_SOURCE_FILES_PROPERTIES( ${gsoap_GENERATED_FILES}
	PROPERTIES GENERATED TRUE)

IF(NOT WITH_WS_SECURITY)

	SET (gsoap_COPIED_FILES
		${DPWS_WS_DIR}/DPWS-typemap.dat
		${DPWS_WS_DIR}/xsd.h
		${DPWS_WS_DIR}/xop.h
		${DPWS_WS_DIR}/devprof_2006_02.gsoap)

	ADD_CUSTOM_COMMAND(
		OUTPUT ${gsoap_COPIED_FILES}
		COMMAND ${CMAKE_COMMAND} -E copy ${CMAKE_CURRENT_SOURCE_DIR}/DPWS-typemap.dat ${DPWS_WS_DIR}/DPWS-typemap.dat
		COMMAND ${CMAKE_COMMAND} -E copy ${CMAKE_CURRENT_SOURCE_DIR}/xsd.h ${DPWS_WS_DIR}/xsd.h
		COMMAND ${CMAKE_COMMAND} -E copy ${CMAKE_CURRENT_SOURCE_DIR}/xop.h ${DPWS_WS_DIR}/xop.h
		COMMAND ${CMAKE_COMMAND} -E copy ${CMAKE_CURRENT_SOURCE_DIR}/devprof_2006_02.gsoap ${DPWS_WS_DIR}/devprof_2006_02.gsoap)
		
ELSE(NOT WITH_WS_SECURITY)

	SET (gsoap_COPIED_FILES
		${DPWS_WS_DIR}/DPWS-typemap.dat
		${DPWS_WS_DIR}/xsd.h
		${DPWS_WS_DIR}/xop.h
		${DPWS_WS_DIR}/c14n.h
		${DPWS_WS_DIR}/ds.h
		${DPWS_WS_DIR}/wsu.h
		${DPWS_WS_DIR}/wsse.h
		${DPWS_WS_DIR}/devprof_2006_02.gsoap)
		
	ADD_CUSTOM_COMMAND(
		OUTPUT ${gsoap_COPIED_FILES}
		COMMAND ${CMAKE_COMMAND} -E copy ${CMAKE_CURRENT_SOURCE_DIR}/DPWS-typemap.dat-wsse ${DPWS_WS_DIR}/DPWS-typemap.dat
		COMMAND ${CMAKE_COMMAND} -E copy ${CMAKE_CURRENT_SOURCE_DIR}/xsd.h ${DPWS_WS_DIR}/xsd.h
		COMMAND ${CMAKE_COMMAND} -E copy ${CMAKE_CURRENT_SOURCE_DIR}/xop.h ${DPWS_WS_DIR}/xop.h
		COMMAND ${CMAKE_COMMAND} -E copy ${CMAKE_CURRENT_SOURCE_DIR}/c14n.h ${DPWS_WS_DIR}/c14n.h
		COMMAND ${CMAKE_COMMAND} -E copy ${CMAKE_CURRENT_SOURCE_DIR}/ds.h ${DPWS_WS_DIR}/ds.h
		COMMAND ${CMAKE_COMMAND} -E copy ${CMAKE_CURRENT_SOURCE_DIR}/wsu.h ${DPWS_WS_DIR}/wsu.h
		COMMAND ${CMAKE_COMMAND} -E copy ${CMAKE_CURRENT_SOURCE_DIR}/wsse.h ${DPWS_WS_DIR}/wsse.h
		COMMAND ${CMAKE_COMMAND} -E copy ${CMAKE_CURRENT_SOURCE_DIR}/devprof_2006_02.gsoap-wsse ${DPWS_WS_DIR}/devprof_2006_02.gsoap)
		
ENDIF(NOT WITH_WS_SECURITY)

SET(typemap_FILE ${DPWS_WS_DIR}/DPWS-typemap.dat)

DO_WSDL2H(outfiles ${wsdl2h_OPTS} ${typemap_FILE} ${xsd_FILES})

SET(install_FILES
	${gsoap_GENERATED_FILES}
	${gsoap_COPIED_FILES}
)

ADD_CUSTOM_TARGET(gen_headers 
	DEPENDS ${install_FILES})

INSTALL(FILES ${install_FILES}
	DESTINATION include/ws4d-gSOAP/WS)
