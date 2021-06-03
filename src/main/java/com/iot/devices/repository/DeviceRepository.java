package com.iot.devices.repository;

import com.iot.devices.entity.Device;
import com.iot.devices.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    List<Device> findAllByOwner(User owner);
}
