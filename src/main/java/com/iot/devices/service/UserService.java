package com.iot.devices.service;

import com.iot.devices.entity.User;
import com.iot.devices.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserByLogin(String login) {
        return userRepository.getUserByLogin(login);
    }
}
