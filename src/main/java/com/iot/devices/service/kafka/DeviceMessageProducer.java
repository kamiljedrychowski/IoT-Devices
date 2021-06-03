package com.iot.devices.service.kafka;

import com.iot.devices.entity.kafka.DeviceCommandMessage;
import com.iot.devices.entity.mongo.DeviceCommand;
import com.iot.devices.repository.mongo.DeviceCommandRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Supplier;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class DeviceMessageProducer {

    @Value("${deviceMessageProducer.queueSize:1000}")
    private Integer queueSize;

    private final DeviceCommandRepository deviceCommandRepository;

    @Bean
    public BlockingQueue<DeviceCommandMessage> deviceCommandQueue() {
        return new ArrayBlockingQueue<>(queueSize);
    }

    @Bean
    public Supplier<DeviceCommandMessage> produceDeviceCommandMessage() {
        return () -> {
            try {
                DeviceCommandMessage deviceCommand = deviceCommandQueue().take();
                deviceCommandRepository.save(DeviceCommand.fromMessage(deviceCommand));
                log.info("Sending device command: {}", deviceCommand);
                return deviceCommand;
            } catch (InterruptedException exception) {
                return null;
            }
        };
    }
}
