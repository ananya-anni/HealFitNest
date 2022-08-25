package com.example.HealFitNest.Service;

import com.example.HealFitNest.Model.Category;
import com.example.HealFitNest.Model.Item;
import com.example.HealFitNest.Repository.CategoryRepo;
import com.example.HealFitNest.Repository.ItemRepo;
import com.example.HealFitNest.Service.Implementation.CategoryServiceImp;
import com.example.HealFitNest.Service.Implementation.ItemServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CategoryServiceTest {

    CategoryRepo categoryRepo = mock(CategoryRepo.class);
    ItemRepo itemRepo = mock(ItemRepo.class);

    @Autowired
    Item item = new Item();

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.initMocks(this);
    }


    @InjectMocks
    CategoryServiceImp categoryServiceImp;

    @Mock
    CategoryService categoryService;

    @Mock
    ItemService itemService;



    @Test
    public void addCategoryTest(){
        Category category = new Category();
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
        Category category = new Category();
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
        Category category = new Category();
        List<String> subCat = new ArrayList<>();
        List<String> subName = new ArrayList<>();
        subCat.add("SCI1");
        subCat.add("SCI2");
        subName.add("Fruits and Vegetables");
        subName.add("Snacks");
        category.setCategoryId("CI1");
        category.setSubCategoryId(subCat);
        category.setCategoryName("Ready to eat");
        category.setSubCategoryName(subName);

        when(categoryRepo.findByCategoryName("Ready to eat")).thenReturn(category);
        assertEquals(2, categoryServiceImp.displaySubCategory("Ready to eat").size());
    }

    @Test
    public void displayItemInASubCategory(){
        Category category = new Category();
        List<String> subCat = new ArrayList<>();
        List<String> subName = new ArrayList<>();
        subCat.add("SCI22");
        subCat.add("SCI2");
        subName.add("Fruits and Vegetables");
        subName.add("Snacks");
        category.setCategoryId("CI1");
        category.setSubCategoryId(subCat);
        category.setCategoryName("Ready to eat");
        category.setSubCategoryName(subName);
        categoryRepo.save(category);

        List<Item> items = new ArrayList<>();
        item.setSubCategoryId("SCI9");
        item.setCategoryId("CI6");
        item.setItemName("Mango");
        item.setItemId("II1");
        item.setItemDescription("Juicy and tasty");
        item.setItemPrice(BigDecimal.valueOf(30));
        item.setItemAvailable(true);
        item.setItemImage("avx");
        itemRepo.save(item);

        List<Category> categories = new ArrayList<>();
        categories.add(category);


        when(categoryRepo.findById(any())).thenReturn(Optional.of(category));
        List<Item> itemsInSubCategory = categoryService.displayItemInASubcategory("Ready to eat","Fruits and Vegetables");
        assertEquals(0, itemsInSubCategory.size());
    }


}
