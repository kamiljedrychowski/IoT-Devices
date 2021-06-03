package com.iot.devices.controller;

import com.iot.devices.entity.kafka.DeviceCommandMessage;
import com.iot.devices.enums.DeviceResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;

@RestController
@RequestMapping("example")
@RequiredArgsConstructor
public class ExampleController {

    private final BlockingQueue<DeviceCommandMessage> deviceCommandQueue;


    @GetMapping
    public String exampleMethod() {
//        DeviceCommandMessage deviceCommandMessage = DeviceCommandMessage.builder()
//                .commandUuid(UUID.randomUUID())
//                .deviceUuid(UUID.randomUUID())
//                .responseStatus(DeviceResponseStatus.ON)
//                .timestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))
//                .build();

//        deviceCommandQueue.add(deviceCommandMessage);

        return "qwertyuiop";
    }

}
