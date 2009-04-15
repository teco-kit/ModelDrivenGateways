package edu.teco.automata.generator.core;

import java.io.IOException;


import edu.teco.automata.generator.types.TypeStringIntf;

import edu.teco.automata.generator.core.BitsIO;

public abstract class DecoderAutomata {

	protected BitsIO io;

	public DecoderAutomata(BitsIO io){this.io=io;}
	public abstract TypeStringIntf element(String xmlElement) throws IOException;
}
