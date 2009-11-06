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

#include "stdsoap2.h"

#include "dpwsH.h"
#include <stdarg.h>
#include "ws4d_misc.h"
#include "soap_misc.h"

#include "ws4d_abstract_eprlist.h"
#include "ws4d_eprllist.h"
#include "ws4d_target.h"

#include "stddpws.h"
#include "ws-addressing.h"

#ifdef DPWS_DEVICE
#define WSD_TARGET
#endif

#ifdef DPWS_CLIENT
#define WSD_CLIENT
#endif

#include "ws-discovery.h"

/**
 * @addtogroup Internals Internals
 *
 * @{
 */

char *
dpws_header_gen_MessageId (char *buffer, size_t size)
{
  char uuidstr[WS4D_UUID_SIZE];

  /* test parameters */
  ws4d_assert (buffer && (size >= DPWS_MSGID_SIZE), NULL);

  memset (buffer, 0, size);

  ws4d_uuid_generate_random (uuidstr);
  ws4d_uuid_generate_schema (buffer, uuidstr);

  return buffer;
}

int
dpws_header_gen_oneway (struct soap *soap, const char *MessageId,
                        const char *To, const char *Action,
                        const char *FaultTo, size_t size)
{
  char MsgId[DPWS_MSGID_SIZE];

  /* test parameters */
  soap_assert (soap, soap, SOAP_ERR);

  if (!MessageId)
    {
      dpws_header_gen_MessageId (MsgId, DPWS_MSGID_SIZE);
      MessageId = soap_strdup (soap, (char *) MsgId);
    }

  return wsa_header_gen_oneway (soap, MessageId, To, Action, FaultTo, size);
}

int
dpws_header_gen_request (struct soap *soap, const char *MessageId,
                         const char *To, const char *Action,
                         const char *FaultTo, const char *ReplyTo,
                         size_t size)
{
  char MsgId[DPWS_MSGID_SIZE];

  /* test parameters */
  soap_assert (soap, soap, SOAP_ERR);

  if (!MessageId)
    {
      dpws_header_gen_MessageId (MsgId, DPWS_MSGID_SIZE);
      MessageId = soap_strdup (soap, (char *) MsgId);
      if (!MessageId)
        {
          return soap->error;
        }
    }

  return wsa_header_gen_request (soap, MessageId, To, Action, FaultTo,
                                 ReplyTo, size);
}

int
dpws_header_gen_response (struct soap *soap, const char *MessageId,
                          const char *To, const char *Action,
                          const char *RelatesTo, size_t size)
{
  return wsa_header_gen_response (soap, MessageId, To, Action, RelatesTo,
                                  size);
}

int
dpws_sender_fault (struct soap *soap, const char *MessageId, const char *To,
                   const char *Action, const char *RelatesTo, size_t size,
                   const char *faultstring, const char *faultdetail)
{
  int ret = 0;

  if (!Action)
    {
      Action = "http://schemas.xmlsoap.org/ws/2006/02/devprof/Fault";
    }

  ret = wsa_header_gen_fault (soap, MessageId, To, Action, RelatesTo, size);
  if (ret != SOAP_OK)
    return ret;

  return soap_sender_fault (soap, faultstring, faultdetail);
}

int
dpws_receiver_fault (struct soap *soap, const char *MessageId,
                     const char *To, const char *Action,
                     const char *RelatesTo, size_t size,
                     const char *faultstring, const char *faultdetail)
{
  int ret = 0;
  char MsgId[DPWS_MSGID_SIZE];

  if (!MessageId)
    {
      dpws_header_gen_MessageId (MsgId, DPWS_MSGID_SIZE);
      MessageId = soap_strdup (soap, (char *) MsgId);
    }

  ret = wsa_header_gen_fault (soap, MessageId, To, Action, RelatesTo, size);
  if (ret != SOAP_OK)
    return ret;

  return soap_sender_fault (soap, faultstring, faultdetail);
}

struct soap *
dpws_maccept (struct dpws_s *dpws, ws4d_time timeout, int count,
              struct soap **soap_handles)
{
  register int i, found_dis_mca = 0;
#ifdef DPWS_DEVICE
  int found_hosting = 0;
#endif
#ifdef DPWS_CLIENT
  int found_dis_uni = 0;
#endif

  /* test parameters */
  ws4d_assert (dpws && (count > 0) && soap_handles, NULL);

  for (i = 0; i < count; i++)
    {
      if (soap_handles[i])
        {
          if (soap_handles[i] == dpws->dis_mca)
            found_dis_mca = 1;
#ifdef DPWS_DEVICE
          if (soap_handles[i] == dpws->hosting_handle)
            found_hosting = 1;
#endif
#ifdef DPWS_CLIENT
          if (soap_handles[i] == dpws->dis_uni)
            found_dis_uni = 1;
#endif
        }
    }

  if (dpws->dis_mca && !found_dis_mca)
    {
      soap_handles[count] = dpws->dis_mca;
      count++;
    }
#ifdef DPWS_DEVICE
  if (dpws->hosting_handle && !found_hosting)
    {
      soap_handles[count] = dpws->hosting_handle;
      count++;
    }
#endif
#ifdef DPWS_CLIENT
  if (dpws->dis_uni && !found_dis_uni)
    {
      soap_handles[count] = dpws->dis_uni;
      count++;
    }
#endif

  return soap_maccept (timeout, count, soap_handles);
}

extern int target_serve_request (struct soap *soap);
extern int hosting_serve_request (struct soap *soap);

int
dpws_mserve (struct soap *soap, int count,
             int (*serve_requests[])(struct soap * soap))
{
  register int i, found_discovery = 0, found_hosting = 0;

  /* test parameters */
  soap_assert (soap, soap && serve_requests, SOAP_ERR);

  if (count > 0)
    {
      for (i = 0; i < count; i++)
        {
          if (serve_requests[i])
            {
#ifndef DPWS_HOSTED_SERVICE
              if ((found_discovery == 0) && (serve_requests[i]
                                             == target_serve_request))
                {
                  found_discovery = 1;
                }
#endif
              if ((found_hosting == 0) && (serve_requests[i]
                                           == hosting_serve_request))
                {
                  found_hosting = 1;
                }
            }
        }
    }
  else
    {
      count = 0;
    }

#ifndef DPWS_HOSTED_SERVICE
  if (found_discovery == 0)
    {
      serve_requests[count] = target_serve_request;
      count++;
    }
#endif

  if (found_hosting == 0)
    {
      serve_requests[count] = hosting_serve_request;
      count++;
    }

  return soap_mserve (soap, count, serve_requests);
}

/**
 * DPWS structure logic
 */

#ifdef DPWS_CLIENT
#include "ws4d_targetcache.h"
#endif

#ifdef DPWS_DEVICE
#ifndef DPWS_HOSTED_SERVICE

#include "ws4d_targetservice.h"

struct ws4d_targetservice default_targetservice, *dpws_targetservice = NULL;

int
dpws_init_targetservice (struct ws4d_targetservice *ts)
{
  if (dpws_targetservice == NULL)
    {
      if (ts)
        {
          dpws_targetservice = ts;
        }
      else
        {
          ws4d_targetservice_init (&default_targetservice);
          dpws_targetservice = &default_targetservice;
        }

      return WS4D_OK;
    }
  else
    {
      return WS4D_ERR;
    }

}

void
dpws_done_targetservice ()
{
  if (dpws_targetservice)
    ws4d_targetservice_done (dpws_targetservice);
}
#endif
#endif

int
dpws_init (struct dpws_s *dpws, const char *host)
{
  int err = WS4D_OK;

  /* test parameters */
  ws4d_assert (dpws && host && *host, WS4D_ERR);

  if (!strcmp (host, "0.0.0.0"))
    {
      printf ("ANY_DEVICE not supported as host\n");
      return WS4D_ERR;
    }

  memset (dpws, 0, sizeof (struct dpws_s));

#ifdef WITH_MUTEXES
  ws4d_mutex_init (&dpws->lock);
#endif

  dpws->host = host;
  WS4D_ALLOCLIST_INIT (&dpws->alloc_list);

#ifdef DPWS_DEVICE
#ifndef DPWS_HOSTED_SERVICE
  dpws_init_targetservice (NULL);
#endif
  ws4d_hosting_init (&dpws->hosting, host);
#endif

#ifdef DPWS_CLIENT
  /* initialize target cache */
  err = dpws_init_tc (dpws, dpws_create_tc (NULL));
#endif

  return err;
}

#ifdef WITH_MUTEXES
void
dpws_lock (struct dpws_s *dpws)
{
  ws4d_assert (dpws,);

  ws4d_mutex_lock (&dpws->lock);
}

void
dpws_unlock (struct dpws_s *dpws)
{
  ws4d_assert (dpws,);

  ws4d_mutex_unlock (&dpws->lock);
}
#endif /*WITH_MUTEXES */

#ifdef DPWS_CLIENT

int
dpws_init2 (struct dpws_s *dpws, const char *host, struct ws4d_tc_int *tc)
{
  int err = WS4D_OK;

  /* test parameters */
  ws4d_assert (dpws && host && *host, WS4D_ERR);

  if (!strcmp (host, "0.0.0.0"))
    {
      printf ("ANY_DEVICE not supported as host\n");
      return WS4D_ERR;
    }

  memset (dpws, 0, sizeof (struct dpws_s));

  dpws->host = host;
  WS4D_ALLOCLIST_INIT (&dpws->alloc_list);

#ifdef DPWS_DEVICE
#ifndef DPWS_HOSTED_SERVICE
  dpws_init_targetservice (NULL);
#endif
  ws4d_hosting_init (&dpws->hosting, host);
#endif

#ifdef DPWS_CLIENT
  /* initialize target cache */
  err = dpws_init_tc (dpws, tc);
#endif

  return err;
}

struct ws4d_tc_int *
dpws_create_tc (void *arg)
{
  struct ws4d_tc_int *result = NULL;
  int err;

  result = (struct ws4d_tc_int *) ws4d_malloc (sizeof (struct ws4d_tc_int));
  ws4d_assert (result, NULL);

  err = ws4d_tc_init (result, arg);
  if (err != WS4D_OK)
    {
      ws4d_free (result);
      return NULL;
    }

  return result;
}

int
dpws_init_tc (struct dpws_s *dpws, struct ws4d_tc_int *tc)
{
  /* test parameters */
  ws4d_assert (dpws && tc, WS4D_ERR);

  dpws->tc = tc;

  return WS4D_OK;
}
#endif

#ifdef DPWS_CLIENT

int
dpws_init_implicit_discovery2 (struct dpws_s *dpws, struct ws4d_tc_int *tc,
                               struct soap *dis_mca,
                               struct dpws_discovery_hooks *hooks)
{
  int err = WS4D_OK;

  /* test parameters */
  ws4d_assert (dpws && dpws->host && tc && dis_mca, WS4D_ERR);

#ifdef DEBUG
  soap_omode (dis_mca, SOAP_XML_INDENT);
#endif

  err = wsa_register_handle (dis_mca);
  if (err != SOAP_OK)
    return WS4D_ERR;

  err
    =
    wsd_soap_init_implicite (dis_mca, tc, dpws->host, 100, NULL,
                             (struct wsd_dis_hooks_t *) hooks);
  if (err == SOAP_OK)
    {
      dpws->dis_mca = dis_mca;
      return WS4D_OK;
    }
  else
    {
      return WS4D_ERR;
    }
}

