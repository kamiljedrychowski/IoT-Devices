package com.iot.devices.dto;

import com.iot.devices.entity.Device;
import com.iot.devices.enums.DeviceStatus;
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
    private DeviceStatus status;
    private String ownerLogin;

    public static DeviceDto fromEntity(Device entity) {
        return DeviceDto.builder()
                .id(entity.getId())
                .uuid(entity.getUuid())
                .name(entity.getName())
                .type(entity.getType())
                .status(entity.getStatus())
                .ownerLogin(entity.getOwner().getLogin())
                .build();
    }
}
