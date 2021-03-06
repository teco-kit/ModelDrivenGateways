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

IF("${ALT_IO_MODE}" STREQUAL "none")
  INCLUDE_DIRECTORIES(AFTER ${GSOAP_INCLUDE_DIR} ${DPWS_INCLUDES} ${CMAKE_CURRENT_BINARY_DIR})
ELSE("${ALT_IO_MODE}" STREQUAL "none")
  ADD_DEFINITIONS ( "-DWITH_NOIO -DWITH_SOAPDEFS_H" )
  INCLUDE_DIRECTORIES(AFTER ${DPWS_ALT_IO_INCLUDES} ${GSOAP_INCLUDE_DIR} ${DPWS_INCLUDES} ${CMAKE_CURRENT_BINARY_DIR})
ENDIF("${ALT_IO_MODE}" STREQUAL "none")

SET(HOSTINGSERVICE_CONFIG_FILE ${CMAKE_INSTALL_PREFIX}/etc/ws4d-gSOAP/conf.d/hosting.xml CACHE STRING "" FORCE)
SET(HOSTEDSERVICE_META_DIR ${CMAKE_INSTALL_PREFIX}/etc/ws4d-gSOAP/meta.d CACHE STRING "" FORCE)
SET(HOSTEDSERVICE_CONF_DIR ${CMAKE_INSTALL_PREFIX}/etc/ws4d-gSOAP/conf.d CACHE STRING "" FORCE)
SET(HOSTEDSERVICE_INIT_DIR ${CMAKE_INSTALL_PREFIX}/etc/ws4d-gSOAP/init.d CACHE STRING "" FORCE)
SET(HOSTEDSERVICE_ANNC_DIR ${CMAKE_INSTALL_PREFIX}/etc/ws4d-gSOAP/announce.d CACHE STRING "" FORCE)
SET(HOSTEDSERVICE_BIN_DIR ${CMAKE_INSTALL_PREFIX}/bin CACHE STRING "" FORCE)
SET(HOSTEDSERVICE_RUN_DIR ${CMAKE_INSTALL_PREFIX}/var/run CACHE STRING "" FORCE)

CONFIGURE_FILE(${CMAKE_CURRENT_SOURCE_DIR}/config.h.in
               ${CMAKE_CURRENT_BINARY_DIR}/config.h
               @ONLY IMMEDIATE)

CONFIGURE_FILE(${CMAKE_CURRENT_SOURCE_DIR}/start-stop-service.in
               ${CMAKE_CURRENT_BINARY_DIR}/start-stop-service
               @ONLY IMMEDIATE)

# stdsoap2.c
GSOAP_SET_RUNTIME_FLAGS("-DWITH_NONAMESPACES -DWITH_UDP")

# hosting_service
ADD_EXECUTABLE(hosting_service
	hosting_service.c
	)

TARGET_LINK_LIBRARIES(hosting_service
	${DPWS_LIBRARIES}
	${DPWS-D_LIBRARIES}
	${OPENSSL_LIBRARIES})

# files for hosting_service

INSTALL(TARGETS hosting_service RUNTIME DESTINATION bin)

INSTALL(FILES example-hosting-metadata.xml RENAME hosting.xml DESTINATION etc/ws4d-gSOAP/conf.d/)
INSTALL(FILES ${CMAKE_CURRENT_BINARY_DIR}/start-stop-service DESTINATION etc/ws4d-gSOAP/conf.d/)
INSTALL(FILES ${CMAKE_CURRENT_SOURCE_DIR}/start_service.sh ${CMAKE_CURRENT_SOURCE_DIR}/stop_service.sh ${CMAKE_CURRENT_SOURCE_DIR}/debug_service.sh
		DESTINATION etc/ws4d-gSOAP/
		PERMISSIONS OWNER_EXECUTE OWNER_WRITE OWNER_READ GROUP_EXECUTE GROUP_READ WORLD_READ WORLD_EXECUTE)
INSTALL(FILES ${CMAKE_CURRENT_SOURCE_DIR}/hostingservice_start.sh ${CMAKE_CURRENT_SOURCE_DIR}/hostingservice_stop.sh ${CMAKE_CURRENT_SOURCE_DIR}/hostingservice_debug.sh
		DESTINATION etc/ws4d-gSOAP/
		PERMISSIONS OWNER_EXECUTE OWNER_WRITE OWNER_READ GROUP_EXECUTE GROUP_READ WORLD_READ WORLD_EXECUTE)

INSTALL(DIRECTORY meta.d DESTINATION etc/ws4d-gSOAP
	PATTERN ".svn" EXCLUDE)
INSTALL(DIRECTORY conf.d DESTINATION etc/ws4d-gSOAP
	PATTERN ".svn" EXCLUDE)
INSTALL(DIRECTORY init.d DESTINATION etc/ws4d-gSOAP
	PATTERN ".svn" EXCLUDE)
INSTALL(DIRECTORY announce.d DESTINATION etc/ws4d-gSOAP
	PATTERN ".svn" EXCLUDE)
	
IF(NOT EXISTS ${CMAKE_INSTALL_PREFIX}/var/run)
INSTALL(DIRECTORY run DESTINATION var/
	PATTERN ".svn" EXCLUDE
	PATTERN "README" EXCLUDE)
ENDIF(NOT EXISTS ${CMAKE_INSTALL_PREFIX}/var/run)

MARK_AS_ADVANCED(
	HOSTINGSERVICE_CONFIG_FILE
	HOSTEDSERVICE_META_DIR
	HOSTEDSERVICE_CONF_DIR
	HOSTEDSERVICE_INIT_DIR
	HOSTEDSERVICE_ANNC_DIR
	HOSTEDSERVICE_BIN_DIR
	HOSTEDSERVICE_RUN_DIR)
