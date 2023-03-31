package com.example.demo.webclient.clients;

import com.example.demo.webclient.domain.ArticlePost;
import exceptions.ServerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerErrorException;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class TypicodeClient {

    @Autowired
    private WebClient webClientTypicode;

    public Mono<ArticlePost> getPostById(int id) {

        return webClientTypicode.get()
                        .uri("posts/" + id)
                        .retrieve()
                        .onStatus(HttpStatusCode::is2xxSuccessful, clientResponse -> Mono.error(new RuntimeException("4xx error")))
                        //.onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("4xx error")))
                       // .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> throw new ServerException())
                        .bodyToMono(ArticlePost.class)
                        .onErrorResume(e -> {
                            //throw new ServerException("no article found", "data not found");
                            return Mono.just(ArticlePost.builder().body("body").title("title").id(4).build());
                            // return Mono.empty();
                        })
                        .doOnNext((e) -> log.info("in webclient done"));
    }
}
