package com.iot.devices.controller;

import com.iot.devices.entity.mongo.DeviceCommand;
import com.iot.devices.service.DeviceCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("commands")
@RequiredArgsConstructor
public class CommandsController {

    private final DeviceCommandService deviceCommandService;

    @RequestMapping(method = RequestMethod.GET)
    public List<DeviceCommand> getNewestCommands() {
        return deviceCommandService.getNewestCommands();
    }

}
