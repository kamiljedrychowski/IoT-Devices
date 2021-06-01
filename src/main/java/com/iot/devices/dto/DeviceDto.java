package com.iot.devices.dto;

import com.iot.devices.entity.User;
import com.iot.devices.enums.DeviceState;
import com.iot.devices.enums.DeviceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceDto {

    private Long id;
    private UUID uuid;
    private String name;
    private DeviceType type;
    private DeviceState status;
    private User owner;
}
