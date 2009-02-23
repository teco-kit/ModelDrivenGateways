package edu.teco.automata.generator.transformer;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
/**
 * XML SAX Content handler used to parse the annotations from a XML-Schema
 * such as precision 
 * @author dy
 */
public class CHandler implements ContentHandler {

   private double stepping = -1;

   public double getStepping() {
      return stepping;
   }

   @Override
   public void startElement(String uri, String localName, String name,
         Attributes atts) throws SAXException {
      if (localName.equals("stepping")) {
         for (int i = 0; i < atts.getLength(); i++) {
            if (atts.getLocalName(i).equals("value"))
               stepping = Double.parseDouble(atts.getValue(i));
         }
      }

   }

   @Override
   public void startPrefixMapping(String prefix, String uri)
         throws SAXException {
   }

   @Override
   public void characters(char[] arg0, int arg1, int arg2) throws SAXException {
   }

   @Override
   public void endDocument() throws SAXException {
   }

   @Override
   public void endElement(String arg0, String arg1, String arg2)
         throws SAXException {
   }

   @Override
   public void endPrefixMapping(String arg0) throws SAXException {
   }

   @Override
   public void ignorableWhitespace(char[] arg0, int arg1, int arg2)
         throws SAXException {
   }

   @Override
   public void processingInstruction(String arg0, String arg1)
         throws SAXException {
   }

   @Override
   public void setDocumentLocator(Locator arg0) {
   }

   @Override
   public void skippedEntity(String arg0) throws SAXException {
   }

   @Override
   public void startDocument() throws SAXException {
   }

}
