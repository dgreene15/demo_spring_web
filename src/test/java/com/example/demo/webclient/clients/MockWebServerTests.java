package com.example.demo.webclient.clients;

import com.example.demo.webclient.domain.Person;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.Test;
import okhttp3.mockwebserver.MockResponse;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * MockWebServer (okhttp)
 *
 */
public class MockWebServerTests {

    @Test
    public void testMockWebServer() throws IOException {
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
            Person response = client.getUserById();
            Person response2 = client.getUserById();

            // shutdown server
            mockWebServer.shutdown();

            assertThat(baseUrl.toString()).isEqualTo("http://localhost:" + mockWebServer.getPort() + "/");
            assertThat(response.getId()).isEqualTo(1);
            assertThat(response2.getId()).isEqualTo(2);
        }

    }
}

