package com.example.HealFitNest.Repository;

import com.example.HealFitNest.Model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends MongoRepository<Category,String> {
    Category findBycategoryName(String categoryName);
}