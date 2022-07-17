package com.example.HealFitNest.Repository;

import com.example.HealFitNest.Model.OrderLine;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderLineRepo extends MongoRepository<OrderLine,String> {
}
