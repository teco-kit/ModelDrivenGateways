package edu.teco.automata.generator.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Before;
import org.xml.sax.SAXException;

import edu.teco.automata.generator.Runner;
import edu.teco.automata.generator.core.Decoder;
import edu.teco.automata.generator.core.Encoder;
import edu.teco.automata.generator.xml.CHandler;
import edu.teco.automata.generator.xml.XmlReader;


public class DecEnc extends TestCase {
   
   String genDir  = "src-gen/edu/teco/automata/generator/gen";
   String testDir = "src/edu/teco/automata/generator/test/";
   String xmlPath= testDir + "/prs74.xml";
   String xsdPath     = testDir + "/prs74.xsd";
   
   @Before
   public void setUp() throws Exception {
      
      
	  Map<String, String> properties = new HashMap<String, String>();
	  properties.put("schemaFile", xsdPath);
	  properties.put("writeAutomataFile", "false");
	  
	  properties.put("NSPrefix", "teco");
	  
	  DeleteDir.deleteDirectory(new File(genDir));
	  properties.put("outlet_path", genDir);
	//  properties.put("automataFile", automataPath);

	  String[] target=new String[] { "JavaXML" };
      Runner.main(properties,target);
      
   } 
   

   public void testDecEnc() throws IOException, SAXException {
      Decoder  w       = new Decoder(genDir + "/output.bin");
      CHandler handler = new CHandler(w);
     
      XmlReader xml    = 
         new XmlReader(xmlPath, handler,handler);
      
      
      xml.parse(true);
      
      try {
         FileInputStream fis = new FileInputStream(genDir + "/output.bin");
         FileOutputStream fos = new FileOutputStream(genDir + "/prs74.xml");

         Encoder reader = new Encoder(fis, new PrintStream(fos));
         reader.run();
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }
      
      assertTrue("XML Files Differ", XMLDiff.diffXML(testDir + "/prs74.xml", 
                  genDir + "/prs74.xml"));
   }

}
