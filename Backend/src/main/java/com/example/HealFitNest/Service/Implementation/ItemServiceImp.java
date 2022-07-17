package com.example.HealFitNest.Service.Implementation;

import java.util.List;

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

    @Override
    public void save(@RequestBody Item item) {
        itemRepo.save(item);
    }

    @Override
    public void edit(long id, Item newItem) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(long id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Item findById(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Item> findAllByOrderByIdAsc() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Item> findAllByCategoryId(long categoryId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }



}
