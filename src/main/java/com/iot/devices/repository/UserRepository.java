package com.iot.devices.repository;

import com.iot.devices.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User getUserByLogin(String login);
}
