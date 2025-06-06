package com.example.demo.webclient.mockmvc;

import com.example.demo.webclient.WebclientApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @SprintBootTest
 *  - Used for integration test
 *  - Load entire application context
 *
 *  JSON Example
 *  .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
 */
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = WebclientApplication.class)
@AutoConfigureMockMvc
public class MockMVCTests {
    @Value(value="${local.server.port}")
    private int portFromValue;

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testWithMockMvc() throws Exception {
        mockMvc.perform(get("/greet").param("name", "John"))
                .andExpect(status().isOk())
                .andExpect(content().string("(inside MyService class) Hello, John"));
    }

    @Test
    void shouldReturnCustomGreeting() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/greet")
                        .param("name", "World"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.parseMediaType("text/plain;charset=UTF-8")))
                .andExpect(MockMvcResultMatchers.content().string("(inside MyService class) Hello, World"));
    }
}
