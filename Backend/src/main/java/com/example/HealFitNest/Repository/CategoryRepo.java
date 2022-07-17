package com.example.HealFitNest.Repository;

import com.example.HealFitNest.Model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepo extends MongoRepository<Category,String> {
}
