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

#include <stdarg.h>

#include "dpwsH.h"
#include "soap_misc.h"

/**
 * @addtogroup Internals Internals
 *
 * @{
 */

#ifdef WITH_MUTEXES
void
ws4d_mutex_init (WS4D_MUTEX (*mutex))
{
#ifdef WIN32
  if (mutex)
    *mutex = CreateMutex (NULL, FALSE, NULL);
#else
  pthread_mutex_init (mutex, NULL);
#endif
}

void
ws4d_mutex_destroy (WS4D_MUTEX (*mutex))
{
#ifdef WIN32
  if (mutex)
    CloseHandle (*mutex);
#else
  pthread_mutex_destroy (mutex);
#endif
}

void
ws4d_mutex_lock (WS4D_MUTEX (*mutex))
{
#ifdef WIN32
  if (mutex)
    WaitForSingleObject (*mutex, INFINITE);
#else
  pthread_mutex_lock (mutex);
#endif
}

void
ws4d_mutex_unlock (WS4D_MUTEX (*mutex))
{
#ifdef WIN32
  if (mutex)
    ReleaseMutex (*mutex);
#else
  pthread_mutex_unlock (mutex);
#endif
}
#endif

#include "ws4d_localizedstring.h"

struct wsdp__LocalizedStringType
  *ws4d_locstring_tosoap(struct ws4d_locstring *string,
    int size, ws4d_alloc_list * alist)
{
  struct wsdp__LocalizedStringType *res;
  int i, err = WS4D_OK;

  /* test parameters */
  ws4d_assert (string && (size > 0) && alist, NULL);

  res = ws4d_malloc_alist (sizeof (struct wsdp__LocalizedStringType) * size, alist);
  for (i = 0; i < size; i++)
    {
      if (string[i].lang && string[i].string)
        {
          res[i].xml__lang = ws4d_strdup (string[i].lang, alist);
          res[i].__item = ws4d_strdup (string[i].string, alist);
        }
      else
        {
          err = WS4D_ERR;
        }
    }

  if (err != WS4D_OK)
    {
      for (; i > 0; i--)
        {
          if (string[i].lang && string[i].string)
            {
              ws4d_free_alist (res[i].__item);
              ws4d_free_alist (res[i].xml__lang);
            }
        }
    }

  return res;
}

struct ws4d_locstring
  *soap_locstring_tows4d(struct wsdp__LocalizedStringType *string,
    int size, ws4d_alloc_list * alist)
{
  struct ws4d_locstring *res;
  int i, err = WS4D_OK;

  /* test parameters */
  ws4d_assert (string && (size > 0) && alist, NULL);

  res = ws4d_malloc_alist (sizeof (struct ws4d_locstring) * size, alist);
  for (i = 0; i < size; i++)
    {
      if (string[i].xml__lang && string[i].__item)
        {
          res[i].lang = ws4d_strdup (string[i].xml__lang, alist);
          res[i].string = ws4d_strdup (string[i].__item, alist);
        }
      else
        {
          err = WS4D_ERR;
        }
    }

  if (err != WS4D_OK)
    {
      for (; i > 0; i--)
        {
          if (string[i].xml__lang && string[i].__item)
            {
              ws4d_free_alist (res[i].lang);
              ws4d_free_alist (res[i].string);
            }
        }
    }

  return res;
}

char *
soap_locstring_get (struct wsdp__LocalizedStringType *string,
                          int size, const char *lang)
{
  int i = 0;
  char *result = NULL;

  /* test parameters */
  ws4d_assert (string && (size > 0) && lang, NULL);

  for (i = 0; i < size; i++)
    {
      if (string[i].xml__lang && !STRCASECMP (string[i].xml__lang, lang))
        {
          result = string[i].__item;
          break;
        }
    }

  return result;
}

void soap_locstring_free (struct wsdp__LocalizedStringType *string, int size)
{
  int i;

  ws4d_assert (string && (size > 0),);

  for (i = 0; i < size; i++)
    {
      if (string[i].xml__lang)
        {
          ws4d_free_alist (string[i].xml__lang);
        }

      if (string[i].__item)
        {
          ws4d_free_alist (string[i].__item);
        }
    }
  ws4d_free_alist (string);
}

static int
soap_ser_send (struct soap *soap, const char *buf, size_t len)
{
  if (soap->user)
    {
      strncat (soap->user, buf, len);
    }
  return SOAP_OK;
}

