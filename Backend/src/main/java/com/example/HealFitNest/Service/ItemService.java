package com.example.HealFitNest.Service;

import java.util.List;

import com.example.HealFitNest.Model.Item;

public interface ItemService {
    void save(Item item);
    void edit(long id, Item newItem);
    void delete(long id);
    Item findById(long id);
    List<Item> findAllByOrderByIdAsc();
    List<Item> findAllByCategoryId(long categoryId);
    long count();
}
