package edu.teco.automata.generator.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.xml.sax.SAXException;



public class BinXMLPrinter{
 
   private BinXMLPrinterAutomata automata;
   

   
   @SuppressWarnings("unchecked")
   public BinXMLPrinter(String encoderAutomataClass) throws FileNotFoundException,  InstantiationException  {	     
	    //TODO make more generic
	   try {
			 Class<BinXMLPrinterAutomata> d = (Class<BinXMLPrinterAutomata>) BinXMLPrinterAutomata.class.getClassLoader().loadClass(encoderAutomataClass);
			 automata=d.newInstance();
			// Constructor<BinXMLPrinterAutomata> c = d.getConstructor(new Class[]{BitsIO.class,OutputStream.class});
			// automata = (BinXMLPrinterAutomata)c.newInstance(); 
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
   
//delegate
   public void run(InputStream ins, OutputStream outs) throws IOException, SAXException {   
	   BitsIO io=new BitsIO(ins);
       automata.run(io,new PrintStream(outs)); //TODO move constructor into Automata -> Transcoder interface
   }

   public static void main(String argv[]) {
      try {
         FileInputStream fis = new FileInputStream("src-gen/edu/teco/automata/generator/gen/output.bin");
         if(fis.available()==0)
        	 throw new IOException();
         
         BinXMLPrinter reader = new BinXMLPrinter(argv[0]);
         reader.run(fis, System.out);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
