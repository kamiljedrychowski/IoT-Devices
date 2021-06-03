package com.iot.devices.service;

import com.iot.devices.dto.DeviceDto;
import com.iot.devices.dto.UserDto;
import com.iot.devices.enums.DeviceStatus;
import com.iot.devices.enums.DeviceType;
import com.iot.devices.exceptions.ObjectNotFoundException;
import com.iot.devices.exceptions.RequiredDataNotFoundException;
import com.iot.devices.repository.DeviceRepository;
import com.iot.devices.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    @DisplayName("UserService - registerUser saving")
    void registerUserSaving() throws Exception {
        UserDto userDto = UserDto.builder()
                .name("name")
                .surname("surname")
                .login("login")
                .password("________")
                .build();
        assertEquals(userService.registerUser(userDto).getName(), userDto.getName(),
                "Saved device should have same name as deviceDto");
    }

    @Test
    @DisplayName("UserService - validateUser correct")
    void validateUserCorrect() {
        UserDto userDto = UserDto.builder()
                .name("name")
                .surname("surname")
                .login("login")
                .password("________")
                .build();

        assertTrue(userService.validateUser(userDto), "Given user is correct");
    }

    @Test
    @DisplayName("UserService - validateUser incorrect")
    void validateUserIncorrect() {
        UserDto userDto = UserDto.builder()
                .name("name")
                .surname("surname")
                .build();

        assertFalse(userService.validateUser(userDto), "Given user is incorrect");
    }

    @Test
    @DisplayName("UserService - registerUser RequiredDataNotFoundException")
    void registerUserRequiredDataNotFoundException() {
        UserDto userDto = new UserDto();
        assertThrows(RequiredDataNotFoundException.class, () -> userService.registerUser(userDto));
    }
}