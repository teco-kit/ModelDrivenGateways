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
 *
 *  Created on: 18.08.2008
 *      Author: Elmar Zeeb
 */

#include "ws4d_misc.h"
#include "ws4d_abstract_eprlist.h"
#include "ws4d_target.h"
#include "ws4d_targetservice.h"

#include "ws4d_eprllist.h"

int
ws4d_targetservice_init (struct ws4d_targetservice *ts)
{
  ws4d_assert (ts, WS4D_ERR);

  ts->as.MessageNumber = 0;
  ts->as.InstanceId = ws4d_systime_s ();

  return ws4d_eprlist_init (&ts->targets, ws4d_eprllist_init, NULL);
}

int
ws4d_targetservice_done (struct ws4d_targetservice *ts)
{
  ws4d_eprlist_done (&ts->targets);
  return WS4D_OK;
}

struct ws4d_epr *
ws4d_targetservice_inittarget (struct ws4d_targetservice *ts,
                               const char *Addrs, const char *XAddrs)
{
  struct ws4d_epr *target;

  ws4d_assert (ts && Addrs && XAddrs, NULL);

  target = ws4d_eprlist_alloc (&ts->targets);
  if (target)
    {
      ws4d_register_targetep (target);

      /* set logical address */
      ws4d_epr_set_Addrs (target, Addrs);

      /* set pyhsical address */
      ws4d_targetep_set_XAddrs (target, XAddrs);

      ws4d_targetep_set_ts (target, ts);

      ws4d_eprlist_add (&ts->targets, target);
    }

  return target;
}

int
ws4d_targetservice_deltarget (struct ws4d_targetservice *ts,
                              struct ws4d_epr *target)
{
  int err;
  struct ws4d_epr *del_target;

  del_target =
    ws4d_targetservice_gettarget_byAddr (ts, ws4d_epr_get_Addrs (target));
  if (!del_target)
    return WS4D_ERR;

  err = ws4d_eprlist_remove (&ts->targets, del_target);
  if (err != WS4D_OK)
    return err;

  return ws4d_eprlist_free (&ts->targets, del_target);
}

struct ws4d_epr *
ws4d_targetservice_gettarget_byAddr (struct ws4d_targetservice *ts,
                                     const char *addr)
{
  /* test parameters */
  ws4d_assert (ts && addr, NULL);

  return ws4d_eprlist_get_byAddr (&ts->targets, addr);
}

int
ws4d_targetservice_getmatches (struct ws4d_targetservice *ts,
                               const char *Scopes, const char *Types,
                               struct ws4d_abs_eprlist *matches)
{
  char **scopesToMatch = NULL;
  char **typesToMatch = NULL;
  int scope_matches_always = 0;
  int type_matches_always = 0;
  ws4d_alloc_list alist;
  register struct ws4d_epr *target, *next;

  ws4d_assert (ts && matches, WS4D_ERR);

  WS4D_ALLOCLIST_INIT (&alist);

  if (Scopes)
    {
      if (strcmp (Scopes, ""))
        {
          scopesToMatch = ws4d_xsdList_to_Array (Scopes, &alist);
        }
      else
        {
          /* empty scope matches */
          scope_matches_always = 1;
        }
    }
  else
    {
      /* no scope matches */
      scope_matches_always = 1;
    }

  if (Types)
    {
      if (strcmp (Types, ""))
        {
          typesToMatch = ws4d_xsdList_to_Array (Types, &alist);
        }
      else
        {
          /* empty type matches */
          type_matches_always = 1;
        }
    }
  else
    {
      /* no type matches */
      type_matches_always = 1;
    }

  /* compare each active hosting_target with the probe request */
  ws4d_eprlist_foreach (target, next, &ts->targets)
  {
    int scope_matches = 0;
    int type_matches = 0;

    if (!ws4d_targetep_isactive (target))
      continue;

    /* first match Scope */
    if (scope_matches_always)
      {
        scope_matches = 1;
      }
    else
      {
        if (ws4d_targetep_matches_Scopes
            (target, (const char **) scopesToMatch) == WS4D_OK)
          {
            scope_matches = 1;
          }
      }

    /* then match Type */
    if (type_matches_always)
      {
        type_matches = 1;
      }
    else
      {
        if (ws4d_targetep_matches_Types (target, (const char **) typesToMatch)
            == WS4D_OK)
          {
            type_matches = 1;
          }
      }

    /* if scope and type matches then add to result list */
    if (type_matches && scope_matches)
      {
        struct ws4d_epr *copy;
        copy = ws4d_eprlist_alloc (matches);
        ws4d_epr_copy (copy, target);
        ws4d_eprlist_add (matches, copy);
      }
  }

  if (scopesToMatch)
    ws4d_free_xsdArray (scopesToMatch);
  if (typesToMatch)
    ws4d_free_xsdArray (typesToMatch);

  return WS4D_OK;
}

int
ws4d_targetservice_inc_MessageNumber (struct ws4d_targetservice *ts)
{
  ws4d_assert (ts, WS4D_ERR);

  ts->as.MessageNumber++;

  return WS4D_OK;
}

struct ws4d_appsequence *
ws4d_targetservice_get_appsequence (struct ws4d_targetservice *ts)
{
  ws4d_assert (ts, NULL);

  return &ts->as;
}