int
dpws_init_implicit_discovery (struct dpws_s *dpws, struct soap *dis_mca,
                              struct dpws_discovery_hooks *dis_hooks)
{
  return dpws_init_implicit_discovery2 (dpws, dpws->tc, dis_mca, dis_hooks);
}
#endif

#ifdef DPWS_DEVICE
#ifndef DPWS_HOSTED_SERVICE
int
dpws_init_target (struct dpws_s *dpws, struct soap *dis_mca, int backlog)
{
  int err = WS4D_OK;

  /* test parameters */
  ws4d_assert (dpws && dpws->host, WS4D_ERR);

  if (!dis_mca)
    {
      dis_mca = ws4d_malloc_alist (sizeof (struct soap), &dpws->alloc_list);
      if (!dis_mca)
        return WS4D_EOM;
      soap_init (dis_mca);
    }

#ifdef DEBUG
  soap_omode (dis_mca, SOAP_XML_INDENT);
#endif

  err = wsa_register_handle (dis_mca);
  if (err != SOAP_OK)
    return WS4D_ERR;

  err =
    wsd_target_init (dis_mca, (char *) dpws->host, backlog,
                     dpws_targetservice, NULL);
  if (err == SOAP_OK)
    {
      dpws->dis_mca = dis_mca;
      return WS4D_OK;
    }
  else
    {
      return WS4D_ERR;
    }
}

#endif
#endif

int
dpws_done (struct dpws_s *dpws)
{
  /* test parameters */
  ws4d_assert (dpws, WS4D_ERR);

#ifdef DPWS_DEVICE
  if (dpws->hosting_handle)
    {
      if (dpws->dis_mca)
        {
          soap_done (dpws->dis_mca);
        }
    }
  ws4d_hosting_done (&dpws->hosting);
#ifndef DPWS_HOSTED_SERVICE
  dpws_done_targetservice ();
#endif
#endif

#ifdef DPWS_CLIENT
  /* destroy target cache */
  ws4d_tc_done (dpws->tc);
  ws4d_free (dpws->tc);
#endif

  ws4d_alloclist_done (&dpws->alloc_list);

  return WS4D_OK;
}

/**
 * DPWS Handle Logic
 */

#define DPWS_SOAP_PLUGIN_ID "DPWS-Soap-Handle-PLUGIN-0.1"
const char *dpws_soap_plugin_id = DPWS_SOAP_PLUGIN_ID;

INLINE struct dpws_soap_s *dpws_handle_get_plugindata (struct soap *soap);

int
dpws_handle_plugin_init (struct soap *soap, struct soap_plugin *p, void *arg);

INLINE struct dpws_soap_s *
dpws_handle_get_plugindata (struct soap *soap)
{
  return (struct dpws_soap_s *)
    soap_lookup_plugin (soap, dpws_soap_plugin_id);
}

static void
dpws_handle_plugin_delete (struct soap *soap, struct soap_plugin *p)
{
  memset (p->data, 0, sizeof (struct dpws_soap_s));
  SOAP_FREE (soap, p->data);
}

static int
dpws_handle_plugin_copy (struct soap *soap, struct soap_plugin *dst,
                         struct soap_plugin *src)
{
  dst->id = dpws_soap_plugin_id;

  dst->data = (void *) SOAP_MALLOC (soap, sizeof (struct dpws_soap_s));

  dst->fcopy = dpws_handle_plugin_copy;
  dst->fdelete = dpws_handle_plugin_delete;

  if (dst->data && src->data)
    {
      struct dpws_soap_s *data = dst->data;

      memcpy (dst->data, src->data, sizeof (struct dpws_soap_s));
      data->copy_of = src->data;

      return SOAP_OK;
    }
  return SOAP_ERR;
}

int
dpws_handle_plugin_init (struct soap *soap, struct soap_plugin *p, void *arg)
{
  /* set plugin id */
  p->id = dpws_soap_plugin_id;

  /* allocate memory for plugin data */
  p->data = (void *) SOAP_MALLOC (soap, sizeof (struct dpws_soap_s));
  memset (p->data, 0, sizeof (struct dpws_soap_s));

  p->fcopy = dpws_handle_plugin_copy;
  p->fdelete = dpws_handle_plugin_delete;

  /* plugin data initialization */
  if (p->data)
    {
      return SOAP_OK;
    }
  return SOAP_ERR;
}

int
dpws_handle_init (struct dpws_s *dpws, struct soap *soap)
{
  int err = SOAP_OK;
  struct dpws_soap_s *handle_data = dpws_handle_get_plugindata (soap);

  /* test parameters */
  soap_assert (soap, dpws && soap, SOAP_ERR);

  /* is handle or another handle already registered */
  if (handle_data)
    {
      return SOAP_ERR;
    }

  /* register soap handle as dpws serving handle */
  err = soap_register_plugin (soap, dpws_handle_plugin_init);
  if (err != SOAP_OK)
    return err;

  /* get plugin data */
  handle_data = dpws_handle_get_plugindata (soap);
  if (!handle_data)
    return SOAP_ERR;

  handle_data->dpws = dpws;

  return SOAP_OK;
}

int
dpws_handle_bind (struct dpws_s *dpws, struct soap *soap, char *uri,
                  size_t size, int backlog)
{
  struct dpws_soap_s *data = dpws_handle_get_plugindata (soap);
  struct sockaddr_in addr;
  SOCKLEN_T addrlen = sizeof (struct sockaddr_in);
  struct ws4d_uri uri_s;
  SOAP_SOCKET result = SOAP_INVALID_SOCKET;

  /* test parameters */
  soap_assert (soap, dpws && dpws->host && soap, SOAP_INVALID_SOCKET);

  ws4d_uri_init (&uri_s);

  if (ws4d_parse_uri (uri, &uri_s, WITH_ALL) != WS4D_OK)
    {
      ws4d_uri_done (&uri_s);
      return SOAP_INVALID_SOCKET;
    }

  if (!data || ((data->port == 0) && (data->transport == NULL)))
    {
      char paddr[DPWS_URI_MAX_LEN + 1];
      char in_port[DPWS_PORT_MAX_LEN + 1];

      if (!strncmp (uri_s.scheme, "soap.udp", 8))
        {
          soap_set_mode (soap, SOAP_IO_UDP);
        }

      /* check if a host is specified */
      if (strncmp (uri_s.host, "host", 4))
        {
          /* check if host ist the same as dpws->host */
          if (strncmp (uri_s.host, dpws->host, strlen (dpws->host)))
            {
              ws4d_uri_done (&uri_s);
              return SOAP_INVALID_SOCKET;
            }
        }

      if (!data)
        {
          if (dpws_handle_init (dpws, soap) == SOAP_OK)
            {
              data = dpws_handle_get_plugindata (soap);
              if (!data)
                {
                  ws4d_uri_done (&uri_s);
                  return SOAP_INVALID_SOCKET;
                }
            }
          else
            {
              ws4d_uri_done (&uri_s);
              return SOAP_INVALID_SOCKET;
            }
        }

      result = soap_bind (soap, dpws->host, atoi (uri_s.port), backlog);
      if (result == SOAP_INVALID_SOCKET)
        {
          ws4d_uri_done (&uri_s);
          soap_print_fault (soap, stderr);
          return SOAP_INVALID_SOCKET;
        }

      getsockname (result, (struct sockaddr *) &addr, &addrlen);

      SNPRINTF (in_port, DPWS_PORT_MAX_LEN, "%d", ntohs (addr.sin_port));
      SNPRINTF (paddr, DPWS_URI_MAX_LEN, "%s://%s:%d", uri_s.scheme,
                dpws->host, ntohs (addr.sin_port));
      data->transport = ws4d_strdup (uri_s.scheme, &dpws->alloc_list);
      data->port = ntohs (addr.sin_port);
      data->paddr = ws4d_strdup (paddr, &dpws->alloc_list);

      uri_s.port = in_port;
      uri_s.host = (char *) dpws->host;
      ws4d_uri_tostr (&uri_s, uri, size);

      ws4d_uri_done (&uri_s);

      wsa_register_handle (soap);

      return result;

    }
  else
    {
      char in_port[DPWS_PORT_MAX_LEN + 1];

      if (uri_s.scheme && strcmp (data->transport, uri_s.scheme))
        {
          ws4d_uri_done (&uri_s);
          return SOAP_INVALID_SOCKET;
        }

      if (uri_s.port && (atoi (uri_s.port) != 0) && (atoi (uri_s.port)
                                                     != data->port))
        {
          ws4d_uri_done (&uri_s);
          return SOAP_INVALID_SOCKET;
        }

      result = soap->master;

      SNPRINTF (in_port, DPWS_PORT_MAX_LEN, "%d", data->port);
      uri_s.port = in_port;
      uri_s.host = (char *) dpws->host;
      ws4d_uri_tostr (&uri_s, uri, size);

      ws4d_uri_done (&uri_s);
      return result;

    }
}

const char *
dpws_handle_get_paddr (struct soap *soap)
{
  struct dpws_soap_s *data = dpws_handle_get_plugindata (soap);

  /* test parameters */
  ws4d_assert (data, NULL);

  return (const char *) data->paddr;
}

#ifdef DPWS_DEVICE
#ifndef DPWS_HOSTED_SERVICE
/******
 * Hosting Service Logic
 *
 */

int
dpws_add_hosting_service (struct dpws_s *dpws, struct soap *hosting,
                          int port, const char *id, const char *uuid,
                          int backlog)
{
  int err;
  char uuidstr[37];
  char *uri = NULL;
  struct ws4d_qname *dpws_type;

  /* test parameters */
  ws4d_assert (dpws && dpws->host && hosting, WS4D_ERR);

  /* is handle or another handle already registered */
  if (dpws->hosting_handle)
    return WS4D_ERR;

#ifdef DEBUG
  soap_omode (hosting, SOAP_XML_INDENT);
#endif

  /* generate logical address of hosting_handle service */
  if (uuid)
    {
      dpws->hosting_addr = ws4d_strdup (uuid, &dpws->alloc_list);
    }
  else
    {
      dpws->hosting_addr = ws4d_malloc_alist (WS4D_UUID_SCHEMA_SIZE
                                             * sizeof (char),
                                             &dpws->alloc_list);
      ws4d_uuid_generate_random (uuidstr);
      ws4d_uuid_generate_schema ((char *) dpws->hosting_addr, uuidstr);
    }

  uri = ws4d_malloc_alist (250, &dpws->alloc_list);
  if (!uri)
    return WS4D_EOM;
  /* TODO: add uuid to device uri */
  sprintf (uri, "http://host:%d/", port);

  if (dpws_handle_bind (dpws, hosting, uri, 250, backlog) ==
      SOAP_INVALID_SOCKET)
    {
      return WS4D_ERR;
    }

  ws4d_free_alist (uri);

  dpws->hosting_handle = hosting;

  /* check if hosting_target service is initialized */
  if (!dpws->dis_mca)
    {
      err = dpws_init_target (dpws, NULL, backlog);
      if (err != WS4D_OK)
        {
          return err;
        }
    }

  /* make hosting_handle service a hosting_target service */
  dpws->hosting_target = wsd_target_add (dpws->dis_mca, dpws->hosting_addr,
                                         dpws_handle_get_paddr (dpws->
                                                                hosting_handle),
                                         NULL);
  if (!dpws->hosting_target)
    {
      return WS4D_ERR;
    }

  if (!id)
    {
      dpws->hosting_id =
        ws4d_malloc_alist (strlen
                          (dpws_handle_get_paddr (dpws->hosting_handle)) +
                          strlen ("HostingService") + 2, &dpws->alloc_list);
      SNPRINTF ((char *) dpws->hosting_id, 100, "%s/%s",
                dpws_handle_get_paddr (dpws->hosting_handle),
                "HostingService");
    }
  else
    {
      dpws->hosting_id = ws4d_strdup (id, &dpws->alloc_list);
    }

  dpws_type = ws4d_qname_alloc (1, &dpws->alloc_list);
  dpws_type->prefix = ws4d_strdup ("wsdp", &dpws->alloc_list);
  dpws_type->name = ws4d_strdup ("Device", &dpws->alloc_list);
  dpws_type->ns =
    ws4d_strdup ("http://schemas.xmlsoap.org/ws/2006/02/devprof",
                  &dpws->alloc_list);
  dpws_add_type (dpws, dpws_type);

  err = mex_register_handle (dpws->hosting_handle, NULL);
  if (err != SOAP_OK)
    {
      return WS4D_ERR;
    }

  return WS4D_OK;
}

