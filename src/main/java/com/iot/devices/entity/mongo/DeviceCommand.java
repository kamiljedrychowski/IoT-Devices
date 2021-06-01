package com.iot.devices.entity.mongo;

import com.iot.devices.enums.DeviceResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
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
    private DeviceResponseStatus responseStatus;
}
