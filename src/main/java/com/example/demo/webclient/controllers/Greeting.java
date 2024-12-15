package com.example.demo.webclient.controllers;

import com.example.demo.webclient.services.MyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class Greeting {

    @Autowired
    MyService myService;

    @GetMapping("/greet")
    public String greet(@RequestParam String name) {
        log.info("greeting");
        return myService.greet(name);
    }
}