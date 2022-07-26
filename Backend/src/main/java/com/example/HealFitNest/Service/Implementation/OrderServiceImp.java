package com.example.HealFitNest.Service.Implementation;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HealFitNest.Model.Cart;
import com.example.HealFitNest.Model.CartItem;
import com.example.HealFitNest.Model.Item;


import com.example.HealFitNest.Model.Order;
import com.example.HealFitNest.Repository.OrderRepo;
import com.example.HealFitNest.Service.OrderService;
import com.example.HealFitNest.Service.CartService;
import com.example.HealFitNest.Service.ItemService;



@Service
public class OrderServiceImp implements OrderService{
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private CartService cartService;


    public List<Order> showOrder() {
        return orderRepo.findAll();
    }

//    public Cart showCartofId(String cartId){
//        return cartRepo.findById(cartId).get();
//    }

    @Override
    public Order showOrderbyId(String orderId) {
        return orderRepo.findById(orderId).get();
    }

}
