package com.example.demo.webclient.mockmvc;

import  com.example.demo.webclient.services.MyService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class MyTestConfiguration {
    @Bean
    @Primary
    public MyService myGreeting() {
        return new MyService(){
            @Override
            public String greet(String name) {
                return "(inside MyTestConfiguration class) Hello, " + name;
            }
        };
    }
}
