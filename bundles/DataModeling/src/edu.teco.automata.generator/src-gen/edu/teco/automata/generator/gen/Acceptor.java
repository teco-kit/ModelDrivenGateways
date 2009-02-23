package edu.teco.automata.generator.gen;
import java.io.IOException;
import edu.teco.automata.generator.core.BitsIO;
import edu.teco.automata.generator.types.TypesBin;

public class Acceptor {
	private BitsIO io;
	private int label;

	public Acceptor(BitsIO io) {
		this.io = io;
	}

	public void run() throws IOException {

		// initNeeded SensorValues

		// initNeeded AcclX

		// initNeeded AcclValue

		// setNeeded /SensorValues/AcclX/AcclValue

		// initNeeded AcclIdx

		// setNeeded /SensorValues/AcclX/AcclIdx

		// initNeeded AcclY

		// stIterator AcclY
		// setNeeded2 AcclX true

		// stIterator AcclY
		// setNeeded2 SensorValues true

		// initNeeded AcclValue

		// setNeeded /AcclY/AcclValue

		// initNeeded AcclIdx

		// setNeeded /AcclY/AcclIdx

		// initNeeded AcclZ

		// stIterator AcclZ
		// setNeeded2 AcclY true

		// initNeeded AcclValue

		// setNeeded /AcclZ/AcclValue

		// initNeeded AcclIdx

		// setNeeded /AcclZ/AcclIdx

		// initNeeded Audio

		// stIterator Audio
		// setNeeded2 AcclZ true

		// setNeeded Audio

		// initNeeded Light

		// initNeeded LightValue

		// setNeeded /Light/LightValue

		// initNeeded LightIdx

		// setNeeded /Light/LightIdx

		// initNeeded AmbientLight

		// stIterator AmbientLight
		// setNeeded2 Light true

		// initNeeded LightValue

		// setNeeded /AmbientLight/LightValue

		// initNeeded LightIdx

		// setNeeded /AmbientLight/LightIdx

		// initNeeded Force

		// stIterator Force
		// setNeeded2 AmbientLight true

		// initNeeded ForceValue

		// setNeeded /Force/ForceValue

		// initNeeded ForceIdx

		// setNeeded /Force/ForceIdx

		// initNeeded Temperature

		// stIterator Temperature
		// setNeeded2 Force true

		// initNeeded TempValue

		// setNeeded /Temperature/TempValue

		// initNeeded TempIdx

		// setNeeded /Temperature/TempIdx

		// initNeeded Voltage

		// stIterator Voltage
		// setNeeded2 Temperature true

		// setNeeded Voltage

		while (true) {
			switch (label) {

				case 0 : {

				} /* stateEnd */

				case 1 : {

					if (io.read_bit()) { /* depth 1*/

					} else {
						label = 6;
						continue;
					} /* AcclX */

				} /* stateEnd */

				case 2 : {

					// stateType /SensorValues/AcclX/AcclValue

				} /* stateEnd */

				case 3 : {

					// stateType /SensorValues/AcclX/AcclIdx

				} /* stateEnd */

					//statePapaEnd AcclX

				case 4 : {

					label = 1;
					continue;

				} /* case */

				case 5 : {

					if (io.read_bit()) { /* depth 1*/

					} else {
						label = 10;
						continue;
					} /* AcclY */

				} /* stateEnd */

				case 6 : {

					// stateType /SensorValues/AcclY/AcclValue

				} /* stateEnd */

				case 7 : {

					// stateType /SensorValues/AcclY/AcclIdx

				} /* stateEnd */

					//statePapaEnd AcclY

				case 8 : {

					label = 5;
					continue;

				} /* case */

				case 9 : {

					if (io.read_bit()) { /* depth 1*/

					} else {
						label = 14;
						continue;
					} /* AcclZ */

				} /* stateEnd */

				case 10 : {

					// stateType /SensorValues/AcclZ/AcclValue

				} /* stateEnd */

				case 11 : {

					// stateType /SensorValues/AcclZ/AcclIdx

				} /* stateEnd */

					//statePapaEnd AcclZ

				case 12 : {

					label = 9;
					continue;

				} /* case */

				case 13 : {

					// loop /SensorValues/Audio

					/* loop /SensorValues/Audio */
					while (io.read_bit()) { /* depth 1 */

						// stateType /SensorValues/Audio

						// loopEnd Audio

					} /* Audio */

				} /* stateEnd */

				case 14 : {

					if (io.read_bit()) { /* depth 1*/

					} else {
						label = 19;
						continue;
					} /* Light */

				} /* stateEnd */

				case 15 : {

					// stateType /SensorValues/Light/LightValue

				} /* stateEnd */

				case 16 : {

					// stateType /SensorValues/Light/LightIdx

				} /* stateEnd */

					//statePapaEnd Light

				case 17 : {

					label = 14;
					continue;

				} /* case */

				case 18 : {

					if (io.read_bit()) { /* depth 1*/

					} else {
						label = 23;
						continue;
					} /* AmbientLight */

				} /* stateEnd */

				case 19 : {

					// stateType /SensorValues/AmbientLight/LightValue

				} /* stateEnd */

				case 20 : {

					// stateType /SensorValues/AmbientLight/LightIdx

				} /* stateEnd */

					//statePapaEnd AmbientLight

				case 21 : {

					label = 18;
					continue;

				} /* case */

				case 22 : {

					if (io.read_bit()) { /* depth 1*/

					} else {
						label = 27;
						continue;
					} /* Force */

				} /* stateEnd */

				case 23 : {

					// stateType /SensorValues/Force/ForceValue

				} /* stateEnd */

				case 24 : {

					// stateType /SensorValues/Force/ForceIdx

				} /* stateEnd */

					//statePapaEnd Force

				case 25 : {

					label = 22;
					continue;

				} /* case */

				case 26 : {

					if (io.read_bit()) { /* depth 1*/

					} else {
						label = 31;
						continue;
					} /* Temperature */

				} /* stateEnd */

				case 27 : {

					// stateType /SensorValues/Temperature/TempValue

				} /* stateEnd */

				case 28 : {

					// stateType /SensorValues/Temperature/TempIdx

				} /* stateEnd */

					//statePapaEnd Temperature

				case 29 : {

					label = 26;
					continue;

				} /* case */

				case 30 : {

					// loop /SensorValues/Voltage

					/* loop /SensorValues/Voltage */
					while (io.read_bit()) { /* depth 1 */

						// stateType /SensorValues/Voltage

						// loopEnd Voltage

					} /* Voltage */

				} /* stateEnd */

					//statePapaEnd SensorValues

				case 31 : {

				} /* case */

				default :
					return;

			}
		}
	}
}