char *
soap_elem_to_str (void *ptr, char *tag, int type)
{
  struct soap tmp;
  soap_init (&tmp);
  soap_omode (&tmp, SOAP_XML_CANONICAL);
  tmp.fsend = soap_ser_send;
  soap_begin_count (&tmp);
  soap_putelement (&tmp, ptr, tag, -1, type);
  soap_end_count (&tmp);
  tmp.user = malloc (tmp.count + 1);
  memset (tmp.user, 0, tmp.count + 1);
  soap_begin_send (&tmp);
  soap_putelement (&tmp, ptr, tag, -1, type);
  soap_end_send (&tmp);
  return tmp.user;
}

struct soap_recv_buf
{
  int pos;
  size_t size;
  char *buf;
};

static size_t
soap_deser_recv (struct soap *soap, char *buf, size_t len)
{
  if (soap->user)
    {
      struct soap_recv_buf *recv_buf = soap->user;
      int ret = 0;
      if ((unsigned int) recv_buf->pos >= recv_buf->size)
        return 0;
      if (len >= (recv_buf->size - recv_buf->pos))
        {
          ret = recv_buf->size - recv_buf->pos;
        }
      else
        {
          ret = len;
        }

      memcpy (buf, recv_buf->buf + recv_buf->pos, ret);
      recv_buf->pos += ret;
      return ret;
    }
  return 0;
}

int
soap_begin_deser (struct soap *soap, char *str, size_t str_len,
                  struct Namespace *ns)
{
  struct soap_recv_buf *buf;
  int i;
  soap->frecv = soap_deser_recv;
  soap_begin_recv (soap);
  for (i = 0; ns[i].id; i++)
    {
      soap_push_namespace (soap, ns[i].id, ns[i].ns);
    }
  soap->level++;

  buf = (struct soap_recv_buf *) soap_malloc (soap,
                                              sizeof (struct soap_recv_buf));
  buf->pos = 0;
  buf->size = str_len + 1;
  buf->buf = str;
  soap->user = buf;
  return SOAP_OK;
}

int
soap_end_deser (struct soap *soap)
{
  soap->level--;
  soap_pop_namespace (soap);
  soap_end_recv (soap);
  return SOAP_OK;
}

void *
ws4d_malloc (size_t length)
{
  void *p = NULL;
  p = WS4D_MALLOC (length);
  return memset (p, 0, length);
}

void
ws4d_free (void *p)
{
  if (p)
    {
      WS4D_FREE (p);
    }
}

/**
 * an element of an allocation list
 *
 */

typedef struct soap_alloc_list_item_s
{
  struct ws4d_list_node list;
  ws4d_alloc_list *alist;
  size_t size;
  void *start;
} soap_alloc_list_item;

void *
ws4d_malloc_alist (size_t length, ws4d_alloc_list * alist)
{
  register soap_alloc_list_item *p = NULL;

  if (alist)
    {
      ws4d_mutex_lock (&alist->lock);
      p = (soap_alloc_list_item *) ws4d_malloc (length
                                                 +
                                                 sizeof
                                                 (soap_alloc_list_item));
      if (p)
        {
          p->size = length + sizeof (soap_alloc_list_item);
          p->alist = alist;
          ws4d_list_add_tail (&p->list, &alist->alist);
          ws4d_mutex_unlock (&alist->lock);
          return &p->start;
        }
      ws4d_mutex_unlock (&alist->lock);
    }
  return NULL;
}

void
ws4d_alloclist_done (ws4d_alloc_list * alist)
{
  register soap_alloc_list_item *item, *next;
  if (alist)
    {
      ws4d_mutex_lock (&alist->lock);
      ws4d_list_foreach (item, next, &alist->alist,
                         soap_alloc_list_item, list)
      {
        ws4d_list_del (&item->list);
        memset (item, 0, item->size);
        ws4d_free (item);
      }
      ws4d_mutex_unlock (&alist->lock);
    }
}


void
ws4d_free_alist (void *p)
{
  soap_alloc_list_item *entry;

  if (p)
    {
      entry = ws4d_container_of (p, soap_alloc_list_item, start);
      if (entry && entry->alist)
        {
#ifdef WITH_MUTEXES
          ws4d_alloc_list *alist = entry->alist;
#endif
          ws4d_mutex_lock (&alist->lock);
          ws4d_list_del (&entry->list);
          memset (entry, 0, entry->size);
          ws4d_free (entry);
          ws4d_mutex_unlock (&alist->lock);
        }
    }
}

