package com.example.HealFitNest.Service;

import com.example.HealFitNest.Model.Inventory;
import com.example.HealFitNest.Model.Item;
import com.example.HealFitNest.Model.Order;
import com.example.HealFitNest.Repository.InventoryRepo;
import com.example.HealFitNest.Repository.ItemRepo;
import com.example.HealFitNest.Service.Implementation.InventoryServiceImp;
import com.example.HealFitNest.Service.Implementation.ItemServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class InventoryServiceTest {
    InventoryRepo inventoryRepo=mock(InventoryRepo.class);
    @Mock
    ItemRepo itemRepo;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.initMocks(this);
    }

    @InjectMocks
    InventoryServiceImp inventoryServiceImp;

    @InjectMocks
    ItemService itemService;

    @Test
    public void addNewItem(){
        Inventory inventory1=new Inventory();
        Item item=new Item();
        item.setItemId("I123");
        item.setItemName("Mango");
        item.setCategoryId("C1234");
        item.setSubCategoryId("S1234");
        item.setItemDescription("Tasty");
        item.setItemPrice(BigDecimal.valueOf(143));
        itemRepo.save(item);

        inventory1.setItemId(item.getItemId());
        inventory1.setItemName(item.getItemName());
        inventory1.setItemQuantity(100);
        inventory1.setAmountPresent(100);
        inventory1.setSoldItem(0);

        when(inventoryRepo.save(any())).thenReturn((inventory1));
        inventoryServiceImp.addNewItem(inventory1.getItemId(),inventory1.getAmountPresent());
        //doNothing().when(inventoryRepo.save(inventory1));
        //inventoryServiceImp.addNewItem(inventory1.getItemId(),inventory1.getItemQuantity()))
        verify(inventoryRepo,times(1)).save(inventory1);


    }

    @Test
    public void showInventory(){
        List<Inventory> inventory=new ArrayList<>();
        Inventory inventory1=new Inventory();
        inventory1.setItemId("10125");
        inventory1.setItemName("Body lotion");
        inventory1.setItemQuantity(100);
        inventory1.setAmountPresent(100);
        inventory1.setSoldItem(0);

        Inventory inventory2=new Inventory();
        inventory2.setItemId("10126");
        inventory2.setItemName("Body wash");
        inventory2.setItemQuantity(150);
        inventory2.setAmountPresent(150);
        inventory2.setSoldItem(0);

        inventory.add(inventory1);
        inventory.add(inventory2);

        when(inventoryRepo.findAll()).thenReturn(inventory);
        List<Inventory> inventories=inventoryServiceImp.showInventory();
        assertEquals(2,inventories.size());

    }

    //@Test
    //public void ShowInventoryItem(){
    //    List<Inventory> inventory=new ArrayList<>();
    //    Inventory inventory1=new Inventory();
    //    inventory1.setItemId("10127");
    //    inventory1.setItemName("face wash");
    //    inventory1.setItemQuantity(150);
    //    inventory1.setAmountPresent(150);
    //    inventory1.setSoldItem(0);
//
//
    //    when(inventoryRepo.findById(any())).thenReturn(Optional.of(inventory));
    //    Inventory inventory3=inventoryServiceImp.showInventoryItem(inventory.getItemId());
    //    assertEquals("10127",inventory3.getItemId());
    //}


}
