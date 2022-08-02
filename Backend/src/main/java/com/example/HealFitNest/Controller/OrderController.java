package com.example.HealFitNest.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.HealFitNest.Model.Order;
import com.example.HealFitNest.Service.OrderService;

@RestController
@RequestMapping("/api/v6")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/addOrder")
    public String addToOrder(@RequestBody Order order){
        orderService.saveOrder(order);
        return "order added sucessfully";
    }


    @GetMapping("/order")
    public List<Order> allCart(){

        return orderService.showOrder();
    }

    @GetMapping("/order/{orderId}")
    public Order showCartWithId(@PathVariable String orderId){

        return orderService.showOrderbyId(orderId);
    }

}
