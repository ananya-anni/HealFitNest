package com.example.HealFitNest.Service;

import java.util.List;

import com.example.HealFitNest.Model.Order;

public interface OrderService {
    public List<Order> showOrder();

    Order showOrderbyId(String orderId) ;

    void  saveOrder(Order order);

}
