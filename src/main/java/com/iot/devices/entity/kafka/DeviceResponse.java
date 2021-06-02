package com.iot.devices.entity.kafka;


import com.iot.devices.enums.DeviceCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceResponse {
    private Long timestamp;
    private UUID deviceUuid;
    private UUID commandUuid;
    private DeviceCommand command;
}
