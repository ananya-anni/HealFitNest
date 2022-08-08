package com.example.HealFitNest.Service.Implementation;

import java.util.List;

import com.example.HealFitNest.Handler.OrderNotFoundException;
import com.example.HealFitNest.Model.Address;
import com.example.HealFitNest.Model.Cart;
import com.example.HealFitNest.Model.CartItem;
import com.example.HealFitNest.Model.Item;
import com.example.HealFitNest.Service.AddressService;
import com.example.HealFitNest.Service.CartService;
import com.example.HealFitNest.Service.InventoryService;
import com.example.HealFitNest.Service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


import com.example.HealFitNest.Model.Order;
import com.example.HealFitNest.Repository.OrderRepo;
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
    private AddressService addressService;
    @Autowired
    MongoTemplate mongoTemplate;

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
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        return mongoTemplate.find(query, Order.class);
//        return orderRepo.findById(userId).orElseThrow(()-> new OrderNotFoundException("User Does Not Exist"));
    }

    public String statusChange(String orderId) {
        Order order =orderRepo.findById(orderId).orElseThrow(() -> new OrderNotFoundException("OrderId not found"));
        order.setOrderStatus(true);
        orderRepo.save(order);
        return "Status Changed";
    }

    public String addOrderBycartId(String cartId) {
        try{
            Cart cart=cartService.showCartofId(cartId);
            cart.setCartStatus(false);
            Order order=new Order();
            String userId=cart.getUserId();
            List<Address> address_list=addressService.getAllAddress(userId);
            System.out.println(address_list);
            order.setAddressId(address_list.get(0).getAddressId());
//            Optional<Address> address=addressRepo.findById(userId);
//            order.setAddressId(address.);
//            Optional<Address> address=addressService.showAddbyId(userId);
//            order.setAddressId();
            order.setCartId(cart.getCartId());
            order.setTotalPrice(cart.getTotalPrice());
            order.setUserId(cart.getUserId());
            List<CartItem> cartItems = cart.getCartItems();
            for(CartItem eachCartItem : cartItems){
                inventService.amountVariation(eachCartItem.getItemId(), eachCartItem.getItemQuantity());
                boolean avail = inventService.itemAvailability(eachCartItem.getItemId());
                Item item = itemService.findItemById(eachCartItem.getItemId());
                item.setItemAvailable(avail);
                itemService.saveItem(item);
            }
            orderRepo.save(order);
            return "ADDED";
        }
        catch ( OrderNotFoundException e){
            return "Cart Id Not Exist";
        }
    }

}
