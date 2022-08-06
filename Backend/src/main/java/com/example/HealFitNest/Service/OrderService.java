package com.example.HealFitNest.Service;

import java.util.List;

import com.example.HealFitNest.Model.Order;

public interface OrderService {
    public List<Order> showOrder();
    public Order showOrderbyId(String orderId) ;
    public Order showOrderByUserId(String userId);
    public String addOrderBycartId(String cartId);
    public String statusChange(String orderId);

    //void  saveOrder(Order order);

}
