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

#include <stdint.h>
#include <stddef.h>
#include <string.h>
#include <ctype.h>
#include "concom.h"
#include "acltypes.h"


void cc_ll_addr()
{
   return;
}
#if 0
#include "ll.h"
/** receives one packet in non-blocking behaviour from the link layer.
 * Cares for byte ordering of the contained tuples and converts them to
 * host format.
 *
 * @example
 * void my_func()
 * {
 *   cc_pkt pkt;
 *
 *   if( cc_recv(pkt) > 0 )
 *      // do something with pkt.
 *   else
 *      // report some error.
 * }
 * @endexample
 *
 * @param p    buffer to the put data into.
 *
 * @return     n   number of bytes received.
 *             0   if no packet is available.
 *             -1  if CRC check failed.
 */
int16_t
cc_recv( cc_pkt *p )
{
    cc_tuple *t;
    int16_t n = cc_ll_recv( (uint8_t *) p, sizeof(cc_pkt) );

    if(n == 0)
      return 0;

    // buffer boundary check
    if(n < offsetof(cc_pkt, buf) || n > sizeof(cc_pkt))
    {
      p->len = 0;
      return -1;
    }

    // reset the len field
    p->len = n - offsetof(cc_pkt, buf);

    // fix type byte-ordering
    for( t=cc_iter(p, NULL); t; t=cc_iter(p, t) )
      t->type = ntohs(t->type);

    return n;
}

/** sends one packet in non-blocking behaviour
 *
 * Cares for byte ordering of the contained tuples and converts them to
 * network format.
 *
 * @example
 * void my_func()
 * {
 *   cc_pkt  pkt;
 *   uint8_t data[4] = {1,2,3,4};
 *
 *   cc_add( &pkt, acltype(a,b,c), NULL, 0 );
 *   cc_add( &pkt, acltype(d,a,t), data, sizeof(data) );
 *   cc_add( &pkt, acltype(a,n,u), (uint8_t*) &htonl(UINT32_MAX), sizeof(uint32_t) );
 *
 *   cc_send( &pkt );
 *  }
 *
 * @return n   number of bytes sent.
 *         -1  on error
 */
int16_t
cc_send( cc_pkt *p )
{
    int16_t n = 0;
    cc_tuple *t;

    // check buffer boundaries
    if( p->len > sizeof(p->buf) )
      return -1;

    // fix cc_type byte-ordering
    for( t=cc_iter(p, NULL); t; t=cc_iter(p, t) )
      t->type = htons(t->type);

    n = cc_ll_send( (uint8_t *) p, offsetof(cc_pkt, buf) + p->len );

    // revert cc_type byte-ordering
    for( t=cc_iter(p, NULL); t; t=cc_iter(p, t) )
      t->type = ntohs(t->type);

    return n;
}
#endif
/** copies the nodes addr into buf
 *
 * @param buf  buffer to copy address of the node to.
 * @param n    size of the buffer
 */
void
cc_addr(uint8_t *buf, size_t n)
{
  cc_ll_addr(buf, n);
}

/** subscribe message type and optionally call func on reception.
 *
 * @param t    type of message (first tuple in a packet).
 * @param func pointer to function to be called on msg reception, or NULL.
 * @returns true if subscribing did work (one possible error is not enough
 * space in subscription table)
 */
//bool
//cc_subscribe(cc_type t, (*func) (cc_pkt *p))
//{
//#if defined(JENNIC) || defined(jennic)
//#else
//#endif
//  return false;
//}

/** iterator for a buffer which contains cc_tuples.
 *
 * @param  p    packet to search in.
 * @param  cur  cc_tuple from which the search should be started or
 *              NULL if search should be started at the head of buf.
 *
 * No further check is done wether cur is really contained in buf.
 *
 * @return pointer to tuple after cur or NULL if there are no more tuples.
 */
static cc_tuple*
_cc_iter(uint8_t *buf, size_t len, cc_tuple *cur )
{
  int offset = 0;
  uint8_t *end;
  cc_tuple *t;

  if( cur )
  {
    // calculate the pointer to the end of the current tuple
    // and afterwards calculate the offset to the tuple.
    end    = ((uint8_t*) cur) + cur->len + sizeof(cc_tuple);
    offset = end - buf;
  }

  t = (cc_tuple*) (buf + offset);

  // check if the tuple is in the buffer boundaries and we are really iterating
  if( offset+sizeof(cc_tuple)         <= len &&
      offset+sizeof(cc_tuple)+t->len  <= len &&
      t != cur )
    return t;
  else
    return NULL;
}

