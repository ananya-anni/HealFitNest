

package com.example.HealFitNest.Service.Implementation;

import java.util.List;

import com.example.HealFitNest.Model.*;
import com.example.HealFitNest.Repository.OrderLineRepo;
import com.example.HealFitNest.Service.CartService;
import com.example.HealFitNest.Service.OrderService;
import com.example.HealFitNest.Service.OrderLineService;
import com.example.HealFitNest.Model.Cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderLineServiceImp implements OrderLineService {
    @Autowired
    private OrderLineRepo orderLineRepo;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    public String addOrderLineByOrderId(String orderId) {
        Order order = orderService.showOrderbyId(orderId);
        String cartid = order.getCartId();
        Cart cart = cartService.showCartofId(cartid);
        List<CartItem> cartItems = cart.getCartItems();
        for (CartItem eachCartItem : cartItems) {
            OrderLine orderline = new OrderLine();
            orderline.setOrderId(orderId);
            orderline.setItemId(eachCartItem.getItemId());
            orderline.setItemQuantity(eachCartItem.getItemQuantity());
            orderLineRepo.save(orderline);
        }
        return "ADDED";
    }
}
