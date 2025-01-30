package com.example.demo.webclient.clients.restclient;

import com.example.demo.webclient.domain.Person;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class RestClientService {
    private final RestClient restClient;

    public RestClientService() {
        this.restClient = RestClient.create("https://jsonplaceholder.typicode.com");
    }

    public Person getUserById(int id) {
        return restClient.get()
                .uri("/users/{id}", id)
                //.header("Authorization", "Bearer token")
                .retrieve()
                .onStatus(status -> status.is4xxClientError(), (request, response) -> {
                    throw new RuntimeException("User not found");
                })
                .body(Person.class);

        // can also have  .toEntity(Person.class) which will return ResponseEntity<Person>
    }
}
