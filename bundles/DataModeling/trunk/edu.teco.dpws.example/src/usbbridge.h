/*
 * Copyright (c) 2007
 * Telecooperation Office (TecO), Universitaet Karlsruhe (TH), Germany.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above
 *    copyright notice, this list of conditions and the following
 *    disclaimer in the documentation and/or other materials provided
 *    with the distribution.
 * 3. Neither the name of the Universitaet Karlsruhe (TH) nor the names
 *    of its contributors may be used to endorse or promote products
 *    derived from this software without specific prior written
 *    permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Author(s): Philipp Scholl <scholl@teco.edu>
 */

#ifndef _USBBRIDGE_H_
#define _USBBRIDGE_H_

#include <stdint.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>
#include <concom.h>

/** the state of the bridge
 */
enum usbbridge_state
{
  CONNECTED,
  DISCONNECTED,
  PORTS_CHANGED,
};

/** modes the bridge can work under
 */
enum usbbridge_mode
{
  MODE_PARTICLE,
  MODE_CHCON
};

/** the usbbridge defines a connection between a particle bridge
 * and the ethernet.
 *
 * public values are defined with a normal name.
 * private values are defined with a _ prefix.
 */
typedef struct
{
  void *eventdevice;   

  struct ftdi_context *ftdic;       // connection handle to the usb chip

  size_t crc_errors;                // number of crc errors
  size_t usb_errors;                // number of errors during bus operations (incomplete reads etc.)
  size_t total_packets_recvd;       // total number of packets received
  size_t total_bytes_recvd;         // total number of bytes received
  size_t total_packets_sent;        // total number of packets sent
  size_t total_bytes_sent;          // total number of bytes sent
  size_t skipped;                   // number of skipped packets from eth

  enum usbbridge_state state;       // current state of the bridge
  enum usbbridge_mode  mode;        // communication mode: particle or chcon

  // XXX: not used at the moment
  // struct usbbridge_plugin *plugins; // a list of plugins that are enabled on
  // size_t plugins_len;               // this bridge

  char error[256];                  // error string explaining the cause
  char errbuf[256];                 // temporary buffer for building err strings

  char *bus;                        // usb bus file name
  char *dev;                        // usb device file name

  int timeout;                      // the latency timer
  bool show_packets;                // if true packets passing through the bridge are put out on stdout
  bool geo_filtering;               // turn on the location filter, see usbbridge_forward()
} usbbridge;


/** initializes a usbbridge structure.
 *
 * @return 0 if OK, -1 if ftdi_init() failed
 */
int
usbbridge_init(usbbridge *bridge);

/** execute one step of a bridging loop.
 *
 * If the bridge is in disconnected state -> connect it.
 * If the ports have been changed -> reconnect it.
 */
int
usbbridge_step(usbbridge *bridge);

/** sets the usb bus number, to search a device on.
 *
 * @param bridge bridge object to modify
 * @param bus    bus number or NULL if don't care.
 */
void
usbbridge_set_bus(usbbridge *bridge, char *bus);

/** sets the usb device number.
 *
 * @param bridge bridge object to modify
 * @param dev    device number or NULL if don't care.
 */
void
usbbridge_set_dev(usbbridge *bridge, char *dev);

/** sets the particle port.
 */
void
usbbridge_set_eventdevice(usbbridge *bridge, void *device);


/** set the communication mode for the ftdi chip
 *
 * @parameter mode one of "chipcon" or "particle"
 * @return -1 on error, 0 on success
 */
int
usbbridge_set_mode(usbbridge *bridge, char* mode);

/** set the location of the usbbridge
 *
 * There are three formats for the location string:
 *
 *  * pure bytes, "0.0.0....0", 44 bytes seperated by dots.
 *  * root as bytes, levels as string, "0.0...0 [a,b,c,d] [0/0/0]@0" 16 bytes
 *    root field in bytes, four level identifiers as string of a maximum length
 *    10 each, x,y,z and deviation field (one byte each).
 *  * root as gps coords (a maximum of 12 post-comma chars), levels, x, y, z and
 *    deviation as above.
 *
 * @param bridge the bridge to change.
 * @param loc the location to set given in string format.
 * @return 0 on succes, -1 on errror
 */
int
usbbridge_set_location(usbbridge *bridge, char *loc);
#endif
