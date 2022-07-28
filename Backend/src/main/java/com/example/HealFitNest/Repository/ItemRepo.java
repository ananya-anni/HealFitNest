package com.example.HealFitNest.Repository;

import com.example.HealFitNest.Model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepo extends MongoRepository<Item,String> {
}

