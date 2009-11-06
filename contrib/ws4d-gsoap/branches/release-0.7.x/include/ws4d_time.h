/* WS4D-gSOAP - Implementation of the Devices Profile for Web Services
 * (DPWS) on top of gSOAP
 * Copyright (C) 2007 University of Rostock
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA
 */

#ifndef WS4D_TIME_H_
#define WS4D_TIME_H_

/**
 * @addtogroup DpwsTime Functions for system time
 * @ingroup WS4D_UTILS
 *
 * @{
 */

typedef unsigned long ws4d_time;

/**
 * Function returns system time in milli seconds
 *
 */
ws4d_time ws4d_systime_ms ();


/**
 * Function returns system time in seconds
 *
 */
ws4d_time ws4d_systime_s ();

#ifndef SOAP_TYPE_ws4d_dur
#define SOAP_TYPE_ws4d_dur (11)
struct ws4d_dur
{
  int years;                    /* required element of type xsd:int */
  int months;                   /* required element of type xsd:int */
  int days;                     /* required element of type xsd:int */
  int hours;                    /* required element of type xsd:int */
  int minutes;                  /* required element of type xsd:int */
  int seconds;                  /* required element of type xsd:int */
};
#endif

int ws4d_dur_to_s (struct ws4d_dur *dur, ws4d_time * s);
int ws4d_s_to_dur (ws4d_time s, struct ws4d_dur *dur);

/** @} */

#endif /*WS4D_TIME_H_ */
