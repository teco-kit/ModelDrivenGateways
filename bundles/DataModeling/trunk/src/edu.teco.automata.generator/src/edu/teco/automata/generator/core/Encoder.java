package edu.teco.automata.generator.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import edu.teco.automata.generator.gen.EncoderAutomata;

public class Encoder {
   private BitsIO io;
   private PrintStream out;

   public Encoder(InputStream ins, PrintStream outs) {
      io = new BitsIO(ins);
      out = outs;
   }

   public void run() {
         try {
            EncoderAutomata automata = new EncoderAutomata(io, out);
            automata.run();
         } catch (IOException e) {
            e.printStackTrace();
         }
   }

   public static void main(String argv[]) {
      try {
         FileInputStream fis = new FileInputStream("src-gen/edu/teco/automata/generator/gen/output.bin");

         Encoder reader = new Encoder(fis, System.out);
         reader.run();
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }
   }
}
