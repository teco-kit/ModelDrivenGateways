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
 * Author(s):   Philipp Scholl <scholl@teco.edu>
 *
 * Based on work by:
 *              Igor Kuznetsov <kuznetsov@teco.edu>
 *              Thomas Morper <morper@teco.edu>
 *
 *              Matthias Kranz (matthias@hcilab.org)
 *              Lorenz Moesenlechner (lorenz@hcilab.org)
 *              Research Group Embedded Interaction, University of Munich, Germany
 *
 * This file implements the protocol between a usbbridge and the pc. It translates
 * messages from the ethernet to those on the particle network and vice versa.
 * The usbbridge uses a serial line emulation chip manufactured by ftdi
 * (http://www.ftdichip.com).
 */
#include <sys/types.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <assert.h>

#include <ftdi.h>
#include <arpa/inet.h>


#include "usbbridge.h"
#include "concom.h"
#include "acltypes.h"
//#include "error.h"
//#include "future.h"

//#include "stats.h"
int prt2evt(usbbridge *);
int read_packet( usbbridge *, char *, size_t  );
// basic error condition macro.
#define SET_ERROR(STR, ...) \
  snprintf(bridge->errbuf, sizeof(bridge->errbuf), STR, ##__VA_ARGS__ );\
  memcpy(bridge->error, bridge->errbuf, sizeof(bridge->error));

#define TRY(COND, RET, STR, ...) do { if (COND) \
{\
  SET_ERROR(STR, ##__VA_ARGS__)\
  return RET;\
} } while(false);

// vendor and product id of the TECO USBBRIDGE
#define TECO_USB_VENDOR     0x0403
#define TECO_USB_PRODUCT    0xcc40

// define buffer maximum values
#define MAX_PCK_SIZE    256

enum usbbridge_baudrates
{
  BAUDRATE_CHCON    = 115200,
  BAUDRATE_PARTICLE = 625000
};

#if 0
void
print_packet( struct cc_pkt *p, bool incoming )
{
  cc_tuple *c; char buf[512];

  if( incoming )
    printf( "<= " );
  else
    printf( "=> " );

  printf("%s\n", cc_pkt2str( p, buf, sizeof(buf) ) );
}
#endif

void
usbbridge_set_bus(usbbridge *bridge, char *bus)
{
  bridge->bus   = bus;
  bridge->state = DISCONNECTED;
}

void
usbbridge_set_dev(usbbridge *bridge, char *dev)
{
  bridge->dev   = dev;
  bridge->state = DISCONNECTED;
}


void
usbbridge_set_eventdevice(usbbridge *bridge, void *device)
{
  bridge->eventdevice = device;
}

int
usbbridge_set_mode(usbbridge *bridge, char* mode)
{
  if( strstr(mode, "chipcon") )
  {
    if( bridge->mode == MODE_CHCON )
      return true;

    bridge->mode  = MODE_CHCON;
    bridge->state = DISCONNECTED;
    return true;
  }

  if( strstr(mode, "particle") )
  {
    if( bridge->mode == MODE_PARTICLE )
      return true;

    bridge->mode  = MODE_PARTICLE;
    bridge->state = DISCONNECTED;
    return true;
  }

  return false;
}

static char buffer1[MAX_PCK_SIZE];
static char buffer2[MAX_PCK_SIZE];

static int curr_buf = 0;
static char * global_packet_buffer = buffer1;
char * get_global_packet_buf(void)
{
   return global_packet_buffer;
}

#if 0
int
usbbridge_set_location(usbbridge *bridge, char *str)
{
  int n=0, i=0;

  for( i=0; i<strlen(str); i++ )
    if( str[i] == '.' ) n++;

  if( n == sizeof(location) - 1 )
  {
    // pure byte format
    uint8_t *buf = (uint8_t*) &bridge->loc;

    for( i=0; i<sizeof(location); i++, str=strchr(str, '.')+1 )
      sscanf(str, "%hhd", buf[i]);
  }
  else if( n == sizeof(bridge->loc.root) - 1 )
  {
    // pure byte root format
    char *node[4], *end;

    for( i=0; i<sizeof(bridge->loc.root); i++, str=strchr(str, '.')+1 )
      sscanf(str, "%hhu", bridge->loc.root[i] );

    node[0]=str=strchr(str, '[')+1;
    node[1]=str=strchr(str, ',')+1;
    node[2]=str=strchr(str, ',')+1;
    node[3]=str=strchr(str, ',')+1;
    end        =strchr(str, ']');

    if( node[0]==NULL || node[1]==NULL || node[2]==NULL || node[3]==NULL ||
        end==NULL )
    {
      ERROR( "parse error on location\n" );
      return -1;
    }

    node[1][-1]='\0'; // mark the end of node[0]
    node[2][-1]='\0'; // mark the end of node[1]
    node[3][-1]='\0'; // mark the end of node[2]
    *end       ='\0'; // mark the end of node[3]

    for( i=0; i<4; i++ )
      strncpy( bridge->loc.level[i], node[i], sizeof(bridge->loc.level[i]) );
  }
  else if( n >= 2 && n <= sizeof(bridge->loc.root) - 1 )
  {
    // gps coordinate format
    char *node[4], *end;

    cc_wkt2loc( str, strlen(str), &bridge->loc );

    node[0]=str=strchr(str, '[')+1;
    node[1]=str=strchr(str, ',')+1;
    node[2]=str=strchr(str, ',')+1;
    node[3]=str=strchr(str, ',')+1;
    end        =strchr(str, ']');

    if( node[0]==NULL || node[1]==NULL || node[2]==NULL || node[3]==NULL ||
        end==NULL )
    {
      ERROR( "parse error on location\n" );
      return -1;
    }

    node[1][-1]='\0'; // mark the end of node[0]
    node[2][-1]='\0'; // mark the end of node[1]
    node[3][-1]='\0'; // mark the end of node[2]
    *end       ='\0'; // mark the end of node[3]

    for( i=0; i<4; i++ )
      strncpy( bridge->loc.level[i], node[i], sizeof(bridge->loc.level[i]) );
  }
  else
  {
    // format unknown
    ERROR( "format for usbbridge_set_location() not known" );
    return -1;
  }
}
#endif

/** initializes a usbbridge structure.
 *
 * @return 0 if OK, -1 if ftdi_init() failed
 */
int
usbbridge_init(usbbridge *bridge)
{
  bridge->state         = DISCONNECTED;
  bridge->eventdevice        = NULL;
  bridge->bus           = NULL;
  bridge->dev           = NULL;
  bridge->ftdic         = malloc( sizeof( struct ftdi_context ) );
  bridge->timeout       = 10;  // set the latency timer to 10ms
  bridge->mode          = MODE_PARTICLE;
  bridge->show_packets  = false;
  bridge->geo_filtering = false;

  //memset( (char*) &bridge->loc, 0, sizeof(location) ); // nullify the location

  ftdi_init( bridge->ftdic );

  return 0;
}

/** Initializes the ftdi chip serial connection.
 *
 * @param bridge bridge context to use.
 * @param dev usb device to connect to.
 * @return 0 if ok, -1 on error
 */
int
usbbridge_setup(usbbridge *bridge, struct usb_device *dev)
{
  struct ftdi_context *ftdic = bridge->ftdic;
  int baudrate = 0;

  TRY( ftdi_usb_open_dev( ftdic, dev ), -1,
       "could not open device: %s", ftdi_get_error_string( ftdic ) )

  TRY( ftdi_set_line_property( ftdic, BITS_8, STOP_BIT_1, NONE ),
       -1, "unable to set line characteristics: %s",
       ftdi_get_error_string( ftdic ) )

  TRY( ftdi_setflowctrl( ftdic, SIO_RTS_CTS_HS ),
       -1, "unable to set flow control: %s",
       ftdi_get_error_string( ftdic ) )

  TRY( ftdi_set_latency_timer( ftdic, bridge->timeout ),
       -1, "unable to set latency timeout: %s",
       ftdi_get_error_string( ftdic ) )

  //XXX cc_set_ports(bridge->inport, bridge->outport);

  switch(bridge->mode)
  {
    case MODE_PARTICLE:
      baudrate = BAUDRATE_PARTICLE;
      break;
    case MODE_CHCON:
      baudrate = BAUDRATE_CHCON;
      break;
    default:
      printf( "unknown baudrate" );
    // TODO: autonegation
  }

  TRY( ftdi_set_baudrate( ftdic, baudrate ), -1,
       "unable to set baudrate: %s",
       ftdi_get_error_string( ftdic ) )

  ftdi_usb_purge_buffers( bridge->ftdic );

  return 0;
}

/** tries to connect to the usb device and initialize the communication.
 *
 * Searches through all available usb device to find a particle usbbridge and
 * if found sets up the ftdi connection. If bridge->bus or bridge->dev are not
 * NULL, they will be used to limit the search on the usb bus. This allows to
 * connect to a specific usb device.
 *
 * @param bridge bridge context to use.
 * @return 0 if ok, -1 on a general error, -2 device not found
 */
int
usbbridge_connect(usbbridge *bridge)
{
  struct usb_bus *bus;
  struct usb_device *dev;

  // search for the usbbridge device
  usb_init();
  TRY( usb_find_busses() < 0, -1, "usb_find_busses() failed, check permissions on /proc/usb" )
  TRY( usb_find_devices() < 0, -1, "usb_find_devices() failed, check permissions on /proc/usb" )

  // walk through all busses checking if the one specified in the bridge context
  // is there, afterwards walk through all devices and look for ours.
  for( bus = usb_busses; bus; bus = bus->next)
    if( bridge->bus == NULL || strstr( bridge->bus, bus->dirname ) )
    {
      for( dev = bus->devices; dev; dev = dev->next )
        if( dev->descriptor.idVendor  == TECO_USB_VENDOR  &&
            dev->descriptor.idProduct == TECO_USB_PRODUCT &&
            ( bridge->dev == NULL || strstr( bridge->dev, dev->filename ) ) )
        {
          // okay, we found our device
          TRY( usbbridge_setup( bridge, dev ), -1, "bridge setup failed: %s", bridge->error )

          // TODO: do we really need a handshake
          //TRY( usbbridge_handshake( bridge ), -1, "handshake failed" )

          bridge->state = CONNECTED;
        }
    }

  TRY( bridge->state == DISCONNECTED, -2, "device not found" );

  return 0;
}

/** decides wether a packet should be forwarded. This is done by comparing
 * the location id of a received ethernet packet with the location set on
 * socket.
 *
 * The location of a packet is a 44-byte long field defining the geographic
 * location of a packet. As this field is only included in packets coming from
 * the ethernet it defines the geographic location the packet should be send to.
 * A matching location can be defined on the bridge itself, it is used as a
 * simple forwarding rule. This means whenever both the location of the bridge
 * and the location of received ethernet packet match it will be forwarded.
 * Zeros in the bridge location serve as wild cards.
 *
 * @param bridge bridge context to find the socket in
 * @param p      packet to decide on, assume the crc check did not fail
 *
 * @return false if not forwarding, true if forwarding
 */
#if 0
bool
usbbridge_forward(usbbridge *bridge, location *l)
{
  location wildcard = { {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}, /*root*/
                         { {0,0,0,0,0,0}, {0,0,0,0,0,0},   /*levels*/
                          {0,0,0,0,0,0}, {0,0,0,0,0,0} },
                         0,0,0,0 };                        /*x,y,z,deviation*/

  #define cmp(X) (!memcmp( (char*) &wildcard.X, (char*) &(l->X), sizeof(l->X) ) ||\
                  !memcmp( (char*) &(bridge->loc.X), (char*) &(l->X), sizeof(l->X)))

  if( bridge->geo_filtering )
    return cmp(root) && cmp(level[0]) && cmp(level[1]) && cmp(level[2]) &&
           cmp(level[3]);
  else
    return true;
}
#endif

/** Execute one step of the bridge algorithm.
 *
 * Create packet buffers and if not connected, connect to the ftdi chip and
 * ethernet. Afterwards start to relay one packet from each side.
 *
 */
int
usbbridge_step(usbbridge *bridge)
{
  int n;

  // make sure that there is enough space allocated to add the crc when
  // transmitting to the bridge
  //assert(MAX_PCK_SIZE >= sizeof(pp) + sizeof(uint16_t));

  /************
   * If the bridge is not connected do it now.
   *
   * If the ports have been changed between two steps, reconnect the bridge
   * to catch this change.
   */
  if( bridge->state == DISCONNECTED || bridge->state == PORTS_CHANGED)
    TRY( usbbridge_connect( bridge ), -1, "usbbridge_connect() failed: %s.",
         bridge->error )

  /************
   * Now run through the transformation steps
   */
  if( (n = prt2evt(bridge)) < 0 ) return n;

//  stats_recalc();

  return 0;
}
#if 0
/** test if the bridge is ready to receive.
 *
 * Note that CHIPCON bridges only support receiving so this function
 * always returns false for them.
 */
static bool
usbbridge_rtr( usbbridge *bridge )
{
  int tmp = 0;

  if( bridge->mode == MODE_CHCON )
    return false;

  // signal the chip that we like to write.
  ftdi_setrts( bridge->ftdic, 1 );

  // wait for signal from chip.
  while( !( ftdi_getcts(bridge->ftdic) ) && tmp < 13 )
    tmp++;

  return tmp != 13;
}
#endif

/***********
 * particle network -> ethernet
 *
 * receive one packet from usb, transform it to a ethernet packet and send it
 * over the ethernet.
 *
 * try to read the particle packet header. If we got one, the len field
 * specifies how many bytes have to be read afterwards.
 */
int
prt2evt(usbbridge *bridge)
{
  int n;
  static char b_pe [MAX_PCK_SIZE];
  struct par_cc_pkt *pp = (struct par_cc_pkt *) b_pe; // stores a particle packet

  // signal the chip that we like to read.
  ftdi_setrts( bridge->ftdic, 0 );

  while( (n = read_packet(bridge, (char*) pp, MAX_PCK_SIZE)) > 0 )
  {
    // the length of the packet is included in the packet header, we check
    // that here. The 2 bytes crc is not included in the length.
    if( offsetof(struct par_cc_pkt, buf) + pp->len + 2 != n )
    {
      printf( "incomplete read: got %i bytes but needed %i\n",
            n, offsetof(struct par_cc_pkt, buf) + pp->len + 2 );

      return 0;
    }
    else
    {
      cc_tuple *c;

      // fix byte ordering of tuples.
      for( c=cc_iter(pp , NULL); c;
           c=cc_iter(pp, c) )
      {
      // XXX
        c->type = ntohs(c->type);
        if(bridge->eventdevice && c->type==acltype(x,m,l))
        {
        	extern void SensorValuesEvent_event(void *,char *);
        	SensorValuesEvent_event(bridge->eventdevice,c->buf);
           if (curr_buf == 0)
           {
              memcpy(buffer2, c->buf, c->len);
              curr_buf = 1;
              global_packet_buffer = buffer2;
           }
           else
           {
              memcpy(buffer1, c->buf, c->len);
              curr_buf = 0;
              global_packet_buffer = buffer1;
           }
        }
      // Filter XML
      }
      // cc_send( pe );

//      if( bridge->show_packets ) print_packet( pp, false );

      // Filter XML
      // convert amp packets to aup packets
      // WARNING: packets will be modified !!!
    }
  }

  return 0;
}

/** read one packet from the ftdi connection */
int
read_packet( usbbridge *bridge, char *buf, size_t len )
{
  int n=0, got_packet=false, escaped_char=false;
  uint8_t c;

  // try to find the start of the packet as we might start receiving in the
  // middle of a packet
  // c == 0 marks the start of a packet and c == 192 the end of a packet
  do
  {
    n = ftdi_read_data(bridge->ftdic, &c, 1);
  } while( n != 0 && c!=0 );

  // no packet available
  if( n == 0 || c != 0 )
    return 0;

  // receive and unescape the buffer.
  for( n=0; n < len && !got_packet; )
  {
    // wait for data from ftdi
    while( ftdi_read_data(bridge->ftdic, &c, 1) == 0 )
      ;

    // is the current character a escaped one?
    if( escaped_char )
    {
      // escaped chars are also guarded by incrementing by 1, so
      // we decrement here
      buf[n++] = --c;
      escaped_char = false;
    }
    else
    {
      switch(c)
      {
        case 192:
          // packet end
          got_packet = true;
          break;
        case 219:
          // escape sequence
          escaped_char = true;
          break;
        default:
          // data
          buf[n++] = c;
          break;
      }
    }
  }

  return n;
}

void send_buf(struct dpws_s *device,int service_id,int op_id, struct soap* soap, char* buf,ssize_t length)
{
	//TODO dispatch by  device,
	//TODO fill in service, operation and message context
}

char *rcv_buf(struct dpws_s *device,int service_id,int op_id, struct soap*)
{
	//TODO dispatch by  device, service, operation and message context
	if (op_id==OP_SensorValues_GetSensorValues)
		return get_global_packet_buf();
	else return NULL;
}
