package com.iot.devices.enums;

import lombok.Getter;

public enum DeviceCommand {
    TURN_ON(DeviceResponseStatus.ON),
    TURN_OFF(DeviceResponseStatus.OFF),
    ERROR_MESSAGE(DeviceResponseStatus.ERROR);

    DeviceCommand(DeviceResponseStatus status) {
        this.status = status;
    }

    @Getter
    private final DeviceResponseStatus status;

}
