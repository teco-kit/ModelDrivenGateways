#!/bin/bash

TESTDIR=$(pwd)/test_1_2
DEVICE_DIR=$TESTDIR/device
CLIENT_DIR=$TESTDIR/client

MSIOP_DIR=../../../../samples/ms-iop
MSIOP_DEVICE=$MSIOP_DIR/iop_device

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
$RESOLVE -h 139.30.201.146 -d $DEVICE_ID &> ./output1
TEST_RESULT=$?

if [ $TEST_RESULT -eq 0 ]; then
	HOSTING_ADDRESS=$(cat ./output1)
	$DESCRIBE -h 139.30.201.200 -d $HOSTING_ADDRESS -o dmr &> ./output2
	TEST_RESULT=$?
	
	if [ $TEST_RESULT -eq 0 ]; then
		cat ./output2
	fi 
fi

kill -SIGINT $MSIOP_DEVICE_PID

exit $TEST_RESULT