int
dpws_add_type (struct dpws_s *device, struct ws4d_qname *type)
{
  int err;

  /* test parameters */
  ws4d_assert (device && device->hosting_target && type, WS4D_ERR);

  err = ws4d_targetep_add_type (device->hosting_target, type);
  if (err)
    {
      return WS4D_ERR;
    }

  err = wsd_target_set_namespaces (device->hosting_target, device->dis_mca,
                                   NULL, &device->alloc_list);
  if (err)
    {
      return WS4D_ERR;
    }
  else
    {
      return WS4D_OK;
    }
}

int
dpws_add_typestring (struct dpws_s *device, const char *types)
{
  int err;

  ws4d_assert (device && types && device->hosting_target, WS4D_ERR);

  err = ws4d_targetep_add_typestr (device->hosting_target, types);
  if (err)
    {
      return WS4D_ERR;
    }

  err = wsd_target_set_namespaces (device->hosting_target, device->dis_mca,
                                   NULL, &device->alloc_list);
  if (err)
    {
      return WS4D_ERR;
    }
  else
    {
      return WS4D_OK;
    }
}

int
dpws_add_scope (struct dpws_s *device, const char *scope)
{
  int err;

  /* test parameters */
  ws4d_assert (device && device->hosting_target && scope, WS4D_ERR);

  err = ws4d_targetep_add_Scope (device->hosting_target, scope);
  if (err)
    {
      return WS4D_ERR;
    }
  else
    {
      return WS4D_OK;
    }
}

/*******
 * Discovery
 *
 */

const char *
dpws_device_get_typeslist (struct dpws_s *dpws)
{
  return ws4d_targetep_get_Types (dpws->hosting_target);
}

int
dpws_device_send_Hello (struct dpws_s *dpws, const char *types)
{
  char MsgId[DPWS_MSGID_SIZE];
  struct soap anounce;

  /* test parameters */
  ws4d_assert (dpws && dpws->hosting_target, WS4D_ERR);

  if (ws4d_targetep_isactive (dpws->hosting_target))
    {
      ws4d_alloc_list alist;

      WS4D_ALLOCLIST_INIT (&alist);

      soap_init (&anounce);
#ifdef DEBUG
      soap_omode (&anounce, SOAP_XML_INDENT);
#endif

      wsa_register_handle (&anounce);

      dpws_header_gen_MessageId (MsgId, DPWS_MSGID_SIZE);
      wsd_send_Hello (&anounce, MsgId, dpws->hosting_target, types, &alist);

      soap_end (&anounce);
      /*to force close */
      anounce.omode &= ~SOAP_IO_UDP;
      soap_done (&anounce);

      ws4d_alloclist_done (&alist);

      return WS4D_OK;
    }
  else
    {
      return WS4D_ERR;
    }
}

int
dpws_device_send_Bye (struct dpws_s *dpws)
{
  char MsgId[DPWS_MSGID_SIZE];
  struct soap anounce;

  /* test parameters */
  ws4d_assert (dpws && dpws->hosting_target, WS4D_ERR);

  if (ws4d_targetep_isactive (dpws->hosting_target))
    {
      ws4d_alloc_list alist;

      WS4D_ALLOCLIST_INIT (&alist);

      soap_init (&anounce);
#ifdef DEBUG
      soap_omode (&anounce, SOAP_XML_INDENT);
#endif

      wsa_register_handle (&anounce);

      dpws_header_gen_MessageId (MsgId, DPWS_MSGID_SIZE);
      wsd_send_Bye (&anounce, MsgId, dpws->hosting_target, &alist);

      soap_end (&anounce);
      /*to force close */
      anounce.omode &= ~SOAP_IO_UDP;
      soap_done (&anounce);

      ws4d_alloclist_done (&alist);

      return WS4D_OK;
    }
  else
    {
      return WS4D_ERR;
    }
}

int
dpws_activate_hosting_service (struct dpws_s *dpws)
{
  const char *types = NULL;
  int err;

  /* test parameters */
  ws4d_assert (dpws && dpws->hosting_target, WS4D_ERR);

  if (ws4d_targetep_activate (dpws->hosting_target, 0, 0, 0) == SOAP_OK)
    {
      types = dpws_device_get_typeslist (dpws);
      err = dpws_device_send_Hello (dpws, types);
      return err;
    }
  else
    {
      return WS4D_OK;
    }
}

int
dpws_deactivate_hosting_service (struct dpws_s *dpws)
{
  int err;

  /* test parameters */
  ws4d_assert (dpws && dpws->hosting_target, WS4D_ERR);

  if (ws4d_targetep_isactive (dpws->hosting_target))
    {
      dpws_device_send_Bye (dpws);
      err = ws4d_targetep_deactivate (dpws->hosting_target);
      if (err)
        {
          return WS4D_ERR;
        }
      else
        {
          return WS4D_OK;
        }
    }
  else
    {
      return WS4D_OK;
    }
}
#endif
#endif

#ifndef DPWS_HOSTED_SERVICE
/**
 * Function to receive Probe messages and to send ProbeMatches messages
 * if the device matches the request. The function processed multicast
 * probes and directed probes. To dispatch these
 */
int
__wsd__Probe (struct soap *soap, struct wsd__ProbeType *wsd__Probe)
{
#ifdef DPWS_DEVICE
  struct ws4d_abs_eprlist matching_targets;

  if (wsa_message_isdup (soap))
    return SOAP_OK;

  ws4d_eprlist_init (&matching_targets, ws4d_eprllist_init, NULL);

  if (wsd_process_probe (soap, wsd__Probe, &matching_targets) == SOAP_OK)
    {
      char to[1024] = "";
      struct soap matches;
      char MsgId[DPWS_MSGID_SIZE];
      struct wsd__ProbeMatchesType my__ProbeMatches;
      ws4d_alloc_list alist;

      WS4D_ALLOCLIST_INIT (&alist);
      soap_init (&matches);
      soap_set_mode (&matches, SOAP_IO_UDP);
      matches.connect_flags = SO_BROADCAST;

#ifdef DEBUG
      soap_omode (&matches, SOAP_XML_INDENT);
#endif
      SNPRINTF (to, 1023, "soap.udp://%s:%d/",
                inet_ntoa (soap->peer.sin_addr), ntohs (soap->peer.sin_port));

      dpws_header_gen_MessageId (MsgId, DPWS_MSGID_SIZE);

      wsd_gen_ProbeMatches (&matches, &matching_targets, &my__ProbeMatches);

      wsd_send_async_ProbeMatches (&matches, MsgId, to, &matching_targets,
                                   soap, &alist, &my__ProbeMatches);

      soap_end (&matches);
      /*to force close */
      matches.omode &= ~SOAP_IO_UDP;
      soap_done (&matches);
      ws4d_alloclist_done (&alist);

      ws4d_eprlist_done (&matching_targets);
    }
  else
    {

      struct dpws_soap_s *data = dpws_handle_get_plugindata (soap);
      if (!data)
        {
          return SOAP_OK;
        }
      if (wsd_process_probe
          (data->dpws->dis_mca, wsd__Probe, &matching_targets) == SOAP_OK)
        {
          ws4d_alloc_list alist;
          char MsgId[DPWS_MSGID_SIZE];
          struct wsd__ProbeMatchesType wsd__ProbeMatches;

          WS4D_ALLOCLIST_INIT (&alist);
          memset (&wsd__ProbeMatches, 0,
                  sizeof (struct wsd__ProbeMatchesType));

          dpws_header_gen_MessageId (MsgId, DPWS_MSGID_SIZE);

          /* TODO: check if we leek here  */
          /* FIX: if this is a hosting handle, we destroy the namespace
           * table */
          wsd_gen_ProbeMatches_header (soap, MsgId, "", &matching_targets,
                                       soap, soap->namespaces, &alist);

          wsd_gen_ProbeMatches (soap, &matching_targets, &wsd__ProbeMatches);

          /* directly send probe matches */
          soap_serializeheader (soap);
          soap_serialize_wsd__ProbeMatchesType (soap, &wsd__ProbeMatches);
          if (soap_begin_count (soap))
            return soap->error;
          if (soap->mode & SOAP_IO_LENGTH)
            {
              if (soap_envelope_begin_out (soap) || soap_putheader (soap)
                  || soap_body_begin_out (soap)
                  || soap_put_wsd__ProbeMatchesType (soap,
                                                     &wsd__ProbeMatches,
                                                     "wsd:ProbeMatches",
                                                     "")
                  || soap_body_end_out (soap) || soap_envelope_end_out (soap))
                return soap->error;
            };
          if (soap_end_count (soap) || soap_response (soap, SOAP_OK)
              || soap_envelope_begin_out (soap) || soap_putheader (soap)
              || soap_body_begin_out (soap)
              || soap_put_wsd__ProbeMatchesType (soap, &wsd__ProbeMatches,
                                                 "wsd:ProbeMatches", "")
              || soap_body_end_out (soap) || soap_envelope_end_out (soap)
              || soap_end_send (soap))
            return soap->error;

          /* TODO: we leek the alist and the types here !!! */

          ws4d_eprlist_done (&matching_targets);

          return SOAP_OK;
        }
      else
        {
          ws4d_eprlist_done (&matching_targets);

          return soap_send_empty_response (soap, SOAP_OK);
        }
    }
#endif
  return SOAP_STOP;
}

int
__wsd__Resolve (struct soap *soap, struct wsd__ResolveType *wsd__Resolve)
{
#ifdef DPWS_DEVICE
  struct ws4d_epr *target = NULL;
  int err;

  if (wsa_message_isdup (soap))
    return SOAP_OK;

  err = wsd_process_resolve (soap, wsd__Resolve, &target);
  if (target != NULL)
    {
      char to[1024];
      struct soap matches;
      char MsgId[DPWS_MSGID_SIZE];
      ws4d_alloc_list alist;

      WS4D_ALLOCLIST_INIT (&alist);
      soap_init (&matches);
      soap_set_mode (&matches, SOAP_IO_UDP);
      matches.connect_flags = SO_BROADCAST;

#ifdef DEBUG
      soap_omode (&matches, SOAP_XML_INDENT);
#endif
      SNPRINTF (to, 1023, "soap.udp://%s:%d/",
                inet_ntoa (soap->peer.sin_addr), ntohs (soap->peer.sin_port));
      dpws_header_gen_MessageId (MsgId, DPWS_MSGID_SIZE);
      wsd_send_ResolveMatches (&matches, MsgId, to, target, soap, &alist);
      soap_end (&matches);
      /*to force close */
      matches.omode &= ~SOAP_IO_UDP;
      soap_done (&matches);
      ws4d_alloclist_done (&alist);
    }
  else
    {
      return err;
    }

#endif
  return SOAP_OK;
}

