package com.example.HealFitNest.Service.Implementation;

import com.example.HealFitNest.Model.Category;
import com.example.HealFitNest.Model.Item;
import com.example.HealFitNest.Repository.CategoryRepo;
import com.example.HealFitNest.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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
    public Category addCategory(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public List<Category> findAllCategories(){
        List<Category> categories = new ArrayList<>();
        categories = categoryRepo.findAll();
        return categories;
    }

    //This displays all subcategory of a particular category
    @Override
    public List<String> displaySubCategory(String categoryName){
        Category category=categoryRepo.findByCategoryName(categoryName);
        List<String> list1=category.getSubCategoryName();
        return list1;
    }
    //This displays all items in a subcategory of a particular category
    @Override
    public List<Item> displayItemInASubcategory(String categoryName, String subCategoryName){
        Category category=categoryRepo.findByCategoryName(categoryName);
        List<String> list1=category.getSubCategoryName();
        int index=list1.indexOf(subCategoryName);

        List<String> subCategoryId=category.getSubCategoryId();
        String subId=subCategoryId.get(index);
        return itemService.getAllItem(subId);

    }
    //This displays all categories by its names
    @Override
    public List<String> displayAllCategory(){
        List<Category> category1=categoryRepo.findAll();
        for(Category cat:category1){
            category.add(cat.getCategoryName());
        }
        return category;
    }
}