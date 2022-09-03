package com.example.HealFitNest.Service.Implementation;

import com.example.HealFitNest.Model.Category;
import com.example.HealFitNest.Model.Item;
import com.example.HealFitNest.Repository.CategoryRepo;
import com.example.HealFitNest.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ItemServiceImp itemService;

    @Autowired
    MongoTemplate mongoTemplate;
    List<Item> items=new ArrayList<Item>();
    List<String> category=new ArrayList<String>();


    @Override
    public String addCategory(Category category) {
        categoryRepo.save(category);
        return "Category added Successfully";
    }

    @Override
    public List<Category> findAllCategories(){

        return categoryRepo.findAll();

    }

    //Displaying all the subcategories of a particular category by its name
    public List<String> displaySubCategory(String categoryName){
        Category category=categoryRepo.findBycategoryName(categoryName);
        List<String> subCategoryName=category.getSubCategoryName();
        return subCategoryName;
    }

    //Displaying items of a particular subcategory
    public List<Item> displayItemInASubcategory(String CategoryName, String SubCategoryName){
        Category category=categoryRepo.findBycategoryName(CategoryName);
        List<String> subCategoryName=category.getSubCategoryName();
        int index=subCategoryName.indexOf(SubCategoryName);

        List<String> subCategoryId=category.getSubCategoryId();
        String subId=subCategoryId.get(index);
        return itemService.getAllItem(subId);

    }

    //Displaying only the categories by its name
    public List<String> displayAllCategory(){
        List<Category> category1=categoryRepo.findAll();
        for(Category cat:category1){
            category.add(cat.getCategoryName());
        }
        return category;
    }
}