int
__wsd__Hello (struct soap *soap, struct wsd__HelloType *wsd__Hello)
{
#ifdef DPWS_CLIENT
  if (wsa_message_isdup (soap))
    return SOAP_OK;

  return wsd_process_hello (soap, wsd__Hello);
#else
  return SOAP_OK;
#endif
}

int
__wsd__Bye (struct soap *soap, struct wsd__ByeType *wsd__Bye)
{
#ifdef DPWS_CLIENT
  if (wsa_message_isdup (soap))
    return SOAP_OK;

  return wsd_process_bye (soap, wsd__Bye);
#else
  return SOAP_OK;
#endif
}

int
__wsd__ProbeMatches (struct soap *soap,
                     struct wsd__ProbeMatchesType *wsd__ProbeMatches)
{
#ifdef DPWS_CLIENT
  if (wsa_message_isdup (soap))
    return SOAP_OK;

  return wsd_process_probematches (soap, wsd__ProbeMatches);
#else
  return SOAP_OK;
#endif
}

int
__wsd__ResolveMatches (struct soap *soap,
                       struct wsd__ResolveMatchesType *wsd__ResolveMatches)
{
#ifdef DPWS_CLIENT
  if (wsa_message_isdup (soap))
    return SOAP_OK;

  return wsd_process_resolvematches (soap, wsd__ResolveMatches);
#else
  return SOAP_OK;
#endif
}
#endif

#ifdef DPWS_CLIENT

#include "ws4d_discovery_ctx.h"

int
dpws_probe (struct dpws_s *dpws, ws4d_qnamelist * Types, const char *Scope,
            ws4d_time timeout, int backlog,
            struct dpws_discovery_hooks *dis_hooks, struct ws4d_tc_int *tc,
            struct ws4d_abs_eprlist *result)
{
  struct dpws_probectx ctx;
  struct soap handle;
  char MsgId[DPWS_MSGID_SIZE];
  int ret = 0, matches = 0;

  /* test parameters */
  ws4d_assert (dpws, WS4D_ERR);

  if (!tc)
    {
      ws4d_assert (dpws->tc, WS4D_ERR);
      tc = dpws->tc;
    }

  soap_init (&handle);
#ifdef DEBUG
  soap_omode (&handle, SOAP_XML_INDENT);
#endif

  dpws_header_gen_MessageId (MsgId, DPWS_MSGID_SIZE);

  ret = ws4d_discoveryctx_init (&ctx, (char *) MsgId, dpws->host, tc, &handle,
                                (struct wsd_dis_hooks_t *) dis_hooks,
                                dpws->uni_hooks, backlog);
  if (ret != WS4D_OK)
    {
      ws4d_discoveryctx_done (&ctx);
      return WS4D_ERR;
    }

  ws4d_discoveryctx_set_Types (&ctx, Types);
  ws4d_discoveryctx_set_Scope (&ctx, Scope);

  ret = ws4d_discoveryctx_probe (&ctx, timeout);
  if (ret == WS4D_OK)
    {
      ret = ws4d_discoveryctx_process (&ctx);

      if ((ret == WS4D_OK) || (ret == WS4D_TO))
        {
          matches = ws4d_discoveryctx_get_ProbeResults (&ctx, result);
          if (matches > 0)
            {
              ret = WS4D_OK;
            }
          else
            {
              ret = WS4D_TO;
            }
        }
      else
        {
          ret = WS4D_ERR;
        }
    }
  else
    {
      ret = WS4D_ERR;
    }

  ws4d_discoveryctx_done (&ctx);

  return ret;
}

int
dpws_dprobe (struct dpws_s *dpws, struct ws4d_epr *epr,
             ws4d_qnamelist * Types, const char *Scope,
             struct ws4d_tc_int *tc, ws4d_time timeout)
{
  struct soap handle;
  struct dpws_probectx ctx;
  char MsgId[DPWS_MSGID_SIZE];
  int ret = 0;
  char *addr = NULL;

  /* test parameters */
  ws4d_assert (dpws, WS4D_ERR);

  if (!tc)
    {
      ws4d_assert (dpws->tc, WS4D_ERR);
      tc = dpws->tc;
    }

  addr = (char *) dpws_resolve_addr (dpws, epr, NULL, timeout);
  if (!addr)
    return WS4D_ERR;

  soap_init (&handle);
#ifdef DEBUG
  soap_omode (&handle, SOAP_XML_INDENT);
#endif

  dpws_header_gen_MessageId (MsgId, DPWS_MSGID_SIZE);
  ret = ws4d_discoveryctx_init_directed (&ctx, MsgId, dpws->host, tc, &handle,
                                         NULL, NULL, 100);
  if (ret != SOAP_OK)
    return WS4D_ERR;

  ws4d_discoveryctx_set_Types (&ctx, Types);
  ws4d_discoveryctx_set_Scope (&ctx, Scope);

  ret = ws4d_discoveryctx_dprobe (&ctx, addr, timeout);
  if (ret == SOAP_OK)
    {
      ws4d_discoveryctx_get_dProbeResult (&ctx, epr);
    }

  soap_end (&handle);
  soap_done (&handle);

  ws4d_discoveryctx_done (&ctx);

  return ret;
}

const char *
dpws_resolve_addr (struct dpws_s *dpws, struct ws4d_epr *epr,
                   struct ws4d_tc_int *tc, ws4d_time timeout)
{
  const char *XAddrs = NULL;

  /* test parameters */
  ws4d_assert (dpws && epr, NULL);

  if (!tc)
    {
      ws4d_assert (dpws->tc, NULL);
      tc = dpws->tc;
    }

  ws4d_tc_lock (tc);
  XAddrs = ws4d_tc_get_XAddrs (tc, ws4d_epr_get_Addrs (epr));
  ws4d_tc_unlock (tc);

  if (!XAddrs)
    {
      struct soap handle;
      struct dpws_probectx ctx;
      char MsgId[DPWS_MSGID_SIZE];
      int ret;

      soap_init (&handle);
#ifdef DEBUG
      soap_omode (&handle, SOAP_XML_INDENT);
#endif

      /* do we have to resolve this address ? */
      if (wsd_is_physical_addr (&handle, ws4d_epr_get_Addrs (epr)) == SOAP_OK)
        {
          /* TODO: do this without direct target cache api use */
          ws4d_targetep_set_XAddrs (epr, ws4d_epr_get_Addrs (epr));
          return ws4d_epr_get_Addrs (epr);
        }

      dpws_header_gen_MessageId (MsgId, DPWS_MSGID_SIZE);
      if (ws4d_discoveryctx_init (&ctx, MsgId, dpws->host, tc, &handle, NULL,
                                  NULL, 100) != SOAP_OK)
        {
          return NULL;
        }

      ws4d_discoveryctx_set_Address (&ctx, ws4d_epr_get_Addrs (epr));

      ret = ws4d_discoveryctx_resolve (&ctx, timeout);
      if (ret == WS4D_OK)
        {
          ret = ws4d_discoveryctx_process_resolve (&ctx);

          if (ret == WS4D_OK)
            {
              ws4d_discoveryctx_get_ResolveResult (&ctx, epr);
              XAddrs = ws4d_targetep_get_XAddrs (epr);
            }
        }

      soap_end (&handle);
      soap_done (&handle);

      ws4d_discoveryctx_done (&ctx);
    }

  return XAddrs;
}

#endif

#ifdef DPWS_DEVICE

#include "ws4d_hostingservice.h"
#include "ws4d_eprllist.h"

struct ws4d_epr *
dpws_service_init (struct dpws_s *device, const char *serviceid)
{
  return ws4d_hosting_add_service (&device->hosting, serviceid);
}

int
dpws_service_bind (struct dpws_s *device, struct ws4d_epr *service,
                   struct soap *handle, char *uri, size_t size, int backlog)
{
  int ret;

  /* test parameters */
  ws4d_assert (device && service && handle && uri && (size > 0), WS4D_ERR);

  ret = dpws_handle_bind (device, handle, uri, size, backlog);
  if (ret == SOAP_INVALID_SOCKET)
    {
      return WS4D_ERR;
    }

  ret = ws4d_hosting_bind_service (&device->hosting, service, uri, size);
  if (ret != WS4D_OK)
    {
      return ret;
    }

  ws4d_hosted_set_transportdata (service, handle);

  mex_register_handle (handle, NULL);

  return WS4D_OK;
}

int
dpws_add_hosted_service (struct dpws_s *device,
                         struct ws4d_epr *service, char *uri, size_t size)
{
  /* test parameters */
  ws4d_assert (device, WS4D_ERR);

  return ws4d_hosting_activate_service (&device->hosting, service, uri, size);
}

int
dpws_del_hosted_service (struct dpws_s *device, struct ws4d_epr *service)
{
  /* test parameters */
  ws4d_assert (device, WS4D_ERR);

  return ws4d_hosting_deactivate_service (&device->hosting, service);
}

int
dpws_service_done (struct dpws_s *device, struct ws4d_epr *service)
{
  /* test parameters */
  ws4d_assert (device, WS4D_ERR);

  return ws4d_hosting_remove_service (&device->hosting, service);
}

struct ws4d_epr *
dpws_get_hosted_service (struct dpws_s *device, const char *serviceid)
{
  /* test parameters */
  ws4d_assert (device, NULL);

  return ws4d_hosting_get_service (&device->hosting, serviceid);
}

int
dpws_service_add_type (struct ws4d_epr *service, struct ws4d_qname *type)
{
  return ws4d_hosted_add_type (service, type);
}

int
dpws_service_add_typestring (struct ws4d_epr *service, const char *types)
{
  return ws4d_hosted_add_typestring (service, types);
}

int
dpws_service_set_wsdl (struct ws4d_epr *service, const char *wsdl)
{
  return ws4d_hosted_set_wsdl (service, wsdl);
}

#endif
/******
 * Description
 *
 */

#ifdef DPWS_DEVICE

