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

   public void element(String xmlElement) {
     
      try {
         type  = decoder.element(xmlElement);
         saved = false;
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
   }

   public void elementData(String value) {
      try {
         if (saved) // elementData is called twice?
            return;
         if (value == null) // just be sure
            return;
         if (type == null) // no type in the table
            return;
         
         type.write(value, io);
         saved = true;
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   public void finish() {
      try {
         io.write_finish();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   
   
   public static void main(String argv[]) {
      Decoder    w       = 
         new Decoder("src/edu/teco/automata/generator/test/output.bin");
      CHandler  handler = new CHandler(w);
      XmlReader xml     = 
         new XmlReader("src/edu/teco/automata/generator/test/prs74.xml", 
                       handler);
      xml.parse();
      w.finish();
   }
}
