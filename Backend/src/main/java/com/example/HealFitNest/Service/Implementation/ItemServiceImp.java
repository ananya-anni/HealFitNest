package com.example.HealFitNest.Service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
        return itemRepo.findById(id).get();
    }


    @Override
    public Item searchItem(String name){
        return itemRepo.findByitemName(name);


    }
    @Override
    public List<Item> getAllItem(String subId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("subCategoryId").is(subId));
        return mongoTemplate.find(query, Item.class);
    }

    public List<Item> getAllItems(String categoryId){
//        itemRepo.findAll(new QPageRequest(pageNumber,pageLimit));
//        query.addCriteria(Criteria.where("categoryId").is(categoryId));
        return mongoTemplate.find(new Query(Criteria.where("categoryId").is(categoryId)), Item.class);
    }
//    public List<Item> getFewItems(String categoryId){
//        List<Item> list = new ArrayList<Item>();
//        List<Item> items = itemRepo.findAll();
//        for(Item i:items){
////            if(categoryId == i.getCategoryId())
//                list.add(item);
//        }
////        Query query = new Query();
////        query.addCriteria(Criteria.where("categoryId").is(categoryId));
////        for(int i=0;i<3;i++){
////             list = mongoTemplate.find(query, Item.class);
////        }
//        return list;
//    }



}