package com.iot.devices.entity.mongo;

import com.iot.devices.entity.kafka.DeviceCommandMessage;
import com.iot.devices.entity.kafka.DeviceResponseMessage;
import com.iot.devices.enums.DeviceResponseStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeviceResponseTest {

    private static DeviceResponseMessage deviceResponseMessage;

    @BeforeAll
    public static void setUp() {
        deviceResponseMessage = DeviceResponseMessage.builder()
                .timestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))
                .deviceUuid(UUID.randomUUID())
                .commandUuid(UUID.randomUUID())
                .responseStatus(DeviceResponseStatus.ON)
                .build();
    }

    @Test
    @DisplayName("DeviceResponseMessage - fromMessage")
    void fromMessage() {
        DeviceResponse result = DeviceResponse.fromMessage(deviceResponseMessage);

        assertEquals(LocalDateTime.ofEpochSecond(deviceResponseMessage.getTimestamp(), 0, ZoneOffset.UTC),
                result.getDate(), "Result should have same date as deviceResponseMessage");
        assertEquals(deviceResponseMessage.getDeviceUuid(), result.getDeviceUuid(),
                "Result should have same deviceUuid as deviceResponseMessage");
        assertEquals(deviceResponseMessage.getCommandUuid(), result.getCommandUuid(),
                "Result should have same commandUuid as deviceResponseMessage");
        assertEquals(deviceResponseMessage.getResponseStatus(), result.getResponseStatus(),
                "Result should have same command as deviceResponseMessage");
    }
}