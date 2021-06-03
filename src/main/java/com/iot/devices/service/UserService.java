package com.iot.devices.service;

import com.iot.devices.dto.DeviceDto;
import com.iot.devices.dto.UserDto;
import com.iot.devices.entity.User;
import com.iot.devices.enums.UserRole;
import com.iot.devices.exceptions.ObjectAlreadyExistException;
import com.iot.devices.exceptions.ObjectNotFoundException;
import com.iot.devices.exceptions.RequiredDataNotFoundException;
import com.iot.devices.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(UserDto::fromEntity).collect(Collectors.toList());
    }

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

    public UserDto registerUser(UserDto userDto) throws Exception {
        if(!validateUser(userDto)) {
            log.error("UserDto has wrong data: {}", userDto);
            throw new RequiredDataNotFoundException("UserDto has wrong data");
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
            return UserDto.fromEntity(newUser);
        } catch (Exception e) {
            log.error("User with this login already exist");
            throw new ObjectAlreadyExistException("User with this login already exist");
        }

    }

    public boolean validateUser(UserDto userDto) {
        return userDto != null && StringUtils.hasText(userDto.getLogin()) && StringUtils.hasText(userDto.getPassword())
                && StringUtils.hasText(userDto.getName()) && StringUtils.hasText(userDto.getSurname());
    }

    public void deleteUser(Long id) throws Exception {
        try{
            userRepository.deleteById(id);
        } catch (Exception exception) {
            log.error("User with given id: {} does not exist", id);
            throw new ObjectNotFoundException("User with given id does not exist");
        }
    }

    public UserDto updateUser(Long id, UserDto userDto) throws ObjectNotFoundException {
        try{
            User user = userRepository.getById(id);
            boolean mod = false;
            if(StringUtils.hasText(userDto.getName())) {
                mod = true;
                user.setName(userDto.getName());
            }
            if(StringUtils.hasText(userDto.getSurname())){
                mod = true;
                user.setSurname(userDto.getSurname());
            }

            if(StringUtils.hasText(userDto.getLogin())){
                mod = true;
                user.setLogin(userDto.getLogin());
            }

            if(StringUtils.hasText(userDto.getPassword())){
                mod = true;
                user.setPassword(userDto.getPassword());
            }
            if(mod) {
                userRepository.save(user);
            }
            return UserDto.fromEntity(user);
        } catch (Exception exception) {
            log.error("User with given id: {} does not exist", id);
            throw new ObjectNotFoundException("User with given id does not exist");
        }
    }
}
