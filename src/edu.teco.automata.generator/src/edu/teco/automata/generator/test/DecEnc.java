package edu.teco.automata.generator.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import edu.teco.automata.generator.Runner;
import edu.teco.automata.generator.core.BinXMLPrinter;
import edu.teco.automata.generator.core.SAXSerializer;
import edu.teco.automata.generator.xml.XmlReader;

public class DecEnc extends TestCase {

	String genDir = "src-gen/edu/teco/automata/generator/gen/";
	String binDir = "bin/edu/teco/automata/generator/gen/";
	String testDir = "src/edu/teco/automata/generator/test/";
	String xmlPath = testDir + "xsd/prs74.xml";
	String xsdPath = testDir + "xsd/prs74.xsd";
	String rootElem ="SensorValues";

	@Before
	public void setUp() throws Exception {

		Map<String, String> properties = new HashMap<String, String>();
		properties.put("schemaFile", xsdPath);
		properties.put("automataFile", "src-gen/prs74.automata");
		properties.put("writeAutomataFile", "false");

		properties.put("NSPrefix", "teco");

		assertTrue("clean", DeleteDir.deleteDirectory(new File(genDir)));
		assertTrue("clean", DeleteDir.deleteDirectory(new File(binDir)));
		properties.put("outlet_path", genDir);
		// properties.put("automataFile", automataPath);

		String[] target = new String[] { "JavaXML" };
		Runner.main(properties, target);
		assertTrue(org.eclipse.jdt.core.compiler.batch.BatchCompiler.compile(new String[] {
				"-source","1.5",
				"-classpath", "bin", "-d", "bin",
				genDir + rootElem +"EncoderAutomata.java",
				genDir + rootElem +"DecoderAutomata.java" },
		new java.io.PrintWriter(System.out),new java.io.PrintWriter(System.err), null
		));
		return;
	}

	@Test
	public void testDecEnc() throws IOException, SAXException,
			InstantiationException {
		assertFalse(new File(genDir + "/output.bin").exists());
		FileOutputStream fos=null;
		
		
		fos= new FileOutputStream(genDir + "/output.bin");
		try{
		SAXSerializer w = new SAXSerializer(fos,"edu.teco.automata.generator.gen.SensorValuesDecoderAutomata");
		
		XmlReader xml = new XmlReader(xmlPath, w, w);

		xml.parse(true);
		}finally
		{
		fos.close();
		}
		assertTrue(new File(genDir + "/output.bin").exists());
	//	FileOutputStream fos = null;
		FileInputStream fis = null;
		
		
		fis = new FileInputStream(genDir + "/output.bin");try{
		fos = new FileOutputStream(genDir + "/prs74.xml");try{
			BinXMLPrinter reader = new BinXMLPrinter("edu.teco.automata.generator.gen."+rootElem+"EncoderAutomata");
			reader.run(fis, fos);
		}finally {	fos.close();}
		}finally {	fis.close();}

		assertTrue("XML Files Differ", XMLDiff.diffXML(xmlPath,
				genDir + "/prs74.xml"));
	}

	@After
	public void tearDown() throws Exception {
		assertTrue("clean", DeleteDir.deleteDirectory(new File(binDir)));
		//assertTrue("clean", DeleteDir.deleteDirectory(new File(genDir)));	
	}

}
