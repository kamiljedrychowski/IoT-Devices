package com.iot.devices.service;

import com.iot.devices.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class DeviceUserRepositoryTest {

    @Autowired
    DeviceService deviceService;

    @Test
    @DisplayName("DeviceService - deleteDevice ObjectNotFoundException")
    void deleteDevice() {
        assertThrows(ObjectNotFoundException.class, () -> deviceService.deleteDevice(0L));
    }

}
