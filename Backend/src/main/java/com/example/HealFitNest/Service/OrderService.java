package com.example.HealFitNest.Service;

import java.util.List;

import com.example.HealFitNest.Model.Order;

public interface OrderService {
    public List<Order> showOrder();
    public Order showOrderbyId(String orderId) ;
    public List<Order> showOrderByUserId(String userId);
    public Order addOrderBycartId(String cartId);
    public Order statusChange(String orderId,String userId);

}