int
dpws_register_Metadata (struct dpws_s *dpws, struct soap *soap,
                        const char *endpoint, char *dialect, void *metadata)
{
  struct _wsm__MetadataSection *section;
  int err;

  /* test parameters */
  ws4d_assert (dpws && soap && endpoint && *endpoint && dialect
               && *dialect && metadata, WS4D_ERR);

  section = ws4d_malloc_alist (sizeof (struct _wsm__MetadataSection),
                              &dpws->alloc_list);
  if (!section)
    return WS4D_EOM;

  soap_default__wsm__MetadataSection (soap, section);

  section->Dialect = dialect;
#ifndef DPWS_HOSTED_SERVICE
  if (!strcmp (dialect, DPWS_MEX_MODEL))
    {
      struct ws4d_thisModel *src = metadata;
      struct wsdp__ThisModelType *model =
        ws4d_malloc_alist (sizeof (struct wsdp__ThisModelType),
                          dpws_get_alist(dpws));

      if (!model)
        return WS4D_EOM;

      model->ManufacturerUrl =
        ws4d_strdup (src->ManufacturerUrl, dpws_get_alist (dpws));
      model->ModelNumber =
        ws4d_strdup (src->ModelNumber, dpws_get_alist (dpws));
      model->ModelUrl = ws4d_strdup (src->ModelUrl, dpws_get_alist (dpws));
      model->PresentationUrl =
        ws4d_strdup (src->PresentationUrl, dpws_get_alist (dpws));

      model->__sizeManufacturer = src->__sizeManufacturer;
      model->Manufacturer =
        ws4d_locstring_tosoap (src->Manufacturer, src->__sizeManufacturer,
                            dpws_get_alist (dpws));
      model->__sizeModelName = src->__sizeModelName;
      model->ModelName =
        ws4d_locstring_tosoap (src->ModelName, src->__sizeModelName,
                            dpws_get_alist (dpws));

      section->__union_MetadataSection
        = SOAP_UNION__wsm__union_MetadataSection_wsdp__ThisModel;
      section->union_MetadataSection.wsdp__ThisModel = model;
    }
  else if (!strcmp (dialect, DPWS_MEX_DEVICE))
    {
      struct ws4d_thisDevice *src = metadata;
      struct wsdp__ThisDeviceType *_device =
        ws4d_malloc_alist (sizeof (struct wsdp__ThisDeviceType),
                          dpws_get_alist(dpws));

      if (!_device)
        return WS4D_EOM;

      _device->FirmwareVersion =
        ws4d_strdup (src->FirmwareVersion, dpws_get_alist (dpws));
      _device->SerialNumber =
        ws4d_strdup (src->SerialNumber, dpws_get_alist (dpws));

      _device->__sizeFriendlyName = src->__sizeFriendlyName;
      _device->FriendlyName =
        ws4d_locstring_tosoap (src->FriendlyName, src->__sizeFriendlyName,
                            dpws_get_alist (dpws));

      section->__union_MetadataSection
        = SOAP_UNION__wsm__union_MetadataSection_wsdp__ThisDevice;
      section->union_MetadataSection.wsdp__ThisDevice = _device;
    }
  else if (!strcmp (dialect, DPWS_MEX_RELATIONSHIP))
    {
      int i;
      struct ws4d_relationship *src = metadata;
      struct _wsdp__Relationship *relationship =
        ws4d_malloc_alist (sizeof (struct _wsdp__Relationship),
                          dpws_get_alist (dpws));

      if (!relationship)
        return WS4D_EOM;

      /* host section */
      relationship->wsdp__Host =
        ws4d_malloc_alist (sizeof (struct wsdp__HostServiceType),
                          &dpws->alloc_list);
      if (!relationship->wsdp__Host)
        return WS4D_EOM;

      relationship->wsdp__Host->wsa__EndpointReference.Address =
        ws4d_strdup (dpws->hosting_addr, dpws_get_alist (dpws));
      relationship->wsdp__Host->wsdp__Types =
        ws4d_strdup ((char *) dpws_device_get_typeslist (dpws), dpws_get_alist (dpws));
      relationship->wsdp__Host->wsdp__ServiceId =
        ws4d_strdup (dpws->hosting_id, dpws_get_alist (dpws));

      /* hosted section */
      relationship->__sizeHosted = src->hosted_count;
      relationship->wsdp__Hosted =
        ws4d_malloc_alist (sizeof (struct wsdp__HostServiceType) *
                          src->hosted_count, dpws_get_alist (dpws));

      for (i = 0; i < src->hosted_count; i++)
        {
          relationship->wsdp__Hosted[i].wsa__EndpointReference.Address =
            ws4d_strdup (src->hosted[i].Addr, dpws_get_alist (dpws));
          relationship->wsdp__Hosted[i].wsdp__ServiceId =
            ws4d_strdup (src->hosted[i].ServiceId, dpws_get_alist (dpws));
          relationship->wsdp__Hosted[i].wsdp__Types =
            (char *) ws4d_qnamelist_tostring (&src->hosted[i].types,
                                              dpws_get_alist (dpws));
        }

      section->__union_MetadataSection
        = SOAP_UNION__wsm__union_MetadataSection_wsdp__Relationship;
      section->union_MetadataSection.wsdp__Relationship = relationship;
    }
  else
    {
#endif
      section->__union_MetadataSection
        = SOAP_UNION__wsm__union_MetadataSection___any;
      section->union_MetadataSection.__any = metadata;
#ifndef DPWS_HOSTED_SERVICE
    }
#endif

  err = mex_register_metadata_section (soap, endpoint, section);
  if (err)
    return WS4D_ERR;

  return WS4D_OK;
}

void *
dpws_lookup_Metadata (struct dpws_s *dpws, struct soap *soap,
                      const char *endpoint, const char *dialect)
{
  struct _wsm__MetadataSection *section = NULL;

  ws4d_assert (dpws && soap && endpoint && *endpoint && dialect
               && *dialect, NULL);

  section =
    (struct _wsm__MetadataSection *) mex_get_metadata_section (soap, endpoint,
                                                               dialect);
  if (section && section->Dialect)
    {
      if (!strcmp (section->Dialect, DPWS_MEX_MODEL))
        {
          return section->union_MetadataSection.wsdp__ThisModel;
        }
      else if (!strcmp (section->Dialect, DPWS_MEX_DEVICE))
        {
          return section->union_MetadataSection.wsdp__ThisDevice;
        }
      else if (!strcmp (section->Dialect, DPWS_MEX_RELATIONSHIP))
        {
          return section->union_MetadataSection.wsdp__Relationship;
        }
      else
        {
          return section->union_MetadataSection.__any;
        }
    }
  return NULL;
}

int
dpws_free_Metadata (struct dpws_s *dpws, struct soap *soap,
                    const char *endpoint, const char *dialect)
{
  struct _wsm__MetadataSection *section = NULL;

  ws4d_assert (dpws && soap && endpoint && *endpoint && dialect
               && *dialect, WS4D_ERR);

  section =
    (struct _wsm__MetadataSection *) mex_get_metadata_section (soap, endpoint,
                                                               dialect);
  if (section)
    {
      switch (section->__union_MetadataSection)
        {
        case SOAP_UNION__wsm__union_MetadataSection_wsdp__ThisDevice:
          ws4d_free_alist (section->union_MetadataSection.
                               wsdp__ThisDevice->FirmwareVersion);
          ws4d_free_alist (section->union_MetadataSection.
                               wsdp__ThisDevice->SerialNumber);
          soap_locstring_free (section->union_MetadataSection.
                               wsdp__ThisDevice->FriendlyName,
                               section->union_MetadataSection.
                               wsdp__ThisDevice->__sizeFriendlyName);
          ws4d_free_alist (section->union_MetadataSection.
                               wsdp__ThisDevice);
          break;
        case SOAP_UNION__wsm__union_MetadataSection_wsdp__ThisModel:
          ws4d_free_alist (section->union_MetadataSection.
                               wsdp__ThisModel->ManufacturerUrl);
          ws4d_free_alist (section->union_MetadataSection.
                               wsdp__ThisModel->ModelNumber);
          ws4d_free_alist (section->union_MetadataSection.
                               wsdp__ThisModel->ModelUrl);
          ws4d_free_alist (section->union_MetadataSection.
                               wsdp__ThisModel->PresentationUrl);
          soap_locstring_free (section->union_MetadataSection.
                               wsdp__ThisModel->Manufacturer,
                               section->union_MetadataSection.
                               wsdp__ThisModel->__sizeManufacturer);
          soap_locstring_free (section->union_MetadataSection.
                               wsdp__ThisModel->ModelName,
                               section->union_MetadataSection.
                               wsdp__ThisModel->__sizeModelName);
          ws4d_free_alist (section->union_MetadataSection.
                               wsdp__ThisModel);
          break;
        case SOAP_UNION__wsm__union_MetadataSection_wsdp__Relationship:
          ws4d_free_alist (section->union_MetadataSection.
                               wsdp__Relationship->wsdp__Host->
                               wsa__EndpointReference.Address);
          ws4d_free_alist (section->union_MetadataSection.
                               wsdp__Relationship->wsdp__Host->wsdp__Types);
          ws4d_free_alist (section->union_MetadataSection.
                               wsdp__Relationship->wsdp__Host->
                               wsdp__ServiceId);
          if (section->union_MetadataSection.wsdp__Relationship->
              __sizeHosted > 0)
            {
              int i;

              for (i = 0;
                   i <
                   section->union_MetadataSection.wsdp__Relationship->
                   __sizeHosted; i++)
                {
                  ws4d_free_alist (section->union_MetadataSection.
                                       wsdp__Relationship->wsdp__Hosted[i].
                                       wsa__EndpointReference.Address);
                  ws4d_free_alist (section->union_MetadataSection.
                                       wsdp__Relationship->wsdp__Hosted[i].
                                       wsdp__Types);
                  ws4d_free_alist (section->union_MetadataSection.
                                       wsdp__Relationship->wsdp__Hosted[i].
                                       wsdp__ServiceId);
                }
              ws4d_free_alist (section->union_MetadataSection.
                                   wsdp__Relationship->wsdp__Hosted);
            }
          ws4d_free_alist (section->union_MetadataSection.
                               wsdp__Relationship);
          break;
        }
      mex_remove_metadata_section (soap, endpoint, dialect);
      ws4d_free_alist (section);
      return WS4D_OK;
    }
  else
    {
      return WS4D_ERR;
    }
}

#ifndef DPWS_HOSTED_SERVICE

struct ws4d_thisDevice *
dpws_change_thisdevice (struct dpws_s *dpws)
{
  return ws4d_hosting_change_thisdevice (&dpws->hosting);
}

/**
 * Function prepares device metadata section for GetMetadata
 *
 * @param dpws device
 *
 * @return
 */
int
dpws_updatemetadata_Device (struct dpws_s *dpws)
{
  int err = WS4D_OK;
  struct _wsm__MetadataSection *section = NULL;
  struct ws4d_thisDevice *_device =
    ws4d_hosting_get_thisdevice (&dpws->hosting);

  section = dpws_lookup_Metadata (dpws, dpws->hosting_handle,
                                  dpws->hosting_addr, DPWS_MEX_DEVICE);

  if (section)
    {
      dpws_free_Metadata (dpws, dpws->hosting_handle, dpws->hosting_addr,
                          DPWS_MEX_DEVICE);
    }

  err = dpws_register_Metadata (dpws, dpws->hosting_handle,
                                dpws->hosting_addr, DPWS_MEX_DEVICE, _device);
  if (err)
    return err;

  section = dpws_lookup_Metadata (dpws, dpws->hosting_handle,
                                  ws4d_targetep_get_XAddrs (dpws->
                                                            hosting_target),
                                  DPWS_MEX_DEVICE);

  if (section)
    {
      dpws_free_Metadata (dpws, dpws->hosting_handle,
                          ws4d_targetep_get_XAddrs (dpws->hosting_target),
                          DPWS_MEX_DEVICE);
    }

  return dpws_register_Metadata (dpws, dpws->hosting_handle,
                                 ws4d_targetep_get_XAddrs (dpws->
                                                           hosting_target),
                                 DPWS_MEX_DEVICE, _device);
}

struct ws4d_thisModel *
dpws_change_thismodel (struct dpws_s *dpws)
{
  return ws4d_hosting_change_thismodel (&dpws->hosting);
}

/**
 * Function prepares model metadata section for GetMetadata
 *
 * @param dpws device
 *
 * @return
 */

int
dpws_updatemetadata_Model (struct dpws_s *dpws)
{
  int err = WS4D_OK;
  struct _wsm__MetadataSection *section = NULL;
  struct ws4d_thisModel *model = ws4d_hosting_get_thismodel (&dpws->hosting);

  section = dpws_lookup_Metadata (dpws, dpws->hosting_handle,
                                  dpws->hosting_addr, DPWS_MEX_MODEL);

  if (section)
    {
      dpws_free_Metadata (dpws, dpws->hosting_handle, dpws->hosting_addr,
                          DPWS_MEX_MODEL);
    }

  err = dpws_register_Metadata (dpws, dpws->hosting_handle,
                                dpws->hosting_addr, DPWS_MEX_MODEL, model);
  if (err)
    return err;

  section = dpws_lookup_Metadata (dpws, dpws->hosting_handle,
                                  ws4d_targetep_get_XAddrs (dpws->
                                                            hosting_target),
                                  DPWS_MEX_MODEL);

  if (section)
    {
      dpws_free_Metadata (dpws, dpws->hosting_handle,
                          ws4d_targetep_get_XAddrs (dpws->hosting_target),
                          DPWS_MEX_MODEL);
    }

  return dpws_register_Metadata (dpws, dpws->hosting_handle,
                                 ws4d_targetep_get_XAddrs (dpws->
                                                           hosting_target),
                                 DPWS_MEX_MODEL, model);

}

