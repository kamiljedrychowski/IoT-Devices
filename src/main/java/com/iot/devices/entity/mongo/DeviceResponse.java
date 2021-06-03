package com.iot.devices.entity.mongo;

import com.iot.devices.entity.kafka.DeviceCommandMessage;
import com.iot.devices.entity.kafka.DeviceResponseMessage;
import com.iot.devices.enums.DeviceResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Document(collection = "device_response")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceResponse {

    private LocalDateTime date;
    private UUID deviceUuid;
    private UUID commandUuid;
    private DeviceResponseStatus responseStatus;

    public static DeviceResponse fromMessage(DeviceResponseMessage message) {
        return DeviceResponse.builder()
                .date(LocalDateTime.ofEpochSecond(message.getTimestamp(), 0, ZoneOffset.UTC))
                .deviceUuid(message.getDeviceUuid())
                .commandUuid(message.getCommandUuid())
                .responseStatus(message.getResponseStatus())
                .build();
    }
}
