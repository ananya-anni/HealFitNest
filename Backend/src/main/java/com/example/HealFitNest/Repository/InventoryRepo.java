package com.example.HealFitNest.Repository;

import com.example.HealFitNest.Model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InventoryRepo extends MongoRepository<Inventory,String> {
}
