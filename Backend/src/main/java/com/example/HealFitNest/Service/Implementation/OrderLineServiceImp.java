package com.example.HealFitNest.Service.Implementation;


import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import com.example.HealFitNest.Model.*;
import com.example.HealFitNest.Service.CartService;
import com.example.HealFitNest.Service.OrderService;
import com.example.HealFitNest.Model.Cart;
import com.example.HealFitNest.Repository.OrderLineRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HealFitNest.Service.OrderLineService;

@Service
public class OrderLineServiceImp implements OrderLineService {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderLineRepo orderLineRepo;


    public String addOrderLineByOrderId(String orderId) {

        Order order = orderService.showOrderbyId(orderId);
        String cartid = order.getCartId();
        Cart cart = cartService.showCartofId(cartid);
        List<CartItem> cartItems = cart.getCartItems();
        Iterator<CartItem> cartItemIterate = cartItems.iterator();

        for (CartItem eachCartItem : cartItems) {
//            price = price.add(eachCartItem.getItemPrice().multiply(BigDecimal.valueOf(eachCartItem.getItemQuantity())));
            OrderLine orderline = new OrderLine();
            orderline.setOrderId(orderId);
            orderline.setItemId(eachCartItem.getItemId());
            orderline.setItemQuantity(eachCartItem.getItemQuantity());
            orderLineRepo.save(orderline);
        }
        return null;
    }
}

