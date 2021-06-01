package com.iot.devices.service;

import com.iot.devices.repository.DeviceStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeviceStatusService {

    private final DeviceStatusRepository deviceStatusRepository;
}
