package com.iot.devices.service.kafka;

import com.iot.devices.entity.kafka.DeviceCommandMessage;
import com.iot.devices.entity.kafka.DeviceResponseMessage;
import com.iot.devices.enums.DeviceResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.function.Function;

@Component
@Slf4j
public class DeviceResponseMockProcessor {
    @Bean
    public Function<KStream<String, DeviceCommandMessage>, KStream<String, DeviceResponseMessage>> processCommand() {
        return input -> input.map((key, value) -> {
            log.debug("MOCK processing device command and creating response received from 'events': {}", value);
            return new KeyValue<>(key, createResponse(value));
        }).filter(((key, value) -> value != null));
    }

    private DeviceResponseMessage createResponse(DeviceCommandMessage command) {
        return DeviceResponseMessage.builder()
                .timestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))
                .deviceUuid(command.getDeviceUuid())
                .commandUuid(command.getCommandUuid())
                .responseStatus(DeviceResponseStatus.ON)
                .build();
    }
}
