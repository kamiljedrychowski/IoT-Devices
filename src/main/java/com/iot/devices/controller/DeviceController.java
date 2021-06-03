package com.iot.devices.controller;

import com.iot.devices.dto.DeviceDto;
import com.iot.devices.enums.DeviceStatus;
import com.iot.devices.exceptions.ObjectNotFoundException;
import com.iot.devices.exceptions.UnauthorizedAccessException;
import com.iot.devices.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("device")
public class DeviceController {

    private final DeviceService deviceService;

    @RequestMapping(method = RequestMethod.GET)
    public List<DeviceDto> getAllDevices(HttpServletRequest requestHeader) throws UnauthorizedAccessException {

        return deviceService.getAllDevices(requestHeader);
    }

    @RequestMapping(value = "/status/{deviceId}/{deviceStatus}", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> changeDeviceStatus(@PathVariable Long deviceId,
        @PathVariable DeviceStatus deviceStatus) throws ObjectNotFoundException {

        deviceService.changeDeviceStatus(deviceId, deviceStatus);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> addNewDevice(@RequestBody DeviceDto deviceDto) throws Exception {
        deviceService.addDevice(deviceDto);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteDevice(@PathVariable Long id) throws Exception {
        deviceService.deleteDevice(id);
        return ResponseEntity.ok().build();
    }

}
