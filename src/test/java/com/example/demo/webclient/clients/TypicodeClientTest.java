package com.example.demo.webclient.clients;

import com.example.demo.webclient.domain.ArticlePost;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TypicodeClientTest {

    @InjectMocks
    TypicodeClient client;

    @Mock
    WebClient webClientMock;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriMock;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpecMock;

    @Mock
    private WebClient.ResponseSpec responseSpecMock;

    @Test
    public void getPostByIdTest() {

        ArticlePost expectedArticle = ArticlePost.builder().body("body").title("article_title").build();

        when(webClientMock.get()).thenReturn(requestHeadersUriMock);
        when(requestHeadersUriMock.uri("posts/1")).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(ArticlePost.class)).thenReturn(Mono.just(expectedArticle));

        Mono<ArticlePost> articlePostMono = client.getPostById(1);

        StepVerifier.create(articlePostMono)
                .expectNextMatches(a -> a.getTitle().equals("article_title"))
              //  .expectNext(expectedArticle)
                .verifyComplete();
    }
}