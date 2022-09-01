package com.example.HealFitNest.Service;

import com.example.HealFitNest.Model.Inventory;
import com.example.HealFitNest.Model.Item;
import com.example.HealFitNest.Model.Order;
import com.example.HealFitNest.Repository.InventoryRepo;
import com.example.HealFitNest.Repository.ItemRepo;
import com.example.HealFitNest.Service.Implementation.InventoryServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

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
    @MockBean
    CartService cartService;


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


}