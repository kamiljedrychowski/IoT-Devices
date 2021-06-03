package com.iot.devices.enums;

import lombok.Getter;

import java.util.EnumSet;

public enum UserRole {
    USER,
    MANAGER,
    ADMIN;

    @Getter
    private final static EnumSet<UserRole> privilegedRoles = EnumSet.of(MANAGER, ADMIN);
}
