/* ========================================================================
 *
 *        Filename   : decoder.c
 *        Description:
 *        Version    : 1.0
 *        (C)        : Dimitar Yordanov (domidimi [at] gmail [dot] com)
 *        ---- History ---------------------------------------------------
 *        05.04.2008 21:43    dy    initial version
 * ========================================================================
 */

#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <libxml/xmlreader.h>

#include "write_str_func.h"
#include "decoder_automata.h"

static int fd = -1;
static struct STR_WRITER_STRUCT writer_struct;
static void decoder_processNode(xmlTextReaderPtr reader);
static void decoder_processElemAttribute(xmlTextReaderPtr reader);
int decoder_parse( const char * xmlFile );

#define TEST
#ifdef TEST
/* ========================================================================
 *
 *
 * ========================================================================*/
int main(int argc, char* argv[])
{
	fd = open("output.bin",
			  O_WRONLY | O_CREAT | O_TRUNC,
			  S_IRWXU | S_IRWXG);
	if (fd == -1)
	{
		fprintf(stderr, "Error opening output file\n");
		return (-1);
	}

	automata_init(fd);
	decoder_parse(argv[1]);
	automata_finish();
	close(fd);

	return 0;
}
#endif

/* ========================================================================
 *
 *
 * ========================================================================*/
int decoder_parse( const char * xmlFile )
{
   xmlTextReaderPtr reader = xmlNewTextReaderFilename(xmlFile);
   if (reader == NULL)
      return 1;

   while (xmlTextReaderRead(reader) == 1)
      decoder_processNode(reader);

   xmlFreeTextReader(reader);

   return 0;

}

/* ========================================================================
 *
 *
 * ========================================================================*/
static void decoder_processNode(xmlTextReaderPtr reader)
{
   /* process here */
   switch (xmlTextReaderNodeType(reader))
   {
      case XML_ELEMENT_NODE:
         {
            xmlChar *name  = xmlTextReaderName(reader);
            /*int      depth = xmlTextReaderDepth(reader);*/

            printf("Name: <%s>, Depth <%d>\n", name, xmlTextReaderDepth(reader));
            getElementDecoder((char *)name, &writer_struct);

            while (xmlTextReaderMoveToNextAttribute(reader) == 1)
               decoder_processElemAttribute(reader);

            xmlFree(name);
         }
         break;
      case XML_TEXT_NODE:
         printf("Text: %s\n", xmlTextReaderConstValue(reader));
         writer_struct.func((char *)xmlTextReaderConstValue(reader),
        		            &writer_struct.args);
         printf("After Text %s\n", xmlTextReaderConstValue(reader));
         break;
      default:
    	  /*
          xmlChar * name = xmlTextReaderName(reader);
          printf("DEF <%s>\n", name);
          xmlFree(name);
          */
         break;
   }

}

/* ========================================================================
 *
 *
 * ========================================================================*/
static void decoder_processElemAttribute(xmlTextReaderPtr reader)
{
   xmlChar *child = xmlTextReaderName(reader);
   xmlChar *value = xmlTextReaderValue(reader);

   printf("Attribute: <%s>, Value: <%s>\n", child, value);
   getElementDecoder((char *)child, &writer_struct);
   writer_struct.func((char *)value, &writer_struct.args);

   xmlFree(child);
   xmlFree(value);
}
