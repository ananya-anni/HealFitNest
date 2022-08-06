package com.example.HealFitNest.Service;

import java.util.List;

import com.example.HealFitNest.Model.Item;

public interface ItemService {
    void saveItem(Item item);
    Item findItemById(String id);
    Item searchItem(String name);
    List<Item> getAllItems(String categoryId);
    List<Item> getAllItem(String subId);
    List<Item> searchAllItems(String itemName);
}
