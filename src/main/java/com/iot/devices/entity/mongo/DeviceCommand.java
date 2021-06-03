package com.iot.devices.entity.mongo;

import com.iot.devices.entity.kafka.DeviceCommandMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Document(collection = "device_command")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceCommand {

    private LocalDateTime date;
    private UUID deviceUuid;
    private UUID commandUuid;
    private com.iot.devices.enums.DeviceCommand deviceCommand;

    public static DeviceCommand fromMessage(DeviceCommandMessage message) {
        return DeviceCommand.builder()
                .date(LocalDateTime.ofEpochSecond(message.getTimestamp(), 0, ZoneOffset.UTC))
                .deviceUuid(message.getDeviceUuid())
                .commandUuid(message.getCommandUuid())
                .deviceCommand(message.getCommand())
                .build();
    }
}
