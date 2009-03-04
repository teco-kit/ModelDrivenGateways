

// headerfile for application

#case


// to reduce compiler optimization. necessary for ccs 202. don't have this comment in the same line as #opt!
#opt 0

#define byte	BYTE
#define boolean BOOLEAN
#define true	TRUE
#define false	FALSE
#define global	GLOBAL


#include "system\acltypes.h"			// the acl types
#include "boards\18f6720.h"
#include "boards\18f6720_R.h"
#include "boards\pc232.h"
#include "boards\ssimp202.h"
#include "boards\wdt18fxx20.h"


#include "boards\18f6720.c"
//#include "boards\pci2c.c"

//#include "boards\pceeprom.c"
#include "boards\AT45DB041.c"
#include "boards\sensori2c.c"
#include "boards\owmb.c"
#include "boards\ds2431.c"
#include "boards\ballswitch.c"
#include "boards\pc232.c"
#include "boards\ssimp202.c"

#include "system\rssi.h"
//#include "system\sdjs.h"
#include "system\awarecon#092.c"			// stack
#include "boards\wdt18fxx20.c"

#include "system\flash_otap.c"
#include "system\rtc.c"					// RTC


//+++++ include the sensors your want to use ++++++++++++++++++++++++++++++++++
//#include "piezo.c"
#include "sensors\tsl250.c"
#include "sensors\fsr.c"
#include "sensors\SP0101NC1.c"
#include "sensors\adxl210.c"
#include "sensors\adxl210_z.c"
//#include "sensors\tc74.c"
#include "sensors\mcp9800.c"
#include "sensors\tsl2550.c"
#include "sensors\voltage.c"

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//#include "senscore.c"			// this is essential for all sensorboard stuff

// other app stuff
#include "system\ack.c"						// acknowledged transmit/receive
#include "system\rssi.c"
//#include "system\sdjs.c"
#include "prs74.c" 								// programmable sensors


// test codes
//#include "test\otaptest.c"


#fuses hs,noprotect,nobrownout,nolvp,put,nowdt



// change the a/d port for the application if you want
//#define PIC_ADCON0_USER_VALUE 0b01010101
//#define PIC_ADCON1_USER_VALUE 0b10101010