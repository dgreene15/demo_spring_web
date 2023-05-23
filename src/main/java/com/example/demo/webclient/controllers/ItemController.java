package com.example.demo.webclient.controllers;

import com.example.demo.webclient.services.MongoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Calls MongoDb using JPA
 */
@RestController
@RequestMapping("/items")
@Slf4j
public class ItemController {
    @Autowired
    MongoService service;

    @GetMapping("/tryMongo")
    public void runMongoTest() {
        log.info("Deleting all records...");
        service.deleteAll();

        log.info("adding item...");
        service.addItem();

        log.info("printing Count...");
        service.printCount();

        log.info("printing all items...");
        service.printAllItems();

        log.info("adding test apple");
        service.addTestItem();


        log.info("printing testapple item");
        service.printByName("testapple");

        log.info("updating testapple...");
        service.updateItem();


        log.info("printing all items...");
        service.printAllItems();

        log.info("print all item in 'fruit' category");
        service.printAllItemsByCategory();
    }
}
