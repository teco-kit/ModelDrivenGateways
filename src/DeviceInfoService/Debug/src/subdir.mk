################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
C_SRCS += \
../src/Conversion.c \
../src/DeviceInfoServer.c \
../src/DeviceInfo_deliver_event.c 

OBJS += \
./src/Conversion.o \
./src/DeviceInfoServer.o \
./src/DeviceInfo_deliver_event.o 

C_DEPS += \
./src/Conversion.d \
./src/DeviceInfoServer.d \
./src/DeviceInfo_deliver_event.d 


# Each subdirectory must supply rules for building sources it contributes
src/%.o: ../src/%.c
	@echo 'Building file: $<'
	@echo 'Invoking: Cygwin C Compiler'
	gcc -I"C:\Users\Administrator\workspace\edu.teco.dpws.generator\shared\include" -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


