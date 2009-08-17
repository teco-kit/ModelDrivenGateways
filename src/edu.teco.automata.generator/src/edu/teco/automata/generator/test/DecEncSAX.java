package edu.teco.automata.generator.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import edu.teco.automata.generator.Runner;
import edu.teco.automata.generator.core.SAXDeserializer;
import edu.teco.automata.generator.core.SAXSerializer;


public class DecEncSAX extends TestCase {

	final String genDir = "src-gen/edu/teco/automata/generator/gen/";
	final String binDir = "bin/edu/teco/automata/generator/gen/";
	final String testDir = "src/edu/teco/automata/generator/test/";
	final String xmlPath = testDir + "xsd/config.xml";
	final String xsdPath = testDir + "xsd/config.xsd";
	String rootElem ="SensorConfiguration";

	@Before
	public void setUp() throws Exception {

		Map<String, String> properties = new HashMap<String, String>();
		properties.put("schemaFile", xsdPath);
		properties.put("writeAutomataFile", "false");

		properties.put("NSPrefix", "teco");

		assertTrue("clean", DeleteDir.deleteDirectory(new File(genDir)));
		assertTrue("clean", DeleteDir.deleteDirectory(new File(binDir)));
		properties.put("outlet_path", genDir);
		// properties.put("automataFile", automataPath);

		String[] target = new String[] { "JavaXML2" };
		Runner.main(properties, target);
		assertTrue(org.eclipse.jdt.core.compiler.batch.BatchCompiler.compile(new String[] {
				"-source","1.5",
				"-classpath", "bin", "-d", "bin",
				genDir + rootElem +"EncoderSAXAutomata.java",
				genDir + rootElem +"DecoderAutomata.java" },
				new java.io.PrintWriter(System.out),new java.io.PrintWriter(System.err), null
		)); //re-compilation of DecoderAutomata will have no effect if class is already loaded
		return;
	}

	@Test
	public void testDecEncSAX() throws IOException, SAXException,
	InstantiationException, TransformerFactoryConfigurationError, TransformerException {

		assertFalse(new File(genDir + "/out.bin").exists());

		{
			FileOutputStream fos  = new FileOutputStream(genDir + "/out.bin");try{	
			Source source=new StreamSource(new File(xmlPath));
			Result result=new SAXResult(new SAXSerializer(fos,"edu.teco.automata.generator.gen."+rootElem+"DecoderAutomata"));

			Transformer xml2bin=TransformerFactory.newInstance().newTransformer();
			xml2bin.transform(source,result);
			}finally{fos.close();}
		}

		assertTrue(new File(genDir + "/out.bin").exists());
		assertFalse(new File(genDir + "/out.xml").exists());

		{
			FileInputStream fis = new FileInputStream(genDir + "/out.bin");try{
			FileOutputStream fos = new FileOutputStream(genDir + "/out.xml");try{       

			XMLReader reader=new SAXDeserializer();

			reader.setProperty(SAXDeserializer.AUTOMATA_URI, "edu.teco.automata.generator.gen."+rootElem+"EncoderSAXAutomata");
			{
				Source source=new SAXSource(reader,new InputSource(fis));
				Result result=new StreamResult(fos);

				Transformer bin2xml=TransformerFactory.newInstance().newTransformer();
				bin2xml.transform(source,result);
			}

			}finally{fos.close();}
			}finally{fis.close();}
		}

		assertTrue("XML Files Differ", XMLDiff.diffXML(xmlPath,
				genDir + "/out.xml"));
	}

	@After
	public void tearDown() throws Exception {
		assertTrue("clean", DeleteDir.deleteDirectory(new File(binDir)));
		//assertTrue("clean", DeleteDir.deleteDirectory(new File(genDir)));	
	}

}
