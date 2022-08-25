package com.example.HealFitNest.Service.Implementation;

import com.example.HealFitNest.Model.Inventory;
import com.example.HealFitNest.Repository.InventoryRepo;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImp implements ItemService {


    @Autowired
    MongoTemplate mongoTemplate;

    private final ItemRepo itemRepo;
    @Autowired
    private InventoryRepo inventoryRepo;


    @Autowired
    public ItemServiceImp(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }


    List<Integer> soldItemList=new ArrayList<Integer>();


    @Override
    public void  saveItem(@RequestBody Item item) {
        itemRepo.save(item);
    }

    @Override
    public Item findItemById(String id) {
        return itemRepo.findById(id).orElseThrow(() -> new ItemNotFoundException("Item not found of this id: "+id));
    }
    //Get items by its category
    @Override
    public List<Item> getAllItems(String categoryId){
//        Query query = new Query();
//        query.addCriteria(Criteria.where("categoryId").is(categoryId));
//        return mongoTemplate.find(query, Item.class);
        return itemRepo.findByCategoryId(categoryId);
    }

    //Search item by name
    @Override
    public Item searchItem(String name){
        return itemRepo.findByItemName(name).orElseThrow(() -> new ItemNotFoundException("Item not found of this name: "+name));


    }
    //Get items by its subcategory
    @Override
    public List<Item> getAllItem(String subId) {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("subCategoryId").is(subId));
////        query.limit(3);
//        return mongoTemplate.find(query, Item.class);
        return itemRepo.findBySubCategoryId(subId);
    }

    public List<Inventory> BestSeller() {
        List<Inventory> itemList = inventoryRepo.findAll();
        for (Inventory eachInventory : itemList) {

            int itemQuantity = eachInventory.getItemQuantity();
            int amountPresent = eachInventory.getAmountPresent();
            int soldItem = itemQuantity - amountPresent;
            eachInventory.setSoldItem(soldItem);
        }
        Collections.sort(itemList);
        return itemList;
    }
//    Search items using regex
    @Override
    public List<Item> searchAllItems(String itemName){
        Query query = new Query();
        query.addCriteria(Criteria.where("itemName").regex(itemName));
        return mongoTemplate.find(query, Item.class);
    }
}