/** returns first tuple of type "type".
 *
 * @param p        packet to search in.
 * @param type     cc_type of the tuple to look for.
 * @param cur      NULL if the search should be started at the start of
 *                 the buffer or the tuple where the search should be started
 *                 from.
 *
 * @return pointer to the found tuple or NULL.
 */
cc_tuple*
_cc_get( uint8_t *buf, size_t len, cc_type type, cc_tuple *cur )
{
  cc_tuple *t;

  for( t=_cc_iter(buf,len,cur); t; t=_cc_iter(buf,len, t) )
  {
    if( t->type == type )
      return t;
  }

  return NULL;
}

cc_tuple* cc_iter(cc_pkt *p, cc_tuple *cur)                              { return _cc_iter(p->buf,p->len,cur); }
cc_tuple* cc_get (cc_pkt *p, cc_type type, cc_tuple *cur)                { return _cc_get(p->buf,p->len,type,cur); }
cc_tuple* cc_par_iter(struct par_cc_pkt *p, cc_tuple *cur)               { return _cc_iter(p->buf,p->len,cur); }
cc_tuple* cc_par_get (struct par_cc_pkt *p, cc_type type, cc_tuple *cur) { return _cc_get(p->buf,p->len,type,cur); }
cc_tuple* cc_eth_iter(struct eth_cc_pkt *p, cc_tuple *cur)               { return _cc_iter(p->buf,p->len,cur); }
cc_tuple* cc_eth_get (struct eth_cc_pkt *p, cc_type type, cc_tuple *cur) { return _cc_get(p->buf,p->len,type,cur); }

bool
cc_is_forme( cc_pkt *p )
{
  static uint8_t addr[8];
  static bool    cached = false;
  cc_tuple *t;

  if(!cached)
  {
    cc_ll_addr(addr, sizeof(addr));
    cached = true;
  }

  if( (t=cc_get(p, acltype(c,a,d), NULL)) )
    return (t->len == sizeof(addr) && memcmp(addr, t->buf, sizeof(addr)) == 0);
  else
    return true; // no control addresses tuple => assuming broadcast
}

/** sets default values in the packet. Always do this prior to modifying any
 * packet created by you.
 *
 * @param pkt  cc_pkt to modify.
 * @returns pointer to pkt given as an argument.
 */
cc_pkt*
cc_init( cc_pkt *p )
{
  if(p==NULL) p = malloc(sizeof(cc_pkt));
  memset( (void*) p, 0x00, sizeof(cc_pkt) );
  cc_ll_addr( (void*) p->id, sizeof(p->id) );
  return p;
}

/** adds the given buffer to the packet.
 *
 * @param p    packet to add the data to
 * @param type ACL type to add the data under
 * @param buf  the actual data to add
 * @param n    size of the data buffer
 *
 * @return true if the data could be added, false if the packet is already full
 */

bool
cc_add( cc_pkt *p, cc_type type, uint8_t *buf, size_t n )
{
  cc_tuple *new;

  if( p->len + sizeof(cc_tuple) + n > sizeof(p->buf) )
    return false;

  // get the pointer to the unallocated space in the packet
  new = (cc_tuple *) (p->buf + p->len);

  // copy in the new tuple
  new->type = type;
  new->len  = n;
  memcpy( new->buf, buf, n );

  // update the len of the packet
  p->len += n + sizeof(cc_tuple);

  return true;
}

/** adds the given buffer to an already created tuple in the packet.
 *
 * @param p    packet to add the buffer to.
 * @param type type of the tuple the buffer should be added under.
 * @param buf  actual data to add.
 * @param n    size of the buffer.
 *
 * @return true if it could be added, false if the tuple was not found in the
 * packet or the packet there is not enough space in the packet.
 */
bool
cc_add_more( cc_pkt *p, cc_type type, uint8_t *buf, size_t n )
{
  cc_tuple *t = cc_get(p, type, NULL);
  int offset  = (void*) p - (void*) t;

  if( !t )
    return false;

  // check if this is the last tuple in the packet and if there is enough room.
  // creating a gap is not supported atm. TODO
  if( (int) p + p->len - t->len - sizeof(cc_tuple) == offset &&
      p->len + n <= sizeof(cc_pkt) )
  {
    // copy in the data and updated the len fields.
    memcpy( t->buf + t->len, buf, n );

    p->len += n;
    t->len += n;

    return true;
  }
  else
  {
    return false;
  }
}

/** action handler for system packet (tuple subject type acm). Implements
 * default actions for system services. Only ping at the moment, which echoes a
 * packet with the copied seqnr back.
 *
 * @param p  inout argument, if this function returns true it was
 *           modified.
 *
 * @returns true if some actions took place (and modified the packet).
 */
