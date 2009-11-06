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
#include "ws-addressing.h"

/**
 * @addtogroup Internals Internals
 *
 * @{
 */
#define WSA_PLUGIN_ID "WS-Addressing-PLUGIN-0.1"

const char *wsa_faultAction =
  "http://schemas.xmlsoap.org/ws/2004/08/addressing/fault";
const char *wsa_anonymousURI =
  "http://schemas.xmlsoap.org/ws/2004/08/addressing/role/anonymous";
#define WSA_MAX_MESSAGEIDS 5
const char *wsa_plugin_id = WSA_PLUGIN_ID;
struct wsa_plugin_data
{
  struct ws4d_list_node message_ids;
  struct ws4d_list_node free_ids;
  struct message_id_s *ids_buffer;
  int ids_buffer_size;
  int (*fheader) (struct soap *);
  void (*fseterror) (struct soap *, const char **, const char **);
  ws4d_alloc_list alist;
#ifdef WITH_MUTEXES
    WS4D_MUTEX (lock);
#endif
};

INLINE struct wsa_plugin_data *wsa_get_plugindata (struct soap *soap);

int wsa_plugin_init (struct soap *soap, struct soap_plugin *p, void *arg);

int
wsa_plugin_copy (struct soap *soap, struct soap_plugin *dst,
                 struct soap_plugin *src);

static void wsa_plugin_delete (struct soap *soap, struct soap_plugin *p);

INLINE struct wsa_plugin_data *
wsa_get_plugindata (struct soap *soap)
{
  return (struct wsa_plugin_data *) soap_lookup_plugin (soap, WSA_PLUGIN_ID);
}


int wsa_messageid_buffer_init (struct wsa_plugin_data *data);

int wsa_messageid_buffer_done (struct wsa_plugin_data *data);

int
wsa_messageid_buffer_init (struct wsa_plugin_data *data)
{
  if (!data)
    return SOAP_ERR;
  if (data->ids_buffer == NULL)
    {
      int i = 0;
      data->ids_buffer = ws4d_malloc (data->ids_buffer_size
                                       * sizeof (struct message_id_s));
      if (!data->ids_buffer)
        return SOAP_EOM;
      for (i = 0; i < data->ids_buffer_size; i++)
        {
          ws4d_list_add_tail (&data->ids_buffer[i].list, &data->free_ids);
        }
    }
  return SOAP_OK;
}


int
wsa_messageid_buffer_done (struct wsa_plugin_data *data)
{
  if (!data)
    return SOAP_ERR;
  if (data->ids_buffer != NULL)
    {
      int i = 0;
      for (i = 0; i < data->ids_buffer_size; i++)
        {
          if (data->ids_buffer[i].valid)
            {
              ws4d_free_alist (data->ids_buffer[i].msg_id);
            }
        }

      ws4d_free (data->ids_buffer);
      data->ids_buffer = NULL;
    }
  return SOAP_OK;
}


static void
wsa_plugin_delete (struct soap *soap, struct soap_plugin *p)
{
  wsa_messageid_buffer_done (p->data);
  ws4d_free (p->data);
}


int
wsa_plugin_copy (struct soap *soap, struct soap_plugin *dst,
                 struct soap_plugin *src)
{
  return wsa_plugin_init (soap, dst, NULL);
}


static int
soap_wsa_header (struct soap *soap)
{
  struct wsa_plugin_data *data = wsa_get_plugindata (soap);

  if (!data)
    return SOAP_PLUGIN_ERROR;

  if (data->fheader && data->fheader (soap))
    return soap->error;

  if (soap->header && soap->header->wsa__Action)
    soap->action = soap->header->wsa__Action;

  return SOAP_OK;
}


int
wsa_plugin_init (struct soap *soap, struct soap_plugin *p, void *arg)
{
  p->id = wsa_plugin_id;
  p->data = (void *) ws4d_malloc (sizeof (struct wsa_plugin_data));
  p->fcopy = wsa_plugin_copy;
  p->fdelete = wsa_plugin_delete;
  if (p->data)
    {
      struct wsa_plugin_data *data = p->data;

      WS4D_INIT_LIST (&data->message_ids);
      WS4D_INIT_LIST (&data->free_ids);

      data->ids_buffer = NULL;
      data->ids_buffer_size = WSA_MAX_MESSAGEIDS;

      data->fheader = soap->fheader;
      data->fseterror = soap->fseterror;
      soap->fheader = soap_wsa_header;

      WS4D_ALLOCLIST_INIT (&data->alist);
#ifdef WITH_MUTEXES
      ws4d_mutex_init (&data->lock);
#endif
    }
  return SOAP_OK;
}


