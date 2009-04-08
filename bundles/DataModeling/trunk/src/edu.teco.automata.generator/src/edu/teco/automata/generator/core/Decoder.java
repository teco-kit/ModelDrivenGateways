package edu.teco.automata.generator.core;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import edu.teco.automata.generator.gen.DecoderAutomata;
import edu.teco.automata.generator.types.TypeStringIntf;
import edu.teco.automata.generator.xml.CHandler;
import edu.teco.automata.generator.xml.XmlReader;


public class Decoder {
   public  String              fileName;
   private FileOutputStream    fos;
   private BitsIO              io;
   private TypeStringIntf      type;
   private boolean             saved;
   private DecoderAutomata     decoder;

   public Decoder(String fileName) {
      try {
         this.fileName = fileName;
         fos           = new FileOutputStream(fileName);
         io            = new BitsIO(fos);
         decoder       = new DecoderAutomata(io); 
         
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }
   }

   public void element(String xmlElement) throws IOException {
     
         type  = decoder.element(xmlElement);
         saved = false; 
      
   }

   public void elementData(String value) throws IOException {
         if (saved) // elementData is called twice?
            return;
         if (value == null) // just be sure
            return;
         if (type == null) // no type in the table
            return;
         
         type.write(value, io);
         saved = true;
   }
   
   public void finish() throws IOException {
         io.write_finish();
   }
   
   
   public static void main(String argv[]) {
      Decoder    w       = 
         new Decoder("src/edu/teco/automata/generator/test/output.bin");
      CHandler  handler = new CHandler(w);
      XmlReader xml     =          new XmlReader("src/edu/teco/automata/generator/test/prs74.xml", 
                       handler, handler);
      
      
      try {
        xml.parse(); 
	} catch (Exception e) {
		e.printStackTrace();
		System.exit(1);
	}
   }
}
