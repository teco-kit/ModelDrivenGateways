#!/bin/bash

TESTDIR=$(pwd)/test_1_3
DEVICE_DIR=$TESTDIR/device
CLIENT_DIR=$TESTDIR/client

MSIOP_DIR=../../../../samples/ms-iop
MSIOP_DEVICE=$MSIOP_DIR/iop_device

PROBE=../../../../bin/probe
RESOLVE=../../../../bin/resolve
DESCRIBE=../../../../bin/describe

DEVICE_ID="urn:uuid:$(uuidgen)"

if [ -d $TESTDIR ]; then
	rm -rf $TESTDIR
fi 

mkdir $TESTDIR
mkdir $DEVICE_DIR
mkdir $CLIENT_DIR

cd $DEVICE_DIR
echo "Testing Device \"$DEVICE_ID\""

$MSIOP_DEVICE -h 139.30.201.146 -u $DEVICE_ID &> ./output1 &
MSIOP_DEVICE_PID=$!

sleep 2

cd $CLIENT_DIR
$PROBE -h 139.30.201.146 &> ./output1
TEST_RESULT=$?

if [ $TEST_RESULT -eq 0 ]; then
	TEST_RESULT=1
	for found_device in $(cat ./output1); do
		if [ "$found_device" = "$DEVICE_ID" ]; then
			echo "Device $found_device found by probe!" > ./output2
			TEST_RESULT=0
		fi
	done
fi 

if [ $TEST_RESULT -eq 0 ]; then
	$RESOLVE -h 139.30.201.146 -d $DEVICE_ID &> ./output3
	TEST_RESULT=$?
fi	

if [ $TEST_RESULT -eq 0 ]; then
	HOSTING_ADDRESS=$(cat ./output3)
	$DESCRIBE -h 139.30.201.146 -d $HOSTING_ADDRESS -o dmr &> ./output4
	TEST_RESULT=$?
	
	if [ $TEST_RESULT -eq 0 ]; then
		cat ./output4
	fi 
fi 

kill -SIGINT $MSIOP_DEVICE_PID

exit $TEST_RESULT