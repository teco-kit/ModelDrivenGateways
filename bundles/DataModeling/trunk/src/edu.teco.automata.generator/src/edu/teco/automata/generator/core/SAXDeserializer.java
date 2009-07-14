package edu.teco.automata.generator.core;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;


public class SAXDeserializer implements XMLReader,Locator {

	public static final String AUTOMATA_URI = "http://www.teco.edu/automata/";
	
	protected ContentHandler contentHandler = null;
	protected DTDHandler dtdHandler = null;
	protected EntityResolver entityResolver = null;
	protected ErrorHandler errorHandler = null;
	protected boolean namespacePrefixes = false, isParsing = false;
	protected String namespaceURI = null, prefix = null, systemId = null,
			publicId = null;

	private BinXMLReaderAutomata automata;



public ContentHandler getContentHandler() {
	return contentHandler;
}

public void setContentHandler(ContentHandler contentHandler) {
	this.contentHandler = contentHandler;
}

public DTDHandler getDTDHandler() {
	return dtdHandler;
}

public void setDTDHandler(DTDHandler dtdHandler) {
	this.dtdHandler = dtdHandler;
}

public EntityResolver getEntityResolver() {
	return entityResolver;
}

public void setEntityResolver(EntityResolver entityResolver) {
	this.entityResolver = entityResolver;
}

public ErrorHandler getErrorHandler() {
	return errorHandler;
}

public void setErrorHandler(ErrorHandler errorHandler) {
	this.errorHandler = errorHandler;
}

@Override
public boolean getFeature(String name) throws SAXNotRecognizedException,
		SAXNotSupportedException {
	return false;
}


@Override
public void parse(InputSource input) throws IOException, SAXException {
	BitsIO io=new BitsIO(getInputStream(input));
	if(null==automata) throw new SAXException("property \""+AUTOMATA_URI+"\" not set");
	try{
		contentHandler.startDocument();
		automata.run(io,contentHandler);
		contentHandler.endDocument();
	}
	catch(SAXParseException e)
	{
		errorHandler.fatalError(e);
	}
}

public String getAutomata() {
	return automata.getClass().getCanonicalName(); 
}

@SuppressWarnings("unchecked")
public void setAutomata(String encoderAutomataClass) throws InstantiationException {
	   try {
			 Class<BinXMLReaderAutomata> d = (Class<BinXMLReaderAutomata>) BinXMLReaderAutomata.class.getClassLoader().loadClass(encoderAutomataClass);
			 automata=d.newInstance();
			 //Constructor<BinXMLReaderAutomata> c = d.getConstructor(new Class[]{BitsIO.class,OutputStream.class});
			 //automata = (BinXMLReaderAutomata)c.newInstance(new Object[]{}); 
		}catch(ClassCastException e){
			throw new InstantiationException();
     }catch (ClassNotFoundException e) {
			throw new InstantiationException();
		} catch (IllegalAccessException e) {
			throw new InstantiationException();
		} catch (SecurityException e) {
			throw new InstantiationException();
		} catch (IllegalArgumentException e) {
			throw new InstantiationException();
		}
}

@Override
public void parse(String systemId)
   throws IOException, SAXException
{
   parse(new InputSource(systemId));
}


@Override
public void setFeature(String name, boolean value)
		throws SAXNotRecognizedException, SAXNotSupportedException {
	throw new SAXNotSupportedException();
	
}


public Object getProperty(String name)
throws SAXNotRecognizedException, SAXNotSupportedException
{
if(name.equals(AUTOMATA_URI))
   return getAutomata();
else
   throw new SAXNotRecognizedException(name);
}


public void setProperty(String name,Object value)
throws SAXNotRecognizedException, SAXNotSupportedException
{
if(name.equals(AUTOMATA_URI))
   if(value instanceof String)
	try {
		setAutomata((String)value);
	} catch (InstantiationException e) {
		throw new SAXNotSupportedException(AUTOMATA_URI);
	}
else
   throw new SAXNotSupportedException(AUTOMATA_URI);
else
   throw new SAXNotRecognizedException(name);
}


@Override
public int getColumnNumber() {
	return -1;
}

@Override
public int getLineNumber() {
	return -1;
}

@Override
public String getPublicId() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public String getSystemId() {
	// TODO Auto-generated method stub
	return null;
}
static private InputStream getInputStream(InputSource source)
throws SAXException, IOException
{

if(source.getByteStream() != null)
{
	return source.getByteStream();
}

else if(source.getSystemId() != null)
{
   URLConnection connection = new URL(source.getSystemId()).openConnection();
   connection.connect();
   return connection.getInputStream();
}

throw new SAXException("No suitable input in InputSource");

}


}
