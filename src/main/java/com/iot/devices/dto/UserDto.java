package com.iot.devices.dto;

import com.iot.devices.entity.User;
import com.iot.devices.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private UserRole role;

    public static UserDto fromEntity(User entity) {
        return UserDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .login(entity.getLogin())
                .role(entity.getRole())
                .build();
    }
}
