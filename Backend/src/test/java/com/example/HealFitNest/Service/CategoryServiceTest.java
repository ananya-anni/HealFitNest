package com.example.HealFitNest.Service;

import com.example.HealFitNest.Model.Category;
import com.example.HealFitNest.Model.Item;
import com.example.HealFitNest.Repository.CategoryRepo;
import com.example.HealFitNest.Service.Implementation.CategoryServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CategoryServiceTest {

    CategoryRepo categoryRepo = mock(CategoryRepo.class);

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.initMocks(this);
    }

    @InjectMocks
    CategoryServiceImp categoryServiceImp;


    Category category = new Category();
    Item item = new Item();


    @Test
    public void addCategoryTest(){
        List<String> subCat = new ArrayList<String>();
        List<String> subName = new ArrayList<String>();
        subCat.add("SCI1");
        subCat.add("SCI2");
        subName.add("Fruits and Vegetables");
        subName.add("Snacks");
        category.setCategoryId("CI1");
        category.setSubCategoryId(subCat);
        category.setCategoryName("Ready to eat");
        category.setSubCategoryName(subName);
        when(categoryRepo.save(category)).thenReturn(category);
        assertEquals(category, categoryServiceImp.addCategory(category));

    }

    @Test
    public void findAllCategoryTest(){
        List<String> subCat = new ArrayList<String>();
        List<String> subName = new ArrayList<String>();
        subCat.add("SCI1");
        subCat.add("SCI2");
        subName.add("Fruits and Vegetables");
        subName.add("Snacks");
        category.setCategoryId("CI1");
        category.setSubCategoryId(subCat);
        category.setCategoryName("Ready to eat");
        category.setSubCategoryName(subName);
        when(categoryRepo.findAll()).thenReturn(Stream.of(category).collect(Collectors.toList()));
        assertEquals(1, categoryServiceImp.findAllCategories().size());
    }

    @Test
    public void displaySubCategoryTest(){
        List<String> subCat = new ArrayList<String>();
        List<String> subName = new ArrayList<String>();
        subCat.add("SCI1");
        subCat.add("SCI2");
        subName.add("Fruits and Vegetables");
        subName.add("Snacks");
        category.setCategoryId("CI1");
        category.setSubCategoryId(subCat);
        category.setCategoryName("Ready to eat");
        category.setSubCategoryName(subName);
        when(categoryRepo.findAll()).thenReturn(Stream.of(category).collect(Collectors.toList()));
        assertEquals(1, categoryServiceImp.displaySubCategory("Fruits and Vegetables"));
    }

    public void displayItemInASubCategory(){
        item.setSubCategoryId("SCI1");
        item.setCategoryId("CI1");
        item.setItemName("Mango");
        item.setItemId("II1");
        item.setItemDescription("Juicy and tasty");
        item.setItemPrice(BigDecimal.valueOf(30));
        when(categoryRepo.findAll()).thenReturn(Stream.of(category).collect(Collectors.toList()));
        assertEquals(1, categoryServiceImp.displayItemInASubcategory("CI1","SCI1"));
    }
}
