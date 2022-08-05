package com.example.HealFitNest.Controller;


import java.util.List;
import com.example.HealFitNest.Handler.OrderNotFoundException;
import com.example.HealFitNest.Model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HealFitNest.Model.Order;
import com.example.HealFitNest.Service.OrderService;

@RestController
@RequestMapping("/api/v6")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping ("/order")
    public ResponseEntity<List<Order>> allCart(){
        List<Order> orders = orderService.showOrder();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<Order> showCartWithId(@PathVariable String orderId){
        Order order=orderService.showOrderbyId(orderId);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }

    @GetMapping("/orderuser/{userId}")
    public ResponseEntity<Order> showOrderUserId(@PathVariable String userId){
        Order order=orderService.showOrderByUserId(userId);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }

    @PostMapping("/addToOrder/{cartId}")
    public ResponseEntity<?> addToCart( @PathVariable String cartId){
        orderService.addOrderBycartId(cartId );
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
    

    @PostMapping("/statusChange/{orderId}")
    public ResponseEntity<?> statuschange(@PathVariable String orderId){
        orderService.statusChange(orderId);
        return new ResponseEntity<>(null,HttpStatus.CREATED);
    }




}
