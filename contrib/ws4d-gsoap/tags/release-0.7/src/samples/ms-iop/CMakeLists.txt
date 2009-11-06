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

IF(NOT CMAKE_CROSSCOMPILING)

SET(gen_DIR ${CMAKE_CURRENT_BINARY_DIR}/gen)

# If the directory for the generated sources does not
# already exists, create it.
IF(NOT EXISTS ${gen_DIR})
	FILE(MAKE_DIRECTORY ${gen_DIR})
ENDIF(NOT EXISTS ${gen_DIR})

INCLUDE_DIRECTORIES(${GSOAP_INCLUDE_DIR} ${DPWS_INCLUDES} ${gen_DIR} ${CMAKE_CURRENT_SOURCE_DIR})

GSOAP_GENERATE(SimpleService.h sis ${gen_DIR})
GSOAP_GENERATE(AttachmentService.h ats ${gen_DIR})
GSOAP_GENERATE(EventingService.h evs ${gen_DIR})

# generate device description and setup code
DPWS_METADATA_GENERATE(metadata.xml msiop ${gen_DIR})

DPWS_EMBEDD_WSDL(${gen_DIR} msiop SimpleService.wsdl AttachmentService.wsdl EventingService.wsdl)

# stdsoap2.c
GSOAP_SET_RUNTIME_FLAGS("-DWITH_NONAMESPACES -DWITH_UDP")

SET(iop_device_SRCS
	iop_device.c
	fileio.c
	SimpleService.c
	AttachmentService.c
	EventingService.c
	${GSOAP_STDSOAP2_SOURCE}
	${gen_DIR}/sisServerLib.c
	${gen_DIR}/atsServerLib.c
	${gen_DIR}/evsClientLib.c
	${gen_DIR}/msiop_metadata.c
	${gen_DIR}/msiop_wsdl.c)

EXTEND_COMPILE_FLAGS(${iop_device_SRCS}
	FLAGS "-DDPWS_DEVICE")
	
# iop_device
ADD_EXECUTABLE(iop_device
	${iop_device_SRCS})

TARGET_LINK_LIBRARIES(iop_device ${DPWS_LIBRARIES} ${DPWS-D_LIBRARIES})

ADD_DEPENDENCIES(iop_device tool_embedwsdl)


# files for iop_device
GET_TARGET_PROPERTY(iop_device_LOCATION iop_device LOCATION)
GET_FILENAME_COMPONENT(iop_device_PATH ${iop_device_LOCATION} PATH)

ADD_CUSTOM_TARGET(iop_device_pics ALL
	COMMAND ${CMAKE_COMMAND} -E copy ${CMAKE_CURRENT_SOURCE_DIR}/dpws1ref.jpg ${iop_device_PATH}/
	COMMAND ${CMAKE_COMMAND} -E copy ${CMAKE_CURRENT_SOURCE_DIR}/dpws2.jpg ${iop_device_PATH}/
	DEPENDS iop_device)


# call_SimpleService
ADD_EXECUTABLE(call_SimpleService
	call_SimpleService.c fileio.c ${GSOAP_STDSOAP2_SOURCE} ${gen_DIR}/sisClientLib.c)

TARGET_LINK_LIBRARIES(call_SimpleService ${DPWS_LIBRARIES} ${DPWS-C_LIBRARIES})
	
	
# call_AttachmentService
ADD_EXECUTABLE(call_AttachmentService
	call_AttachmentService.c fileio.c ${GSOAP_STDSOAP2_SOURCE} ${gen_DIR}/atsClientLib.c)

TARGET_LINK_LIBRARIES(call_AttachmentService ${DPWS_LIBRARIES} ${DPWS-C_LIBRARIES})

ENDIF(NOT CMAKE_CROSSCOMPILING)
