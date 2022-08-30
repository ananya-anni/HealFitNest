package com.example.HealFitNest.Controller;

import com.example.HealFitNest.Model.Inventory;
//import com.example.HealFitNest.Service.Implementation.EmailSenderService;
import com.example.HealFitNest.Handler.ItemNotFoundException;
import com.example.HealFitNest.Model.Item;
import com.example.HealFitNest.Repository.ItemRepo;
import com.example.HealFitNest.Service.Implementation.ItemServiceImp;
import com.example.HealFitNest.Service.ItemService;
//import com.example.HealFitNest.Service.Implementation.ItemServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1")

@RestController

public class ItemController {

    @Autowired
    private ItemRepo itemRepo;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemServiceImp itemServiceImp;

    //Adding an item into the database
    @PostMapping("/addItem")
    public String saveItem(@RequestBody Item item){
        try{
            itemRepo.save(item);
            return "Item Added Successfully";
        } catch (ConstraintViolationException e){
            return e.getMessage();
        }
    }

    //Getting all items present in the database
    @GetMapping("/items")
    public List<Item> getItem(){
        return itemRepo.findAll();
    }

    //Getting a particular item from the database using its Id
    @GetMapping("/get/{itemId}")
    public ResponseEntity<Item> getItemsById(@PathVariable String itemId){
        Item item = itemRepo.findById(itemId)
               .orElseThrow(() -> new ItemNotFoundException("Item with the Id : " + itemId + " was not found!"));
        return ResponseEntity.ok(item);
    }

    //Updating an item of a particular item id in the database
    @PutMapping("/update/{itemId}")
    public ResponseEntity<Item> updateItem(@PathVariable String id, @RequestBody Item updatedItem){
        Item updateItem = itemRepo.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item with the Id : " + id + " was not found!"));
        updateItem.setItemName(updatedItem.getItemName());
        updateItem.setItemDescription(updatedItem.getItemDescription());
        updateItem.setItemPrice(updatedItem.getItemPrice());
        updateItem.setItemImage(updatedItem.getItemImage());
        updateItem.setCategoryId(updatedItem.getCategoryId());
        updateItem.setSubCategoryId(updatedItem.getSubCategoryId());
        itemRepo.save(updateItem);
        return ResponseEntity.ok(updateItem);
    }

    //Deleting an item of a particular item id
    @DeleteMapping("/delete/{itemId}")
    public String deleteProduct(@PathVariable String itemId){
        itemRepo.deleteById(itemId);
        return "Item Deleted Successfully";
    }

    //Getting all items of a particular category
    @GetMapping("/get/{categoryId}")
    public List<Item> getItems(@PathVariable String categoryId){
        return itemService.getAllItems(categoryId);
    }

    //Getting item using its name
    @GetMapping("/item/{name}")
    public Item searchByName(@PathVariable String name){
        return itemService.searchItem(name);
    }

    //Getting the bestSeller item
    @GetMapping("/getBestSeller")
    public List<Inventory> BestSellerItems(){
        return itemServiceImp.BestSeller();
    }




    @GetMapping("/search/{itemName}")
    public List<Item> searchItems(@PathVariable String itemName){
        return itemService.searchAllItems(itemName);
    }

//    @GetMapping("/item/find/{subId}")
//    public List<Item> getAddresses(@PathVariable String subId){
//    return itemService.getAllItem(subId);}

}
