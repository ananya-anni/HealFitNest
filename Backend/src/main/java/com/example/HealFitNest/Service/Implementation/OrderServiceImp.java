package com.example.HealFitNest.Service.Implementation;

import java.util.List;
import java.util.Optional;

import com.example.HealFitNest.Handler.OrderNotFoundException;
import com.example.HealFitNest.Model.Address;
import com.example.HealFitNest.Model.Cart;
import com.example.HealFitNest.Repository.AddressRepo;
import com.example.HealFitNest.Service.AddressService;
import com.example.HealFitNest.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.HealFitNest.Model.Order;
import com.example.HealFitNest.Repository.OrderRepo;
import com.example.HealFitNest.Repository.AddressRepo;
import com.example.HealFitNest.Service.OrderService;

@Service
public class OrderServiceImp implements OrderService{
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private AddressRepo addressRepo;
    @Autowired
    private CartService cartService;
    @Autowired
    private AddressService addressService;

    public List<Order> showOrder() {
        return orderRepo.findAll();
    }

//    public Cart showCartofId(String cartId){
//        return cartRepo.findById(cartId).get();
//    }

    public Order showOrderbyId(String orderId) {
        return orderRepo.findById(orderId).orElseThrow(()-> new OrderNotFoundException("Order Does Not Exist"));
    }

    public Order showOrderByUserId(String userId){
        return orderRepo.findById(userId).orElseThrow(()-> new OrderNotFoundException("User Does Not Exist"));
    }

    public String statusChange(String orderId) {
        Order order =orderRepo.findById(orderId).orElseThrow(() -> new OrderNotFoundException("OrderId not found"));
        order.setStatus(true);
        orderRepo.save(order);
        return "Status Changed";
    }

    public String addOrderBycartId(String cartId) {
        try{
            Cart cart=cartService.showCartofId(cartId);
            Order order=new Order();
            String userId=cart.getUserId();
            List<Address> address_list=addressService.getAllAddress(userId);
            order.setAddressId(address_list.get(0).getAddressId());
//            Optional<Address> address=addressRepo.findById(userId);
//            order.setAddressId(address.);
//            Optional<Address> address=addressService.showAddbyId(userId);
//            order.setAddressId();
            order.setCartId(cart.getCartId());
            order.setTotalPrice(cart.getTotalPrice());
            order.setUserId(cart.getUserId());
            orderRepo.save(order);
            return "ADDED";
        }
        catch ( OrderNotFoundException e){
            return "Cart Id Not Exist";
        }
    }

}
