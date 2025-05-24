package com.example.demo.wiremock;

import com.example.demo.webclient.WebclientApplication;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * webEnvironment is needed for TestRestTemplate
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = WebclientApplication.class)
@WireMockTest(httpPort = 8090) // Starts WireMock on port 8080 for this test
@ActiveProfiles("test")
public class WireMockTests {


    @Test
    public void httpClientWiremockTest() throws IOException, InterruptedException {
        stubFor(get(urlEqualTo("/api/users/123"))
                .willReturn(okJson("{\"id\": 123, \"name\": \"John Doe\"}")));

        String externalServiceUrl = "http://localhost:8090/api/users/123";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(externalServiceUrl))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, response.statusCode());
        assertEquals("{\"id\": 123, \"name\": \"John Doe\"}", response.body());

        // Optionally, verify that the request was actually made
        verify(getRequestedFor(urlEqualTo("/api/users/123")));
    }

    @Test
    public void restTemplateWiremockTest() {
        stubFor(post(urlEqualTo("/api/process"))
                .withRequestBody(equalToJson("{\"data\": \"test\"}"))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"status\": \"processed\"}")));

        String externalServiceUrl = "http://localhost:8090/api/process";
        RestTemplate restTemplate = new RestTemplate();
        String requestBody = "{\"data\": \"test\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(externalServiceUrl, entity, String.class);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals("{\"status\": \"processed\"}", response.getBody());

        verify(postRequestedFor(urlEqualTo("/api/process"))
                .withRequestBody(equalToJson("{\"data\": \"test\"}")));
    }
}