int
wsa_register_handle (struct soap *soap)
{
  struct wsa_plugin_data *data = wsa_get_plugindata (soap);

  if (!data)
    {
      return soap_register_plugin (soap, wsa_plugin_init);
    }
  else
    {
      return SOAP_OK;
    }
}

/**
 * Check if a header has an known message id and so if the message is
 * obsolete
 *
 * @param soap handle to check
 *
 * @return return SOAP_OK if message is not obsolete or SOAP_ERR
 * otherwise
 *
 */
int
wsa_message_isdup (struct soap *soap)
{
  register int i = 0;
  register char *id = NULL;
  struct wsa_plugin_data *data = wsa_get_plugindata (soap);

  if (!data)
    return SOAP_ERR;

  ws4d_mutex_lock (&data->lock);
  if (!soap || !soap->header || !soap->header->wsa__MessageID)
    {
      ws4d_mutex_unlock (&data->lock);
      return SOAP_ERR;
    }

  wsa_messageid_buffer_init (data);
  id = ws4d_strdup (soap->header->wsa__MessageID, &data->alist);
  if (!id)
    {
      ws4d_mutex_unlock (&data->lock);
      return SOAP_ERR;
    }

  ws4d_mutex_unlock (&data->lock);
  for (i = 0; i < data->ids_buffer_size; i++)
    {
      if (data->ids_buffer[i].valid
          && data->ids_buffer[i].msg_id
          && !strcmp (data->ids_buffer[i].msg_id, id))
        {
          ws4d_free_alist (id);
          id = NULL;
          return SOAP_ERR;
        }
    }

  ws4d_mutex_lock (&data->lock);
  if (ws4d_list_empty (&data->free_ids))
    {

      struct message_id_s *entry = ws4d_list_entry (data->message_ids.prev,
                                                    struct message_id_s,
                                                    list);
      if (!entry)
        {
          ws4d_mutex_unlock (&data->lock);
          return SOAP_ERR;
        }

      entry->valid = 0;
      ws4d_free_alist (entry->msg_id);
      entry->msg_id = NULL;
      entry->msg_id = id;
      entry->valid = 1;
      ws4d_list_move (&entry->list, &data->message_ids);
    }
  else
    {

      struct message_id_s *entry = ws4d_list_entry (data->free_ids.next,
                                                    struct message_id_s,
                                                    list);
      if (!entry)
        {
          ws4d_mutex_unlock (&data->lock);
          return SOAP_ERR;
        }

      entry->msg_id = id;
      entry->valid = 1;
      ws4d_list_move (&entry->list, &data->message_ids);
    }

  ws4d_mutex_unlock (&data->lock);

  return SOAP_OK;
}


/**
 * Sets the wsa:To field in the ws addressing header
 *
 * @param soap handle to set header field
 * @param To value the field is set to
 *
 * @return SOAP_OK on success, SOAP_ERR on failure
 */
INLINE int
wsa_header_set_To (struct soap *soap, const char *To)
{
  /* check parameters */
  soap_assert (soap, soap && soap->header && To, SOAP_ERR);

  /* Changed this to ensure the To is set when the call is done. (SF) */
  /* old: soap->header->wsa__To = (char *) To; */
  soap->header->wsa__To = soap_strdup (soap, To);

  return SOAP_OK;
}

/**
 *
 */
INLINE char *
wsa_header_get_To (struct soap *soap)
{
  /* check parameters */
  soap_assert (soap, soap && soap->header, NULL);

  return soap->header->wsa__To;
}

/**
 * Generates message id and sets wsa:MsgId field in the ws addressing
 * header. The caller must pass a buffer for the message id.
 *
 * @param soap handle to set header field
 * @param Id buffer the id is stored an can be returned
 *
 * @return SOAP_OK on success, SOAP_ERR on failure
 */
