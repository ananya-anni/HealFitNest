package com.example.HealFitNest.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HealFitNest.Model.Inventory;
import com.example.HealFitNest.Service.InventoryService;

@RestController
@RequestMapping("/api/v5")
public class InventoryController {
    @Autowired
    private InventoryService inventService;

    // Add item to the invetory table
    @PostMapping("/addInventItem/{itemId}/{amount}")
    public void addInventItem(@PathVariable String itemId, @PathVariable int amount){
        inventService.addNewItem(itemId, amount);
    }

    // List all the items present in the inventory
    @GetMapping("/inventory")
    public List<Inventory> showAll(){
        return inventService.showInventory();
    }

    // Updates the item quantity present in inventory 
    @PostMapping("/updateInventItem/{itemId}/{amount}")
    public void updateInventItem(@PathVariable String itemId, @PathVariable int amount){
        inventService.updateInventQuantity(itemId, amount);
    }
}
