package com.example.HealFitNest.Service.Implementation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.HealFitNest.Handler.ItemNotFoundException;
import com.example.HealFitNest.Model.Inventory;
import com.example.HealFitNest.Model.Item;
import com.example.HealFitNest.Repository.InventoryRepo;
import com.example.HealFitNest.Service.InventoryService;
import com.example.HealFitNest.Service.ItemService;

@Service
public class InventoryServiceImp implements InventoryService {
    @Autowired
    private ItemService itemService;

    @Autowired
    private InventoryRepo inventRepo;

    //Adding item into the inventory
    public void addNewItem(String itemId, int amount){
        Item item = itemService.findItemById(itemId);
        if(item!=null) {
            Inventory inventItem = new Inventory(itemId, item.getItemName(), amount, amount, 0);
            inventRepo.save(inventItem);
            boolean avail = itemAvailability(itemId);
            item.setItemAvailable(avail);
            itemService.saveItem(item);
        }
    }
    //Show list of inventory/items
    public List<Inventory> showInventory(){
        return inventRepo.findAll();
    }
    //Get item by its id
    public Inventory showInventoryItem(String itemId){
        return inventRepo.findById(itemId).orElseThrow(() -> new ItemNotFoundException("Item does not exists."));
    }
    //If item is present in inventory it will return true as item availability
    public boolean itemAvailability(String itemId){
        Inventory inventItem = inventRepo.findById(itemId).orElseThrow(() -> new ItemNotFoundException("Item does not exists."));
        return inventItem.getAmountPresent() > 10;
    }
    ////It reduces the amount of item in inventory as soon as item gets added to cart
    public void amountVariation(String itemId, int quantity){
        Inventory inventItem = showInventoryItem(itemId);
        int amount  = inventItem.getAmountPresent() - quantity;
        inventItem.setAmountPresent(amount);
        inventRepo.save(inventItem);
    }

    //Update the inventory items
    public void updateInventQuantity(String itemId, int quantity){
        Inventory inventItem = showInventoryItem(itemId);
        int itemQuantity=inventItem.getItemQuantity()+quantity;
        inventItem.setItemQuantity(itemQuantity);
        int amount  = inventItem.getAmountPresent() + quantity;
        inventItem.setAmountPresent(amount);
        inventRepo.save(inventItem);
        Item item = itemService.findItemById(itemId);
        boolean avail = itemAvailability(itemId);
        item.setItemAvailable(avail);
        itemService.saveItem(item);
    }
}