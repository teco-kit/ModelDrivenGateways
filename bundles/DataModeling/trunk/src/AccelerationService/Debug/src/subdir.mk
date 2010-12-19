################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
C_SRCS += \
../src/AccelerationServer.c \
../src/Acceleration_deliver_event.c \
../src/Conversion.c 

OBJS += \
./src/AccelerationServer.o \
./src/Acceleration_deliver_event.o \
./src/Conversion.o 

C_DEPS += \
./src/AccelerationServer.d \
./src/Acceleration_deliver_event.d \
./src/Conversion.d 


# Each subdirectory must supply rules for building sources it contributes
src/%.o: ../src/%.c
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C Compiler'
	gcc -I"/home/dpwsteco/workspace/edu.teco.dpws.generator/shared/include" -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


