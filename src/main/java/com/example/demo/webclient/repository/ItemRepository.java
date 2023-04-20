package com.example.demo.webclient.repository;

import java.util.List;

import com.example.demo.webclient.domain.ItemDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface ItemRepository extends MongoRepository<ItemDetails, String> {

    // ?0 specific first parameter of name
    @Query("{name:'?0'}")
    ItemDetails findItemByName(String name);

    // fields specifies the projection so only want name, quantity in response
    @Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
    List<ItemDetails> findAll(String category);

    public long count();

}
