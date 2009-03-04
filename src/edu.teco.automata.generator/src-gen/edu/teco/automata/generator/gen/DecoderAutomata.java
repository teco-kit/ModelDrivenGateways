package edu.teco.automata.generator.gen;

import java.io.IOException;
import java.util.Stack;

import edu.teco.automata.generator.types.TypeStringIntf;
import edu.teco.automata.generator.types.TypesString;
import edu.teco.automata.generator.core.BitsIO;

public class DecoderAutomata {
	private int elemCount = 0;
	private int idx = 1;
	private BitsIO io;
	private Stack<IdxMemo> stack = null;

	public DecoderAutomata(BitsIO io) {
		this.io = io;
		stack = new Stack<IdxMemo>();
	}

	public TypeStringIntf element(String xmlElement) throws IOException {
		while (true) {
			switch (idx) {

				case 1 : // SensorValues
					if ("SensorValues" != xmlElement) {
						idx++;

						if (elemCount > 1 || elemCount < 1)
							System.err.println("Error. Wrong count of element "
									+ "SensorValues" + " upB " + "1" + " lowB "
									+ "1 ");

						elemCount = 0;

					} else {
						elemCount++;

						return new TypesString.COMPLEXTYPE();
					}

				case 2 : // AcclX
					if ("AcclX" != xmlElement) {
						idx++;

						if ("AcclValue" == xmlElement) {

							if (elemCount > 1)
								System.err
										.println("Error. Wrong count of element "
												+ "AcclX"
												+ " upB "
												+ "1"
												+ " lowB " + "0");

							stack.push(new IdxMemo(2, elemCount));
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

				case 3 : // AcclValue
					if ("AcclValue" != xmlElement) {
						idx++;

						if (elemCount > 1 || elemCount < 1)
							System.err.println("Error. Wrong count of element "
									+ "AcclValue" + " upB " + "1" + " lowB "
									+ "1 ");

						elemCount = 0;

					} else {
						elemCount++;

						return new TypesString.SHORT();
					}

				case 4 : // AcclIdx
					if ("AcclIdx" != xmlElement) {
						idx++;

						if (elemCount > 1 || elemCount < 1)
							System.err.println("Error. Wrong count of element "
									+ "AcclIdx" + " upB " + "1" + " lowB "
									+ "1 ");

						elemCount = 0;

						if (!stack.isEmpty()) {
							IdxMemo memo = stack.pop();
							idx = memo.idx;
							elemCount = memo.elemCount;
							continue;
						}

					} else {
						elemCount++;

						return new TypesString.BYTE();
					}

				case 5 : // AcclY
					if ("AcclY" != xmlElement) {
						idx++;

						if ("AcclValue" == xmlElement) {

							if (elemCount > 1)
								System.err
										.println("Error. Wrong count of element "
												+ "AcclY"
												+ " upB "
												+ "1"
												+ " lowB " + "0");

							stack.push(new IdxMemo(5, elemCount));
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

				case 6 : // AcclValue
					if ("AcclValue" != xmlElement) {
						idx++;

						if (elemCount > 1 || elemCount < 1)
							System.err.println("Error. Wrong count of element "
									+ "AcclValue" + " upB " + "1" + " lowB "
									+ "1 ");

						elemCount = 0;

					} else {
						elemCount++;

						return new TypesString.SHORT();
					}

				case 7 : // AcclIdx
					if ("AcclIdx" != xmlElement) {
						idx++;

						if (elemCount > 1 || elemCount < 1)
							System.err.println("Error. Wrong count of element "
									+ "AcclIdx" + " upB " + "1" + " lowB "
									+ "1 ");

						elemCount = 0;

						if (!stack.isEmpty()) {
							IdxMemo memo = stack.pop();
							idx = memo.idx;
							elemCount = memo.elemCount;
							continue;
						}

					} else {
						elemCount++;

						return new TypesString.BYTE();
					}

				case 8 : // AcclZ
					if ("AcclZ" != xmlElement) {
						idx++;

						if ("AcclValue" == xmlElement) {

							if (elemCount > 1)
								System.err
										.println("Error. Wrong count of element "
												+ "AcclZ"
												+ " upB "
												+ "1"
												+ " lowB " + "0");

							stack.push(new IdxMemo(8, elemCount));
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

				case 9 : // AcclValue
					if ("AcclValue" != xmlElement) {
						idx++;

						if (elemCount > 1 || elemCount < 1)
							System.err.println("Error. Wrong count of element "
									+ "AcclValue" + " upB " + "1" + " lowB "
									+ "1 ");

						elemCount = 0;

					} else {
						elemCount++;

						return new TypesString.SHORT();
					}

				case 10 : // AcclIdx
					if ("AcclIdx" != xmlElement) {
						idx++;

						if (elemCount > 1 || elemCount < 1)
							System.err.println("Error. Wrong count of element "
									+ "AcclIdx" + " upB " + "1" + " lowB "
									+ "1 ");

						elemCount = 0;

						if (!stack.isEmpty()) {
							IdxMemo memo = stack.pop();
							idx = memo.idx;
							elemCount = memo.elemCount;
							continue;
						}

					} else {
						elemCount++;

						return new TypesString.BYTE();
					}

				case 11 : // Audio
					if ("Audio" != xmlElement) {
						idx++;

						elemCount = 0;

						io.write_false();

					} else {
						elemCount++;

						io.write_true();

						return new TypesString.BYTE();
					}

				case 12 : // Light
					if ("Light" != xmlElement) {
						idx++;

						if ("LightValue" == xmlElement) {

							if (elemCount > 1)
								System.err
										.println("Error. Wrong count of element "
												+ "Light"
												+ " upB "
												+ "1"
												+ " lowB " + "0");

							stack.push(new IdxMemo(12, elemCount));
							elemCount = 0;
							continue;
						}

						else {

							idx = 15;

							io.write_false();
							elemCount = 0;
							continue;
						}

					} else {
						elemCount++;

						io.write_true();

						return new TypesString.COMPLEXTYPE();
					}

				case 13 : // LightValue
					if ("LightValue" != xmlElement) {
						idx++;

						if (elemCount > 1 || elemCount < 1)
							System.err.println("Error. Wrong count of element "
									+ "LightValue" + " upB " + "1" + " lowB "
									+ "1 ");

						elemCount = 0;

					} else {
						elemCount++;

						return new TypesString.BYTE();
					}

				case 14 : // LightIdx
					if ("LightIdx" != xmlElement) {
						idx++;

						if (elemCount > 1 || elemCount < 1)
							System.err.println("Error. Wrong count of element "
									+ "LightIdx" + " upB " + "1" + " lowB "
									+ "1 ");

						elemCount = 0;

						if (!stack.isEmpty()) {
							IdxMemo memo = stack.pop();
							idx = memo.idx;
							elemCount = memo.elemCount;
							continue;
						}

					} else {
						elemCount++;

						return new TypesString.BYTE();
					}

				case 15 : // AmbientLight
					if ("AmbientLight" != xmlElement) {
						idx++;

						if ("LightValue" == xmlElement) {

							if (elemCount > 1)
								System.err
										.println("Error. Wrong count of element "
												+ "AmbientLight"
												+ " upB "
												+ "1" + " lowB " + "0");

							stack.push(new IdxMemo(15, elemCount));
							elemCount = 0;
							continue;
						}

						else {

							idx = 18;

							io.write_false();
							elemCount = 0;
							continue;
						}

					} else {
						elemCount++;

						io.write_true();

						return new TypesString.COMPLEXTYPE();
					}

				case 16 : // LightValue
					if ("LightValue" != xmlElement) {
						idx++;

						if (elemCount > 1 || elemCount < 1)
							System.err.println("Error. Wrong count of element "
									+ "LightValue" + " upB " + "1" + " lowB "
									+ "1 ");

						elemCount = 0;

					} else {
						elemCount++;

						return new TypesString.BYTE();
					}

				case 17 : // LightIdx
					if ("LightIdx" != xmlElement) {
						idx++;

						if (elemCount > 1 || elemCount < 1)
							System.err.println("Error. Wrong count of element "
									+ "LightIdx" + " upB " + "1" + " lowB "
									+ "1 ");

						elemCount = 0;

						if (!stack.isEmpty()) {
							IdxMemo memo = stack.pop();
							idx = memo.idx;
							elemCount = memo.elemCount;
							continue;
						}

					} else {
						elemCount++;

						return new TypesString.BYTE();
					}

				case 18 : // Force
					if ("Force" != xmlElement) {
						idx++;

						if ("ForceValue" == xmlElement) {

							if (elemCount > 1)
								System.err
										.println("Error. Wrong count of element "
												+ "Force"
												+ " upB "
												+ "1"
												+ " lowB " + "0");

							stack.push(new IdxMemo(18, elemCount));
							elemCount = 0;
							continue;
						}

						else {

							idx = 21;

							io.write_false();
							elemCount = 0;
							continue;
						}

					} else {
						elemCount++;

						io.write_true();

						return new TypesString.COMPLEXTYPE();
					}

				case 19 : // ForceValue
					if ("ForceValue" != xmlElement) {
						idx++;

						if (elemCount > 1 || elemCount < 1)
							System.err.println("Error. Wrong count of element "
									+ "ForceValue" + " upB " + "1" + " lowB "
									+ "1 ");

						elemCount = 0;

					} else {
						elemCount++;

						return new TypesString.BYTE();
					}

				case 20 : // ForceIdx
					if ("ForceIdx" != xmlElement) {
						idx++;

						if (elemCount > 1 || elemCount < 1)
							System.err.println("Error. Wrong count of element "
									+ "ForceIdx" + " upB " + "1" + " lowB "
									+ "1 ");

						elemCount = 0;

						if (!stack.isEmpty()) {
							IdxMemo memo = stack.pop();
							idx = memo.idx;
							elemCount = memo.elemCount;
							continue;
						}

					} else {
						elemCount++;

						return new TypesString.BYTE();
					}

				case 21 : // Temperature
					if ("Temperature" != xmlElement) {
						idx++;

						if ("TempValue" == xmlElement) {

							if (elemCount > 1)
								System.err
										.println("Error. Wrong count of element "
												+ "Temperature"
												+ " upB "
												+ "1"
												+ " lowB " + "0");

							stack.push(new IdxMemo(21, elemCount));
							elemCount = 0;
							continue;
						}

						else {

							idx = 24;

							io.write_false();
							elemCount = 0;
							continue;
						}

					} else {
						elemCount++;

						io.write_true();

						return new TypesString.COMPLEXTYPE();
					}

				case 22 : // TempValue
					if ("TempValue" != xmlElement) {
						idx++;

						if (elemCount > 1 || elemCount < 1)
							System.err.println("Error. Wrong count of element "
									+ "TempValue" + " upB " + "1" + " lowB "
									+ "1 ");

						elemCount = 0;

					} else {
						elemCount++;

						return new TypesString.BYTE();
					}

				case 23 : // TempIdx
					if ("TempIdx" != xmlElement) {
						idx++;

						if (elemCount > 1 || elemCount < 1)
							System.err.println("Error. Wrong count of element "
									+ "TempIdx" + " upB " + "1" + " lowB "
									+ "1 ");

						elemCount = 0;

						if (!stack.isEmpty()) {
							IdxMemo memo = stack.pop();
							idx = memo.idx;
							elemCount = memo.elemCount;
							continue;
						}

					} else {
						elemCount++;

						return new TypesString.BYTE();
					}

				case 24 : // Voltage
					if ("Voltage" != xmlElement) {
						idx++;

						elemCount = 0;

						io.write_false();

						if (!stack.isEmpty()) {
							IdxMemo memo = stack.pop();
							idx = memo.idx;
							elemCount = memo.elemCount;
							continue;
						}

					} else {
						elemCount++;

						io.write_true();

						return new TypesString.SHORT();
					}

				default :
					if (idx < 26)
						idx++;
					else
						idx = 1;
					elemCount = 0;
					System.err.println("Unknown id");
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
