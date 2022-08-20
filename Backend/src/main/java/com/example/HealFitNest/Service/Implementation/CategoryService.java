package com.example.HealFitNest.Service.Implementation;

import com.example.HealFitNest.Model.Category;
import com.example.HealFitNest.Model.Item;
import com.example.HealFitNest.Repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ItemServiceImp itemService;

    @Autowired
    MongoTemplate mongoTemplate;
    List<Item> items=new ArrayList<Item>();
    List<String> category=new ArrayList<String>();

    public List<String> displaySubCategory(String categoryName){
        Category category=categoryRepo.findBycategoryName(categoryName);
        List<String> list1=category.getSubCategoryName();
        return list1;
    }

    public List<Item> displayItemInASubcategory(String CategoryName, String SubCategoryName){
        Category category=categoryRepo.findBycategoryName(CategoryName);
        List<String> list1=category.getSubCategoryName();
        int index=list1.indexOf(SubCategoryName);

        List<String> subCategoryId=category.getSubCategoryId();
        String subId=subCategoryId.get(index);
        return itemService.getAllItem(subId);

    }

    public List<String> displayAllCategory(){
        List<Category> category1=categoryRepo.findAll();
        for(Category cat:category1){
            category.add(cat.getCategoryName());
        }
        return category;
    }
}