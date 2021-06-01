package com.iot.devices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("example")
public class ExampleController {

    @GetMapping
    public String exampleMethod() {
        return "qwertyuiop";
    }

}