INLINE int
wsa_header_set_MessageId (struct soap *soap, const char *Id)
{
  /* check parameters */
  soap_assert (soap, soap && soap->header && Id, SOAP_ERR);

  /* Changed this to ensure the Action is set when the call is done. (SF) */
  /* old:  soap->header->wsa__MessageID = (char *) Id; */
  soap->header->wsa__MessageID = soap_strdup (soap, Id);

  return SOAP_OK;
}


/**
 * Check if message id is set in the ws addressing
 * header.
 *
 * @param soap handle to check header field
 *
 * @return SOAP_OK on success, SOAP_ERR on failure
 */
INLINE int
wsa_header_check_MessageId (struct soap *soap)
{
  /* check parameters */
  soap_assert (soap, soap && soap->header, SOAP_ERR);

  if (!soap->header->wsa__MessageID)
    {
      return SOAP_ERR;
    }

  return SOAP_OK;
}


/**
 *
 */
INLINE char *
wsa_header_get_MessageId (struct soap *soap)
{
  /* check parameters */
  soap_assert (soap, soap && soap->header, NULL);

  return soap->header->wsa__MessageID;
}


/**
 *
 */
INLINE int
wsa_header_set_Action (struct soap *soap, const char *Action)
{
  /* check parameters */
  soap_assert (soap, soap && soap->header && Action, SOAP_ERR);

  /* Changed this to ensure the Action is set when the call is done. (SF) */
  /* old:  soap->header->wsa__Action = (char *) Action; */
  soap->header->wsa__Action = soap_strdup (soap, Action);

  return SOAP_OK;
}


/**
 *
 */
INLINE char *
wsa_header_get_Action (struct soap *soap)
{
  /* check parameters */
  soap_assert (soap, soap && soap->header, NULL);

  return soap->header->wsa__Action;
}


/**
 * Sets the wsa:RelatesTo field in the ws addressing header
 *
 * @param soap handle to set header field
 * @param MessageId relating message id the field is set to
 *
 * @return SOAP_OK on success, SOAP_ERR on failure
 */
INLINE int
wsa_header_set_RelatesTo (struct soap *soap, const char *MessageId)
{
  /* check parameters */
  soap_assert (soap, soap && soap->header && MessageId, SOAP_ERR);

  soap->header->wsa__RelatesTo =
    soap_malloc (soap, sizeof (struct wsa__Relationship));
  if (!soap->header->wsa__RelatesTo)
    {
      return soap->error;
    }

  soap_default_wsa__Relationship (soap, soap->header->wsa__RelatesTo);
  /* Changed this to ensure the MessageId is set when the call is done. (SF) */
  /* old:  soap->header->wsa__RelatesTo->__item = (char *) MessageId; */
  soap->header->wsa__RelatesTo->__item = soap_strdup (soap, MessageId);

  return SOAP_OK;
}


/**
 *
 */
INLINE char *
wsa_header_get_RelatesTo (struct soap *soap)
{
  /* check parameters */
  soap_assert (soap, soap && soap->header, NULL);

  if (soap->header->wsa__RelatesTo)
    {
      return soap->header->wsa__RelatesTo->__item;
    }
  else
    {
      return NULL;
    }
}


/**
 * Sets the wsa:ReplyTo field in the ws addressing header
 *
 * @param soap handle to set header field
 * @param Address address the field is set to
 *
 * @return SOAP_OK on success, SOAP_ERR on failure
 */
INLINE int
wsa_header_set_ReplyTo (struct soap *soap, const char *Address)
{
  /* check parameters */
  soap_assert (soap, soap && soap->header && Address, SOAP_ERR);

  if (wsa_header_check_MessageId (soap))
    return SOAP_ERR;

  soap->header->wsa__ReplyTo =
    soap_malloc (soap, sizeof (struct wsa__EndpointReferenceType));
  if (!soap->header->wsa__ReplyTo)
    {
      return soap->error;
    }

  soap_default_wsa__EndpointReferenceType (soap, soap->header->wsa__ReplyTo);
  soap->header->wsa__ReplyTo->Address = soap_strdup (soap, (char *) Address);

  return SOAP_OK;
}


/**
 *
 */
