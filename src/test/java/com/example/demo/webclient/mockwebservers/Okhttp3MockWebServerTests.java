package com.example.demo.webclient.mockwebservers;

import com.example.demo.webclient.clients.WebClientBuilder;
import com.example.demo.webclient.domain.Person;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.Test;
import okhttp3.mockwebserver.MockResponse;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * MockWebServer (okhttp)
 * okhttp3 is a fully-featured HTTP client
 * Has a component called mockwebserver to create local, fake HTTP server that test code can interact with.
 */
public class Okhttp3MockWebServerTests {

    /**
     * MockWebServer enqueue
     * Useful for defining sequence of responses
     */
    @Test
    public void mockUsingEnqueue() throws IOException, InterruptedException {
        // start mock server
        try (MockWebServer mockWebServer = new MockWebServer()) {
            mockWebServer.start();

            // enqueue (add item to queue) the mocked response
            // the mocks are applied in sequence
            MockResponse mockResponse = new MockResponse()
                    .setResponseCode(200)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .setBody("{\"id\": 1, \"name\":\"duke\"}")
                    .throttleBody(16, 5, TimeUnit.SECONDS);
            mockWebServer.enqueue(mockResponse);

            MockResponse mockResponse2 = new MockResponse()
                    .setResponseCode(200)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .setBody("{\"id\": 2, \"name\":\"duke2\"}")
                    .throttleBody(16, 5, TimeUnit.SECONDS);
            mockWebServer.enqueue(mockResponse2);

            // setup client call to use base URL for mock server
            HttpUrl baseUrl = mockWebServer.url("/");

            WebClientBuilder client = new WebClientBuilder(WebClient.builder(), baseUrl.toString());

            // make client call which will get results from mock response
            Person response = client.getUserById(1);
            RecordedRequest recordedRequest1 = mockWebServer.takeRequest(1, TimeUnit.SECONDS);

            Person response2 = client.getUserById(2);
            RecordedRequest recordedRequest2 = mockWebServer.takeRequest(1, TimeUnit.SECONDS);

            // shutdown server
            mockWebServer.shutdown();

            assertNotNull(recordedRequest1);
            assertThat(recordedRequest1.getMethod()).isEqualTo("GET");
            assertThat(recordedRequest1.getPath()).isEqualTo("/users/1");

            assertNotNull(recordedRequest2);
            assertThat(recordedRequest2.getMethod()).isEqualTo("GET");
            assertThat(recordedRequest2.getPath()).isEqualTo("/users/2");

            assertThat(baseUrl.toString()).isEqualTo("http://localhost:" + mockWebServer.getPort() + "/");
            assertThat(response.getId()).isEqualTo(1);
            assertThat(response2.getId()).isEqualTo(2);
        }

    }
}