void *
ws4d_memdup (const void *src, size_t length, ws4d_alloc_list * alist)
{
  void *res = NULL;

  if (!(res = ws4d_malloc_alist (length, alist)))
    return res;

  return memcpy (res, src, length);
}

char *
ws4d_strdup (const char *src, ws4d_alloc_list * alist)
{
  char *res = NULL;
  size_t len;

  if (!src)
    return res;

  len = strlen (src);
  res = (char *) ws4d_memdup (src, len + 1, alist);
  res[len] = '\0';

  return res;
}

char *
ws4d_strndup (const char *src, size_t size, ws4d_alloc_list * alist)
{
  char *res = NULL;
  size_t len;

  if (!src)
    return res;

  len = strlen (src);
  if (len > size)
    {
      len = size;
    }
  res = (char *) ws4d_memdup (src, len + 1, alist);
  res[len] = '\0';

  return res;
}

int
soap_header_new (struct soap *soap, size_t size)
{
  if (!soap || (size < 1))
    return SOAP_ERR;

  soap->header = (struct SOAP_ENV__Header *) soap_malloc (soap, size);

  if (soap->header)
    {
      memset (soap->header, 0, size);
      return SOAP_OK;
    }
  else
    {
      return soap->error;
    }
}

/**
 * @addtogroup DpwsXsdList xsd:List and xsd:Array helper functions
 * @ingroup Internals
 *
 * @{
 */

/**
 * Function allocates memory for a xsd:Array of a defined size
 *
 * @param count number of elements in Array
 * @param alist allocation list to insert allocation
 * @returns pointer to newly-allocated xsd:Array or NULL if no memory
 */

char **
soap_alloc_xsdArray (int count, ws4d_alloc_list * alist)
{
  char **result = { NULL };
  int i = 0;

  if (!alist || (count < 1))
    return result;

  result = ws4d_malloc_alist (sizeof (char *) * (count + 1), alist);

  if (result)
    {
      for (i = 0; i < count; i++)
        result[i] = "";
      result[count] = NULL;
    }

  return result;
}

void
ws4d_free_xsdArray (char **Array)
{
  int count = 0;
  if (Array)
    {
      while (Array[count])
        {
          ws4d_free_alist (Array[count]);
          count++;
        }
      ws4d_free_alist (Array);
    }
}

char **
ws4d_xsdList_to_Array (const char *List, ws4d_alloc_list * alist)
{
  char **noresult = { NULL }, **result = NULL;
  const char *pos1 = NULL, *pos2 = NULL;
  int count = 1, i;

  if (!List || !alist)
    return result;

  while (List[0] == ' ')
    List++;

  pos1 = List;
  while (pos1)
    {
      pos1 = memchr (pos1, ' ', strlen (pos1));
      if (pos1)
        {
          pos1++;
          count++;
        }
    }

  pos1 = List;
  result = soap_alloc_xsdArray (count, alist);

  if (result)
    {
      for (i = 0; i < count; i++)
        {
          int n;

          pos2 = memchr (pos1, ' ', strlen (pos1));
          if (!pos2)
            {
              pos2 = pos1 + strlen (pos1);
            }
          result[i] = ws4d_malloc_alist (pos2 - pos1 + 1, alist);
          if (result[i])
            {
              strncpy (result[i], pos1, pos2 - pos1);
              result[i][pos2 - pos1] = '\0';
            }
          else
            {
              return noresult;
            }
          pos1 = pos2;
          for (n = 0; (n < (signed int) strlen (pos1)) && (pos2[0] == ' ');
               n++, pos2++)
            ;
          pos1 = pos2;
        }
      return result;
    }
  else
    {
      return noresult;
    }
}

/** @} */

int
ws4d_xsdArray_match (char **Types, char **TypesToMatch,
                     int (*soap_match_func) (const char *s1, const char *s2))
{
  int matches = 1;

  if (!Types || !TypesToMatch || !soap_match_func)
    return 0;

  while (*TypesToMatch && matches)
    {
      char **LoopTypes;

      matches = 0;
      LoopTypes = Types;

      while (*LoopTypes)
        {
          if (!soap_match_func (*LoopTypes, *TypesToMatch))
            {
              matches = 1;
              break;
            }
          LoopTypes++;
        }
      TypesToMatch++;
    }

  return matches;
}

