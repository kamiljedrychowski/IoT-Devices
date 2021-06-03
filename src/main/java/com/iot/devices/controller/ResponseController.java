package com.iot.devices.controller;

import com.iot.devices.entity.mongo.DeviceResponse;
import com.iot.devices.service.DeviceResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("response")
@RequiredArgsConstructor
public class ResponseController {

    private final DeviceResponseService deviceResponseService;

    @RequestMapping(method = RequestMethod.GET)
    public List<DeviceResponse> getNewestCommands() {
        return deviceResponseService.getNewestResponse();
    }
}
