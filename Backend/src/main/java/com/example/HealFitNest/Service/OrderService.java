package com.example.HealFitNest.Service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.HealFitNest.Model.Cart;
import com.example.HealFitNest.Model.Order;


public interface OrderService {
    public List<Order> showOrder();

    Order showOrderbyId(String orderId) ;

}