ws4d_time
ws4d_systime_ms ()
{
#ifndef WIN32
  struct timeval tv;
  gettimeofday (&tv, (struct timezone *) 0);

  return (ws4d_time) (tv.tv_sec * 1000) + (tv.tv_usec / 1000);
#else
  return (ws4d_time) GetTickCount ();
#endif
}

ws4d_time
ws4d_systime_s ()
{
#ifndef WIN32
  struct timeval tv;
  gettimeofday (&tv, (struct timezone *) 0);

  return (ws4d_time) tv.tv_sec;
#else
  return (ws4d_time) GetTickCount () / 1000;
#endif
}

struct Namespace *
soap_extend_namespaces (struct Namespace *namespaces,
                        struct Namespace *extns, ws4d_alloc_list * alist)
{
  register struct Namespace *entry = NULL;
  struct Namespace *result = NULL, *tmp_result = NULL;
  int old_size = 0, ext_size = 0, new_size = 0, count = 0;

  if (!extns || !namespaces || !alist)
    return result;

  for (entry = namespaces; entry && entry->id; entry++)
    {
      old_size++;
    }

  for (entry = extns; entry && entry->id; entry++)
    {
      ext_size++;
    }

  tmp_result = malloc (sizeof (struct Namespace) * (ext_size));
  memset (tmp_result, 0, sizeof (struct Namespace) * (ext_size));

  new_size = old_size;

  for (entry = extns; entry && entry->id && entry->ns; entry++)
    {
      int duplicate = 0;
      register struct Namespace *old_entry = NULL;

      for (old_entry = namespaces; old_entry && old_entry->id
           && old_entry->ns; old_entry++)
        {
          if (!strcmp (old_entry->ns, entry->ns) && !strcmp (old_entry->id,
                                                             entry->id))
            {
              duplicate = 1;
              break;
            }
        }

      if (!duplicate)
        {
          tmp_result[new_size - old_size].id = entry->id;
          tmp_result[new_size - old_size].ns = entry->ns;
          new_size++;
        }
    }

  if (new_size > old_size)
    {
      result = ws4d_malloc_alist (sizeof (struct Namespace) * (new_size + 1),
                                 alist);
    }
  else
    {
      if (tmp_result)
        free (tmp_result);

      return namespaces;
    }

  memset (result, 0, sizeof (struct Namespace) * (new_size + 1));
  memcpy (result, namespaces, sizeof (struct Namespace) * (old_size + 1));

  entry = result;
  entry += old_size;

  for (count = 0; count < (new_size - old_size); count++, entry++)
    {
      entry->id = tmp_result[count].id;
      entry->ns = tmp_result[count].ns;
    }

  if (tmp_result)
    free (tmp_result);

  return result;
}

struct soap *
soap_maccept (ws4d_time timeout, int count, struct soap **soap_handles)
{
  struct timeval timeout_t;
  register int i, r = -1;
  SOAP_SOCKET bigfd = SOAP_INVALID_SOCKET;
  fd_set fd;
  struct soap *result = NULL;

  FD_ZERO (&fd);

  if (!soap_handles)
    return result;

  for (i = 0; i < count; i++)
    {
      if (soap_valid_socket (soap_handles[i]->master))
        {
          FD_SET ((SOAP_SOCKET) soap_handles[i]->master, &fd);
          if ((SOAP_SOCKET) soap_handles[i]->master > bigfd)
            bigfd = soap_handles[i]->master;
        }
      else
        {
          printf ("register soap handle number %d first\n", i);
          goto exit;
        }
    }

  if (timeout != DPWS_SYNC)
    {
      timeout_t.tv_sec = timeout / 1000;
      timeout_t.tv_usec = (timeout % 1000) * 1000;
    }

  while (r < 0)
    {
      r = select (bigfd + 1, &fd, NULL, NULL, &timeout_t);
      if (r > 0)
        {
          for (i = 0; i < count; i++)
            {
              if (soap_valid_socket (soap_handles[i]->master))
                {
                  if (FD_ISSET ((SOAP_SOCKET) soap_handles[i]->master, &fd))
                    {
                      result = soap_handles[i];
                      goto exit;
                    }
                }
            }
        }
    }

exit:

  if (result)
    soap_accept (result);

  return result;
}