bool
cc_services( cc_pkt *p )
{
#if 0
  if( cc_get( p, acltype(a,c,m), NULL ) &&
      cc_get( p, acltype(c,h,e), NULL ) )
  {
    uint8_t seq = p->seqnr, // keep the received seqnr
              i = 0,
          adr[] = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF};
    bool forme  = false;
    cc_tuple *t = cc_get(p, acltype(c,h,e), NULL);

    if( t->len == sizeof(adr) )
    {
      // check request address
      forme = memcmp(t->buf, &adr, sizeof(adr)) == 0;
      if(!forme)
      {
        cc_ll_addr(adr, sizeof(adr));
        forme = memcmp(t->buf, &adr, sizeof(adr)) == 0;
      }

      if(forme)
      {
        cc_init(p);

        p->seqnr = seq;
        cc_add( p, acltype(a,c,m), NULL, 0 );
        cc_add( p, acltype(c,e,h), NULL, 0 );

        cc_send( p );

        return true;
      }
    }
  }
#endif
  return false;
}

#ifdef WANT_WKT2LOC
# include <stdio.h>
# include <math.h>

char*
_bcd_decode_location(char *str, size_t n, unsigned char *buf, size_t m)
{
  uint8_t a,b;
  int i, j;
  char *tmp = str;

  for(i=0; i<m; i++)
  {
    a = buf[i] >> 4;
    b = buf[i] & 0x0F;

    if( i == 0 )
    {
      if( a == 0 && b == 0 )
        continue;
      else if( a == 0 )
        tmp += snprintf(tmp, n, "%d", b);
      else
        tmp += snprintf(tmp, n, "%d%d", a, b);
    }
    else if( i == 1 )
      tmp += snprintf(tmp, n, "%d.%d", a, b);
    else
      tmp += snprintf(tmp, n, "%d%d", a, b);
  }

  return str;
}

unsigned char*
_bcd_encode(unsigned char* arr, size_t n, double val)
{
  char buf[128], *str;
  int s=0, i=0, j=0;
  uint8_t num;

  if( val < -999.0 || val > 999.0 )
    return NULL;

  // clear the array first
  memset(arr, 0, n);

  s = snprintf(buf, sizeof(buf), "%#016.12lf", val);

  // encode pre-comma
  str = strtok(buf, ".");
  arr[0]  = (str[j++]-'0') << 4;
  arr[0] |= (str[j++]-'0') & 0x0F;
  arr[1]  = (str[j++]-'0') << 4;

  // encode post-comma
  str = strtok(NULL, ".");
  arr[1] |= (str[0]-'0') & 0x0F;

  for( i=2,j=1; i<n && j<strlen(str); i++ )
  {
    arr[i]  = (str[j++]-'0') << 4;
    arr[i] |= (str[j++]-'0') & 0x0F;
  }

  return arr;
}

char *
cc_loc2wkt(location *loc, char* wkt, size_t n)
{
  double  lat, lon;
  uint8_t root[16];
  char    buf[6*2+2];

  // packet with position set? parse and store it in lat and lon
  if (loc->root[0]==204 && loc->root[1]==156 && loc->root[2]==0 && loc->root[3]==2)
  {
    sscanf(_bcd_decode_location(buf, sizeof(buf), loc->root+4, 6), "%lf", &lat);
    sscanf(_bcd_decode_location(buf, sizeof(buf), loc->root+10, 6), "%lf", &lon);
  }
  else
  {
    // TECO Coordinates
    lat=49.0056; lon=8.4025;
  }

  snprintf(wkt, n, "POINT(%.9lf %.9lf)\0", lat, lon);
  return wkt;
}

char*
cc_locxyz2wkt(location *loc, char *wkt, size_t n)
{
  #define EARTH_RADIUS 6371000.785
  double  lat, lon;
  int8_t  x, y, z;
  uint8_t root[16];
  char    buf[6*2+2];

  // packet with position set? parse and store it in lat and lon
  if (loc->root[0]==204 && loc->root[1]==156 && loc->root[2]==0 && loc->root[3]==2)
  {
    sscanf(_bcd_decode_location(buf, sizeof(buf), loc->root+4, 6), "%lf", &lat);
    sscanf(_bcd_decode_location(buf, sizeof(buf), loc->root+10, 6), "%lf", &lon);
  }
  else
  {
    // TECO Coordinates
    lat=49.0056; lon=8.4025;
  }

  // calculate the correction stored in the x and y fields.
  lat -= atan2(x, EARTH_RADIUS) * 180.0/M_PI;
  lon -= atan2(y, cos(lat * 180.0/M_PI) * EARTH_RADIUS) * 180.0/M_PI;

  snprintf(wkt, n, "POINT(%.9lf %.9lf)\0", lat, lon);

  return wkt;
}

