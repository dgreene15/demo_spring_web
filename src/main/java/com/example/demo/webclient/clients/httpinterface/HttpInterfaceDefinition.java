package com.example.demo.webclient.clients.httpinterface;

import org.springframework.web.service.annotation.GetExchange;

public interface HttpInterfaceDefinition {

    @GetExchange("/users/1")
    String getData();
}
