#!/bin/bash

DECODER=../dec/decoder
ENCODER=../enc/encoder
PRS74=./test

$PRS74 
$ENCODER > sensor.tmp.xml
mv output.bin output.bin.org
$DECODER sensor.tmp.xml
diff output.bin output.bin.org
if [ $? = 0 ]
then
   echo Passed 1
else
   echo Failed 1
fi
$ENCODER > sensor2.tmp.xml
diff sensor.tmp.xml sensor2.tmp.xml
if [ $? = 0 ]
then
   echo Passed 2
else
   echo Failed 2
fi

