package com.iot.devices.entity.mongo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "device_command")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceCommand {
    private Long id;
}
