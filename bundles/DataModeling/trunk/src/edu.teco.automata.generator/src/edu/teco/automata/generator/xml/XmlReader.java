package edu.teco.automata.generator.xml;

import java.io.IOException;
import java.io.StringReader;
import org.xml.sax.ErrorHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


public class XmlReader {

   private String fileName = null;
   private ContentHandler handler = null;
   private ErrorHandler ehandler = null;
   
   public XmlReader(String fileName, ContentHandler handler, ErrorHandler ehandler ) {
      this.fileName = fileName;
      this.handler = handler;
      this.ehandler = ehandler;
   }

   public XmlReader(ContentHandler handler,ErrorHandler ehandler) {
      this(null,handler,ehandler);
   }
   

	   
   public void parse() throws SAXException, IOException {
	   parse(false);
   }
   public void parse(boolean validate) throws SAXException, IOException {

         XMLReader parser = XMLReaderFactory.createXMLReader();
        if(validate)
        {
         parser.setFeature("http://xml.org/sax/features/validation",true);
         parser.setFeature("http://apache.org/xml/features/validation/schema",true);
        }
        if(null!=ehandler)
        	parser.setErrorHandler(ehandler);

         parser.setContentHandler(handler);
         parser.parse(fileName);
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