int
dpws_updatemetadata_Relationship (struct dpws_s *dpws)
{
  struct ws4d_relationship *relationship;
  ws4d_qnamelist relationship_types;
  const char *device_types;
  int err = WS4D_OK, i = 0;

  ws4d_qnamelist_init (&relationship_types);

  ws4d_mutex_lock (&dpws->lock);
  relationship =
    ws4d_hosting_get_relationship (&dpws->hosting, &dpws->alloc_list);
  ws4d_mutex_unlock (&dpws->lock);

  device_types = dpws_device_get_typeslist (dpws);
  ws4d_qnamelist_addstring (device_types, &relationship_types,
                            &dpws->alloc_list);

  for (i = 0; i < relationship->hosted_count; i++)
    {
      ws4d_qnamelist_copy (&relationship->hosted[i].types,
                           &relationship_types, &dpws->alloc_list);
    }

  if (dpws_lookup_Metadata (dpws, dpws->hosting_handle, dpws->hosting_addr,
                            DPWS_MEX_RELATIONSHIP))
    {
      dpws_free_Metadata (dpws, dpws->hosting_handle, dpws->hosting_addr,
                          DPWS_MEX_RELATIONSHIP);
    }

  err = dpws_register_Metadata (dpws, dpws->hosting_handle,
                                dpws->hosting_addr, DPWS_MEX_RELATIONSHIP,
                                relationship);
  if (err)
    return err;

  if (dpws_lookup_Metadata (dpws, dpws->hosting_handle,
                            ws4d_targetep_get_XAddrs (dpws->hosting_target),
                            DPWS_MEX_RELATIONSHIP))
    {
      dpws_free_Metadata (dpws, dpws->hosting_handle,
                          ws4d_targetep_get_XAddrs (dpws->hosting_target),
                          DPWS_MEX_RELATIONSHIP);
    }

  err = dpws_register_Metadata (dpws, dpws->hosting_handle,
                                ws4d_targetep_get_XAddrs (dpws->
                                                          hosting_target),
                                DPWS_MEX_RELATIONSHIP, relationship);

  if (err)
    return err;

  if (!ws4d_list_empty (&relationship_types))
    {
      struct Namespace *result = NULL, *type_namespaces = NULL;

      type_namespaces = soap_qnamelist_namespaces (&relationship_types,
                                                   &dpws->alloc_list,
                                                   (struct Namespace *) dpws->
                                                   hosting_handle->
                                                   namespaces);
      result =
        soap_extend_namespaces ((struct Namespace *) dpws->hosting_handle->
                                namespaces, type_namespaces,
                                &dpws->alloc_list);

      soap_set_namespaces (dpws->hosting_handle, result);

      ws4d_qnamelist_clear (&relationship_types);
    }

  ws4d_hosting_free_relationship (&dpws->hosting, relationship);

  return WS4D_OK;
}
#endif

int
dpws_updatemetadata_WSDL (struct dpws_s *dpws)
{
  register struct ws4d_epr *service = NULL, *iter;
  struct ws4d_abs_eprlist services;
  int err, service_count = 0;

  /* test parameters */
  ws4d_assert (dpws && dpws->wsdls, WS4D_ERR);

  ws4d_eprlist_init (&services, ws4d_eprllist_init, NULL);

  ws4d_mutex_lock (&dpws->lock);
  service_count = ws4d_hosting_get_activeservices (&dpws->hosting, &services);
  ws4d_mutex_unlock (&dpws->lock);

  if (service_count == 0)
    {
      return WS4D_OK;
    }

  ws4d_eprlist_foreach (service, iter, &services)
  {
    char *buf = NULL;
    int d = 0;
    struct soap *service_handle = ws4d_hosted_get_transportdata (service);
    const char *service_addr = ws4d_epr_get_Addrs (service);

    if (!service_handle || !service_addr)
      continue;

    ws4d_mutex_lock (&dpws->lock);
    while (dpws->wsdls[d].content != NULL)
      {
        const char *wsdl_name = ws4d_hosted_get_wsdl (service);
        if (strstr (dpws->wsdls[d].name, wsdl_name))
          {
            buf = dpws->wsdls[d].content;
            break;
          }
        d++;
      }
    ws4d_mutex_unlock (&dpws->lock);

    if (dpws_lookup_Metadata (dpws, service_handle,
                              service_addr, DPWS_MEX_WSDL))
      {
        dpws_free_Metadata (dpws, service_handle,
                            service_addr, DPWS_MEX_WSDL);
      }

    if (buf != NULL)
      {
        err =
          dpws_register_Metadata (dpws, service_handle, service_addr,
                                  DPWS_MEX_WSDL, buf);
        if (err != WS4D_OK)
          {
            return err;
          }
      }
  }

  ws4d_eprlist_clear (&services);
  ws4d_eprlist_done (&services);

  return WS4D_OK;
}

int
dpws_update_Metadata_hosting (struct dpws_s *device)
{
  ws4d_assert (device, WS4D_ERR);

#ifndef DPWS_HOSTED_SERVICE
  if (dpws_updatemetadata_Relationship (device)
      || dpws_updatemetadata_Model (device)
      || dpws_updatemetadata_Device (device))
    {
      return WS4D_ERR;
    }

  if (ws4d_targetep_isactive (device->hosting_target))
    {
      const char *types = NULL;
      int err;

      types = dpws_device_get_typeslist (device);
      err = dpws_device_send_Hello (device, types);

      return err;
    }
#endif
  return WS4D_OK;
}

int
dpws_update_Metadata (struct dpws_s *device)
{
  ws4d_assert (device, WS4D_ERR);

  if (
#ifndef DPWS_HOSTED_SERVICE
       dpws_updatemetadata_WSDL (device)
       || dpws_update_Metadata_hosting (device)
#else
       dpws_updatemetadata_WSDL (device)
#endif
    )
    {
      return WS4D_ERR;
    }

#ifndef DPWS_HOSTED_SERVICE

#endif

  return WS4D_OK;
}
#endif

#ifdef DEVPROF_2005_05
int
wsm__GetMetadata (struct soap *soap,
                  char *wsm__Dialect,
                  char *wsm__Identifier,
                  struct _wsm__Metadata *_GetMetadataResponseMsg)
{
  return SOAP_OK;
}

int
wsm__Get (struct soap *soap, void *_, struct _wsm__Metadata *_GetResponseMsg)
{
  return SOAP_OK;
}
#endif

#ifdef DEVPROF_2006_02
int
__wst__Get (struct soap *soap, void *emptyMessage,
            struct _wsm__Metadata *_GetResponseMsg)
{
#ifdef DPWS_DEVICE
  char *to = NULL;

  /* TODO: should use a check function here */
  if (!soap || !soap->header || !soap->header->wsa__To)
    return SOAP_SYNTAX_ERROR;

  to = soap->header->wsa__To;
  return mex_process_TransferGet (soap, to, _GetResponseMsg);
#else
  return SOAP_ERR;
#endif
}
#endif

#ifdef DPWS_CLIENT
#include "ws4d_servicecache.h"

int
dpws_find_cached_service (struct dpws_s *dpws, struct ws4d_epr *device,
                          ws4d_qnamelist * types,
                          struct ws4d_abs_eprlist *result)
{
  int ret;

  /* test parameters */
  ws4d_assert (dpws && device && types && result, WS4D_ERR);

  ws4d_servicecache_lock (device);
  ret = ws4d_sc_get_servicesByType2 (device, types, result);
  ws4d_servicecache_unlock (device);

  return ret;
}

/**
 * Function analyses the relationship section of a received
 * GetMetadata response.
 *
 * @param device
 * @param Relationship
 *
 * @return
 */
int
dpws_analyse_Relationship (struct ws4d_epr *device,
                           struct _wsm__MetadataSection *relationship_section)
{
  int i = 0;
  struct _wsdp__Relationship *Relationship = NULL;
  struct wsdp__HostServiceType *service = NULL;

  /* test parameters */
  ws4d_assert (device && relationship_section
               && (relationship_section->__union_MetadataSection
                   ==
                   SOAP_UNION__wsm__union_MetadataSection_wsdp__Relationship),
               WS4D_ERR);

  Relationship
    = relationship_section->union_MetadataSection.wsdp__Relationship;
  if (!Relationship)
    {
      return WS4D_ERR;
    }

  if (Relationship->__sizeHosted < 1)
    {
      return WS4D_OK;
    }

  for (i = 0, service = Relationship->wsdp__Hosted; (i < Relationship->
                                                     __sizeHosted) && service;
       i++, service++)
    {
      struct ws4d_epr *service_epr;
      char *Id = service->wsdp__ServiceId;

      ws4d_assert (Id && service->wsa__EndpointReference.Address, WS4D_ERR);

      ws4d_servicecache_lock (device);

      if (ws4d_sc_check_service (device, Id) != WS4D_SERVICECACHE_INVALID)
        {

          if (service->wsa__EndpointReference.Address)
            {
              ws4d_sc_set_Addrs (device, Id,
                                 (const char *) service->
                                 wsa__EndpointReference.Address);
            }

          if (service->wsdp__Types)
            {
              ws4d_sc_set_Types (device, Id,
                                 (const char *) service->wsdp__Types);
            }
        }

      ws4d_servicecache_unlock (device);
    }

  return WS4D_OK;
}

int
dpws_metadata_get (struct dpws_s *dpws, struct soap *soap,
                   struct ws4d_epr *device, void *metadata, ws4d_time timeout)
{
  char MsgId[DPWS_MSGID_SIZE];
  int err = WS4D_OK;
  char *addr = NULL;

  /* test parameters */
  ws4d_assert (soap && device, WS4D_ERR);

  addr = (char *) dpws_resolve_addr (dpws, device, NULL, timeout);
  if (!addr)
    return WS4D_ERR;

  err = mex_soap_init (soap, NULL);
  if (err)
    return err;

  dpws_header_gen_MessageId (MsgId, DPWS_MSGID_SIZE);

  return mex_GetMetadata (soap, MsgId, addr, ws4d_epr_get_Addrs (device), NULL,
                          metadata, timeout);
}

struct _wsm__MetadataSection *
dpws_metadata_filter (struct _wsm__Metadata
                      *metadata, char *dialect, int *iter)
{
  if (dialect && (iter) && (metadata)
      && (metadata->__sizeMetadataSection > 0))
    {
      struct _wsm__MetadataSection *MetadataSection = NULL;
      while (*iter < metadata->__sizeMetadataSection)
        {
          MetadataSection = &metadata->wsm__MetadataSection[*iter];
          if (MetadataSection && MetadataSection->Dialect
              && !strcmp (MetadataSection->Dialect, dialect))
            {
              (*iter)++;
              return MetadataSection;
            }
          (*iter)++;
        }
    }
  return NULL;
}
#endif

#include "ws4d_device_description.h"

