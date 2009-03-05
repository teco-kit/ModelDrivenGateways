package edu.teco.automata.generator.gen;
import java.io.IOException;
import java.io.PrintStream;
import edu.teco.automata.generator.types.TypesString;
import edu.teco.automata.generator.core.BitsIO;

public class EncoderAutomata {
	private BitsIO io;
	private int label = 0;
	private PrintStream out = null;

	public EncoderAutomata(BitsIO io, PrintStream out) {
		this.io = io;
		this.out = out;
	}

	public void run() throws IOException {

		while (true) {
			switch (label) {

				case 0 : {

					out.println("<SensorValues>");

				} /* stateEnd */

				case 1 : {

					if (io.read_bit()) { /* depth 1*/

						out.println("<AcclX>");

					} else {
						label = 5;
						continue;
					} /* AcclX */

				} /* stateEnd */

				case 2 : {

					out.println("<AcclValue>");

					out.println

					(new TypesString.SHORT().read(io));

					out.println("</AcclValue>");

				} /* stateEnd */

				case 3 : {

					out.println("<AcclIdx>");

					out.println

					(new TypesString.BYTE().read(io));

					out.println("</AcclIdx>");

				} /* stateEnd */

				case 4 : {

					out.println("</AcclX>");

					label = 1;
					continue;

				} /* case */

				case 5 : {

					if (io.read_bit()) { /* depth 1*/

						out.println("<AcclY>");

					} else {
						label = 9;
						continue;
					} /* AcclY */

				} /* stateEnd */

				case 6 : {

					out.println("<AcclValue>");

					out.println

					(new TypesString.SHORT().read(io));

					out.println("</AcclValue>");

				} /* stateEnd */

				case 7 : {

					out.println("<AcclIdx>");

					out.println

					(new TypesString.BYTE().read(io));

					out.println("</AcclIdx>");

				} /* stateEnd */

				case 8 : {

					out.println("</AcclY>");

					label = 5;
					continue;

				} /* case */

				case 9 : {

					if (io.read_bit()) { /* depth 1*/

						out.println("<AcclZ>");

					} else {
						label = 13;
						continue;
					} /* AcclZ */

				} /* stateEnd */

				case 10 : {

					out.println("<AcclValue>");

					out.println

					(new TypesString.SHORT().read(io));

					out.println("</AcclValue>");

				} /* stateEnd */

				case 11 : {

					out.println("<AcclIdx>");

					out.println

					(new TypesString.BYTE().read(io));

					out.println("</AcclIdx>");

				} /* stateEnd */

				case 12 : {

					out.println("</AcclZ>");

					label = 9;
					continue;

				} /* case */

				case 13 : {

					/* loop /SensorValues/Audio */
					while (io.read_bit()) { /* depth 1 */

						out.println("<Audio>");

						out.println

						(new TypesString.BYTE().read(io));

						out.println("</Audio>");

					} /* Audio */

				} /* stateEnd */

				case 14 : {

					if (io.read_bit()) { /* depth 1*/

						out.println("<Light>");

					} else {
						label = 18;
						continue;
					} /* Light */

				} /* stateEnd */

				case 15 : {

					out.println("<LightValue>");

					out.println

					(new TypesString.BYTE().read(io));

					out.println("</LightValue>");

				} /* stateEnd */

				case 16 : {

					out.println("<LightIdx>");

					out.println

					(new TypesString.BYTE().read(io));

					out.println("</LightIdx>");

				} /* stateEnd */

				case 17 : {

					out.println("</Light>");

					label = 14;
					continue;

				} /* case */

				case 18 : {

					if (io.read_bit()) { /* depth 1*/

						out.println("<AmbientLight>");

					} else {
						label = 22;
						continue;
					} /* AmbientLight */

				} /* stateEnd */

				case 19 : {

					out.println("<LightValue>");

					out.println

					(new TypesString.BYTE().read(io));

					out.println("</LightValue>");

				} /* stateEnd */

				case 20 : {

					out.println("<LightIdx>");

					out.println

					(new TypesString.BYTE().read(io));

					out.println("</LightIdx>");

				} /* stateEnd */

				case 21 : {

					out.println("</AmbientLight>");

					label = 18;
					continue;

				} /* case */

				case 22 : {

					if (io.read_bit()) { /* depth 1*/

						out.println("<Force>");

					} else {
						label = 26;
						continue;
					} /* Force */

				} /* stateEnd */

				case 23 : {

					out.println("<ForceValue>");

					out.println

					(new TypesString.BYTE().read(io));

					out.println("</ForceValue>");

				} /* stateEnd */

				case 24 : {

					out.println("<ForceIdx>");

					out.println

					(new TypesString.BYTE().read(io));

					out.println("</ForceIdx>");

				} /* stateEnd */

				case 25 : {

					out.println("</Force>");

					label = 22;
					continue;

				} /* case */

				case 26 : {

					if (io.read_bit()) { /* depth 1*/

						out.println("<Temperature>");

					} else {
						label = 30;
						continue;
					} /* Temperature */

				} /* stateEnd */

				case 27 : {

					out.println("<TempValue>");

					out.println

					(new TypesString.BYTE().read(io));

					out.println("</TempValue>");

				} /* stateEnd */

				case 28 : {

					out.println("<TempIdx>");

					out.println

					(new TypesString.BYTE().read(io));

					out.println("</TempIdx>");

				} /* stateEnd */

				case 29 : {

					out.println("</Temperature>");

					label = 26;
					continue;

				} /* case */

				case 30 : {

					/* loop /SensorValues/Voltage */
					while (io.read_bit()) { /* depth 1 */

						out.println("<Voltage>");

						out.println

						(new TypesString.SHORT().read(io));

						out.println("</Voltage>");

					} /* Voltage */

				} /* stateEnd */

				case 31 : {

					out.println("</SensorValues>");

				} /* case */

				default :
					return;

			}
		}
	}
}
