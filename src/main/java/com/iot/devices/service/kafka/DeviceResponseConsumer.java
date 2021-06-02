package com.iot.devices.service.kafka;

import com.iot.devices.entity.kafka.DeviceResponse;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

public class DeviceResponseConsumer {

    @Bean
    public Consumer<DeviceResponse> consumeDeviceResponse() {
        return value -> {

        };
    }
}
