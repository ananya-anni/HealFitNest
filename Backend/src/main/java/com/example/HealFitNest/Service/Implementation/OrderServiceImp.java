package com.example.HealFitNest.Service.Implementation;

import java.util.List;

import com.example.HealFitNest.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import com.example.HealFitNest.Model.Cart;
// import com.example.HealFitNest.Model.CartItem;
// import com.example.HealFitNest.Model.Item;


import com.example.HealFitNest.Model.Order;
import com.example.HealFitNest.Repository.OrderRepo;
import com.example.HealFitNest.Service.OrderService;

@Service
public class OrderServiceImp implements OrderService{
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private CartService cartService;

    public List<Order> showOrder() {

        return orderRepo.findAll();
    }

    @Override
    public Order showOrderbyId(String orderId) {

        return orderRepo.findById(orderId).get();
    }

    @Override
    public void saveOrder(Order order) {
        orderRepo.save(order);
    }

}
