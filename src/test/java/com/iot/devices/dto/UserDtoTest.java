package com.iot.devices.dto;

import com.iot.devices.entity.User;
import com.iot.devices.enums.UserRole;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDtoTest {

    private static User user;

    @BeforeAll
    public static void setUp() {
        user = User.builder()
                .name("Test name")
                .surname("Test surname")
                .login("login")
                .password("password")
                .role(UserRole.USER).build();
        user.setId(-1L);
    }

    @Test()
    @DisplayName("UserDto - fromEntity")
    void fromUserToUserDto() {
        UserDto result = UserDto.fromEntity(user);
        assertEquals(user.getId(), result.getId(), "Result dto should have same id as user");
        assertEquals(user.getName(), result.getName(), "Result dto should have same name as user");
        assertEquals(user.getSurname(), result.getSurname(), "Result dto should have same surname as user");
        assertEquals(user.getLogin(), result.getLogin(), "Result dto should have same login as user");
        assertEquals(user.getRole(), result.getRole(), "Result dto should have same role as user");
    }
}