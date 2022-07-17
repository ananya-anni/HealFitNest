package com.example.HealFitNest.Repository;

import com.example.HealFitNest.Model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepo extends MongoRepository<Order,String> {
}
