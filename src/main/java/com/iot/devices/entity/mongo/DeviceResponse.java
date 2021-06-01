package com.iot.devices.entity.mongo;

import com.iot.devices.enums.DeviceCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "device_response")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceResponse {

    private LocalDateTime timestamp;
    private UUID deviceUuid;
    private UUID commandUuid;
    private DeviceCommand command;
}
