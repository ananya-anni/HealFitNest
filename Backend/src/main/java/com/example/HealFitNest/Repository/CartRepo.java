package com.example.HealFitNest.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.HealFitNest.Model.Cart;

@Repository
public interface CartRepo extends MongoRepository<Cart,String> {

    Cart findByCartStatus();


}