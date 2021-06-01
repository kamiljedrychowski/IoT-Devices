package com.iot.devices.repository.mongo;

import com.iot.devices.entity.mongo.DeviceResponse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceResponseRepository extends MongoRepository<DeviceResponse, Long> {
}
