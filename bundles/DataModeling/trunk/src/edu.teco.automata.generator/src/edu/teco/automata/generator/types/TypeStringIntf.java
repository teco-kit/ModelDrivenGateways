package edu.teco.automata.generator.types;

import java.io.IOException;

import edu.teco.automata.generator.core.BitsIO;

public interface TypeStringIntf {
	public int write(String value, BitsIO io) throws IOException;
	public String read(BitsIO io) throws IOException;
}
