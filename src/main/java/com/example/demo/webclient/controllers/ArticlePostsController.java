package com.example.demo.webclient.controllers;

import com.example.demo.webclient.domain.ArticlePost;
import com.example.demo.webclient.services.ArticlePostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employees")
@Slf4j
public class ArticlePostsController {

    @Autowired
    ArticlePostService articlePostService;

    @GetMapping("/{id}")
    public Mono<ArticlePost> getArticlePostById(@PathVariable("id") Integer id) {
        log.info("ArticlePostsController:  controller (id={})", id);

        return articlePostService.getArticlePost(id)
                .log()
                .doOnNext(s -> log.info("response: " + s));
    }
}
