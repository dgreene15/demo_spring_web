package com.example.demo.webclient.services;

import com.example.demo.webclient.clients.TypicodeClient;
import com.example.demo.webclient.domain.ArticlePost;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ArticlePostService {

    @Autowired
    TypicodeClient typicodeClient;

    public Mono<ArticlePost> getArticlePost(Integer id) {
        log.info("EmployeeService: (id:{})", id);

        return typicodeClient.getPostById(id)
                .doOnError((e) -> Mono.empty());
    }

    public Mono<ArticlePost> getTwoArticlePosts(Integer id) {
        log.info("EmployeeService: (id:{})", id);

        Mono<ArticlePost> articleFromId = typicodeClient.getPostById(id);
        Mono<ArticlePost> articleFromIdPlusOne = typicodeClient.getPostById(++id);

        return Mono.zip(articleFromId, articleFromIdPlusOne)
                .flatMap(zipMono -> {
                    ArticlePost p1 = zipMono.getT1();
                    ArticlePost p2 = zipMono.getT2();

                    ArticlePost combined = ArticlePost.builder()
                            .body(p1.getBody() + ":" + p2.getBody())
                            .title(p1.getTitle() + ":" + p2.getTitle())
                            .userId(p1.getUserId() + ":" + p2.getUserId())
                            .id(p1.getId() + p2.getId())
                            .build();

                    return Mono.just(combined);
                });
    }

}
