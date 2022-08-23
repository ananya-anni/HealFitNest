package com.example.HealFitNest.Repository;

import com.example.HealFitNest.Model.Category;
import com.example.HealFitNest.Model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends MongoRepository<Category,String> {

    @Autowired
    ItemRepo itemRepo = null;
    Category findByCategoryName(String categoryName);
    List<Item> findBySubCategoryId(String subCategoryId);
}
