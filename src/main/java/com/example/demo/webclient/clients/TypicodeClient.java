package com.example.demo.webclient.clients;

import com.example.demo.webclient.domain.ArticlePost;
import com.example.demo.webclient.exceptions.ArticleNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class TypicodeClient {

    @Autowired
    private WebClient webClientTypicode;

    public Mono<ArticlePost> getPostById(int id) {
        System.out.println(HttpStatus.NOT_FOUND.value());

        return webClientTypicode.get()
                        .uri("posts/" + id)
                        .retrieve()
                        .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new ArticleNotFoundException()))
                        //.onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {throw new ArticleNotFoundException();})
                        .bodyToMono(ArticlePost.class)
                        .doOnNext(response -> log.info("Response from webClientTypiCode = {}", response));
    }
}
