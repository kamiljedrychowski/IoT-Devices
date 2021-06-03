package com.iot.devices.dto;

import com.iot.devices.entity.Device;
import com.iot.devices.entity.User;
import com.iot.devices.enums.DeviceStatus;
import com.iot.devices.enums.DeviceType;
import com.iot.devices.enums.UserRole;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DeviceDtoTest {

    private static User user;
    private static Device device;

    @BeforeAll
    public static void setUp() {
        user = User.builder()
                .name("Test name")
                .surname("Test surname")
                .login("login")
                .password("password")
                .role(UserRole.USER).build();

        device = Device.builder()
                .uuid(UUID.randomUUID())
                .name("Test device")
                .type(DeviceType.AIR_CONDITIONING)
                .status(DeviceStatus.ON)
                .owner(user)
                .build();
        device.setId(-1L);

    }

    @Test()
    @DisplayName("DeviceDto - fromEntity")
    void fromDeviceToDeviceDto() {
        DeviceDto result = DeviceDto.fromEntity(device);
        assertEquals(device.getId(), result.getId(), "Result dto should have same id as device");
        assertEquals(device.getUuid(), result.getUuid(), "Result dto should have same uuid as device");
        assertEquals(device.getName(), result.getName(), "Result dto should have same name as device");
        assertEquals(device.getType(), result.getType(), "Result dto should have same type as device");
        assertEquals(device.getStatus(), result.getStatus(), "Result dto should have same status as device");
        assertEquals(device.getOwner().getLogin(), result.getOwnerLogin(),
                "Result dto should have same owner login as device");

    }
}