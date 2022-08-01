package com.example.HealFitNest.Controller;

import com.example.HealFitNest.Handler.ItemNotFoundException;
import com.example.HealFitNest.Model.Item;
import com.example.HealFitNest.Repository.ItemRepo;
import com.example.HealFitNest.Service.Implementation.ItemServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1")

@RestController

public class ItemController {

    @Autowired
    private ItemServiceImp itemServiceImp;
    @Autowired
    private ItemRepo itemRepo;


    @PostMapping("/addItem")
    public String saveItem(@RequestBody Item item){
        itemRepo.save(item);
        return "Item Added Successfully";
    }

    @GetMapping("/items")
    public List<Item> getItem(){
        return itemRepo.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Item> getItemsById(@PathVariable String id){
        Item item = itemRepo.findById(id)
               .orElseThrow(() -> new ItemNotFoundException("Item with the Id : " + id + " was not found!"));
        return ResponseEntity.ok(item);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable String id, @RequestBody Item updatedItem){
        Item updateItem = itemRepo.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item with the Id : " + id + " was not found!"));
        updateItem.setItemName(updatedItem.getItemName());
        updateItem.setItemDescription(updatedItem.getItemDescription());
        updateItem.setItemPrice(updatedItem.getItemPrice());
        updateItem.setItemImage(updatedItem.getItemImage());

        itemRepo.save(updateItem);
        return ResponseEntity.ok(updateItem);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable String id){
        itemRepo.deleteById(id);
        return "Item Deleted Successfully";
    }

    @GetMapping("/get/{categoryId}")
    public List<Item> getItems(@PathVariable String categoryId){
        return itemServiceImp.getAllItems(categoryId);
    }
}