int
dpws_copy_ThisDevice (struct ws4d_thisDevice *dst,
                      struct wsdp__ThisDeviceType *src,
                      ws4d_alloc_list * alist)
{
  /* test parameters */
  ws4d_assert (dst && src && alist, WS4D_ERR);

  memset (dst, 0, sizeof (struct ws4d_thisDevice));

  if (src->FirmwareVersion)
    {
      dst->FirmwareVersion = ws4d_strdup (src->FirmwareVersion, alist);
      if (!dst->FirmwareVersion)
        return WS4D_EOM;
    }

  if (src->SerialNumber)
    {
      dst->SerialNumber = ws4d_strdup (src->SerialNumber, alist);
      if (!dst->SerialNumber)
        return WS4D_EOM;
    }

  if (src->__sizeFriendlyName > 0)
    {
      dst->__sizeFriendlyName = src->__sizeFriendlyName;
      /* TODO: fix dirty cast */
      dst->FriendlyName =
        soap_locstring_tows4d (src->FriendlyName, src->__sizeFriendlyName,
                               alist);
      if (!dst->FriendlyName)
        return WS4D_EOM;
    }

  return WS4D_OK;
}

#ifdef DPWS_CLIENT
int
dpws_metadata_getThisDevice (void *metadata,
                             ws4d_alloc_list * alist,
                             struct ws4d_thisDevice *result)
{
  struct _wsm__MetadataSection *device_section = NULL;
  int i = 0;

  /* test parameters */
  ws4d_assert (metadata && alist && result, WS4D_ERR);

  if ((device_section = dpws_metadata_filter (metadata, DPWS_MEX_DEVICE, &i)))
    {
      if (device_section->__union_MetadataSection
          == SOAP_UNION__wsm__union_MetadataSection_wsdp__ThisDevice)
        {
          dpws_copy_ThisDevice (result, device_section->union_MetadataSection.
                                wsdp__ThisDevice, alist);
          return WS4D_OK;
        }
    }
  return WS4D_ERR;
}
#endif

int
dpws_copy_ThisModel (struct ws4d_thisModel *dst,
                     struct wsdp__ThisModelType *src, ws4d_alloc_list * alist)
{
  /* test parameters */
  ws4d_assert (dst && src && alist, WS4D_ERR);

  memset (dst, 0, sizeof (struct ws4d_thisModel));

  if (src->ManufacturerUrl)
    {
      dst->ManufacturerUrl = ws4d_strdup (src->ManufacturerUrl, alist);
      if (!dst->ManufacturerUrl)
        return WS4D_EOM;
    }
  if (src->ModelNumber)
    {
      dst->ModelNumber = ws4d_strdup (src->ModelNumber, alist);
      if (!dst->ModelNumber)
        return WS4D_EOM;
    }
  if (src->ModelUrl)
    {
      dst->ModelUrl = ws4d_strdup (src->ModelUrl, alist);
      if (!dst->ModelUrl)
        return WS4D_EOM;
    }
  if (src->PresentationUrl)
    {
      dst->PresentationUrl = ws4d_strdup (src->PresentationUrl, alist);
      if (!dst->PresentationUrl)
        return WS4D_EOM;
    }

  if (src->__sizeManufacturer > 0)
    {
      dst->__sizeManufacturer = src->__sizeManufacturer;
      dst->Manufacturer =
        soap_locstring_tows4d (src->Manufacturer, src->__sizeManufacturer,
                            alist);
      if (!dst->Manufacturer)
        return WS4D_EOM;
    }

  if (src->__sizeModelName)
    {
      dst->__sizeModelName = src->__sizeModelName;
      dst->ModelName =
        soap_locstring_tows4d (src->ModelName, src->__sizeModelName, alist);
      if (!dst->ModelName)
        return WS4D_EOM;
    }

  return WS4D_OK;
}

#ifdef DPWS_CLIENT
int
dpws_metadata_getThisModel (void *metadata,
                            ws4d_alloc_list * alist,
                            struct ws4d_thisModel *result)
{
  struct _wsm__MetadataSection *model_section = NULL;
  int i = 0;

  /* test parameters */
  ws4d_assert (metadata && alist && result, WS4D_ERR);

  if ((model_section = dpws_metadata_filter (metadata, DPWS_MEX_MODEL, &i)))
    {

      if (model_section->__union_MetadataSection
          == SOAP_UNION__wsm__union_MetadataSection_wsdp__ThisModel)
        {
          dpws_copy_ThisModel (result, model_section->union_MetadataSection.
                               wsdp__ThisModel, alist);
          return WS4D_OK;
        }
    }

  return WS4D_ERR;
}

int
dpws_metadata_getservices_bytype (struct dpws_s *dpws,
                                  struct ws4d_epr *device,
                                  ws4d_qnamelist * types,
                                  void *metadata,
                                  struct ws4d_abs_eprlist *result)
{
  struct _wsm__MetadataSection *relationship_section = NULL;
  int err = WS4D_ERR;
  int i = 0;

  relationship_section = dpws_metadata_filter (metadata,
                                               DPWS_MEX_RELATIONSHIP, &i);
  if (relationship_section)
    {
      ws4d_register_sc (device);

      err = dpws_analyse_Relationship (device, relationship_section);
      if (err)
        return err;

      do
        {
          relationship_section = dpws_metadata_filter (metadata,
                                                       DPWS_MEX_RELATIONSHIP,
                                                       &i);
          if (relationship_section)
            {
              err = dpws_analyse_Relationship (device, relationship_section);
              if (err)
                return err;
            }

        }
      while (relationship_section);
    }

  return dpws_find_cached_service (dpws, device, types, result);
}

int
dpws_metadata_getservices (struct dpws_s *dpws, struct ws4d_epr *device,
                           void *metadata, struct ws4d_abs_eprlist *result)
{
  ws4d_qnamelist empty_list;

  ws4d_qnamelist_init (&empty_list);
  return dpws_metadata_getservices_bytype (dpws, device, &empty_list,
                                           metadata, result);
}

int
dpws_find_services (struct dpws_s *dpws, struct ws4d_epr *device,
                   ws4d_qnamelist * types, ws4d_time timeout,
                   struct ws4d_abs_eprlist *result)
{
  struct soap handle;
  int ret = 0;
  struct _wsm__Metadata metadata;

  soap_init (&handle);
#ifdef DEBUG
  soap_omode (&handle, SOAP_XML_INDENT);
#endif

  ret = dpws_metadata_get (dpws, &handle, device, &metadata, timeout);
  if (ret != WS4D_OK)
    {
      soap_print_fault (&handle, stderr);
      ret = WS4D_ERR;
    }
  else
    {
      ret = dpws_metadata_getservices_bytype (dpws, device, types, &metadata,
                                              result);
    }

  soap_end (&handle);
  soap_done (&handle);
  return ret;
}

#endif
#ifdef DPWS_DEVICE
#define WSE_SERVER
#endif
#ifdef DPWS_CLIENT
#define WSE_CLIENT
#endif
#include "ws-eventing.h"

#define DPWS_DELIVERYMODE_PUSH "http://schemas.xmlsoap.org/ws/2004/08/eventing/DeliveryModes/Push"
const char *dpws_deliveryMode_push = DPWS_DELIVERYMODE_PUSH;
#define DPWS_FILTERTYPE_ACTION "http://schemas.xmlsoap.org/ws/2006/02/devprof/Action"
const char *dpws_filterType_action = DPWS_FILTERTYPE_ACTION;

#ifdef DPWS_DEVICE

#define DPWS_SUBSCRIPTION_SERVICE "wse_subscription_manager"

int
dpws_processDelivery_push (struct ws4d_subscription *subs, void *data)
{
  struct wse__DeliveryType *delivery = data;

  /* test parameters */
  ws4d_assert (subs && delivery && delivery->wse__NotifyTo.Address, SOAP_ERR);

  if (delivery && delivery->Mode
      && !strcmp (delivery->Mode, dpws_deliveryMode_push)
      && delivery->wse__NotifyTo.Address)
    {
      subs->delivery_mode->DeliveryMode = dpws_deliveryMode_push;
      subs->delivery_mode->data = strdup (delivery->wse__NotifyTo.Address);

      return SOAP_OK;
    }

  return SOAP_ERR;
}

int
dpws_cleanDelivery_push (struct ws4d_subscription *subs)
{
  if (subs->delivery_mode && subs->delivery_mode->data)
    free (subs->delivery_mode->data);

  return WS4D_OK;
}

int
dpws_processFilterType_action (struct ws4d_subscription *subs, void *data)
{
  struct wse__FilterType *Filter = data;

  /* test parameters */
  ws4d_assert (subs && Filter && Filter->__mixed, SOAP_ERR);

  if (Filter && Filter->Dialect
      && !strcmp (Filter->Dialect, dpws_filterType_action))
    {
      subs->filter_mode->FilterType = dpws_filterType_action;
      subs->filter_mode->data =
        (void *) ws4d_xsdList_to_Array (Filter->__mixed,
                                        ws4d_subs_get_alist (subs));

      return SOAP_OK;
    }

  return SOAP_ERR;
}

int
dpws_cleanFilterType_action (struct ws4d_subscription *subs)
{
  if (subs->filter_mode && subs->filter_mode->data)
    ws4d_free_xsdArray (subs->filter_mode->data);

  return SOAP_OK;
}

#define EVENTSOURCE_URI_LEN 255

int
dpws_activate_eventsource (struct dpws_s *device, struct soap *evsrc)
{
  struct soap *hosting = NULL;

  /* test parameters */
  ws4d_assert (device && evsrc, WS4D_ERR);

  if (device->hosting_handle)
    {
      device->subsman = device->hosting_handle;
    }
  else
    {
      device->subsman = evsrc;
    }

  if (!wse_subsm_get_plugindata (device->subsman))
    {
      struct ws4d_epr *subsserv = NULL;
      struct ws4d_subsmanager *subsman;
      char uri[EVENTSOURCE_URI_LEN];
      char *p_addr = (char *) dpws_handle_get_paddr (device->subsman);

      subsserv = dpws_service_init (device, DPWS_SUBSCRIPTION_SERVICE);
      memset (uri, 0, EVENTSOURCE_URI_LEN);
      memcpy (uri, p_addr, strlen (p_addr) + 1);

      if (dpws_service_bind (device, subsserv, device->subsman, uri,
                             EVENTSOURCE_URI_LEN, 100))
        {
          return WS4D_ERR;
        }

      wse_subsm_register_handle (device->subsman,
                                 ws4d_epr_get_Addrs (subsserv));

      subsman = wse_subsm_get (device->subsman);

      ws4d_subsm_register_eventdelivery (subsman,
                                         dpws_deliveryMode_push,
                                         dpws_processDelivery_push,
                                         dpws_cleanDelivery_push);

      ws4d_subsm_register_eventfilter (subsman,
                                       dpws_filterType_action,
                                       dpws_processFilterType_action,
                                       dpws_cleanFilterType_action);
    }

  if (!wse_evsrc_get_plugindata (evsrc))
    {
      wse_evsrc_register_handle (evsrc, device->subsman);
      return WS4D_OK;
    }

  return WS4D_ERR;
}

int
dpws_check_subscriptions (struct dpws_s *device)
{
  struct ws4d_subsmanager *subsman;
  int err;

  /* test parameters */
  ws4d_assert (device, WS4D_ERR);

  subsman = wse_subsm_get (device->subsman);
  ws4d_subsm_lock (subsman);
  err = ws4d_subsm_check_allsubs (subsman);
  ws4d_subsm_unlock (subsman);

  return err;
}

