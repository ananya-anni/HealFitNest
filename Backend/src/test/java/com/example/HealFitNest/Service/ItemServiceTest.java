package com.example.HealFitNest.Service;

import com.example.HealFitNest.Model.Item;
import com.example.HealFitNest.Repository.ItemRepo;

import com.example.HealFitNest.Service.Implementation.ItemServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ItemServiceTest {

    ItemService itemService = mock(ItemService.class);
    ItemRepo itemRepo = mock(ItemRepo.class);

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.initMocks(this);
    }

    @InjectMocks
    ItemServiceImp itemServiceImp;

    @Test
    public void saveItem()
    {
        Item item=new Item();
        item.setItemId("I123");
        item.setItemName("Mango");
        item.setCategoryId("C1234");
        item.setSubCategoryId("S1234");
        item.setItemDescription("Tasty");
        item.setItemPrice(BigDecimal.valueOf(143));

        when(itemRepo.save(any())).thenReturn(item);
        itemServiceImp.saveItem(item);
        verify(itemRepo,times(1)).save(item);
    }


    @Test
    public void findItemById(){

        Item item=new Item();
        item.setItemId("I123");
        item.setItemName("Mango");
        item.setCategoryId("C1234");
        item.setSubCategoryId("S1234");
        item.setItemDescription("Tasty");
        item.setItemPrice(BigDecimal.valueOf(143));
        when(itemRepo.findById(item.getItemId())).thenReturn(Optional.of(item));
        assertEquals("I123",itemServiceImp.findItemById(item.getItemId()).getItemId());
    }

    @Test
    public void searchItem(){

        Item item=new Item();
        item.setItemId("I123");
        item.setItemName("Mango");
        item.setCategoryId("C1234");
        item.setSubCategoryId("S1234");
        item.setItemDescription("Tasty");
        item.setItemPrice(BigDecimal.valueOf(143));

        when(itemRepo.findByItemName(item.getItemName())).thenReturn(Optional.of(item));
        assertEquals(item,itemServiceImp.searchItem(item.getItemName()));
    }

    @Test
    public void getAllItems(){
        List<Item> i=new ArrayList<>();

        Item item1=new Item();
        item1.setItemId("I1234");
        item1.setItemName("Guava");
        item1.setCategoryId("C12345");
        item1.setSubCategoryId("S12345");
        item1.setItemDescription("Tasty");
        item1.setItemPrice(BigDecimal.valueOf(134));

        Item item2=new Item();
        item2.setItemId("I12345");
        item2.setItemName("Orange");
        item2.setCategoryId("C12345");
        item2.setSubCategoryId("S123456");
        item2.setItemDescription("Tasty");
        item2.setItemPrice(BigDecimal.valueOf(132));

        i.add(item1);
        i.add(item2);

        when(itemRepo.findByCategoryId("C12345")).thenReturn(i);
        List<Item> itemList = itemServiceImp.getAllItems("C12345");
        assertEquals(2,itemList.size());



    }
    @Test
    public void getAllItem(){
        List<Item> i=new ArrayList<Item>();
        Item item1=new Item();
        item1.setItemId("I1234");
        item1.setItemName("Guava");
        item1.setCategoryId("C12345");
        item1.setSubCategoryId("S123456");
        item1.setItemDescription("Tasty");
        item1.setItemPrice(BigDecimal.valueOf(134));

        Item item2=new Item();
        item2.setItemId("I12345");
        item2.setItemName("Orange");
        item2.setCategoryId("C12345");
        item2.setSubCategoryId("S123456");
        item2.setItemDescription("Tasty");
        item2.setItemPrice(BigDecimal.valueOf(132));

        i.add(item1);
        i.add(item2);

        when(itemRepo.findBySubCategoryId("S123456")).thenReturn(i);
        List<Item> itemList = itemServiceImp.getAllItem("S123456");
        assertNotNull(itemList);

    }
}