int
soap_mserve (struct soap *soap, int count,
             int (*serve_requests[])(struct soap * soap))
{
  unsigned int k = soap->max_keep_alive;
  int i = 0, err = SOAP_ERR;

  if (!soap || !serve_requests || count < 1)
    return SOAP_ERR;

  do
    {

      soap_begin (soap);

      if (!--k)
        soap->keep_alive = 0;

      if (soap_begin_recv (soap))
        {
          if (soap->error < SOAP_STOP)
            {
              return soap_send_fault (soap);
            }
          soap_closesock (soap);

          continue;
        }

      if (soap_envelope_begin_in (soap) || soap_recv_header (soap)
          || soap_body_begin_in (soap))
        {
          return soap_send_fault (soap);
        }

      if (!soap->action)
        {
          printf
            ("Warning: Incoming message on handle 0x%p has no soap action!\n",
             soap);
        }

      i = 0;
      err = SOAP_NO_METHOD;
      while ((i < count) && (err == SOAP_NO_METHOD))
        {
          err = serve_requests[i] (soap);
          i++;
        }

      if ((err != SOAP_OK) && (err != SOAP_STOP))
        return soap_send_fault (soap);

      if (soap->fserveloop && soap->fserveloop (soap))
        {
          return soap_send_fault (soap);
        }

    }
  while (soap->keep_alive);

  return SOAP_OK;
}

const char *ws4d_uuid_schema_prefix = "urn:uuid:";
const char *ws4d_uuid_schema_format = "urn:uuid:%s";

unsigned char
soap_generaterandchar ()
{
  char s[10];

  sprintf (s, "%x", rand () % 16);
  return s[0];
}

void
ws4d_uuid_generate_random (char *out)
{
  int i = 0;

  srand ((unsigned int) ws4d_systime_ms ());
  for (i = 0; i < 8; i++)
    out[i] = soap_generaterandchar ();
  out[i++] = '-';
  for (i = 9; i < 13; i++)
    out[i] = soap_generaterandchar ();
  out[i++] = '-';
  out[i++] = '4';
  out[i++] = soap_generaterandchar ();
  out[i++] = soap_generaterandchar ();
  out[i++] = soap_generaterandchar ();
  out[i++] = '-';
  out[i++] = '8';
  out[i++] = soap_generaterandchar ();
  out[i++] = soap_generaterandchar ();
  out[i++] = soap_generaterandchar ();
  out[i++] = '-';
  for (i = 24; i < 36; i++)
    out[i] = soap_generaterandchar ();
  out[i++] = 0;
  return;
}

struct ws4d_qname *
ws4d_qname_alloc (int count, ws4d_alloc_list * alist)
{
  struct ws4d_qname *result = NULL;

  if ((count < 1) || !alist)
    return result;

  result = ws4d_malloc_alist (count * sizeof (struct ws4d_qname), alist);

  if (result)
    {
      WS4D_INIT_LIST (&result->list);
    }

  return result;
}

struct ws4d_qname *
ws4d_qname_dup (struct ws4d_qname *src, ws4d_alloc_list * alist)
{
  struct ws4d_qname *result = NULL;

  if (!src || !alist)
    return result;

  result = ws4d_qname_alloc (1, alist);
  if (!result)
    return result;

  if (src->name)
    {
      result->name = ws4d_strdup (src->name, alist);
    }
  else
    {
      result->name = NULL;
    }

  if (src->ns)
    {
      result->ns = ws4d_strdup (src->ns, alist);
    }
  else
    {
      result->ns = NULL;
    }

  if (src->prefix)
    {
      result->prefix = ws4d_strdup (src->prefix, alist);
    }
  else
    {
      result->prefix = NULL;
    }

  return result;
}

int
ws4d_qnamelist_init (ws4d_qnamelist * head)
{
  if (!head)
    return SOAP_ERR;

  WS4D_INIT_LIST (head);

  return SOAP_OK;
}

int
ws4d_qnamelist_done (ws4d_qnamelist * head)
{
  if (!head)
    return SOAP_ERR;

  WS4D_INIT_LIST (head);

  return SOAP_OK;
}

int
ws4d_qnamelist_add (struct ws4d_qname *qname, ws4d_qnamelist * head)
{
  if (!qname || !head)
    return SOAP_ERR;

  ws4d_list_add (&qname->list, head);

  return SOAP_OK;
}

