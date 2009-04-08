package edu.teco.automata.generator.xml;


import java.io.IOException;

import org.xml.sax.Attributes;

import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;

import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


import edu.teco.automata.generator.core.Decoder;

public class CHandler implements ContentHandler,ErrorHandler {
	private Decoder writer;
	
	public CHandler(Decoder wr) {
		writer = wr;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		try {
			writer.elementData(new String(ch, start, length));
		} catch (IOException e) {
			throw new SAXException(e);
		}
	}

	@Override
	public void endDocument() throws SAXException {
		try {
			writer.finish();
		} catch (IOException e) {
			throw new SAXException(e);
		} 
		
	}

	@Override
	public void endElement(String uri, String localName, String name)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processingInstruction(String target, String data)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startDocument() throws SAXException {
		
	}

	@Override
	public void startElement(String uri, String localName, String name,
			Attributes atts) throws SAXException {
		try {
			writer.element(name);
		
		for (int i = 0; i < atts.getLength(); i++)
		{
		   if(!atts.getURI(i).equals("http://www.w3.org/2000/xmlns/") && !atts.getURI(i).equals("http://www.w3.org/2001/XMLSchema-instance") )
		   {
			   writer.element(atts.getLocalName(i));
		       writer.elementData(atts.getValue(i));
		   }
		}
		} catch (IOException e) {
			throw new SAXException(e);
		}
	}

	@Override
	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
		// TODO Auto-generated method stub
	}

	@Override
	public void error(SAXParseException arg0) throws SAXException {
		throw arg0;
	}

	@Override
	public void fatalError(SAXParseException arg0) throws SAXException {
		throw arg0;
		
	}

	@Override
	public void warning(SAXParseException arg0) throws SAXException {
		arg0.printStackTrace();
	}


}
