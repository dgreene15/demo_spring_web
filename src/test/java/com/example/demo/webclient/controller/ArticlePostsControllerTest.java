package com.example.demo.webclient.controller;

import com.example.demo.webclient.domain.ArticlePost;
import com.example.demo.webclient.service.ArticlePostService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static reactor.core.publisher.Mono.when;

@ExtendWith(MockitoExtension.class)
class ArticlePostsControllerTest {

    @InjectMocks
    ArticlePostsController controller;

    @Mock
    ArticlePostService serviceMock;

    @Mock
    ArticlePost articlePostMock;
    @Test
    void getArticlePostById() {

        ArticlePost expectedMono = ArticlePost.builder().body("body").title("title").build();
        Mockito.when(serviceMock.getArticlePost(1)).thenReturn(Mono.just(expectedMono));

        Mono<ArticlePost> actualMono = controller.getArticlePostById(1);

        StepVerifier.create(actualMono)
                .expectNext(expectedMono)
                    .verifyComplete();

        Mockito.verify(serviceMock).getArticlePost(1);
    }
}