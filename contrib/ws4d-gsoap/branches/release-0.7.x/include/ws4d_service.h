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

#ifndef WS4D_SERVICE_H_
#define WS4D_SERVICE_H_

#include "ws4d_mutex.h"
#include "ws4d_epr.h"
#include "ws4d_qname.h"

/**
 * Service Endpoint
 *
 * @addtogroup APIServiceEP Service Endpoint
 * @ingroup WS4D_UTILS
 *
 * @{
 */

int ws4d_register_serviceep (struct ws4d_epr *epr);

int ws4d_serviceep_addtype (struct ws4d_epr *epr, struct ws4d_qname *type);

int ws4d_serviceep_addtypestr (struct ws4d_epr *epr, const char *types);

ws4d_qnamelist *ws4d_serviceep_gettypelist (struct ws4d_epr *epr);

char
  *ws4d_serviceep_gettypestr (struct ws4d_epr *epr, ws4d_alloc_list * alist);

int ws4d_serviceep_setid (struct ws4d_epr *epr, const char *id);

char *ws4d_serviceep_copyid (struct ws4d_epr *epr, ws4d_alloc_list * alist);

char *ws4d_serviceep_getid (struct ws4d_epr *epr);

int ws4d_serviceep_matchesTypes (struct ws4d_epr *service,
                                 ws4d_qnamelist * types);

/** @} */

#endif /*WS4D_SERVICE_H_ */
