package com.iot.devices.service;

import com.iot.devices.entity.mongo.DeviceCommand;
import com.iot.devices.repository.mongo.DeviceCommandRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeviceCommandService {

    private final DeviceCommandRepository deviceCommandRepository;

    public List<DeviceCommand> getNewestCommands() {
        return deviceCommandRepository.findAll(
                PageRequest.of(0, 50, Sort.by("date").descending())).getContent();
    }

}
