package edu.teco.automata.generator.gen;

import java.io.IOException;
import java.util.Stack;

import edu.teco.automata.generator.types.TypeStringIntf;
import edu.teco.automata.generator.types.TypesString;
import edu.teco.automata.generator.core.BitsIO;

public class SensorConfigurationDecoderAutomata
		extends
			edu.teco.automata.generator.core.DecoderAutomata {
	public SensorConfigurationDecoderAutomata(BitsIO io) {
		super(io);
	}

	private int elemCount = 0;
	private int idx = 1;
	private Stack<IdxMemo> stack = new Stack<IdxMemo>();

	public TypeStringIntf element(String xmlElement) throws IOException {
		while (true) {
			switch (idx) {

				case 1 : // SensorConfiguration
					if (!"sensorconfiguration".equals(xmlElement.toLowerCase())) {
						idx++;

						if (elemCount > 1 || elemCount < 1)
							throw new IOException(
									"Error. Wrong count of element "
											+ "SensorConfiguration" + " upB "
											+ "1" + " lowB " + "1 ");

						elemCount = 0;

					} else {
						elemCount++;

						return new TypesString.COMPLEXTYPE();
					}

				case 2 : // sensors
					if (!"sensors".equals(xmlElement.toLowerCase())) {
						idx++;

						if (elemCount > 1 || elemCount < 1)
							throw new IOException(
									"Error. Wrong count of element "
											+ "sensors" + " upB " + "1"
											+ " lowB " + "1 ");

						elemCount = 0;

					} else {
						elemCount++;

						return new TypesString.COMPLEXTYPE();
					}

				case 3 : // AcclX
					if (!"acclx".equals(xmlElement.toLowerCase())) {
						idx++;

						if ("accly".equals(xmlElement.toLowerCase())) {

							if (elemCount > 1)
								throw new IOException(
										"Error. Wrong count of element "
												+ "AcclX" + " upB " + "1"
												+ " lowB " + "0");

							io.write_false();

							elemCount = 0;
							continue;
						}

						else {

							idx = 4;

							io.write_false();
							elemCount = 0;
							continue;
						}

					} else {
						elemCount++;

						io.write_true();

						return new TypesString.COMPLEXTYPE();
					}

				case 4 : // AcclY
					if (!"accly".equals(xmlElement.toLowerCase())) {
						idx++;

						if ("acclz".equals(xmlElement.toLowerCase())) {

							if (elemCount > 1)
								throw new IOException(
										"Error. Wrong count of element "
												+ "AcclY" + " upB " + "1"
												+ " lowB " + "0");

							io.write_false();

							elemCount = 0;
							continue;
						}

						else {

							idx = 5;

							io.write_false();
							elemCount = 0;
							continue;
						}

					} else {
						elemCount++;

						io.write_true();

						return new TypesString.COMPLEXTYPE();
					}

				case 5 : // AcclZ
					if (!"acclz".equals(xmlElement.toLowerCase())) {
						idx++;

						if ("audio".equals(xmlElement.toLowerCase())) {

							if (elemCount > 1)
								throw new IOException(
										"Error. Wrong count of element "
												+ "AcclZ" + " upB " + "1"
												+ " lowB " + "0");

							io.write_false();

							elemCount = 0;
							continue;
						}

						else {

							idx = 6;

							io.write_false();
							elemCount = 0;
							continue;
						}

					} else {
						elemCount++;

						io.write_true();

						return new TypesString.COMPLEXTYPE();
					}

				case 6 : // Audio
					if (!"audio".equals(xmlElement.toLowerCase())) {
						idx++;

						if ("light".equals(xmlElement.toLowerCase())) {

							if (elemCount > 1)
								throw new IOException(
										"Error. Wrong count of element "
												+ "Audio" + " upB " + "1"
												+ " lowB " + "0");

							io.write_false();

							elemCount = 0;
							continue;
						}

						else {

							idx = 7;

							io.write_false();
							elemCount = 0;
							continue;
						}

					} else {
						elemCount++;

						io.write_true();

						return new TypesString.COMPLEXTYPE();
					}

				case 7 : // Light
					if (!"light".equals(xmlElement.toLowerCase())) {
						idx++;

						if ("ambientlight".equals(xmlElement.toLowerCase())) {

							if (elemCount > 1)
								throw new IOException(
										"Error. Wrong count of element "
												+ "Light" + " upB " + "1"
												+ " lowB " + "0");

							io.write_false();

							elemCount = 0;
							continue;
						}

						else {

							idx = 8;

							io.write_false();
							elemCount = 0;
							continue;
						}

					} else {
						elemCount++;

						io.write_true();

						return new TypesString.COMPLEXTYPE();
					}

				case 8 : // AmbientLight
					if (!"ambientlight".equals(xmlElement.toLowerCase())) {
						idx++;

						if ("force".equals(xmlElement.toLowerCase())) {

							if (elemCount > 1)
								throw new IOException(
										"Error. Wrong count of element "
												+ "AmbientLight" + " upB "
												+ "1" + " lowB " + "0");

							io.write_false();

							elemCount = 0;
							continue;
						}

						else {

							idx = 9;

							io.write_false();
							elemCount = 0;
							continue;
						}

					} else {
						elemCount++;

						io.write_true();

						return new TypesString.COMPLEXTYPE();
					}

				case 9 : // Force
					if (!"force".equals(xmlElement.toLowerCase())) {
						idx++;

						if ("temperature".equals(xmlElement.toLowerCase())) {

							if (elemCount > 1)
								throw new IOException(
										"Error. Wrong count of element "
												+ "Force" + " upB " + "1"
												+ " lowB " + "0");

							io.write_false();

							elemCount = 0;
							continue;
						}

						else {

							idx = 10;

							io.write_false();
							elemCount = 0;
							continue;
						}

					} else {
						elemCount++;

						io.write_true();

						return new TypesString.COMPLEXTYPE();
					}

				case 10 : // Temperature
					if (!"temperature".equals(xmlElement.toLowerCase())) {
						idx++;

						if ("voltage".equals(xmlElement.toLowerCase())) {

							if (elemCount > 1)
								throw new IOException(
										"Error. Wrong count of element "
												+ "Temperature" + " upB " + "1"
												+ " lowB " + "0");

							io.write_false();

							elemCount = 0;
							continue;
						}

						else {

							idx = 11;

							io.write_false();
							elemCount = 0;
							continue;
						}

					} else {
						elemCount++;

						io.write_true();

						return new TypesString.COMPLEXTYPE();
					}

				case 11 : // Voltage
					if (!"voltage".equals(xmlElement.toLowerCase())) {
						idx++;

						if ("stop".equals(xmlElement.toLowerCase())) {

							if (elemCount > 1)
								throw new IOException(
										"Error. Wrong count of element "
												+ "Voltage" + " upB " + "1"
												+ " lowB " + "0");

							io.write_false();

							elemCount = 0;
							continue;
						}

						else {

							idx++;

							io.write_false();
							elemCount = 0;
							continue;
						}

					} else {
						elemCount++;

						io.write_true();

						return new TypesString.COMPLEXTYPE();
					}

				default :
					if (idx < 13)
						idx++;
					else
						idx = 1;
					elemCount = 0;
					if (!xmlElement.toLowerCase().equals("stop"))
						throw new IOException("Unknown id");
					else
						return null;
			}
		}
	}
	private class IdxMemo {
		public int idx;
		public int elemCount;
		public IdxMemo(int idx, int elemCount) {
			this.idx = idx;
			this.elemCount = elemCount;
		}
	}
}
