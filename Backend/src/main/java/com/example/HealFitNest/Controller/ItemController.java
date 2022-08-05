package com.example.HealFitNest.Controller;


import com.example.HealFitNest.Model.Item;
import com.example.HealFitNest.Repository.ItemRepo;
import com.example.HealFitNest.Service.Implementation.ItemServiceImp;
import com.example.HealFitNest.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1")

@RestController

public class ItemController {
    @Autowired
    private ItemRepo itemRepo;
    @Autowired
    private ItemService itemService;

    @PostMapping("/addItem")
    public String saveItem(@RequestBody Item item){
        itemRepo.save(item);
        return "Item Added Successfully";
    }

    @GetMapping("/items")
    public List<Item> getItem(){
        return itemRepo.findAll();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable String id, @RequestBody Item updatedItem){
        Item updateItem = itemRepo.findById(id).orElse(null);
        updateItem.setItemName(updatedItem.getItemName());
        updateItem.setItemdescription(updatedItem.getItemdescription());
        updateItem.setItemPrice(updatedItem.getItemPrice());
        updateItem.setItemImage(updatedItem.getItemImage());
        updateItem.setGetItemPrice(updatedItem.getGetItemPrice());

        itemRepo.save(updateItem);
        return ResponseEntity.ok(updateItem);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable String id){
        itemRepo.deleteById(id);
        return "Item Deleted Successfully";
    }

    @GetMapping("/item/{name}")
    public Item searchByName(@PathVariable String name){
        return itemService.searchItem(name);
    }

    @GetMapping("/get/{categoryId}")
    public List<Item> getItems(@PathVariable String categoryId){
        return itemService.getAllItems(categoryId);
    }
}
