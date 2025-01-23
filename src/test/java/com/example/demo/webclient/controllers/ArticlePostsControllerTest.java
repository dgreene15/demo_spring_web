package com.example.demo.webclient.controllers;

import com.example.demo.webclient.domain.ArticlePost;
import com.example.demo.webclient.services.ArticlePostService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class ArticlePostsControllerTest {

    @Mock
    ArticlePostService serviceMock;

    @Mock
    ArticlePost articlePostMock;

    @InjectMocks
    ArticlePostsController controller;

    @Test
    public void getArticlePostByIdTest() {
        ArticlePost expectedMono = ArticlePost.builder().body("body").title("title").build();
        Mockito.when(serviceMock.getArticlePost(1)).thenReturn(Mono.just(expectedMono));

        Mono<ArticlePost> actualMono = controller.getArticlePostById(1);

        StepVerifier.create(actualMono)
                .expectNext(expectedMono)
                    .verifyComplete();

        Mockito.verify(serviceMock).getArticlePost(1);
    }
}