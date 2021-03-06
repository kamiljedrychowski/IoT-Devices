package com.iot.devices.controller;

import com.iot.devices.dto.UserDto;
import com.iot.devices.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) throws Exception {
        return ResponseEntity.ok(userService.registerUser(userDto));
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) throws Exception {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) throws Exception {
        return ResponseEntity.ok(userService.updateUser(id, userDto));
    }

}
