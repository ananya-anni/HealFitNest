package com.example.HealFitNest.Controller;


import java.util.List;

import com.example.HealFitNest.Model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HealFitNest.Model.Order;
import com.example.HealFitNest.Service.OrderService;

@RestController
@RequestMapping("/api/v5")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order")
    public List<Order> allCart(){
        return orderService.showOrder();
    }

    @GetMapping("/order/{orderId}")
    public Order showCartWithId(@PathVariable String orderId){
        return orderService.showOrderbyId(orderId);
    }

}
