package com.example.HealFitNest.Controller;

import com.example.HealFitNest.Model.Category;
import com.example.HealFitNest.Repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v7")
public class CategoryController{

    @Autowired
    CategoryRepo categoryRepo;


    @PostMapping("/addCategory")
    public String addCategory(@RequestBody Category category){
            categoryRepo.save(category);
            return "Category added successfully";
    }

    @GetMapping("/categories")
    public List<Category> findAllCategories(){
        return categoryRepo.findAll();
    }

    @GetMapping("/{id}")
    public Category findCategoryById(@PathVariable String categoryId){
        return categoryRepo.findById(categoryId).orElse(null);
    }

    @PutMapping("/update/{id}")
    public String updateCategory(@PathVariable String categoryId, @RequestBody Category updatedCategory){
        Category updateCategory = categoryRepo.findById(categoryId).orElse(null);
        updateCategory.setCategoryName(updatedCategory.getCategoryName());
        updateCategory.setSubCategoryName(updatedCategory.getSubCategoryName());
        return "updated Successfully";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCategory(@PathVariable String categoryId){
        categoryRepo.deleteById(categoryId);
        return "Category Deleted Successfully";
    }

}
