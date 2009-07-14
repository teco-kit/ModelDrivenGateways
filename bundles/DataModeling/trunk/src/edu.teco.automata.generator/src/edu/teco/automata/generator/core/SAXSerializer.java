package edu.teco.automata.generator.core;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import edu.teco.automata.generator.types.TypeStringIntf;
import edu.teco.automata.generator.xml.XmlReader;


public class SAXSerializer  implements ContentHandler,ErrorHandler{
   public  String              fileName;
   private BitsIO              io; //TODO make more generic here
   private TypeStringIntf      type;
   private boolean             saved;
   private DecoderAutomata     decoder;


   
   @SuppressWarnings("unchecked")
public SAXSerializer(OutputStream os, String decoderAutomataClass) throws FileNotFoundException,  InstantiationException  {

	     this(os,(DecoderAutomata)null); //must be first
	     
		 try {
			 Class<DecoderAutomata> d = (Class<DecoderAutomata>) DecoderAutomata.class.getClassLoader().loadClass(decoderAutomataClass);
			 Constructor<DecoderAutomata> c = d.getConstructor(new Class[]{BitsIO.class});
			 decoder = (DecoderAutomata)c.newInstance(new Object[]{io}); 
		}catch(ClassCastException e){
			throw new InstantiationException();
        }catch (ClassNotFoundException e) {
			throw new InstantiationException();
		} catch (IllegalAccessException e) {
			throw new InstantiationException();
		} catch (SecurityException e) {
			throw new InstantiationException();
		} catch (NoSuchMethodException e) {
			throw new InstantiationException();
		} catch (IllegalArgumentException e) {
			throw new InstantiationException();
		} catch (InvocationTargetException e) {
			throw new InstantiationException();
		}
        
		 // decoder       = new DecoderAutomata(io); 
         
   }
   
   public SAXSerializer(OutputStream os, DecoderAutomata decoder)
   {
       io            = new BitsIO(os);
       this.decoder=decoder;
   }

   //delegate
   public void element(String xmlElement) throws IOException {
     
         type  = decoder.element(xmlElement);
         if (type == null) // no type in the table
        	 throw new IOException("unknown type");
         saved = false; 
      
   }
   
   //TODO: weird type of delegation via some kind of factory
   public void elementData(String value) throws IOException {
         if (saved) // elementData is called twice?
         {
        	return;
            //TODO:throw new IOException("elementData called twice");
         }
            saved = true;
         
         if (value == null) // just be sure
        	 throw new NullPointerException("value passed to elementData is null");
         
         type.write(value, io);
   }
   
   //delegate
   public void finish() throws IOException {
         io.write_finish();
   }
   

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		try {
			elementData(new String(ch, start, length));
		} catch (IOException e) {
			throw new SAXException(e);
		}
	}

	@Override
	public void endDocument() throws SAXException {
		try {
			decoder.element("stop");
			finish();
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
	public void startElement(String uri, String localName, String qname,
			Attributes atts) throws SAXException {
		try {
			element(localName);
		
		for (int i = 0; i < atts.getLength(); i++)
		{
		   if(!atts.getURI(i).equals("http://www.w3.org/2000/xmlns/") && !atts.getURI(i).equals("http://www.w3.org/2001/XMLSchema-instance") )
		   {
			   element(atts.getLocalName(i));
		       elementData(atts.getValue(i));
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
   
   public static void main(String argv[]) {
      SAXSerializer w;
	try {
		FileOutputStream fos           = new FileOutputStream("src/edu/teco/automata/generator/test/output.bin");
		w = new SAXSerializer(fos,argv[0]);
	} catch (FileNotFoundException e1) {
		e1.printStackTrace();
		System.exit(1);
		return;
		
	} catch (InstantiationException e1) {
		e1.printStackTrace();
		System.exit(1);
		return;
	}
	
      XmlReader xml     =          new XmlReader("src/edu/teco/automata/generator/test/prs74.xml", 
                       w, w);
      
      
      try {
        xml.parse(); 
	} catch (Exception e) {
		e.printStackTrace();
		System.exit(1);
		return;
	}
   }
}
