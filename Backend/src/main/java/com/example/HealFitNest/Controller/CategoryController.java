package com.example.HealFitNest.Controller;

import com.example.HealFitNest.Model.Category;
import com.example.HealFitNest.Model.Item;
import com.example.HealFitNest.Repository.CategoryRepo;
import com.example.HealFitNest.Service.Implementation.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

