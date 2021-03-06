/* Copyright (C) 2007  University of Rostock
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

//gsoapopt cw

/******************************************************************************\
 *                                                                            *
 * http://schemas.example.org/EventingService                                 *
 *                                                                            *
\******************************************************************************/


/******************************************************************************\
 *                                                                            *
 * Import                                                                     *
 *                                                                            *
\******************************************************************************/

#import "devprof.gsoap"

/******************************************************************************\
 *                                                                            *
 * Schema Namespaces                                                          *
 *                                                                            *
\******************************************************************************/

/* NOTE:

It is strongly recommended to customize the names of the namespace prefixes
generated by wsdl2h. To do so, modify the prefix bindings below and add the
modified lines to typemap.dat to rerun wsdl2h:

evs1 = "http://schemas.example.org/EventingService"

*/

//gsoap evs1  schema namespace: http://schemas.example.org/EventingService
//gsoap evs1  schema elementForm:       qualified
//gsoap evs1  schema attributeForm:     unqualified

/******************************************************************************\
 *                                                                            *
 * Schema Types                                                               *
 *                                                                            *
\******************************************************************************/



/// "http://schemas.example.org":SimpleEventType is a complexType.
struct evs1__SimpleEventType
{
/// TODO: <any namespace="##other" minOccurs="0" maxOccurs="unbounded">
///       Schema extensibility is user-definable.
///       Consult the protocol documentation to change or insert declarations.
///       Use wsdl2h option -x to remove this element.
///       Use wsdl2h option -d to use xsd__anyType DOM.
/// Size of the dynamic array of XML is 0..unbounded
  int __size;
  _XML *__any;                  ///< Catch any element content in XML string.
/// TODO: <anyAttribute namespace="##other">
///       Schema extensibility is user-definable.
///       Consult the protocol documentation to change or insert declarations.
///       Use wsdl2h option -x to remove this attribute.
   @_XML __anyAttribute;        ///< Catch any attribute content in XML string.
};

/// "http://schemas.example.org":IntegerEventType is a complexType.
struct evs1__IntegerEventType
{
/// Element Param of type xs:int.
  int Param 1;                  ///< Required element.
/// TODO: <any namespace="##other" minOccurs="0" maxOccurs="unbounded">
///       Schema extensibility is user-definable.
///       Consult the protocol documentation to change or insert declarations.
///       Use wsdl2h option -x to remove this element.
///       Use wsdl2h option -d to use xsd__anyType DOM.
/// Size of the dynamic array of XML is 0..unbounded
  int __size;
  _XML *__any;                  ///< Catch any element content in XML string.
/// TODO: <anyAttribute namespace="##other">
///       Schema extensibility is user-definable.
///       Consult the protocol documentation to change or insert declarations.
///       Use wsdl2h option -x to remove this attribute.
   @_XML __anyAttribute;        ///< Catch any attribute content in XML string.
};

/// Element "http://schemas.example.org":SimpleEvent of type "http://schemas.example.org":SimpleEventType.
/// Note: use wsdl2h option -g to generate this global element declaration.

/// Element "http://schemas.example.org":IntegerEvent of type "http://schemas.example.org":IntegerEventType.
/// Note: use wsdl2h option -g to generate this global element declaration.

/******************************************************************************\
 *                                                                            *
 * Services                                                                   *
 *                                                                            *
\******************************************************************************/


//gsoap evs1 service name:      EventingServiceSoap12Binding
//gsoap evs1 service type:      EventingService
//gsoap evs1 service port:      http://localhost/WebService/Eventing.asmx
//gsoap evs1 service namespace: http://schemas.example.org/EventingService
//gsoap evs1 service transport: http://schemas.xmlsoap.org/soap/http

/** @mainpage Service Definitions

@section Service_bindings Bindings
  - @ref EventingServiceSoap12Binding

*/

/**

@page EventingServiceSoap12Binding Binding "EventingServiceSoap12Binding"

@section EventingServiceSoap12Binding_operations Operations of Binding  "EventingServiceSoap12Binding"
  - @ref __evs1__SimpleEvent
  - @ref __evs1__IntegerEvent

@section EventingServiceSoap12Binding_ports Endpoints of Binding  "EventingServiceSoap12Binding"
  - http://localhost/WebService/Eventing.asmx

Note: use wsdl2h option -N to change the service binding prefix name

*/

/******************************************************************************\
 *                                                                            *
 * EventingServiceSoap12Binding                                               *
 *                                                                            *
\******************************************************************************/


/******************************************************************************\
 *                                                                            *
 * __evs1__SimpleEvent                                                        *
 *                                                                            *
\******************************************************************************/


//gsoap evs1 service method-style:      SimpleEvent document
//gsoap evs1 service method-encoding:   SimpleEvent literal
//gsoap evs1 service method-action:     SimpleEvent http://schemas.example.org/EventingService/SimpleEvent
int __evs1__SimpleEvent (struct evs1__SimpleEventType *evs1__SimpleEvent,       ///< Response parameter
                         void   ///< Notification message: no request parameter
  );


/******************************************************************************\
 *                                                                            *
 * __evs1__IntegerEvent                                                        *
 *                                                                            *
\******************************************************************************/


//gsoap evs1 service method-style:      IntegerEvent document
//gsoap evs1 service method-encoding:   IntegerEvent literal
//gsoap evs1 service method-action:     IntegerEvent http://schemas.example.org/EventingService/IntegerEvent
int __evs1__IntegerEvent (struct evs1__IntegerEventType *evs1__IntegerEvent,    ///< Response parameter
                          void  ///< Notification message: no request parameter
  );

/* End of EventingService.h */
