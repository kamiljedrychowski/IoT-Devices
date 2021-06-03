package com.iot.devices.repository.mongo;

import com.iot.devices.entity.mongo.DeviceCommand;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceCommandRepository extends MongoRepository<DeviceCommand, Long> {

//    List<DeviceCommand> fi
}
