package com.example.demo.webclient;

import com.example.demo.webclient.services.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebclientApplication {


	public static void main(String[] args) {
		SpringApplication.run(WebclientApplication.class, args);

	}

}
