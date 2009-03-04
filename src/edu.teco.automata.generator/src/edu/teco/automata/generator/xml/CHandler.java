package edu.teco.automata.generator.xml;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import edu.teco.automata.generator.core.Decoder;

public class CHandler implements ContentHandler {
	private Decoder writer;
	
	public CHandler(Decoder wr) {
		writer = wr;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		writer.elementData(new String(ch, start, length));
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		
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
		writer.element(name);
		for (int i = 0; i < atts.getLength(); i++)
		{
		   writer.element(atts.getLocalName(i));
		   writer.elementData(atts.getValue(i));
		}
	}

	@Override
	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}

}
