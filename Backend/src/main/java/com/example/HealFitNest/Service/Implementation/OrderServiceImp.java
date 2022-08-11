package com.example.HealFitNest.Service.Implementation;

import java.util.List;

import com.example.HealFitNest.Handler.CartNotFoundException;
import com.example.HealFitNest.Handler.OrderNotFoundException;
import com.example.HealFitNest.Handler.UserNotFoundException;
import com.example.HealFitNest.Model.*;
import com.example.HealFitNest.Repository.AddressRepo;
import com.example.HealFitNest.Repository.CartRepo;
import com.example.HealFitNest.Repository.UserRepo;
import com.example.HealFitNest.Service.AddressService;
import com.example.HealFitNest.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


import com.example.HealFitNest.Repository.OrderRepo;
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
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    private CartRepo cartRepo;
    //@Autowired
    //private EmailSenderService emailSenderService;
    @Autowired
    private UserRepo userRepo;

    //shows list of all orders.
    public List<Order> showOrder() {
        return orderRepo.findAll();
    }

    //
    public Order showOrderbyId(String orderId) {
        return orderRepo.findById(orderId).orElseThrow(()-> new OrderNotFoundException("Order Does Not Exist"));
    }

    //shows all the orders of a particular user
    public List<Order> showOrderByUserId(String userId){
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        return mongoTemplate.find(query, Order.class);
    }

    //stutus is changed when order is placed
    public String statusChange(String orderId,String userId) {
        Order order =orderRepo.findById(orderId).orElseThrow(() -> new OrderNotFoundException("OrderId not found"));
      order.setOrderStatus(true);
        orderRepo.save(order);
        String cartId=order.getCartId();
        Cart cart=cartRepo.findById(cartId).orElseThrow(() -> new CartNotFoundException("Cart does not exsists."));;
        List<CartItem> cartItems=cart.getCartItems();
        Users users=userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User  not found"));
        return "Status Changed";
    }

    //cart items move to order on selecting Proceed to Payment
    public String addOrderBycartId(String cartId) {
        try{
            Cart cart=cartService.showCartofId(cartId);
            Order order=new Order();
            String userId=cart.getUserId();
            List<Address> address_list=addressService.getAllAddress(userId);
            order.setAddressId(address_list.get(0).getAddressId());
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
