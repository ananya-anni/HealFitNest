package com.example.HealFitNest.Repository;

import com.example.HealFitNest.Model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepo extends MongoRepository<Item,String> {
}

