package com.example.demo.webclient.clients.restclient;

import com.example.demo.webclient.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

/**
 * RestClient
 * - Replacement for RestTemplate
 * - synchronous and blocking
 */
@SpringBootApplication
@Profile("runner")
public class RestClientDemo implements CommandLineRunner {

    @Autowired
    RestClientService restClientService;

    public static void main(String[] args) {
        SpringApplication.run(RestClientDemo.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Person response = restClientService.getUserById(1);

        System.out.println("CommandLineRunner ßRestClientDemo Response: " + response);
    }
}
