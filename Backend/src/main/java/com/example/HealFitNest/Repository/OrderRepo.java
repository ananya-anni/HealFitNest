package com.example.HealFitNest.Repository;

import com.example.HealFitNest.Model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepo extends MongoRepository<Order,String> {
    List<Order> findAllByUserId(String userId);
    Order findOrderByUserId(String orderId, String userId);
    Optional<Order> findByCartId(String cartId);
}
