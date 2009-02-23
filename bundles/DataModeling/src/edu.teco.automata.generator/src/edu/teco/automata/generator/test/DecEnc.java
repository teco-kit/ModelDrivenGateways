package edu.teco.automata.generator.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import junit.framework.TestCase;

import org.junit.Before;

import edu.teco.automata.generator.Runner;
import edu.teco.automata.generator.core.Decoder;
import edu.teco.automata.generator.core.Encoder;
import edu.teco.automata.generator.xml.CHandler;
import edu.teco.automata.generator.xml.XmlReader;


public class DecEnc extends TestCase {
   
   String genDir  = "src-gen/edu/teco/automata/generator/gen";
   String testDir = "src/edu/teco/automata/generator/test/";
   
   @Before
   public void setUp() throws Exception {
      String xsdPath     = testDir + "/prs74.xsd";

      
      DeleteDir.deleteDirectory(new File(genDir));
      Runner.main(new String[] { xsdPath, genDir });
   }

   public void testDecEnc() {
      Decoder  w       = new Decoder(genDir + "/output.bin");
      CHandler handler = new CHandler(w);
      XmlReader xml    = 
         new XmlReader(testDir + "/prs74.xml", handler);
      
      xml.parse();
      w.finish();
      
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
