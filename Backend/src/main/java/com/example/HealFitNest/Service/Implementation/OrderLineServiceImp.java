package com.example.HealFitNest.Service.Implementation;


import java.util.Iterator;
import java.util.List;

import com.example.HealFitNest.Handler.OrderNotFoundException;
import com.example.HealFitNest.Model.*;
import com.example.HealFitNest.Repository.OrderRepo;
import com.example.HealFitNest.Repository.OrderLineRepo;
import com.example.HealFitNest.Service.CartService;
import com.example.HealFitNest.Service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HealFitNest.Service.OrderLineService;

public class OrderLineServiceImp implements OrderLineService{

    @Autowired
    private OrderLineRepo orderLineRepo;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;

//    public String addOrderLineByOrdertId(String cartId) {
//
//
////        try{
////            Cart cart=cartService.showCartofId(cartId);
////            Order order=new Order();
////            String userId=cart.getUserId();
////            List<Address> address_list=addressService.getAllAddress(userId);
////            order.setAddressId(address_list.get(0).getAddressId());
//////            Optional<Address> address=addressRepo.findById(userId);
//////            order.setAddressId(address.);
//////            Optional<Address> address=addressService.showAddbyId(userId);
//////            order.setAddressId();
////            order.setCartId(cart.getCartId());
////            order.setTotalPrice(cart.getTotalPrice());
////            order.setUserId(cart.getUserId());
////            orderRepo.save(order);
////            return "ADDED";
////        }
////        catch ( OrderNotFoundException e){
////            return "Cart Id Not Exist";
////        }
//    }

    public String addOrderLineByOrderId(String orderId) {

        Order order=orderService.showOrderbyId(orderId);
        String cartid=order.getCartId();
        Cart cart =cartService.showCartofId(cartid);
        List<CartItem> cartItems=cart.getCartItems();
        Iterator<CartItem> cartItemIterate=cartItems.iterator();
        OrderLine orderline=new OrderLine();
        while (cartItemIterate.hasNext()){
            orderline.setOrderId(orderId);
//            orderline.setOrderLineId();
//            orderline.setItemId();
//            orderline.setItemQuantity();
//            orderLineRepo.save(orderline);



        }



        return null;
    }
}
