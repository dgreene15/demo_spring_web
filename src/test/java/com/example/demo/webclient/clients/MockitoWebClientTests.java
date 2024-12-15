package com.example.demo.webclient.clients;


import com.example.demo.webclient.clients.WebClientDemo;
import com.example.demo.webclient.domain.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class MockitoWebClientTests {
    WebClientDemo apiClient;
    @Mock
    private WebClient webClientMock;
    @Mock
    private WebClient.RequestHeadersSpec requestHeadersMock;
    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriMock;
    //@Mock
    //private WebClient.RequestBodySpec requestBodyMock;
    //@Mock
    //private WebClient.RequestBodyUriSpec requestBodyUriMock;
    @Mock
    private WebClient.ResponseSpec responseMock;

    @BeforeEach
    void setUp() {
        apiClient = new WebClientDemo(webClientMock);
    }

    @Test
    public void testMockClientAPI() {
        Person mockPerson = Person.builder().name("mockName").build();

        when(webClientMock.get()).thenReturn(requestHeadersUriMock);
        when(requestHeadersUriMock.uri("/users/1")).thenReturn(requestHeadersMock);
        when(requestHeadersMock.retrieve()).thenReturn(responseMock);
        when(responseMock.bodyToMono(Person.class)).thenReturn(Mono.just(mockPerson));

        Person actualPerson = apiClient.getUserById();
        System.out.println(actualPerson.toString());
    }

}

