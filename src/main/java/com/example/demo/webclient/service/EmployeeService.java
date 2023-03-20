package com.example.demo.webclient.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class EmployeeService {

    public Mono<String> getEmployee(Integer id) {
        log.info("EmployeeService: (id:{})", id);

        return callRemoteService();
    }

    private Mono<String> callRemoteService()  {
        WebClient client = WebClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build();

        return client.get()
                .uri("posts/1")
                .retrieve()
                .bodyToMono(String.class);
    }


}
