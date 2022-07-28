package com.example.HealFitNest.Controller;

import com.example.HealFitNest.Model.Category;
import com.example.HealFitNest.Repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v7")
public class CategoryController{

    @Autowired
    private CategoryRepo categoryRepo;


    @PostMapping("/addCategory")
    public String addCategory(@RequestBody Category category){
            categoryRepo.save(category);
            return "Category added successfully";
    }

    @GetMapping("/categories")
    public List<Category> findAllCategories(){
        return categoryRepo.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> getItemsById(@PathVariable String id){
        Category category = categoryRepo.findById(id).orElse(null);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Category> updateItem(@PathVariable String id, @RequestBody Category updatedCategory){
        Category updateCategory = categoryRepo.findById(id).orElse(null);
        updateCategory.setCategoryName(updatedCategory.getCategoryName());
        updateCategory.setSubCategoryName(updatedCategory.getSubCategoryName());

        categoryRepo.save(updateCategory);
        return ResponseEntity.ok(updateCategory);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCategory(@PathVariable String id){
        categoryRepo.deleteById(id);
        return "Category Deleted Successfully";
    }

}
