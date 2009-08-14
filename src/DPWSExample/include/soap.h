#include <stdsoap2.h>
#if 0
#include <wsdl/wsdlStub.h>
//#include <import/WS-Header.h>
#else
#ifndef WITH_NOGLOBAL

#ifndef SOAP_TYPE_SOAP_ENV__Code
#define SOAP_TYPE_SOAP_ENV__Code (81)
/* SOAP Fault Code: */
struct SOAP_ENV__Code
{
	char *SOAP_ENV__Value;	/* optional element of type xsd:QName */
	struct SOAP_ENV__Code *SOAP_ENV__Subcode;	/* optional element of type SOAP-ENV:Code */
};
#endif

#ifndef SOAP_TYPE_SOAP_ENV__Detail
#define SOAP_TYPE_SOAP_ENV__Detail (83)
/* SOAP-ENV:Detail */
struct SOAP_ENV__Detail
{
	int __type;	/* any type of element <fault> (defined below) */
	void *fault;	/* transient */
	char *__any;
};
#endif

#ifndef SOAP_TYPE_SOAP_ENV__Reason
#define SOAP_TYPE_SOAP_ENV__Reason (84)
/* SOAP-ENV:Reason */
struct SOAP_ENV__Reason
{
	char *SOAP_ENV__Text;	/* optional element of type xsd:string */
};
#endif

#ifndef SOAP_TYPE_SOAP_ENV__Fault
#define SOAP_TYPE_SOAP_ENV__Fault (85)
/* SOAP Fault: */
struct SOAP_ENV__Fault
{
	char *faultcode;	/* optional element of type xsd:QName */
	char *faultstring;	/* optional element of type xsd:string */
	char *faultactor;	/* optional element of type xsd:string */
	struct SOAP_ENV__Detail *detail;	/* optional element of type SOAP-ENV:Detail */
	struct SOAP_ENV__Code *SOAP_ENV__Code;	/* optional element of type SOAP-ENV:Code */
	struct SOAP_ENV__Reason *SOAP_ENV__Reason;	/* optional element of type SOAP-ENV:Reason */
	char *SOAP_ENV__Node;	/* optional element of type xsd:string */
	char *SOAP_ENV__Role;	/* optional element of type xsd:string */
	struct SOAP_ENV__Detail *SOAP_ENV__Detail;	/* optional element of type SOAP-ENV:Detail */
};
#endif

#ifndef SOAP_TYPE_SOAP_ENV__Header
#define SOAP_TYPE_SOAP_ENV__Header (68)
/* SOAP Header: */
struct SOAP_ENV__Header
{
	char *wsa__MessageID;	/* mustUnderstand */
	struct wsa__Relationship *wsa__RelatesTo;	/* mustUnderstand */
	struct wsa__EndpointReferenceType *wsa__From;	/* mustUnderstand */
	struct wsa__EndpointReferenceType *wsa__ReplyTo;	/* mustUnderstand */
	struct wsa__EndpointReferenceType *wsa__FaultTo;	/* mustUnderstand */
	char *wsa__To;	/* mustUnderstand */
	char *wsa__Action;	/* mustUnderstand */
	char *dummy1;	/* optional element of type xsd:string */
	char *dummy2;	/* optional element of type xsd:string */
	char *dummy3;	/* optional element of type xsd:string */
};
#endif
 void  soap_default_SOAP_ENV__Fault(struct soap*, struct SOAP_ENV__Fault *);
 void soap_serialize_SOAP_ENV__Fault(struct soap*, const struct SOAP_ENV__Fault *);
 int soap_put_SOAP_ENV__Fault(struct soap*, const struct SOAP_ENV__Fault *, const char*, const char*);
 int soap_out_SOAP_ENV__Fault(struct soap*, const char*, int, const struct SOAP_ENV__Fault *, const char*);
 struct SOAP_ENV__Fault * soap_get_SOAP_ENV__Fault(struct soap*, struct SOAP_ENV__Fault *, const char*, const char*);
 struct SOAP_ENV__Fault * soap_in_SOAP_ENV__Fault(struct soap*, const char*, struct SOAP_ENV__Fault *, const char*);
#endif
#endif
