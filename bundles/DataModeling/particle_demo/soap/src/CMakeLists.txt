################################################################################
# Common for all ws4d-gsoap projects, don't change this !
################################################################################

# directory to put all generated files in
SET(gen_DIR ${CMAKE_CURRENT_BINARY_DIR}/gen)

# If the directory for the generated sources does not
# already exists, create it.
IF(NOT EXISTS ${gen_DIR})
	FILE(MAKE_DIRECTORY ${gen_DIR})
ENDIF(NOT EXISTS ${gen_DIR})

# set include paths
INCLUDE_DIRECTORIES(${GSOAP_INCLUDE_DIR} ${DPWS_INCLUDES} ${gen_DIR})

# set options for gSOAP runtime
GSOAP_SET_RUNTIME_FLAGS("-DWITH_NONAMESPACES -DWITH_UDP")

###############################################################################
###############################################################################
##                                                                           ##
## Simple sensorValues device                                                ##
##                                                                           ##
###############################################################################
###############################################################################
SET(nsPrefix teco)

###############################################################################
# Code generation - for custom projects this must be modified
###############################################################################

# generate code and C data binding operations of service
GSOAP_GENERATE(${nsPrefix}.gsoap ${nsPrefix} ${gen_DIR})   # normal service

# generate device description and setup code
DPWS_METADATA_GENERATE(metadata.xml ${nsPrefix} ${gen_DIR})

# embed wsdl files into c code
SET(SENSORVALUES_WSDL ${CMAKE_CURRENT_SOURCE_DIR}/SensorValues.wsdl)
DPWS_EMBEDD_WSDL(${gen_DIR} ${nsPrefix} ${SENSORVALUES_WSDL})

################################################################################
# create config.h
################################################################################

CONFIGURE_FILE(${CMAKE_CURRENT_SOURCE_DIR}/config.h.in
               ${gen_DIR}/config.h
               @ONLY IMMEDIATE)
################################################################################
# for custom projects this must be modified
################################################################################

###############################################################################
###############################################################################
##                                                                           ##
## device with eventing                                                      ##
##                                                                           ##
###############################################################################
###############################################################################

# generate code and C data binding for events of service
GSOAP_GENERATE(${nsPrefix}_inv.gsoap ${nsPrefix}_inv ${gen_DIR}) # event service
SET(AUTOMATA_GEN_DIR ${CMAKE_CURRENT_SOURCE_DIR}/../../gen/c)

SET (advanced_device_SRCS
   ${AUTOMATA_GEN_DIR}/advanced_device.c
	${AUTOMATA_GEN_DIR}/deliver_event.c
	${AUTOMATA_GEN_DIR}/gsoap_encoder_automata.c
	${AUTOMATA_GEN_DIR}/gsoap_encoder_automata_inv.c
	${CMAKE_CURRENT_SOURCE_DIR}/../../bitsio/read_bits_buf.c
	${CMAKE_CURRENT_SOURCE_DIR}/../../enc/read_str_func.c
	event_worker.c
	${GSOAP_STDSOAP2_SOURCE}
	${gen_DIR}/${nsPrefix}ServerLib.c
	${gen_DIR}/${nsPrefix}_invClientLib.c
	${gen_DIR}/${nsPrefix}_metadata.c
	${gen_DIR}/${nsPrefix}_wsdl.c)

EXTEND_COMPILE_FLAGS(${advanced_device_SRCS}
	FLAGS "-DDPWS_DEVICE -DWITH_MUTEXES -I/home/teco/include")

# compile all files in simple_sensorvalues_SRCS and link them into
# the device executable
ADD_EXECUTABLE(${nsPrefix}_advanced_device ${advanced_device_SRCS})

# link the device specific dpws libraries into the executable
TARGET_LINK_LIBRARIES(${nsPrefix}_advanced_device
	particle ${DPWS_LIBRARIES} ${DPWS-DMT_LIBRARIES} ${CMAKE_THREAD_LIBS_INIT})

