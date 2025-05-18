package com.example.demo.webclient.clients;

import com.example.demo.webclient.domain.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientBuilder {
    private final WebClient webClient;

    public WebClientBuilder(WebClient client) {
        this.webClient = client;
    }
    public WebClientBuilder(WebClient.Builder builder,
                            @Value("${clients.users.url}") String baseURL) {
        this.webClient = builder.baseUrl(baseURL).build();
    }

    public Person getUserById(int id) {
        // https://jsonplaceholder.typicode.com/users/1
        // WebClient client = WebClient.builder()
        //         .baseUrl("https://jsonplaceholder.typicode.com/").build();
        return webClient.get()
                .uri("/users/" + id)
                .retrieve()
                .bodyToMono(Person.class)
                .block();
    }
}

