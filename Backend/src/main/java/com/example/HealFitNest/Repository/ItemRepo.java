package com.example.HealFitNest.Repository;

import com.example.HealFitNest.Model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepo extends MongoRepository<Item,String> {
    Optional<Item> findByitemName(String itemName);
    List<Item> findBycategoryId(String categoryId);
    List<Item> findBysubCategoryId(String SubCategoryId);
}

