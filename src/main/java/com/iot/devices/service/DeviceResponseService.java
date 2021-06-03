package com.iot.devices.service;

import com.iot.devices.entity.mongo.DeviceResponse;
import com.iot.devices.repository.mongo.DeviceResponseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceResponseService {

    private final DeviceResponseRepository deviceResponseRepository;

    public List<DeviceResponse> getNewestResponse() {
        return deviceResponseRepository.findAll(
                PageRequest.of(0, 50, Sort.by("date").descending())).getContent();
    }
}
