package com.iot.devices.service.kafka;

import com.iot.devices.entity.kafka.DeviceResponseMessage;
import com.iot.devices.entity.mongo.DeviceResponse;
import com.iot.devices.repository.mongo.DeviceResponseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeviceResponseConsumer {

    private final DeviceResponseRepository deviceResponseRepository;

    @Bean
    public Consumer<DeviceResponseMessage> consumeDeviceResponse() {
        return value -> {
            log.info("Processing device response: {}", value);
            deviceResponseRepository.save(DeviceResponse.fromMessage(value));
        };
    }
}
