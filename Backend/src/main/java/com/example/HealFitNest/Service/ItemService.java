package com.example.HealFitNest.Service;

import com.example.HealFitNest.Model.Item;

public interface ItemService {
    void saveItem(Item item);
    Item findItemById(String id);
}
