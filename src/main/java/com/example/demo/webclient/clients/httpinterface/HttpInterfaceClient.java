package com.example.demo.webclient.clients.httpinterface;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class HttpInterfaceClient {

    @Bean
    public HttpInterfaceDefinition myHttpClient(WebClient.Builder webClientBuilder) {
        WebClient webClient = webClientBuilder.baseUrl("https://jsonplaceholder.typicode.com/").build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(WebClientAdapter.create(webClient)).build();
        return factory.createClient(HttpInterfaceDefinition.class);
    }
}
