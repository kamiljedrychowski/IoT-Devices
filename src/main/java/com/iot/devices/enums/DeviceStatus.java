package com.iot.devices.enums;

import lombok.Getter;

public enum DeviceStatus {
    ON(DeviceCommand.TURN_ON),
    OFF(DeviceCommand.TURN_OFF),
    ERROR(DeviceCommand.ERROR_MESSAGE);

    DeviceStatus(DeviceCommand deviceCommand) {
        this.deviceCommand = deviceCommand;
    }

    @Getter
    private final DeviceCommand deviceCommand;
}
