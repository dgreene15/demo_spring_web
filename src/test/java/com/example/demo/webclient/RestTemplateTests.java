package com.example.demo.webclient;

import com.example.demo.webclient.WebclientApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;

/**

 * @SprintBootTest
 *  - Used for integration test
 *  - Load entire application context
 */
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = WebclientApplication.class)
public class RestTemplateTests {
    @Value(value="${local.server.port}")
    private int portFromValue;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testContextLoad() {
        log.info("Server Port: " + port);

        log.info("Server Port from Value: " + portFromValue);
    }

    @Test
    public void testWithTestRestTemplate() {
        String url = "http://localhost:" + port + "/greet?name=John";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("Hello, John");

        String responseObj = restTemplate.getForObject(url, String.class);
        assertThat(responseObj).contains("Hello, John");
    }
}

