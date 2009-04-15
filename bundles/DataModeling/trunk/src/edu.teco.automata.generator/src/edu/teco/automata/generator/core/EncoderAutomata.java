package edu.teco.automata.generator.core;

import java.io.IOException;
import java.io.OutputStream;

public abstract class EncoderAutomata {

	protected BitsIO in; 
	protected OutputStream out;

	public EncoderAutomata(BitsIO in, OutputStream out){this.in=in;this.out=out;}
	
	public abstract void run() throws IOException ;
}
