package com.iot.devices.entity.mongo;

import com.iot.devices.entity.kafka.DeviceCommandMessage;
import com.iot.devices.enums.DeviceCommand;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeviceCommandTest {

    private static DeviceCommandMessage deviceCommandMessage;

    @BeforeAll
    public static void setUp() {
        deviceCommandMessage = DeviceCommandMessage.builder()
                .timestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))
                .deviceUuid(UUID.randomUUID())
                .commandUuid(UUID.randomUUID())
                .command(DeviceCommand.TURN_ON)
                .build();
    }

    @Test
    @DisplayName("DeviceCommandMessage - fromMessage")
    void fromMessage() {
        com.iot.devices.entity.mongo.DeviceCommand result = com.iot.devices.entity.mongo.DeviceCommand.fromMessage(deviceCommandMessage);

        assertEquals(LocalDateTime.ofEpochSecond(deviceCommandMessage.getTimestamp(), 0, ZoneOffset.UTC),
                result.getDate(), "Result should have same date as deviceCommandMessage");
        assertEquals(deviceCommandMessage.getDeviceUuid(), result.getDeviceUuid(),
                "Result should have same deviceUuid as deviceCommandMessage");
        assertEquals(deviceCommandMessage.getCommandUuid(), result.getCommandUuid(),
                "Result should have same commandUuid as deviceCommandMessage");
        assertEquals(deviceCommandMessage.getCommand(), result.getDeviceCommand(),
                "Result should have same command as deviceCommandMessage");
    }
}