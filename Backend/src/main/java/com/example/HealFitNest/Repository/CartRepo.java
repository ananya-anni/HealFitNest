package com.example.HealFitNest.Repository;

import com.example.HealFitNest.Model.Cart;
//import com.example.HealFitNest.Model.Item;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CartRepo extends MongoRepository<Cart,String> {

}