int
ws4d_qnamelist_addstring (const char *string, ws4d_qnamelist * head,
                          ws4d_alloc_list * alist)
{
  char **qname_list = NULL, **temp = NULL;

  if (!string || !head || !alist)
    return SOAP_ERR;

  qname_list = ws4d_xsdList_to_Array (string, alist);
  temp = qname_list;

  while (*temp)
    {
      if (*temp)
        {
          struct ws4d_qname *type;
          char *prefix = NULL, *id = NULL;

          if (*temp[0] == '\"')
            {
              id = strstr (*temp, "\":");
            }
          else
            {
              id = strchr (*temp, ':');
            }

          if (id)
            {
              type = ws4d_qname_alloc (1, alist);

              prefix = ws4d_malloc_alist (id - *temp + 1, alist);
              if (!prefix)
                return SOAP_EOM;

              if (*temp[0] == '\"')
                {
                  memcpy (prefix, *temp + 1, id - *temp - 1);
                  prefix[id - *temp] = '\0';
                }
              else
                {
                  memcpy (prefix, *temp, id - *temp);
                  prefix[id - *temp] = '\0';
                }

              if (*temp[0] == '\"')
                {
                  type->ns = prefix;
                  id += 2;
                }
              else
                {
                  type->prefix = prefix;
                  id += 1;
                }

              type->name = ws4d_strdup (id, alist);

              ws4d_qnamelist_add (type, head);

            }

        }
      temp++;
    }

  ws4d_free_xsdArray (qname_list);

  return SOAP_OK;
}

int
ws4d_qnamelist_copy (ws4d_qnamelist * src, ws4d_qnamelist * dst,
                     ws4d_alloc_list * alist)
{
  register struct ws4d_qname *qname, *next;

  soap_assert (NULL, src && dst && alist, SOAP_ERR);

  ws4d_list_foreach (qname, next, src, struct ws4d_qname, list)
  {
    if (qname && qname->ns && qname->name)
      {
        struct ws4d_qname *dup = NULL;
        int res = 0;

        dup = ws4d_qname_dup (qname, alist);
        if (!dup)
          return SOAP_ERR;

        res = ws4d_qnamelist_add (dup, dst);
        if (res != SOAP_OK)
          return res;
      }
  }

  return SOAP_OK;
}

int
ws4d_qnamelist_remove (struct ws4d_qname *qname)
{
  if (!qname)
    return SOAP_ERR;

  ws4d_list_del (&qname->list);

  return SOAP_OK;
}

int
ws4d_qnamelist_clear (ws4d_qnamelist * head)
{
  register struct ws4d_qname *qname, *next;

  if (!head)
    return SOAP_ERR;

  ws4d_list_foreach (qname, next, head, struct ws4d_qname, list)
  {
    ws4d_qnamelist_remove (qname);
    ws4d_free_alist (qname);
  }

  return SOAP_OK;
}

int
ws4d_qnamelist_empty (ws4d_qnamelist * head)
{
  return ws4d_list_empty (head);
}

char *
ws4d_qnamelist_tostring (ws4d_qnamelist * head, ws4d_alloc_list * alist)
{
  register struct ws4d_qname *qname, *next;
  char *result = NULL;
  int qname_count = 0, length = 0, i = 0;

  if (!head || !alist)
    return result;

  ws4d_list_foreach (qname, next, head, struct ws4d_qname, list)
  {
    if (qname && qname->ns && qname->name)
      {
        qname_count++;
        length++;
        length += strlen (qname->ns) + 2;
        length += strlen (qname->name);
      }
  }

  if (qname_count < 1)
    return NULL;

  result = ws4d_malloc_alist (length + 1, alist);
  result[0] = '\0';

  i = 0;
  ws4d_list_foreach (qname, next, head, struct ws4d_qname, list)
  {
    if (qname && qname->ns && qname->name)
      {
        strcat (result, "\"");
        strcat (result, qname->ns);
        strcat (result, "\"");
        strcat (result, ":");
        strcat (result, qname->name);
        if ((qname_count > 1) && (i < (qname_count - 1)))
          {
            strcat (result, " ");
          }
        i++;
      }
  }

  return result;
}

