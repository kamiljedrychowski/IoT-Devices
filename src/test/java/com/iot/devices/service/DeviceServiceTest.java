package com.iot.devices.service;

import com.iot.devices.dto.DeviceDto;
import com.iot.devices.entity.Device;
import com.iot.devices.enums.DeviceCommand;
import com.iot.devices.enums.DeviceStatus;
import com.iot.devices.enums.DeviceType;
import com.iot.devices.exceptions.ObjectNotFoundException;
import com.iot.devices.exceptions.RequiredDataNotFoundException;
import com.iot.devices.repository.DeviceRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeviceServiceTest {

    @Autowired
    DeviceService deviceService;

    @MockBean
    private DeviceRepository deviceRepository;

    @Test
    @DisplayName("DeviceService - addDevice saving")
    void addDeviceSaving() throws Exception {
        DeviceDto deviceDto = DeviceDto.builder()
                .name("name")
                .status(DeviceStatus.OFF)
                .type(DeviceType.THERMOMETER)
                .ownerLogin("user")
                .build();
        assertEquals(deviceService.addDevice(deviceDto).getName(), deviceDto.getName(),
                "Saved device should have same name as deviceDto");
    }

    @Test
    @DisplayName("DeviceService - prepareCommandMessage")
    void prepareCommandMessage() {
        Device device = Device.builder()
                .uuid(UUID.randomUUID())
                .build();

        DeviceStatus deviceStatus = DeviceStatus.ON;
        assertEquals(deviceService.prepareCommandMessage(device, deviceStatus).getCommand(),
                DeviceCommand.TURN_ON, "Command message should have proper DeviceCommand");
    }

    @Test
    @DisplayName("DeviceService - validateDeviceDto correct")
    void validateDeviceDtoCorrect() {
        DeviceDto deviceDto = DeviceDto.builder()
                .name("qwerty")
                .ownerLogin("owner")
                .type(DeviceType.THERMOMETER)
                .build();

        assertTrue(deviceService.validateDeviceDto(deviceDto), "DeviceDto is correct");
    }

    @Test
    @DisplayName("DeviceService - validateDeviceDto incorrect")
    void validateDeviceDtoIncorrect() {
        DeviceDto deviceDto = DeviceDto.builder()
                .name("qwerty")
                .type(DeviceType.THERMOMETER)
                .build();

        assertFalse(deviceService.validateDeviceDto(deviceDto), "DeviceDto is incorrect");
    }
    @Test
    @DisplayName("DeviceService - addDevice RequiredDataNotFoundException")
    void addDeviceRequiredDataNotFoundException() {
        DeviceDto deviceDto = new DeviceDto();
        assertThrows(RequiredDataNotFoundException.class, () -> deviceService.addDevice(deviceDto));
    }

    @Test
    @DisplayName("DeviceService - addDevice ObjectNotFoundException")
    void addDeviceObjectNotFoundException() {
        DeviceDto deviceDto = DeviceDto.builder()
                .name("name")
                .status(DeviceStatus.OFF)
                .type(DeviceType.THERMOMETER)
                .ownerLogin("_")
                .build();
        assertThrows(ObjectNotFoundException.class, () -> deviceService.addDevice(deviceDto));
    }
}
