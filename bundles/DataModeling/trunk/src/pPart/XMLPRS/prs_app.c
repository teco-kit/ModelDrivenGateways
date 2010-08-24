 
// APPLICATION FILE
// uses ACK, RTC & PRS.
//
#define this(F) F

#case

#opt 6

#define byte	BYTE
#define boolean BOOLEAN
#define true	TRUE
#define false	FALSE
#define global	GLOBAL

#define INC(X) #X
#define ACLAnyDataIsNew ACLDataIsNew

#include "system/acltypes.h"	// the acl types

#include "boards/18f6720.h"
#include "boards/18f6720_R.h"

#include INC(boards/BOARD.h)

#include INC(boards/SENSORBOARD.h)

#include "boards/wdt18fxx20.h"
#include "boards/18f6720.c"

// We use V9 to be more robust !?

#include "boards/wdt18fxx20.c"

#if BOARD_ID_LOW > 229
#include "boards/ballswitch.c"
#include "boards/ds2431.c"		//id chip
#include "boards/AT45DB041.c"	//flash
#include "boards/owmb.c"		//speaker
#else
#include "boards/pci2c.c"
#include "boards/pceeprom.c"	//flash
#endif

#include INC(boards/BOARD.c)



#include "system/awarecon.h"			
#include "system/awarecon.c"		

#ifndef DUMMY_MODE  
#include "boards/SensorI2C.c"		

//+++++ include the sensors your want to use ++++++++++++++++++++++++++++++++++
//#include "piezo.c"
//#include "sensors/tsl250.c"
//#include "sensors\tc74.c"
//#include "sensors/fsr.c"
#include "sensors/SP0101NC1.c"
#include "sensors/adxl210.c"
#include "sensors/adxl210_z.c"
#include "sensors/mcp9800.c"
#include "sensors/tsl2550.c"

#include INC(boards/SENSORBOARD.c)
#else 
#endif


#include "sensors/voltage.c"

#ifndef BOARD_ID_LOW
#error NO BOARD DEFINED
#endif

#fuses hs,noprotect,nobrownout,nolvp,put,nowdt


// other app stuff
//#include "system/ack.c"						// acknowledged transmit/receive
//#include "system/rtc.c"						// acknowledged transmit/receive
//#include "system/rssi.c"
#include "prs74.c" 								// programmable sensors



// this function is called from the fsm at the end of an rf slot
// make sure that it terminates in time
#separate
void SlotEndCallBack()
{
//	RTCUpdate();
//	ACLACKRun();
	//RSSIRun();
	PRSRun();

}


void main()
{

	//TYPICAL STARTUP FLOW
	//*****************************************************************


	
	PCInit();											// is not dangerous, because all pins are set correct . bport is input, i2c and eeprom are initianlized as well
	SSimpInit();

	ACLInit();											// init the stack and start it
	AppSetLEDBehaviour(LEDS_ON_RECEIVE);
	PCLedRedOn();
	delay_ms(100);
	PCLedRedOff();
	enable_interrupts(global);							// must be done before lifesign and


    //*****************************************************************
	//now start your code here


	SSimpSensorsInit();
	SSimpSensorsOn();

	PRSInit();

	for(;;)
	{
	  PRSSendSensorValues();
	}
}



