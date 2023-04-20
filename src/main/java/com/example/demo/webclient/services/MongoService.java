package com.example.demo.webclient.services;

import java.util.Optional;

import com.example.demo.webclient.domain.ItemDetails;
import com.example.demo.webclient.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;


import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MongoService {
    @Autowired
    ItemRepository itemRepo;

    public void printCount() {
        System.out.println(itemRepo.count());
    }

    public void printAllItems() {
        itemRepo.findAll().forEach(item -> printItemDetails(item));
    }

    public void printByName(String name) {
        ItemDetails item = itemRepo.findItemByName(name);
        log.info("printByName: ");
        printItemDetails(item);

    }

    /**
     * The category will be null since this field was not in the projection.
     */
    public void printAllItemsByCategory() {
        itemRepo.findAll("fruit").forEach(item -> printItemDetails(item));
    }

    public void addItem() {
        ItemDetails item = new ItemDetails(null, "Apple", 4, "fruit");
        itemRepo.save(item);
        log.info("Added new entry");
    }

    public void addTestItem() {
        ItemDetails item = new ItemDetails("1", "testapple", 4, "fruit");
        itemRepo.save(item);
        log.info("Added 'testapple' new entry");
    }

    private void printItemDetails(ItemDetails item) {
        System.out.println("Id: " + item.getId() + " Item Name: " + item.getName() + ", \nQuantity: " + item.getQuantity()
                + ", \nItem Category: " + item.getCategory());
    }

    public void updateItem() {

        Example<ItemDetails> example = Example.of(ItemDetails.from("1", "testapple", 4, "fruit"));
        Optional<ItemDetails> actual = itemRepo.findOne(example);
        //Optional<ItemDetails> itemOptional = itemRepo.findById("63c9e603c98f2049f36d3687");
        ItemDetails item = actual.get();
        item.setName("Hello");
        itemRepo.save(item);
        log.info("Updated entry");
    }

    public void deleteAll() {
        itemRepo.deleteAll();
    }
}
