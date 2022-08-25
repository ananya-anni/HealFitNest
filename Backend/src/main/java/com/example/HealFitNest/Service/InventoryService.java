package com.example.HealFitNest.Service;

import java.util.List;

import com.example.HealFitNest.Model.Inventory;
import com.example.HealFitNest.Model.Item;

public interface InventoryService {
    public void addNewItem(String itemId, int amount);
    public List<Inventory> showInventory();
    public Inventory showInventoryItem(String itemId);
    public boolean itemAvailability(String itemId);
    public void amountVariation(String itemId, int quantity);
    public void updateInventQuantity(String itemId, int quantity);
}