char **
ws4d_qnamelist_toarray (ws4d_qnamelist * head, ws4d_alloc_list * alist)
{
  char **noresult = { NULL }, **result = NULL;
  register struct ws4d_qname *qname, *next;
  int qname_count = 0, i = 0;

  if (!head || !alist)
    return noresult;

  ws4d_list_foreach (qname, next, head, struct ws4d_qname, list)
  {
    if (qname && qname->ns && qname->name)
      {
        qname_count++;
      }
  }

  if (qname_count < 1)
    return noresult;

  result = soap_alloc_xsdArray (qname_count + 1, alist);

  if (result)
    {
      i = 0;
      ws4d_list_foreach (qname, next, head, struct ws4d_qname, list)
      {
        if (qname && qname->ns && qname->name)
          {
            int length = 0;

            length += strlen (qname->ns);
            length += strlen (qname->name);
            length += 4;

            result[i] = ws4d_malloc_alist (length + 1, alist);

            strcat (result[i], "\"");
            strcat (result[i], qname->ns);
            strcat (result[i], "\":");
            strcat (result[i], qname->name);

            i++;
          }
      }
    }

  result[qname_count] = NULL;

  return result;
}

char *
soap_gen_prefix (int *num, ws4d_alloc_list * alist,
                 struct Namespace *namespaces)
{
  char prefix[255];
  int collision = 0;

  if (!namespaces)
    return NULL;

  do
    {
      struct Namespace *cur_ns = namespaces;
      collision = 0;

      *num += 1;
      SNPRINTF (prefix, 255, "n%d", *num);

      while (cur_ns->ns)
        {
          if (cur_ns->id)
            {
              if (!strcmp (cur_ns->id, prefix))
                {
                  collision = 1;
                }
            }
          cur_ns++;
        }

    }
  while (collision == 1);

  return ws4d_strdup (prefix, alist);
}

struct Namespace *
soap_qnamelist_namespaces (ws4d_qnamelist * head,
                           ws4d_alloc_list * alist,
                           struct Namespace *namespaces)
{
  register struct ws4d_qname *type, *next;
  register struct Namespace *result = NULL, *entry = NULL, *ns = NULL;
  int type_count = 0, prefix_count = 0;

  if (!head || !alist)
    return result;

  ws4d_list_foreach (type, next, head, struct ws4d_qname, list)
  {
    if (type && type->ns)
      {
        int found = 0;
        for (ns = namespaces; ns && ns->id; ns++)
          {
            if (ns && ns->ns && !strcmp (type->ns, ns->ns))
              {
                found = 1;
                break;
              }
          }
        if (found == 0)
          {
            type_count++;
          }
      }
  }

  result = ws4d_malloc_alist (sizeof (struct Namespace) * (type_count + 1),
                             alist);
  if (!result)
    return result;
  memset (result, 0, sizeof (struct Namespace) * (type_count + 1));

  entry = result;

  ws4d_list_foreach (type, next, head, struct ws4d_qname, list)
  {
    if (type && type->ns)
      {
        int found = 0;
        for (ns = namespaces; ns && ns->id; ns++)
          {
            if (ns && ns->ns && !strcmp (type->ns, ns->ns))
              {
                found = 1;
                break;
              }
          }
        if (found == 0)
          {
            if (type->prefix)
              {
                entry->id = type->prefix;
              }
            else
              {
                /* TODO: should fail somehow if no prefix can be generated */
                entry->id =
                  soap_gen_prefix (&prefix_count, alist, namespaces);
              }
            entry->ns = type->ns;
            entry++;
          }
      }
  }

  return result;
}

