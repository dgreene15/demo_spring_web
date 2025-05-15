package com.example.demo.webclient.clients.httpinterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

/**
 * HttpInterface
 * - part of Spring Boot 3
 * - Part of Spring WebFlux
 * - Built on top of WebClient
 * - Declarative
 * - Uses dynamic proxy concept
 *      - created runtime implementation for interface
 * 1. create interface with HTTP methods
 * 2. create configuration class to define beans (client)
 */
@SpringBootApplication
@Profile("runner")
public class HttpInterfaceDemo implements CommandLineRunner {

    @Autowired
    HttpInterfaceService myHttpInterfaceService;

    public static void main(String[] args) {
        SpringApplication.run(HttpInterfaceDemo.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String response = myHttpInterfaceService.fetchData();

        System.out.println("CommandLineRunner ßHttpInterfaceDemo.class Response: " + response);
    }
}