void
cc_wkt2loc(location *location, char *wkt, size_t n)
{
  double lat, lon;
  // search for latitude and longitude
  if( sscanf( wkt, " %lf %lf", &lat, &lon ) != 2 )
    ;
  else if( (wkt=strstr(wkt, "POINT(")) )
    if( sscanf( wkt, " %lf %lf", &lat, &lon ) != 2 )
      return; // not found, do not modify loc

  cc_dbl2loc(location, lat, lon);
}

void
cc_dbl2loc(location *location, double lat, double lon)
{
  unsigned char *loc = (unsigned char*) location;

  // set the header
  loc[0] = 204; loc[1] = 156; loc[2] = 0; loc[3] = 2;

  // bcd encode the coords into the location field
  _bcd_encode(loc+4, 6, lat);
  _bcd_encode(loc+10, 6, lon);

}

#endif

#ifdef WANT_PKT2STR
/** returns a stringified packet in the supplied buffer.
 *
 * @param p     packet to stringify
 * @param buf   string buffer
 * @param n     string buffer length
 *
 * @returns a pointer to supplied buffer
 */
char*
cc_pkt2str( cc_pkt *p, char *s, size_t n )
{
  int i=0, j=0;
  cc_tuple *c;

  // TODO location!!

  i += snprintf(s+i, n-i,   "len=%hhu seq=%hhu ", p->len, p->seqnr);
  i += snprintf(s+i, n-i, "id=%hhu.%hhu.%hhu.%hhu.%hhu.%hhu.%hhu.%hhu\n",
                p->id[0], p->id[1], p->id[2], p->id[3], p->id[4], p->id[5],
                p->id[6], p->id[7]);

  for( c=cc_iter(p, NULL); c; c=cc_iter(p, c ) )
  {
    i += snprintf(s+i, n-i, " [%s (%3hhu,%3hhu) | %2hhu ", aclstr(c->type, NULL),
                  c->type>>8, c->type&0x00FF, c->len );

    if( c->len > 0 )
      i += snprintf(s+i, n-i, "| %hhu", c->buf[0]);

    for( j=1; j < c->len-1; j++ )
      i += snprintf(s+i, n-i, " %hhu", c->buf[j]);

    if( c->len > 1 )
      i += snprintf(s+i, n-i, " %hhu ]\n", c->buf[c->len-1]);
    else
      i += snprintf(s+i, n-i, "]\n");
  }

  s[i] = 0;

  return s;
}

#endif

#ifdef WANT_STR_FUNC

/** converts a concom type to string. The complementary function is called
 * acltype() and defined in acltypes.h
 *
 * @param t  concom type to convert.
 * @param s  output string (has to be 4 bytes long). Can be NULL, then a
 *           pointer to a shared global buffer will be returned.
 * @returns  input pointer.
 */
char*
aclstr(cc_type t, char* s)
{
  static char buf[] = {0,0,0,0};
  uint16_t i;

  if( !s || strlen(s) < 3 )
    s = buf;

  s[0] = t%40;        t = t-s[0];
  s[1] = (t%1600)/40; t = t-(s[1]*40);
  s[2] = t/1600;

  for(i=0; i<3; i++)
  {
        s[i] += 0x30;
    if( s[i] == 0x3C ) s[i] = 0x20;
    if( s[i] == 0x3B ) s[i] = 0x2F;
    if( s[i] == 0x3A ) s[i] = 0x2D;

    if( (s[i] >= '@'-3) && (s[i] <= 'Z'-3) ) s[i] += 3;
    if( (s[i] >= 'A') && (s[i] <= 'Z') ) s[i] += 0x20;
  }

  return s;
}

/** converts a string to a concom type.
 *
 * @param s  3 byte string to convert.
 * @returns  corresponding cc_type.
 */
cc_type
stracl(char *in)
{
  static char s[4];
  int i;

  memcpy(s, in, sizeof(s));

  for(i=0; i<3; i++)
  {
    if (s[i]==0x20) { s[i]=0x3C; continue; }
    if (s[i]==0x2F) { s[i]=0x3B; continue; }
    if (s[i]==0x2D) { s[i]=0x3A; continue; }

    if ((s[i] <= 'z') && (s[i] >= 'a')) s[i]-=0x20;
    if ((s[i] < '0')  || (s[i] > 'Z'))  s[i] =0x3C;
    if ((s[i] >= '=') && (s[i] <= '?')) s[i] =0x3C;
    if ((s[i] >= '@') && (s[i] <= 'Z')) s[i]-=3;
  }

  return (s[0] - 0x30) + ((s[1] - 0x30) * 40) + ((s[2] - 0x30) * 1600);
}

#endif
