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

#include "ws4d_misc.h"
#include "ws4d_localizedstring.h"

/**
 * Function extracts a string of a speficic language from an array of struct wdp__LocalizedStringType
 *
 * @param string array of structure wdp__LocalizedStringType to
 * extract string from
 * @param size number of elements in array
 * @param lang language to extract
 *
 * @return pointer to extractet string on success, NULL otherwise
 */
char *
ws4d_locstring_get (struct ws4d_locstring *string,
                          int size, const char *lang)
{
  int i = 0;
  char *result = NULL;

  /* test parameters */
  ws4d_assert (string && (size > 0) && lang, NULL);

  for (i = 0; i < size; i++)
    {
      if (string[i].lang && !STRCASECMP (string[i].lang, lang))
        {
          result = string[i].string;
          break;
        }
    }

  return result;
}

struct ws4d_locstring *
ws4d_locstring_dup (struct ws4d_locstring *string,
                    int size, ws4d_alloc_list * alist)
{
  struct ws4d_locstring *res;
  int i, err = WS4D_OK;

  /* test parameters */
  ws4d_assert (string && (size > 0) && alist, NULL);

  res = ws4d_malloc_alist (sizeof (struct ws4d_locstring) * size, alist);
  for (i = 0; i < size; i++)
    {
      if (string[i].lang && string[i].string)
        {
          res[i].lang = ws4d_strdup (string[i].lang, alist);
          res[i].string = ws4d_strdup (string[i].string, alist);
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
              ws4d_free_alist (res[i].lang);
              ws4d_free_alist (res[i].string);
            }
        }
    }

  return res;
}

void
ws4d_locstring_free (struct ws4d_locstring *string, int size)
{
  int i;

  ws4d_assert (string && (size > 0),);

  for (i = 0; i < size; i++)
    {
      if (string[i].lang)
        {
          ws4d_free_alist (string[i].lang);
        }

      if (string[i].string)
        {
          ws4d_free_alist (string[i].string);
        }
    }
  ws4d_free_alist (string);
}

int ws4d_dur_to_s (struct ws4d_dur *dur, ws4d_time *s)
{
  if (dur && s)
    {
      *s = ((946080000 * dur->years) +
            (2592000 * dur->months) +
            (86400 * dur->days) + (3600 * dur->hours) +
            (60 * dur->minutes) + dur->seconds);

      return WS4D_OK;
    }
  else
    {
      return WS4D_ERR;
    }
}

int ws4d_s_to_dur (ws4d_time s, struct ws4d_dur *dur)
{
  if (dur)
    {
      dur->years = s / 946080000;
      s = s % 946080000;
      dur->months = s / 2592000;
      s = s % 2592000;
      dur->days = s / 86400;
      s = s % 86400;
      dur->hours = s / 3600;
      s = s % 3600;
      dur->minutes = s / 60;
      s = s % 60;
      dur->seconds = s;

      return WS4D_OK;
    }
  else
    {
      return WS4D_ERR;
    }
}
