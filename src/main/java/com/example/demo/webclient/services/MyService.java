package com.example.demo.webclient.services;

import com.example.demo.webclient.utiliity.Utility;
import org.springframework.stereotype.Service;

@Service
public class MyService {
    public String greet(String name) {
        return "(inside MyService class) Hello, " + name;
    }

    public String greet2(String name) {
        return Utility.staticMethod(name);
    }

    public int compute(int value) {
        return value * 2;
    }
}
