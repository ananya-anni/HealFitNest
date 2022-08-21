package com.example.HealFitNest.Controller;


import com.example.HealFitNest.Model.Category;
import com.example.HealFitNest.Model.Item;
import com.example.HealFitNest.Repository.CategoryRepo;
import com.example.HealFitNest.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RequestMapping("/api/v7")
@RestController
public class CategoryController {

    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private CategoryService categoryService;

    //To add the category in the database
    @PostMapping("/addCategory")
    public String addCategory(@RequestBody Category category){
        try{
            categoryRepo.save(category);
            return "Category added successfully";
        } catch(ConstraintViolationException e){
            return e.getMessage();
        }

    }
    //Show List of all the categories
    @GetMapping("/categories")
    public List<Category> findAllCategories(){
        return categoryRepo.findAll();
    }
    //This returns the category based on the id of the category
    @GetMapping("{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable String id){
        Category category = categoryRepo.findById(id).orElse(null);
        return ResponseEntity.ok(category);
    }
    //Update the single category by id
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable String id, @RequestBody Category updatedCategory){

        try{
            Category updateCategory = categoryRepo.findById(id).orElse(null);
            if(updateCategory!=null) {
                updateCategory.setCategoryName(updatedCategory.getCategoryName());
                updateCategory.setSubCategoryName(updatedCategory.getSubCategoryName());
                updateCategory.setSubCategoryId(updateCategory.getSubCategoryId());
                updateCategory.setCategoryImage(updateCategory.getCategoryImage());
                categoryRepo.save(updateCategory);
            }
            return ResponseEntity.ok(updateCategory);
        } catch (ConstraintViolationException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }

    //Delete the category by it's id
    @DeleteMapping("/delete/{id}")
    public String deleteCategory(@PathVariable String id){
        categoryRepo.deleteById(id);
        return "Category Deleted Successfully";
    }
    //This displays all categories by its name
    @GetMapping("/categoryNames")
    public List<String> getAllCategories(){
        return categoryService.displayAllCategory();
    }
    //This displays all subcategory of a particular category
    @GetMapping("/categories/{CategoryName}")
    public List<String> showAllSubCategory(@PathVariable String CategoryName){
        return categoryService.displaySubCategory(CategoryName);
    }

    //This displays items present in the particular subcategory of the particular category
    @GetMapping("/categories/{CategoryName}/{SubCategoryName}")
    public List<Item> displayItemsInASubcategory(@PathVariable String CategoryName, @PathVariable String SubCategoryName) {
        return categoryService.displayItemInASubcategory(CategoryName,SubCategoryName);

    }


}

