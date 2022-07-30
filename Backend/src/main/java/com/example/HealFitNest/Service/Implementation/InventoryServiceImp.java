package com.example.HealFitNest.Service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import com.example.HealFitNest.Model.Cart;
// import com.example.HealFitNest.Model.CartItem;
import com.example.HealFitNest.Model.Inventory;
import com.example.HealFitNest.Model.Item;
import com.example.HealFitNest.Repository.InventoryRepo;
// import com.example.HealFitNest.Service.CartService;
import com.example.HealFitNest.Service.InventoryService;
import com.example.HealFitNest.Service.ItemService;

@Service
public class InventoryServiceImp implements InventoryService {
    @Autowired
    private ItemService itemService;

    @Autowired
    private InventoryRepo inventRepo;

    public void addNewItem(String itemId, int amount){
        try{
            Item item = itemService.findItemById(itemId);
            Inventory inventItem = new Inventory(itemId, item.getItemName(), amount);
            inventRepo.save(inventItem);
            boolean avail = itemAvailability(itemId);
            item.setItemAvailable(avail);
            itemService.saveItem(item);
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public List<Inventory> showInventory(){
        return inventRepo.findAll();
    }

    public Inventory showInventoryItem(String itemId){
        return inventRepo.findById(itemId).get();
    }

    public boolean itemAvailability(String itemId){
        Inventory inventItem = inventRepo.findById(itemId).get();
        if(inventItem.getAmountPresent() > 10){
            return true;
        } else {
            return false;
        }
    }

    public void amountVariation(String itemId, int quantity){
         Inventory inventItem = showInventoryItem(itemId);
         int amount  = inventItem.getAmountPresent() - quantity;
         inventItem.setAmountPresent(amount);
         inventRepo.save(inventItem);
    }
    
    public void updateInventQuantity(String itemId, int quantity){
        Inventory inventItem = showInventoryItem(itemId);
        int amount  = inventItem.getAmountPresent() + quantity;
        inventItem.setAmountPresent(amount);
        inventRepo.save(inventItem);
    }
}
