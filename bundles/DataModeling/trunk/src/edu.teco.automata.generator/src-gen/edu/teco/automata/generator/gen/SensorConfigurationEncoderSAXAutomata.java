package edu.teco.automata.generator.gen;

public class SensorConfigurationEncoderSAXAutomata
		implements
			edu.teco.automata.generator.core.BinXMLReaderAutomata {
	private int label = 0;

	public void run(edu.teco.automata.generator.core.BitsIO in,
			org.xml.sax.ContentHandler out) throws java.io.IOException,
			org.xml.sax.SAXException {

		while (true) {
			switch (label) {

				//push SensorConfiguration: 0

				//push sensors: 1

				//push AcclX: 2

				//pop AcclX: 3 

				//push AcclY: 4

				//pop AcclY: 5 

				//push AcclZ: 6

				//pop AcclZ: 7 

				//push Audio: 8

				//pop Audio: 9 

				//push Light: 10

				//pop Light: 11 

				//push AmbientLight: 12

				//pop AmbientLight: 13 

				//push Force: 14

				//pop Force: 15 

				//push Temperature: 16

				//pop Temperature: 17 

				//push Voltage: 18

				//pop Voltage: 19 

				//pop sensors: 20 

				//pop SensorConfiguration: 21 

				// Start State

				case 0 ://stateComplexBegin(SensorConfiguration)
				{
					label = 1; //assert 1==label+1

					out.startElement("", "SensorConfiguration",
							"SensorConfiguration", null);

					//push SensorConfiguration: next 

					break;
				} /* stateEnd */

				case 1 ://stateComplexBegin(sensors)
				{
					label = 2; //assert 2==label+1

					out.startElement("", "sensors", "sensors", null);

					//push sensors: next 

					break;
				} /* stateEnd */

				case 2 ://stateComplexBegin(AcclX)
				{
					label = 3; //assert 3==label+1

					if (in.read_bit()) { /* depth 2*/

						out.startElement("", "AcclX", "AcclX", null);

						//push AcclX: next 4

					} else {
						label = 4;
						continue;
					} /* AcclX */

					break;
				} /* stateEnd */

				case 3 : //stateComplexEnd(AcclX) : 0..1
				{

					label = 2; /* constLoopEnd1 /SensorConfiguration/sensors/AcclX/ */

					out.endElement("", "AcclX", "AcclX");

					continue; /* constLoopEnd2 /SensorConfiguration/sensors/AcclX/ */

				} /* case */
					//pop AcclX

				case 4 ://stateComplexBegin(AcclY)
				{
					label = 5; //assert 5==label+1

					if (in.read_bit()) { /* depth 2*/

						out.startElement("", "AcclY", "AcclY", null);

						//push AcclY: next 6

					} else {
						label = 6;
						continue;
					} /* AcclY */

					break;
				} /* stateEnd */

				case 5 : //stateComplexEnd(AcclY) : 0..1
				{

					label = 4; /* constLoopEnd1 /SensorConfiguration/sensors/AcclY/ */

					out.endElement("", "AcclY", "AcclY");

					continue; /* constLoopEnd2 /SensorConfiguration/sensors/AcclY/ */

				} /* case */
					//pop AcclY

				case 6 ://stateComplexBegin(AcclZ)
				{
					label = 7; //assert 7==label+1

					if (in.read_bit()) { /* depth 2*/

						out.startElement("", "AcclZ", "AcclZ", null);

						//push AcclZ: next 8

					} else {
						label = 8;
						continue;
					} /* AcclZ */

					break;
				} /* stateEnd */

				case 7 : //stateComplexEnd(AcclZ) : 0..1
				{

					label = 6; /* constLoopEnd1 /SensorConfiguration/sensors/AcclZ/ */

					out.endElement("", "AcclZ", "AcclZ");

					continue; /* constLoopEnd2 /SensorConfiguration/sensors/AcclZ/ */

				} /* case */
					//pop AcclZ

				case 8 ://stateComplexBegin(Audio)
				{
					label = 9; //assert 9==label+1

					if (in.read_bit()) { /* depth 2*/

						out.startElement("", "Audio", "Audio", null);

						//push Audio: next 10

					} else {
						label = 10;
						continue;
					} /* Audio */

					break;
				} /* stateEnd */

				case 9 : //stateComplexEnd(Audio) : 0..1
				{

					label = 8; /* constLoopEnd1 /SensorConfiguration/sensors/Audio/ */

					out.endElement("", "Audio", "Audio");

					continue; /* constLoopEnd2 /SensorConfiguration/sensors/Audio/ */

				} /* case */
					//pop Audio

				case 10 ://stateComplexBegin(Light)
				{
					label = 11; //assert 11==label+1

					if (in.read_bit()) { /* depth 2*/

						out.startElement("", "Light", "Light", null);

						//push Light: next 12

					} else {
						label = 12;
						continue;
					} /* Light */

					break;
				} /* stateEnd */

				case 11 : //stateComplexEnd(Light) : 0..1
				{

					label = 10; /* constLoopEnd1 /SensorConfiguration/sensors/Light/ */

					out.endElement("", "Light", "Light");

					continue; /* constLoopEnd2 /SensorConfiguration/sensors/Light/ */

				} /* case */
					//pop Light

				case 12 ://stateComplexBegin(AmbientLight)
				{
					label = 13; //assert 13==label+1

					if (in.read_bit()) { /* depth 2*/

						out.startElement("", "AmbientLight", "AmbientLight",
								null);

						//push AmbientLight: next 14

					} else {
						label = 14;
						continue;
					} /* AmbientLight */

					break;
				} /* stateEnd */

				case 13 : //stateComplexEnd(AmbientLight) : 0..1
				{

					label = 12; /* constLoopEnd1 /SensorConfiguration/sensors/AmbientLight/ */

					out.endElement("", "AmbientLight", "AmbientLight");

					continue; /* constLoopEnd2 /SensorConfiguration/sensors/AmbientLight/ */

				} /* case */
					//pop AmbientLight

				case 14 ://stateComplexBegin(Force)
				{
					label = 15; //assert 15==label+1

					if (in.read_bit()) { /* depth 2*/

						out.startElement("", "Force", "Force", null);

						//push Force: next 16

					} else {
						label = 16;
						continue;
					} /* Force */

					break;
				} /* stateEnd */

				case 15 : //stateComplexEnd(Force) : 0..1
				{

					label = 14; /* constLoopEnd1 /SensorConfiguration/sensors/Force/ */

					out.endElement("", "Force", "Force");

					continue; /* constLoopEnd2 /SensorConfiguration/sensors/Force/ */

				} /* case */
					//pop Force

				case 16 ://stateComplexBegin(Temperature)
				{
					label = 17; //assert 17==label+1

					if (in.read_bit()) { /* depth 2*/

						out
								.startElement("", "Temperature", "Temperature",
										null);

						//push Temperature: next 18

					} else {
						label = 18;
						continue;
					} /* Temperature */

					break;
				} /* stateEnd */

				case 17 : //stateComplexEnd(Temperature) : 0..1
				{

					label = 16; /* constLoopEnd1 /SensorConfiguration/sensors/Temperature/ */

					out.endElement("", "Temperature", "Temperature");

					continue; /* constLoopEnd2 /SensorConfiguration/sensors/Temperature/ */

				} /* case */
					//pop Temperature

				case 18 ://stateComplexBegin(Voltage)
				{
					label = 19; //assert 19==label+1

					if (in.read_bit()) { /* depth 2*/

						out.startElement("", "Voltage", "Voltage", null);

						//push Voltage: next 20

					} else {
						label = 20;
						continue;
					} /* Voltage */

					break;
				} /* stateEnd */

				case 19 : //stateComplexEnd(Voltage) : 0..1
				{

					label = 18; /* constLoopEnd1 /SensorConfiguration/sensors/Voltage/ */

					out.endElement("", "Voltage", "Voltage");

					continue; /* constLoopEnd2 /SensorConfiguration/sensors/Voltage/ */

				} /* case */
					//pop Voltage

				case 20 : //stateComplexEnd(sensors) : 1..1
				{

					label = 21; // Complex End

					out.endElement("", "sensors", "sensors");

					break;

				} /* case */
					//pop sensors

				case 21 : //stateComplexEnd(SensorConfiguration) : 1..1
				{

					label = 22; // Complex End

					out.endElement("", "SensorConfiguration",
							"SensorConfiguration");

					break;

				} /* case */
					//pop SensorConfiguration

				default : //StopState

					return;

			}
		}
	}
}
