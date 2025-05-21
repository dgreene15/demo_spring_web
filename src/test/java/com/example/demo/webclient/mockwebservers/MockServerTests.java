package com.example.demo.webclient.mockwebservers;

import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.RequestDefinition;
import org.mockserver.netty.MockServer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class MockServerTests {

    @Test
    public void mockServerTest() {

        MockServer mockServer = new MockServer(8080);
        MockServerClient mockServerClient = new MockServerClient("localhost", 8080);
        String userId = "123";
        String expectedName = "John Doe";
        String responseBody = String.format("{\"id\": \"%s\", \"name\": \"%s\"}", userId, expectedName);


        mockServerClient
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/users")
                                .withQueryStringParameter("id", "123")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody(responseBody)
                );

        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<User> response = restTemplate.exchange(
                "http://localhost:8080/users?id=123",
                HttpMethod.GET,
                null,
                User.class
        );

        User user = response.getBody();
        assertEquals(userId, user.getId());
        assertEquals(expectedName, user.getName());

        // HttpRequest extends RequestDefinition
        RequestDefinition request = request()
                .withMethod("GET")
                .withPath("/users")
                .withQueryStringParameter("id", userId);

        mockServerClient.verify(request);
    }
}


class User {
    private String id;
    private String name;

    // Default constructor (required for Jackson)
    public User() {
    }

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
