package com.example.HealFitNest.Service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.HealFitNest.Model.Item;
import com.example.HealFitNest.Repository.ItemRepo;
import com.example.HealFitNest.Service.ItemService;

@Service
public class ItemServiceImp implements ItemService {
   
    private final ItemRepo itemRepo;

    @Autowired
    public ItemServiceImp(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    public void saveItem(@RequestBody Item item) {
        itemRepo.save(item);
    }

    public Item findItemById(String id) {
        return itemRepo.findById(id).get();
    }



}
