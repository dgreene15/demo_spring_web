package com.example.demo.webclient.controllers;

import com.example.demo.webclient.clients.WebClientDemo;
import com.example.demo.webclient.domain.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class UserController {

    @Value("${clients.users.url}")
    private String baseURL;

    @GetMapping("/users")
    public Person getUsers() {
        WebClientDemo client = new WebClientDemo(WebClient.builder(),  baseURL);
        Person response = client.getUserById();
        System.out.println(response.toString());
        return response;
    }
}

