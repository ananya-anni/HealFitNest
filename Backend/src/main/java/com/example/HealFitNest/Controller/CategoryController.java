package com.example.HealFitNest.Controller;

import com.example.HealFitNest.Model.Category;
import com.example.HealFitNest.Model.Item;
import com.example.HealFitNest.Repository.CategoryRepo;
import com.example.HealFitNest.Service.Implementation.CategoryService;
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

    @PostMapping("/addCategory")
    public String addCategory(@RequestBody Category category){
        try{
            categoryRepo.save(category);
            return "Category added successfully";
        } catch(ConstraintViolationException e){
            return e.getMessage();
        }
    }

    @GetMapping("/categories")
    public List<Category> findAllCategories(){
        return categoryRepo.findAll();
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Category> getItemsById(@PathVariable String categoryId){
        Category category = categoryRepo.findById(categoryId).orElse(null);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/category/update/{categoryId}")
    public ResponseEntity<?> updateCategory(@PathVariable String categoryId, @RequestBody Category updatedCategory){
        try{
            Category updateCategory = categoryRepo.findById(categoryId).orElse(null);
            updateCategory.setCategoryName(updatedCategory.getCategoryName());
            updateCategory.setSubCategoryName(updatedCategory.getSubCategoryName());
            categoryRepo.save(updateCategory);
            return ResponseEntity.ok(updateCategory);
        } catch (ConstraintViolationException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }
    @DeleteMapping("/category/delete/{categoryId}")
    public String deleteCategory(@PathVariable String categoryId){
        categoryRepo.deleteById(categoryId);
        return "Category Deleted Successfully";
    }

    @GetMapping("/categoryNames")
    public List<String> getAllCategories(){
        return categoryService.displayAllCategory();
    }
    @GetMapping("/categories/{CategoryName}")
    public List<String> showAllSubCategory(@PathVariable String CategoryName){
        return categoryService.displaySubCategory(CategoryName);
    }

//    @GetMapping("/categories/{CategoryName}/{subCategoryName}")
//    public List<Item> showAllItemsInASubcategory(@PathVariable String CategoryName,@PathVariable String subCategoryName){
//        return categoryService.displayItemsInASubcategory(CategoryName,subCategoryName);
//    }

    @GetMapping("/categories/{CategoryName}/{SubCategoryName}")
    public List<Item> displayItemsInASubcategory(@PathVariable String CategoryName, @PathVariable String SubCategoryName) {
        return categoryService.displayItemInASubcategory(CategoryName,SubCategoryName);

    }
}
