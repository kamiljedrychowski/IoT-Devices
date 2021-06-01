package com.iot.devices.configuration;

import com.iot.devices.entity.User;
import com.iot.devices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceAuthorization implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailsServiceAuthorization(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByLogin(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserDetailsAuthorization(user);
    }
}
