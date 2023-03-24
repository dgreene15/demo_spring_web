package com.example.demo.webclient.clients;

import com.example.demo.webclient.domain.ArticlePost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class TypicodeClient {

    @Autowired
    private WebClient webClientTypicode;

    public Mono<ArticlePost> getPostById(int id) {
        return webClientTypicode.get()
                        .uri("posts/" + id)
                        .retrieve()
                        .bodyToMono(ArticlePost.class);
    }
}
