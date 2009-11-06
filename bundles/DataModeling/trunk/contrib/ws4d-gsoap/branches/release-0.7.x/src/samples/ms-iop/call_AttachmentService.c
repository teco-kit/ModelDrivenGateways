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

#include "ats.nsmap"
#include "dpws_client.h"
#include "fileio.h"

static unsigned char buffer[1000000];

int
main (int argc, char **argv)
{
  struct soap client;

  char *addr = NULL;
  char *operation = NULL;

  char MsgId[DPWS_MSGID_SIZE];

  while (argc > 1)
    {
      if (argv[1][0] == '-')
        {
          char *option = &argv[1][1];
          switch (option[0])
            {
            case 'a':
              if (strlen (option) > 2)
                {
                  ++option;
                  addr = option;
                }
              else
                {
                  --argc;
                  ++argv;
                  addr = argv[1];
                }
              printf ("\nService address is \"%s\"\n", addr);
              fflush (NULL);
              break;
            case 'o':
              if (strlen (option) > 2)
                {
                  ++option;
                  operation = option;
                }
              else
                {
                  --argc;
                  ++argv;
                  operation = argv[1];
                }
              printf ("\nOperation is \"%s\"\n", operation);
              fflush (NULL);
              break;
            default:
              fprintf (stderr, "\nBad option %s", argv[1]);
            }
        }
      --argc;
      ++argv;
    }

  if (addr == NULL)
    {
      printf ("\nNo service addr was specified!");
      fflush (NULL);
      exit (0);
    }

  /* initialize client soap handle */
  soap_init1 (&client, SOAP_ENC_MTOM);
  //soap_omode (&client, SOAP_XML_INDENT);

  if (!strcmp (operation, "oneway"))
    {
      struct ats1__AttachmentType ats1__OneWayAttachment;
      struct __ats1__OneWayAttachment _param_1;

      ats1__OneWayAttachment.CRC = 123;
      ats1__OneWayAttachment.Param.__ptr = buffer;
      ats1__OneWayAttachment.Param.__size =
        readfile ("dpws1ref.jpg", ats1__OneWayAttachment.Param.__ptr);

      if (ats1__OneWayAttachment.Param.__size < 0)
        {
          printf ("Cannot read file for attachment\n");
          return SOAP_OK;
        }

      ats1__OneWayAttachment.Param.id = NULL;
      ats1__OneWayAttachment.Param.type = "image/jpeg";
      ats1__OneWayAttachment.Param.options = NULL;
      ats1__OneWayAttachment.__anyAttribute = NULL;

      soap_set_namespaces (&client, ats_namespaces);
      dpws_header_gen_MessageId (MsgId, DPWS_MSGID_SIZE);
      dpws_header_gen_oneway (&client, MsgId, addr,
                              "http://schemas.example.org/AttachmentService/OneWayAttachment",
                              NULL, sizeof (struct SOAP_ENV__Header));

      if (soap_send___ats1__OneWayAttachment
          (&client, addr, NULL, &ats1__OneWayAttachment) == SOAP_OK)
        {
          printf ("Successfully called %s-operation on %s\n", operation,
                  addr);
        }
      else
        {
          printf ("Error: calling %s-operation on %s\n", operation, addr);
        }

      if (soap_recv___ats1__OneWayAttachment (&client, &_param_1) != 202)
        {
          printf ("Error: got not response from %s\n", addr);
        }

    }
  else if (!strcmp (operation, "twoway"))
    {
      struct ats1__AttachmentType ats1__TwoWayAttachmentRequest;
      struct ats1__AttachmentType ats1__TwoWayAttachmentResponse;

      ats1__TwoWayAttachmentRequest.CRC = 123;
      ats1__TwoWayAttachmentRequest.Param.__ptr = buffer;
      ats1__TwoWayAttachmentRequest.Param.__size =
        readfile ("dpws1ref.jpg", ats1__TwoWayAttachmentRequest.Param.__ptr);

      if (ats1__TwoWayAttachmentRequest.Param.__size < 0)
        {
          printf ("Cannot read file for attachment\n");
          return SOAP_OK;
        }

      ats1__TwoWayAttachmentRequest.Param.id = NULL;
      ats1__TwoWayAttachmentRequest.Param.type = "image/jpeg";
      ats1__TwoWayAttachmentRequest.Param.options = NULL;
      ats1__TwoWayAttachmentRequest.__anyAttribute = NULL;

      soap_set_namespaces (&client, ats_namespaces);
      dpws_header_gen_MessageId (MsgId, DPWS_MSGID_SIZE);
      dpws_header_gen_request (&client, MsgId, addr,
                               "http://schemas.example.org/AttachmentService/TwoWayAttachmentRequest",
                               NULL, NULL, sizeof (struct SOAP_ENV__Header));

      if (soap_call___ats1__TwoWayAttachment (&client, addr, NULL,
                                              &ats1__TwoWayAttachmentRequest,
                                              &ats1__TwoWayAttachmentResponse)
          == SOAP_OK)

        {
          printf ("Successfully called %s-operation on %s\n",
                  operation, addr);

          printf ("Received Attachment size(%d) type(%s)\n",
                  ats1__TwoWayAttachmentResponse.Param.__size,
                  ats1__TwoWayAttachmentResponse.Param.type);

          if (writefile ("dpws2recv.jpg",
                         ats1__TwoWayAttachmentResponse.Param.__ptr,
                         ats1__TwoWayAttachmentResponse.Param.__size) < 0)
            {
              printf ("\nCannot save attachment\n");
            }

          //if ((res = comparefiles("dpws2recv.jpg", "dpws2.jpg")) == 0)
          //  printf("Attachment OK\n");
          //else 
          //  printf("Attachment NOK: difference at byte %d\n", res);

        }
      else
        {
          printf ("Error: calling %s-operation on %s\n", operation, addr);
        }
    }
  else
    {
      printf ("Error: operation %s not supported\n", operation);
    }

  soap_end (&client);
  soap_done (&client);

  exit (0);
}