INLINE char *
wsa_header_get_ReplyTo (struct soap *soap)
{
  /* check parameters */
  soap_assert (soap, soap && soap->header, NULL);

  if (soap->header->wsa__ReplyTo)
    {
      return soap->header->wsa__ReplyTo->Address;
    }
  else
    {
      return NULL;
    }
}


/**
 *
 */
INLINE int
wsa_header_set_From (struct soap *soap, const char *Address)
{
  /* check parameters */
  soap_assert (soap, soap && soap->header && Address, SOAP_ERR);

  soap->header->wsa__From =
    soap_malloc (soap, sizeof (struct wsa__EndpointReferenceType));
  if (!soap->header->wsa__From)
    {
      return soap->error;
    }

  soap_default_wsa__EndpointReferenceType (soap, soap->header->wsa__From);
  /* Changed this to ensure the Address is set when the call is done. (SF) */
  /* old:  soap->header->wsa__From->Address = (char *) Address; */
  soap->header->wsa__From->Address = soap_strdup (soap, Address);

  return SOAP_OK;
}


/**
 *
 */
INLINE char *
wsa_header_get_From (struct soap *soap)
{
  /* check parameters */
  soap_assert (soap, soap && soap->header, NULL);

  if (soap->header->wsa__From)
    {
      return soap->header->wsa__From->Address;
    }
  else
    {
      return NULL;
    }
}


/**
 *
 */
INLINE int
wsa_header_set_FaultTo (struct soap *soap, const char *Address)
{
  /* check parameters */
  soap_assert (soap, soap && soap->header && Address, SOAP_ERR);

  if (wsa_header_check_MessageId (soap))
    return SOAP_ERR;

  soap->header->wsa__FaultTo =
    soap_malloc (soap, sizeof (struct wsa__EndpointReferenceType));
  if (!soap->header->wsa__FaultTo)
    {
      return soap->error;
    }

  soap_default_wsa__EndpointReferenceType (soap, soap->header->wsa__FaultTo);
  /* Changed this to ensure the Address is set when the call is done. (SF) */
  /* old:  soap->header->wsa__FaultTo->Address = (char *) Address; */
  soap->header->wsa__FaultTo->Address = soap_strdup (soap, Address);

  return SOAP_OK;
}


/**
 *
 */
INLINE char *
wsa_header_get_FaultTo (struct soap *soap)
{
  /* check parameters */
  soap_assert (soap, soap && soap->header, NULL);

  if (soap->header->wsa__FaultTo)
    {
      return soap->header->wsa__FaultTo->Address;
    }
  else
    {
      return NULL;
    }
}


int
wsa_header_check_request (struct soap *soap)
{
  /* check parameters */
  soap_assert (soap, soap && soap->header, SOAP_ERR);

  /* TODO: to be implemented */

  return SOAP_OK;
}


int
wsa_header_check_response (struct soap *soap)
{
  /* check parameters */
  soap_assert (soap, soap && soap->header, SOAP_ERR);

  /* TODO: to be implemented */

  return SOAP_OK;
}

/**
 *
 */
INLINE int
wsa_header_gen_oneway (struct soap *soap, const char *MessageId,
                       const char *To, const char *Action,
                       const char *FaultTo, size_t size)
{
  int err;

  /* check mandatory parameters */
  soap_assert (soap, soap && To && Action, SOAP_ERR);

  if (soap_header_new (soap, size)
      || wsa_header_set_To (soap, To) || wsa_header_set_Action (soap, Action))
    {
      return SOAP_ERR;
    }

  /* set message id if specified */
  if (MessageId)
    {
      err = wsa_header_set_MessageId (soap, MessageId);
      if (err)
        {
          return err;
        }
    }

  /* set fault to if specified */
  if (FaultTo)
    {
      err = wsa_header_set_FaultTo (soap, FaultTo);
      if (err)
        {
          return err;
        }
    }

  return SOAP_OK;
}


int
wsa_header_gen_request (struct soap *soap, const char *MessageId,
                        const char *To, const char *Action,
                        const char *FaultTo, const char *ReplyTo, size_t size)
{
  int err = 0;

  /* check mandatory parameters */
  soap_assert (soap, soap && MessageId && To && Action, SOAP_ERR);

  err = wsa_header_gen_oneway (soap, MessageId, To, Action, FaultTo, size);
  if (err)
    return err;

  if (ReplyTo)
    {
      return wsa_header_set_ReplyTo (soap, ReplyTo);
    }
  else
    {
      return wsa_header_set_ReplyTo (soap, wsa_anonymousURI);
    }
}