int
soap_out_transform_qnames (struct soap *soap, char **outstring,
                           char *const *a)
{
  char **array = NULL;
  char *pos = NULL;
  int count = 0, entry = 0;

  pos = *a;
  count = 1;
  while (pos)
    {
      pos = memchr (pos, ' ', strlen (pos));
      if (pos)
        {
          pos++;
          count++;
        }
    }

  array = soap_malloc (soap, sizeof (char *) * count);
  memset (array, 0, sizeof (char *) * count);

  pos = *a;
  entry = 0;
  while (pos)
    {
      char *newpos = NULL;

      newpos = memchr (pos, ' ', strlen (pos));

      if (*pos && (pos[0] == '\"'))
        {
          char *name = NULL;

          pos++;
          name = strstr (pos, "\":");

          if (name && ((newpos == 0) || (name < newpos)))
            {
              struct Namespace *namespace = soap->local_namespaces;
              int namespace_len = name - pos;

              while (namespace && namespace->ns && strncmp (namespace->ns,
                                                            pos,
                                                            namespace_len))
                {
                  namespace++;
                }

              name++;

              if (namespace && namespace->id)
                {
                  int qname_len = 0;

                  qname_len = strlen (namespace->id);
                  if (newpos)
                    {
                      qname_len += newpos - name;
                    }
                  else
                    {
                      qname_len += strlen (name);
                    }
                  qname_len += 1;

                  array[entry] =
                    soap_malloc (soap, sizeof (char) * qname_len);
                  memset (array[entry], 0, sizeof (char) * qname_len);
                  strcat (array[entry], namespace->id);
                  if (newpos)
                    {
                      strncat (array[entry], name, newpos - name);
                    }
                  else
                    {
                      strcat (array[entry], name);
                    }
                  entry++;
                }
              else
                {
                  soap->error = SOAP_NAMESPACE;
                  return soap->error;
                }
            }
          else
            {
              soap->error = SOAP_NAMESPACE;
              return soap->error;
            }
        }

      if (newpos && (newpos[0] == ' '))
        {
          newpos++;
        }
      pos = newpos;
    }

  if (entry > 0)
    {
      int i = 0;
      int outstring_len = 0;

      for (i = 0; i < entry; i++)
        {
          outstring_len += strlen (array[i]) + 1;
        }

      if (outstring_len > 0)
        {
          *outstring = soap_malloc (soap, sizeof (char) * outstring_len);
          memset (*outstring, 0, sizeof (char) * outstring_len);

          strcpy (*outstring, array[0]);
          for (i = 1; i < entry; i++)
            {
              strcat (*outstring, " ");
              strcat (*outstring, array[i]);
            }
        }
    }

  return SOAP_OK;
}

int
soap_in_expand_qnames (struct soap *soap, char **instring, char **a)
{
  char **array = NULL;
  char *pos = NULL;
  int count = 0, entry = 0, i, result_size;

  pos = *instring;
  count = 1;
  while (pos)
    {
      pos = memchr (pos, ' ', strlen (pos));
      if (pos)
        {
          pos++;
          count++;
        }
    }

  array = soap_malloc (soap, sizeof (char *) * count);
  memset (array, 0, sizeof (char *) * count);

  pos = *instring;
  entry = 0;
  while (pos)
    {
      char *newpos = NULL, *name = NULL;
      struct soap_nlist *namespace = soap->nlist;

      /* replace white spaces with string terminations */
      newpos = memchr (pos, ' ', strlen (pos));
      if (newpos)
        {
          newpos[0] = '\0';
          newpos++;
        }

      if (*pos)
        {

          name = strchr (pos, ':');

          if (name)
            {
              register int prefixlen = name - pos;
              while (namespace && (strncmp (namespace->id, pos, prefixlen)
                                   || namespace->id[prefixlen]))
                {
                  namespace = namespace->next;
                }
              name++;
            }
          else
            {
              while (namespace && *namespace->id)
                namespace = namespace->next;
              name = pos;
            }

          if (namespace && namespace->index >= 0 && soap->local_namespaces
              && soap->local_namespaces[namespace->index].ns)
            {
              array[entry] = (char *) soap_malloc (soap, strlen (name)
                                                   + strlen (soap->
                                                             local_namespaces
                                                             [namespace->
                                                              index].ns) + 4);
              sprintf (array[entry], "\"%s\":%s",
                       soap->local_namespaces[namespace->index].ns, name);
              entry++;
            }
          else
            {

              if (namespace && namespace->ns)
                {
                  array[entry] = (char *) soap_malloc (soap, strlen (name)
                                                       +
                                                       strlen (namespace->
                                                               ns) + 4);
                  sprintf (array[entry], "\"%s\":%s", namespace->ns, name);
                  entry++;
                }
              else
                {
                  soap->error = SOAP_NAMESPACE;
                  return soap->error;
                }
            }
        }

      pos = newpos;
    }

  result_size = 0;
  for (i = 0; i < entry; i++)
    {
      if (i > 0)
        {
          result_size += strlen (" ");
        }
      result_size += strlen (array[i]);
    }
  result_size++;

  *a = (char *) soap_malloc (soap, sizeof (char) * result_size);
  memset (*a, 0, sizeof (char) * result_size);

  for (i = 0; i < entry; i++)
    {
      if (i > 0)
        {
          strcat (*a, " ");
        }
      strcat (*a, array[i]);
    }

  return SOAP_OK;
}

/** @} */
