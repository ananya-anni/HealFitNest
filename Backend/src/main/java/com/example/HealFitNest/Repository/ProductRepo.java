package com.example.HealFitNest.Repository;

import com.example.HealFitNest.Model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepo extends MongoRepository<Product,Integer> {
}

