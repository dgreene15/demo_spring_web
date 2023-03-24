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

        return typicodeClient.getPostById(id);
    }

}
