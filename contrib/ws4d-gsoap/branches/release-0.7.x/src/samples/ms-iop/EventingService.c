/* <one line to give the program's name and a brief idea of what it does.>
 * Copyright (C) 2007  University of Rostock
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 */

#include "evs.nsmap"
#include "dpws_device.h"
#include "msiop_metadata.h"

#define EVENTSERVICE_URI_LEN 255

int
setup_EventingService (struct dpws_s *device, struct soap *handle,
                       const char *wsdl, int backlog)
{
  soap_set_namespaces (handle, evs_namespaces);
    
  return msiop_setup_EventingService (device, handle, wsdl, backlog);
}