int
dpws_end_subscription (struct dpws_s *device, struct ws4d_subscription *subs,
                       const char *status, char *reason)
{
  char MsgId[DPWS_MSGID_SIZE];

  /* test parameters */
  ws4d_assert (device && device->hosting_handle, WS4D_ERR);

  dpws_header_gen_MessageId (MsgId, DPWS_MSGID_SIZE);
  return wse_subsm_end_subs (device->subsman, MsgId, subs, status, reason);
}

struct ws4d_subscription *
dpws_subsm_get_nextelem_by_action (struct ws4d_subsmanager *subsman,
                                   struct ws4d_subscription *cur,
                                   const char *action)
{
  register struct ws4d_subscription *res = NULL;
  ws4d_alloc_list alist;
  WS4D_ALLOCLIST_INIT (&alist);

  res =
    ws4d_list_prepare_entry (cur, &subsman->subs_list,
                             struct ws4d_subscription, list);
  if (res == NULL)
    return NULL;

  ws4d_list_foreach_continue (res, &subsman->subs_list,
                              struct ws4d_subscription, list)
  {
    if (ws4d_subsm_is_filter (res, dpws_filterType_action))
      {
        char **actionArray = ws4d_xsdList_to_Array (action, &alist);

        if (ws4d_xsdArray_match (res->filter_mode->data, actionArray, strcmp)
            > 0)
          {
            ws4d_free_xsdArray (actionArray);
            ws4d_alloclist_done (&alist);
            return res;
          }

        ws4d_free_xsdArray (actionArray);
        ws4d_alloclist_done (&alist);
      }
  }
  return NULL;
}

struct ws4d_subscription *
dpws_subsm_get_next_by_action (struct dpws_s *device,
                               struct ws4d_subscription *cur,
                               const char *action)
{
  struct ws4d_subsmanager *subsman;

  /* test parameters */
  ws4d_assert (device, NULL);

  subsman = wse_subsm_get (device->subsman);
  return dpws_subsm_get_nextelem_by_action (subsman, cur, action);
}

struct ws4d_subscription *
dpws_subsm_get_first_by_action (struct dpws_s *device, const char *action)
{
  struct ws4d_subsmanager *subsman;

  /* test parameters */
  ws4d_assert (device, NULL);

  subsman = wse_subsm_get (device->subsman);
  return dpws_subsm_get_nextelem_by_action (subsman, NULL, action);
}

char *
dpws_subsm_get_deliveryPush_address (struct dpws_s *device,
                                     struct ws4d_subscription *subs)
{
  if (ws4d_subsm_is_delivery (subs, dpws_deliveryMode_push))
    return subs->delivery_mode->data;
  else
    return NULL;
}
#endif

int
__wse__Subscribe (struct soap *soap, struct _wse__Subscribe *__req,
                  struct _wse__SubscribeResponse *_res)
{
  int err = SOAP_OK;
#ifdef DPWS_DEVICE
  struct ws4d_subscription *subs = NULL;
  char *to = NULL;

  err = wse_process_subscribe_req (soap, __req, &subs);
  if (err != SOAP_OK)
    return SOAP_ERR;

  to = wsa_header_get_ReplyTo (soap);
  if (!to)
    {
      to = (char *) wsa_anonymousURI;
    }

  return wse_process_subscribe_res (soap, to, subs, _res);
#else
  return err;
#endif
}

int
__wse__Renew (struct soap *soap, struct _wse__Renew *__req,
              struct _wse__RenewResponse *_res)
{
  int err = SOAP_OK;
#ifdef DPWS_DEVICE
  struct ws4d_subscription *subs = NULL;
  char *to = NULL;

  err = wse_process_renew_req (soap, __req, &subs);
  if (err != SOAP_OK)
    return SOAP_ERR;

  to = wsa_header_get_ReplyTo (soap);
  if (!to)
    {
      to = (char *) wsa_anonymousURI;
    }

  return wse_process_renew_res (soap, to, subs, _res);
#else
  return err;
#endif
}

int
__wse__GetStatus (struct soap *soap, struct _wse__GetStatus *__req,
                  struct _wse__GetStatusResponse *_res)
{
  int err = SOAP_OK;
#ifdef DPWS_DEVICE
  struct ws4d_subscription *subs = NULL;
  char *to = NULL;

  err = wse_process_getstatus_req (soap, __req, &subs);
  if (err != SOAP_OK)
    return SOAP_ERR;

  to = wsa_header_get_ReplyTo (soap);
  if (!to)
    {
      to = (char *) wsa_anonymousURI;
    }

  return wse_process_getstatus_res (soap, to, subs, _res);
#else
  return err;
#endif
}

struct __wse__UnsubscribeResponse
{
  void *noresp;
};

int
__wse__Unsubscribe (struct soap *soap,
                    struct _wse__Unsubscribe *wse__Unsubscribe,
                    struct __wse__UnsubscribeResponse *_param_1)
{
#ifdef DPWS_DEVICE
  return wse_process_unsubscribe (soap, wse__Unsubscribe);
#else
  return SOAP_OK;
#endif
}

int
__wse__SubscriptionEnd (struct soap *soap, struct _wse__SubscriptionEnd *_req)
{
#ifdef DPWS_CLIENT
  return wse_process_subscriptionend (soap, _req);
#else
  return SOAP_OK;
#endif
}

#ifdef DPWS_CLIENT
int
dpws_init_eventsink (struct soap *soap, char *transport, int port,
                     struct wse_evsnk_hooks_t *hooks)
{
  return wse_evsnk_register_handle (soap, hooks);
}

struct ws4d_delivery_type *
dpws_gen_delivery_push (struct soap *soap, const char *notifyTo)
{
  struct ws4d_delivery_type *result = NULL;
  struct wse__DeliveryType *data = NULL;

  result = (struct ws4d_delivery_type *)
    soap_malloc (soap, sizeof (struct ws4d_delivery_type));
  if (!result)
    return NULL;

  data = (struct wse__DeliveryType *)
    soap_malloc (soap, sizeof (struct wse__DeliveryType));
  if (!data)
    return NULL;

  soap_default_wse__DeliveryType (soap, data);

  result->data = data;
  result->DeliveryMode = (char *) dpws_deliveryMode_push;

  data->Mode = (char *) dpws_deliveryMode_push;
  data->wse__NotifyTo.Address = soap_strdup (soap, notifyTo);

  return result;
}

struct ws4d_filter_type *
dpws_gen_filter_action (struct soap *soap, const char *action_list)
{
  struct ws4d_filter_type *result = NULL;
  struct wse__FilterType *data = NULL;

  result = (struct ws4d_filter_type *)
    soap_malloc (soap, sizeof (struct ws4d_filter_type));
  if (!result)
    return NULL;

  data = (struct wse__FilterType *)
    soap_malloc (soap, sizeof (struct wse__FilterType));
  if (!data)
    return NULL;

  soap_default_wse__FilterType (soap, data);

  result->data = data;
  result->FilterType = (char *) dpws_filterType_action;

  data->Dialect = (char *) dpws_filterType_action;

  data->__sizeftype = 1;
  data->__ftype = soap_malloc (soap, sizeof (struct wse__FilterAnyType));
  data->__ftype[0].__any = soap_strdup (soap, action_list);
  data->__ftype[0].__type = SOAP_TYPE_string;

  return result;
}

const char *
dpws_subscribe (struct soap *soap, struct ws4d_epr *service,
                const char *endToAddress, ws4d_time * expires,
                struct ws4d_delivery_type *delivery,
                struct ws4d_filter_type *filter)
{
  char MsgId[DPWS_MSGID_SIZE];
  struct wsa__EndpointReferenceType *subsman;
  struct ws4d_subscription *subs;
  const char *subsid;
  struct ws4d_dur dur;

  dpws_header_gen_MessageId (MsgId, DPWS_MSGID_SIZE);

  ws4d_s_to_dur (*expires - ws4d_systime_s (), &dur);
  subsman =
    wse_subscribe (soap, MsgId, ws4d_epr_get_Addrs (service), endToAddress,
                   delivery->data, &dur, filter->data);

  subsid = wse_subs_get_id (subsman);

  subs = ws4d_subsproxy_addsubs (service, subsid);
  ws4d_dur_to_s (&dur, expires);
  expires += ws4d_systime_s ();
  ws4d_subs_set_expires (subs, *expires);
  ws4d_subs_set_subsm (subs, subsman->Address);

  return ws4d_subs_get_id (subs);
}

const char *
dpws_subs_get_subsman (struct ws4d_epr *service, const char *id)
{
  return ws4d_subsproxy_getsubsman (service, id);
}

int
dpws_subs_renew2 (struct soap *soap, const char *subsman, const char *id,
                  ws4d_time * expires)
{
  int err;
  char MsgId[DPWS_MSGID_SIZE];
  struct ws4d_dur dur;

  ws4d_assert (expires, WS4D_ERR);

  dpws_header_gen_MessageId (MsgId, DPWS_MSGID_SIZE);
  ws4d_s_to_dur (*expires - ws4d_systime_s (), &dur);

  err = wse_subs_renew (soap, MsgId, subsman, id, &dur);
  if (err)
    return err;

  err = ws4d_dur_to_s (&dur, expires);
  expires += ws4d_systime_s ();

  return err;
}

int
dpws_subs_renew (struct soap *soap, struct ws4d_epr *service, const char *id,
                 ws4d_time * expires)
{
  int err;
  struct ws4d_subscription *subs;

  subs = ws4d_subsproxy_getsubs (service, id);
  if (!subs)
    return WS4D_ERR;

  err = dpws_subs_renew2 (soap, ws4d_subs_get_subsm (subs), id, expires);
  if (err)
    return err;

  return ws4d_subs_set_expires (subs, *expires);
}

int
dpws_subs_unsubscribe2 (struct soap *soap, const char *subsman,
                        const char *id)
{
  char MsgId[DPWS_MSGID_SIZE];

  dpws_header_gen_MessageId (MsgId, DPWS_MSGID_SIZE);

  return wse_subs_unsubscribe (soap, MsgId, subsman, id);
}

int
dpws_subs_unsubscribe (struct soap *soap, struct ws4d_epr *service,
                       const char *id)
{
  int err;
  struct ws4d_subscription *subs;

  subs = ws4d_subsproxy_getsubs (service, id);
  if (!subs)
    return WS4D_ERR;

  err = dpws_subs_unsubscribe2 (soap, ws4d_subs_get_subsm (subs), id);
  if (err)
    return err;

  return ws4d_subsproxy_delsubs (service, id);
}

int
dpws_subs_get_status2 (struct soap *soap, const char *subsman, const char *id,
                       ws4d_time * expires)
{
  int err;
  struct ws4d_dur *res = NULL;
  char MsgId[DPWS_MSGID_SIZE];

  dpws_header_gen_MessageId (MsgId, DPWS_MSGID_SIZE);

  res = wse_subs_get_status (soap, MsgId, subsman, id);
  if (!res)
    return WS4D_ERR;

  err = ws4d_dur_to_s (res, expires);
  expires += ws4d_systime_s ();
  return err;
}

int
dpws_subs_get_status (struct soap *soap, struct ws4d_epr *service,
                      const char *id, ws4d_time * expires)
{
  int err;
  struct ws4d_subscription *subs;

  ws4d_assert (service, WS4D_ERR);

  subs = ws4d_subsproxy_getsubs (service, id);
  if (!subs)
    return WS4D_ERR;

  err = dpws_subs_get_status2 (soap, ws4d_subs_get_subsm (subs), id, expires);
  if (err)
    return err;

  return ws4d_subs_set_expires (subs, *expires);
}
#endif

/** @} */
