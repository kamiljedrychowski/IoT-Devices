package com.iot.devices.repository;

import com.iot.devices.entity.DeviceStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceStatusRepository extends CrudRepository<DeviceStatus, Long> {
}
