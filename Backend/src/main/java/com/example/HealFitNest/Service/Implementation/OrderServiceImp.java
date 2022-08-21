package com.example.HealFitNest.Service.Implementation;

import java.util.List;

import javax.mail.MessagingException;

import com.example.HealFitNest.Handler.AddressNotFoundException;
import com.example.HealFitNest.Handler.OrderNotFoundException;
import com.example.HealFitNest.Handler.UserNotFoundException;
import com.example.HealFitNest.Model.Address;
import com.example.HealFitNest.Model.Cart;
import com.example.HealFitNest.Model.Order;
import com.example.HealFitNest.Model.Users;
import com.example.HealFitNest.Service.AddressService;
import com.example.HealFitNest.Service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.example.HealFitNest.Repository.AddressRepo;
import com.example.HealFitNest.Repository.OrderRepo;
import com.example.HealFitNest.Repository.UserRepo;
import com.example.HealFitNest.Service.OrderService;

@Service
public class OrderServiceImp implements OrderService{
    @Autowired
    private OrderRepo orderRepo;
    
    @Autowired
    private CartService cartService;

    @Autowired
    private AddressService addressService;
    
    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    MongoTemplate mongoTemplate;

    
    @Autowired
    private AddressRepo addressRepo;
    
    @Autowired
    private EmailSenderService emailSenderService;

    public List<Order> showOrder() {
        return orderRepo.findAll();
    }

    public Order showOrderbyId(String orderId) {
        return orderRepo.findById(orderId).orElseThrow(()-> new OrderNotFoundException("Order Does Not Exist"));
    }

    public List<Order> showOrderByUserId(String userId){
        return orderRepo.findAllByUserId(userId);
    }

    public Order statusChange(String orderId) throws MessagingException {
        Order order =orderRepo.findById(orderId).orElseThrow(() -> new OrderNotFoundException("OrderId not found"));
        order.setOrderStatus(true);
        orderRepo.save(order);
        String subject = "Order Summary";
        Address address  = addressRepo.findById(order.getAddressId()).orElseThrow(()-> new AddressNotFoundException("Address does not exists."));
        Users user = userRepo.findById(order.getUserId()).orElseThrow(()-> new UserNotFoundException("User does not exists."));
        emailSenderService.sendHtmlMessage(orderId, subject, user, address);
        return order;
    }

    public Order addOrderBycartId(String cartId) {
        try{
            Cart cart=cartService.showCartofId(cartId);
            cart.setCartStatus(false);
            cartService.createCart(cart);
            Order order=new Order();
            String userId=cart.getUserId();
            List<Address> address_list=addressService.getAllAddress(userId);
            order.setAddressId(address_list.get(0).getAddressId());
            order.setCartId(cart.getCartId());
            order.setTotalPrice(cart.getTotalPrice());
            order.setUserId(cart.getUserId());
            orderRepo.save(order);
            return order;
        }
        catch ( OrderNotFoundException e){
            return null;
        }
    }
}
