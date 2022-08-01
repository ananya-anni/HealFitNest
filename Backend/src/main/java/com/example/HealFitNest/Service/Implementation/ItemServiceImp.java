package com.example.HealFitNest.Service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.HealFitNest.Handler.ItemNotFoundException;
import com.example.HealFitNest.Model.Item;
import com.example.HealFitNest.Repository.ItemRepo;
import com.example.HealFitNest.Service.ItemService;
import java.util.List;

@Service
public class ItemServiceImp implements ItemService {

    @Autowired
    MongoTemplate mongoTemplate;

    private final ItemRepo itemRepo;

    @Autowired
    public ItemServiceImp(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    public void saveItem(@RequestBody Item item) {
        itemRepo.save(item);
    }

    public Item findItemById(String id) {
        return itemRepo.findById(id).orElseThrow(() -> new ItemNotFoundException("Item not found of this id: "+id));
    }

    public List<Item> getAllItems(String categoryId){
        Query query = new Query();
        query.addCriteria(Criteria.where("categoryId").is(categoryId));
        return mongoTemplate.find(query, Item.class);
    }
}