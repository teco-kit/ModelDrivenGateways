sendport = 5555 ;
recvport = 5556 ;
particle = 1;
xvalue = 0  ;
yvalue = 0  ;
zvalue = 0  ;
root = "";
room1 = "";
room2 = "";
room3 = "";
room4 = "";
 function putxvalue()
{
	document.usbconfig.sendport.value=sendport;
	document.usbconfig.recvport.value=recvport;
	document.usbconfig.xvalue.value= xvalue;
	document.usbconfig.yvalue.value=yvalue;
	document.usbconfig.zvalue.value=zvalue;
	document.usbconfig.particle_type.value=particle;
	document.usbconfig.root.value= root;
	document.usbconfig.room1.value=room1;
	document.usbconfig.room2.value=room2;
	document.usbconfig.room3.value=room3;
	document.usbconfig.room4.value=room4;
} 