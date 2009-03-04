package edu.teco.automata.generator.xml;

import java.io.IOException;
import java.io.StringReader;

import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class XmlReader {

   private String fileName = null;
   private ContentHandler handler = null;

   public XmlReader(String fileName, ContentHandler handler) {
      this.fileName = fileName;
      this.handler = handler;
   }

   public XmlReader(ContentHandler handler) {
      this.handler = handler;
   }

   public void parse() {
      try {
         XMLReader parser = XMLReaderFactory.createXMLReader();
         parser.setContentHandler(handler);
         parser.parse(fileName);
         //
      } catch (SAXException e) {
         System.out.println(fileName + " is not well-formed.");
      } catch (IOException e) {
         System.out
               .println("Due to an IOException, the parser could not check "
                     + fileName);
      }
   }

   public void parse(String xmlString) {
      try {
         XMLReader parser = XMLReaderFactory.createXMLReader();
         parser.setContentHandler(handler);
         StringReader inStream = new StringReader(xmlString);
         InputSource inSource = new InputSource(inStream);
         parser.parse(inSource);
         //
      } catch (SAXException e) {
         System.out.println(fileName + " is not well-formed.");
      } catch (IOException e) {
         System.out.println("IO Error");
      }
   }
}