/**
 *
 */
int
wsa_header_gen_response (struct soap *soap, const char *MessageId,
                         const char *To, const char *Action,
                         const char *RelatesTo, size_t size)
{
  int err = 0;

  /* check mandatory parameters */
  soap_assert (soap, soap && To && Action && RelatesTo, SOAP_ERR);

  if (soap_header_new (soap, size)
      || wsa_header_set_To (soap, To)
      || wsa_header_set_Action (soap, Action)
      || wsa_header_set_RelatesTo (soap, RelatesTo))
    {
      return SOAP_ERR;
    }

  if (MessageId)
    {
      err = wsa_header_set_MessageId (soap, MessageId);
      if (err)
        {
          return err;
        }
    }

  return SOAP_OK;
}


int
wsa_header_gen_fault (struct soap *soap, const char *MessageId,
                      const char *To, const char *Action,
                      const char *RelatesTo, size_t size)
{
  int err = 0;

  /* check mandatory parameters */
  soap_assert (soap, soap && To && Action, SOAP_ERR);

  if (soap_header_new (soap, size)
      || wsa_header_set_To (soap, To)
      || wsa_header_set_Action (soap, Action)
      || wsa_header_set_RelatesTo (soap, RelatesTo))
    {
      return SOAP_ERR;
    }

  if (MessageId)
    {
      err = wsa_header_set_MessageId (soap, MessageId);
      if (err)
        {
          return err;
        }
    }

  return SOAP_OK;
}


INLINE void
wsa_prep_fault_subcode (struct soap *soap, const char *faultsubcode,
                        const char *faultstring, const char *faultdetail)
{
  struct SOAP_ENV__Header *oldheader = soap->header;

  soap_header_new (soap, sizeof (struct SOAP_ENV__Header));
  if (oldheader && oldheader->wsa__MessageID)
    {
      wsa_header_set_RelatesTo (soap, oldheader->wsa__MessageID);
    }

  wsa_header_set_Action (soap, (char *) wsa_faultAction);
  if (soap->header->wsa__From)
    wsa_header_set_To (soap, oldheader->wsa__From->Address);
  else
    wsa_header_set_To (soap, (char *) wsa_anonymousURI);
}


int
wsa_sender_fault_subcode (struct soap *soap, const char *faultsubcode,
                          const char *faultstring, const char *faultdetail)
{
  wsa_prep_fault_subcode (soap, faultsubcode, faultstring, faultdetail);
  return soap_sender_fault_subcode (soap, faultsubcode, faultstring,
                                    faultdetail);
}


int
wsa_receiver_fault_subcode (struct soap *soap,
                            const char *faultsubcode,
                            const char *faultstring, const char *faultdetail)
{
  wsa_prep_fault_subcode (soap, faultsubcode, faultstring, faultdetail);
  return soap_receiver_fault_subcode (soap, faultsubcode, faultstring,
                                      faultdetail);
}


int
wsa_error (struct soap *soap, wsa__FaultSubcodeValues fault)
{
  const char *code = soap_wsa__FaultSubcodeValues2s (soap, fault);
  switch (fault)
    {
    case wsa__FaultSubcodeValues__wsa__InvalidMessageInformationHeader:
      return wsa_sender_fault_subcode (soap, code, "", "Invalid header");
    case wsa__FaultSubcodeValues__wsa__MessageInformationHeaderRequired:
      return
        wsa_sender_fault_subcode (soap, code, "", "Missing Header QName");
    case wsa__FaultSubcodeValues__wsa__DestinationUnreachable:
      return wsa_sender_fault_subcode (soap, code, "", NULL);
    case wsa__FaultSubcodeValues__wsa__ActionNotSupported:
      return wsa_sender_fault_subcode (soap, code, "", NULL);
    case wsa__FaultSubcodeValues__wsa__EndpointUnavailable:
      return wsa_receiver_fault_subcode (soap, code, "", NULL);
    }

  return SOAP_FAULT;
}

/** @} */
