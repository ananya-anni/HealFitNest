
package com.example.HealFitNest.Service.Implementation;

import java.util.List;

import com.example.HealFitNest.Handler.CartNotFoundException;
import com.example.HealFitNest.Handler.OrderNotFoundException;
import com.example.HealFitNest.Handler.UserNotFoundException;
import com.example.HealFitNest.Model.Address;
import com.example.HealFitNest.Model.Cart;
import com.example.HealFitNest.Model.CartItem;
import com.example.HealFitNest.Model.Item;
import com.example.HealFitNest.Model.Order;
import com.example.HealFitNest.Model.Users;
import com.example.HealFitNest.Service.AddressService;
import com.example.HealFitNest.Service.CartService;
import com.example.HealFitNest.Service.InventoryService;
import com.example.HealFitNest.Service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.example.HealFitNest.Repository.CartRepo;
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
    private InventoryService inventService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private CartServiceImp cartServiceImp;

    @Autowired
    private AddressService addressService;
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    private CartRepo cartRepo;
//    @Autowired
//    private EmailSenderService emailSenderService;
    @Autowired
    private UserRepo userRepo;

//    public List<Address> getAllAddress(String userId){
//        Query query = new Query();
//        query.addCriteria(Criteria.where("userId").is("62ee2d1fec74e75beb7ea5dd"));
//        return mongoTemplate.find(query, Address.class);
//    }

    public List<Order> showOrder() {
        return orderRepo.findAll();
    }

//    public Cart showCartofId(String cartId){
//        return cartRepo.findById(cartId).get();
//    }

    public Order showOrderbyId(String orderId) {
        return orderRepo.findById(orderId).orElseThrow(()-> new OrderNotFoundException("Order Does Not Exist"));
    }

    public List<Order> showOrderByUserId(String userId){

//        Query query = new Query();
//        query.addCriteria(Criteria.where("userId").is(userId));
//        if (mongoTemplate.find(query, Order.class)==null)
//            throw new OrderNotFoundException("User not found");
//        return mongoTemplate.find(query, Order.class);
//

        return orderRepo.findAllByUserId(userId);
    }



    public Order statusChange(String orderId) {
        Order order =orderRepo.findById(orderId).orElseThrow(() -> new OrderNotFoundException("OrderId not found"));
//        Users users=userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User  not found"));;
        order.setOrderStatus(true);
        orderRepo.save(order);
//        String cartId=order.getCartId();
//        Cart cart=cartRepo.findById(cartId).orElseThrow(() -> new CartNotFoundException("Cart does not exsists."));;
//        List<CartItem> cartItems=cart.getCartItems();
////        String email=users.getEmail();
//        emailSenderService.sendEmail("ish.asthana@gmail.com","Order Summary",emailSenderService.sendBody(userId,cartItems,orderId));
        return order;
    }

    public Order addOrderBycartId(String cartId) {
        try{
            Cart cart=cartService.showCartofId(cartId);
//            Cart cart=cartRepo.findById(cartId);
            cart.setCartStatus(false);
            Order order=new Order();
            String userId=cart.getUserId();
            List<Address> address_list=addressService.getAllAddress(userId);
//            System.out.println(address_list);
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
