//package com.example.HealFitNest.Service;
//
//import com.example.HealFitNest.Model.Item;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Service
//public class SubCategoryService {
//    @Autowired
//    MongoTemplate mongoTemplate;
//
//    public List<Item> getAllItems(String subCategoryId){
//        Query query = new Query();
//        query.addCriteria(Criteria.where("SubCategoryId").is(subCategoryId));
//        return mongoTemplate.find(query, Item.class);
//    }
//}
//
