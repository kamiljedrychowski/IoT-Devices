package com.iot.devices.controller;

import com.iot.devices.dto.UserDto;
import com.iot.devices.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public String getUserRole(HttpServletRequest requestHeader) {
        return userService.getUserRole(requestHeader);
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> registerUser(@RequestBody UserDto userDto) throws Exception {
        userService.registerUser(userDto);
        return ResponseEntity.ok().build();
    }

}
