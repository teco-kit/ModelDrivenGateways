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

#ifndef _CONCOM_CONCOM_H_
# define _CONCOM_CONCOM_H_

# include <stdint.h>
# include <stdbool.h>
# include <stdlib.h>
# include <stddef.h>
//# include "acltypes.h"

#ifndef PACKED
# ifdef __GNUC__
#  define PACKED __attribute((__packed__))
# else
#  define PACKED
# endif
#endif


/** concom tuple types are 2bytes long */
typedef uint16_t cc_type;

/** concom tuples are triplets of type, len and data fields. */
typedef struct cc_tuple
{
  cc_type type;
  uint8_t len;

  uint8_t buf[];
} PACKED cc_tuple;

/** location of a node */
typedef struct location
{
    uint8_t root[16],
            level[4][6],
            x,y,z,deviation;
} location;

/** on-wire format for particles */
struct par_cc_pkt
{
    uint8_t  version;
    uint8_t  len;
    uint8_t  fieldstrength;
    uint8_t  id[8];
    uint8_t  seqnr;

    uint8_t  buf[64];
} PACKED;

/** on-wire format for ethernet */
struct eth_cc_pkt
{
    uint8_t  len;         // bastardize the standard by replacing the version
                          // field with a length field to stay compatible to
                          // the WL_CC_FORMAT
    location loc;
    uint8_t  id[8];
    uint8_t  seqnr;

    uint8_t  buf[64];
} PACKED;

# define WANT_STR_FUNC

typedef struct par_cc_pkt cc_pkt;

int16_t   cc_recv(cc_pkt *p);
int16_t   cc_send(cc_pkt *p);
void      cc_addr(uint8_t *buf, size_t n);

cc_pkt*   cc_init(cc_pkt *p);
bool      cc_add(cc_pkt *p, cc_type type, uint8_t *buf, size_t n);
bool      cc_add_more(cc_pkt *p, cc_type type, uint8_t *buf, size_t n);
bool      cc_services(cc_pkt *p);

cc_tuple* cc_iter(cc_pkt *p, cc_tuple *cur);
cc_tuple* cc_get (cc_pkt *p, cc_type type, cc_tuple *cur);
cc_tuple* cc_par_iter(struct par_cc_pkt *p, cc_tuple *cur);
cc_tuple* cc_par_get (struct par_cc_pkt *p, cc_type type, cc_tuple *cur);
cc_tuple* cc_eth_iter(struct eth_cc_pkt *p, cc_tuple *cur);
cc_tuple* cc_eth_get (struct eth_cc_pkt *p, cc_type type, cc_tuple *cur);

//bool      cc_subscribe(cc_type t, (*func) (cc_pkt *p));
//bool      cc_is_from(cc_pkt *p, uint8_t adr[8]);
//bool      cc_is_ack(cc_pkt *p, cc_pkt *to_be_acked);
bool      cc_is_forme(cc_pkt *p);

#ifdef WANT_WKT2LOC
void      cc_wkt2loc(location *loc, char* str, size_t n);
char*     cc_loc2wkt(location *loc, char *str, size_t n);
void      cc_dbl2loc(location *loc, double lat, double lon);
char*     cc_locxyz2wkt(location *loc, char *str, size_t n);
#endif

#ifdef WANT_STR_FUNC
char*     cc_pkt2str(cc_pkt *p, char *buf, size_t n);
char*     aclstr(cc_type t, char* s);
cc_type   stracl(char *s);
#endif

#if defined(UNIX) || defined(unix)
void      cc_set_ports(int in, int out);
#endif

#if defined(JENNIC) || defined(jennic)
bool      cc_setpandid(uint16_t panid);
bool      cc_setchannel(uint32_t channel);
#endif

#endif /* _CONCOM_H_ */
