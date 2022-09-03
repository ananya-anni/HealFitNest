package com.example.HealFitNest.Service;

import com.example.HealFitNest.Model.Category;
import com.example.HealFitNest.Model.Item;

import java.util.List;

public interface CategoryService {

    String addCategory(Category category);
    List<Category> findAllCategories();

    List<String> displaySubCategory(String categoryName);
    List<Item> displayItemInASubcategory(String CategoryName, String SubCategoryName);
    List<String> displayAllCategory();
}
