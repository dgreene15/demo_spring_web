package com.example.demo.webclient.clients;

import com.example.demo.webclient.domain.Person;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientDemo {

    public static void main(String[] args) {
        WebClient client = WebClient.builder()
                         .baseUrl("https://jsonplaceholder.typicode.com/").build();
        Person response = client.get()
                .uri("/users/1")
                .retrieve()
                .bodyToMono(Person.class)
                .block();
        System.out.println(response.toString());
    }
}
