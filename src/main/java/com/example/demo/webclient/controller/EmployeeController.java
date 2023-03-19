package com.example.demo.webclient.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
@Slf4j
public class EmployeeController {

    @GetMapping("/{id}")
    public void getEmployeeById(@PathVariable("id") Integer id) {
        log.info("employee controller (id={})", id);
        return;
    }
}
