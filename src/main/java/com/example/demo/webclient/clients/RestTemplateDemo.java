package com.example.demo.webclient.clients;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate
 * - replaced in Spring with WebClient
 * - below code keeps getting exception on IllegalArgumentException
 *      - did seperate app with same code and it worked, retry this once other dependencies removed
 */
public class RestTemplateDemo {

    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://jsonplaceholder.typicode.com/users/1   ";

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            System.out.println("RestTemplateDemo.class: " + response.getBody());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

}
