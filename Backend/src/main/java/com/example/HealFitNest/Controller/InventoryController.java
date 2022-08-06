package com.example.HealFitNest.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.HealFitNest.Model.Inventory;
import com.example.HealFitNest.Service.InventoryService;

@RestController
@RequestMapping("/api/v5")
public class InventoryController {
    @Autowired
    private InventoryService inventService;

    // Add item to the invetory table
    @PostMapping("/addInventItem/{itemId}")
    public void addInventItem(@PathVariable String itemId, @RequestBody Inventory inventory){
        inventService.addNewItem(itemId, inventory.getAmountPresent());
    }

    // List all the items present in the inventory
    @GetMapping("/inventory")
    public List<Inventory> showAll(){
        return inventService.showInventory();
    }

    // Updates the item quantity present in inventory 
    @PostMapping("/updateInventItem/{itemId}")
    public void updateInventItem(@PathVariable String itemId, int amount){
        inventService.updateInventQuantity(itemId, amount);
    }
}
