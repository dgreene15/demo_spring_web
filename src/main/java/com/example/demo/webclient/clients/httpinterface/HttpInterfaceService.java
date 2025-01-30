package com.example.demo.webclient.clients.httpinterface;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HttpInterfaceService {

    @Autowired
    private HttpInterfaceDefinition myHttpClient;

    public String fetchData() {
        return myHttpClient.getData();
    }
}
