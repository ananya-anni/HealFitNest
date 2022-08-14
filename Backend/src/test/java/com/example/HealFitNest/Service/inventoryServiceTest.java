package com.example.HealFitNest.Service;

import com.example.HealFitNest.Config.UserDetailService;
import com.example.HealFitNest.Repository.InventoryRepo;
import com.example.HealFitNest.Service.Implementation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.example.HealFitNest.Model.Inventory;
import com.example.HealFitNest.Model.Order;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.example.HealFitNest.Repository.OrderRepo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class inventoryServiceTest {

    InventoryRepo inventoryRepo=mock(InventoryRepo.class);

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.initMocks(this);
    }

    @InjectMocks
    InventoryServiceImp inventoryServiceImp;

    @InjectMocks
    ItemServiceImp itemServiceImp;

    @Test
    public void showInventory(){
        List<Inventory> inventory =new ArrayList<>();

        Inventory iventory1=new Inventory();
        iventory1.setItemId("9999");
        iventory1.setItemName("book");
        iventory1.setItemQuantity(20);
        iventory1.setAmountPresent(10);
        iventory1.setSoldItem(10);

        Inventory iventory2=new Inventory();
        iventory1.setItemId("9998");
        iventory1.setItemName("pen");
        iventory1.setItemQuantity(40);
        iventory1.setAmountPresent(20);
        iventory1.setSoldItem(20);

        inventory.add(iventory1);
        inventory.add(iventory2);

        when(inventoryRepo.findAll()).thenReturn(inventory);
        List<Inventory> Inventorys=inventoryServiceImp.showInventory();
        assertEquals(2,Inventorys.size());

}
}
