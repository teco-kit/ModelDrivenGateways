################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
C_SRCS += \
../src/AccelerationModel_device.c 

OBJS += \
./src/AccelerationModel_device.o 

C_DEPS += \
./src/AccelerationModel_device.d 


# Each subdirectory must supply rules for building sources it contributes
src/%.o: ../src/%.c
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C Compiler'
	gcc -DWITH_MUTEXES -D_GNU_SOURCE -DDPWS_DEVICE -DWITH_NONAMESPACES -DDEBUG -DABORT_ON_ASSERT -I"/home/dpwsteco/workspace/AccelerationService" -I"../../../DPWS/ws4d-gsoap//include" -I"..//usr/local/include/" -I"/include" -I"/home/dpwsteco/workspace/edu.teco.dpws.generator/shared/include" -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


