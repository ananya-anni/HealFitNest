package com.example.HealFitNest.Service;

import java.util.List;

import com.example.HealFitNest.Model.Item;

public interface ItemService {
    void saveItem(Item item);
    Item findItemById(String id);
    Item searchItem(String name);
    List<Item> getAllItem(String subId);
}
