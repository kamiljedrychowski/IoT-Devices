package com.iot.devices.service;

import com.iot.devices.dto.UserDto;
import com.iot.devices.entity.User;
import com.iot.devices.enums.UserRole;
import com.iot.devices.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserByLogin(String login) {
        return userRepository.getUserByLogin(login);
    }

    public User getUserByAuthorization(HttpServletRequest requestHeader) {
        String authorization = requestHeader.getHeader("Authorization");
        String decode = new String(Base64.getDecoder().decode(authorization.split(" ")[1]));
        return getUserByLogin(decode.split(":")[0]);
    }

    public String getUserRole(HttpServletRequest requestHeader) {
        User user = getUserByAuthorization(requestHeader);
        if(user != null) {
            return user.getRole().toString();
        }
        return null;
    }

    public void registerUser(UserDto userDto) throws Exception {
        if(!validateUser(userDto)) {
            log.error("UserDto has wrong data: {}", userDto);
            throw new Exception("UserDto has wrong data");
        }

        User newUser = User.builder()
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .login(userDto.getLogin())
                .password(userDto.getPassword())
                .role(UserRole.USER)
                .build();

        try {
            userRepository.save(newUser);
        } catch (Exception e) {
            log.error("User with this login already exist");
            throw new Exception("User with this login already exist");
        }

    }

    private boolean validateUser(UserDto userDto) {
        return userDto != null && StringUtils.hasText(userDto.getLogin()) && StringUtils.hasText(userDto.getPassword())
                && StringUtils.hasText(userDto.getName()) && StringUtils.hasText(userDto.getSurname());
    }
}
