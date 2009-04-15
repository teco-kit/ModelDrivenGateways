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
import edu.teco.automata.generator.xml.XmlReader;

public class DecEncSAX extends TestCase {

	String genDir = "src-gen/edu/teco/automata/generator/gen/";
	String binDir = "bin/edu/teco/automata/generator/gen/";
	String testDir = "src/edu/teco/automata/generator/test/";
	String xmlPath = testDir + "/prs74.xml";
	String xsdPath = testDir + "/prs74.xsd";

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
		FileOutputStream fos           = new FileOutputStream(genDir + "/output.bin");
		SAXSerializer w = new SAXSerializer(fos,"edu.teco.automata.generator.gen.DecoderAutomata");
		
		XmlReader xml = new XmlReader(xmlPath, w, w);
	

		xml.parse(true);
		fos.close();
		assertTrue(new File(genDir + "/output.bin").exists());
		
	//	FileOutputStream fos = null;
		FileInputStream fis = null;

		fis = new FileInputStream(genDir + "/output.bin");
		fos = new FileOutputStream(genDir + "/prs74.xml");
		
		
		XMLReader reader=new SAXDeserializer();
		reader.setProperty(SAXDeserializer.AUTOMATA_URI, "edu.teco.automata.generator.gen.EncoderSAXAutomata");
		
		Source source=new SAXSource(reader,new InputSource(fis));
		Result result=new StreamResult(fos);
		
		Transformer transformer=TransformerFactory.newInstance().newTransformer();
		transformer.transform(source,result);
		
		fos.close();
		fis.close();

		assertTrue("XML Files Differ", XMLDiff.diffXML(testDir + "/prs74.xml",
				genDir + "/prs74.xml"));
	}

	@After
	public void tearDown() throws Exception {
		assertTrue("clean", DeleteDir.deleteDirectory(new File(binDir)));
		assertTrue("clean", DeleteDir.deleteDirectory(new File(genDir)));	
	}

}
