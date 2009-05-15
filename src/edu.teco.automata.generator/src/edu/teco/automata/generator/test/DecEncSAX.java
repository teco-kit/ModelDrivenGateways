package edu.teco.automata.generator.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.*;
import javax.xml.transform.sax.*;
import javax.xml.transform.stream.*;
import org.xml.sax.*;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.teco.automata.generator.Runner;

import edu.teco.automata.generator.core.SAXDeserializer;
import edu.teco.automata.generator.core.SAXSerializer;


public class DecEncSAX extends TestCase {

	final String genDir = "src-gen/edu/teco/automata/generator/gen/";
	final String binDir = "bin/edu/teco/automata/generator/gen/";
	final String testDir = "src/edu/teco/automata/generator/test/";
	final String xmlPath = testDir + "xsd/config.xml";
	final String xsdPath = testDir + "xsd/config.xsd";

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
		assertTrue(0 == com.sun.tools.javac.Main.compile(new String[] {
				"-classpath", "bin", "-d", "bin",
				genDir + "EncoderSAXAutomata.java",
				genDir + "DecoderAutomata.java" })); //re-compilation of DecoderAutomata will have no effect if class is already loaded
		return;
	}

	@Test
	public void testDecEncSAX() throws IOException, SAXException,
	InstantiationException, TransformerFactoryConfigurationError, TransformerException {

		assertFalse(new File(genDir + "/output.bin").exists());

		{
			FileOutputStream fos  = new FileOutputStream(genDir + "/prs74.bin");try{	
			Source source=new StreamSource(new File(xmlPath));
			Result result=new SAXResult(new SAXSerializer(fos,"edu.teco.automata.generator.gen.DecoderAutomata"));

			Transformer xml2bin=TransformerFactory.newInstance().newTransformer();
			xml2bin.transform(source,result);
			}finally{fos.close();}
		}

		assertTrue(new File(genDir + "/prs74.bin").exists());

		{
			FileInputStream fis = new FileInputStream(genDir + "/prs74.bin");try{
			FileOutputStream fos = new FileOutputStream(genDir + "/prs74.xml");try{       

			XMLReader reader=new SAXDeserializer();

			reader.setProperty(SAXDeserializer.AUTOMATA_URI, "edu.teco.automata.generator.gen.EncoderSAXAutomata");
			{
				Source source=new SAXSource(reader,new InputSource(fis));
				Result result=new StreamResult(fos);

				Transformer bin2xml=TransformerFactory.newInstance().newTransformer();
				bin2xml.transform(source,result);
			}

			}finally{fos.close();}
			}finally{fis.close();}
		}

		assertTrue("XML Files Differ", XMLDiff.diffXML(testDir + "/prs74.xml",
				genDir + "/prs74.xml"));
	}

	@After
	public void tearDown() throws Exception {
		assertTrue("clean", DeleteDir.deleteDirectory(new File(binDir)));
		assertTrue("clean", DeleteDir.deleteDirectory(new File(genDir)));	
	}

}
