package com.example.HealFitNest.Repository;

import com.example.HealFitNest.Model.Cart;
<<<<<<< HEAD
import com.example.HealFitNest.Model.Item;
=======
//import com.example.HealFitNest.Model.Item;
>>>>>>> 9dba5be9c10f81dc7354df9aaab75f248713aa91

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CartRepo extends MongoRepository<Cart,String> {

}
