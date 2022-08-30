
package com.example.HealFitNest.Service.Implementation;

import java.util.List;

import com.example.HealFitNest.Handler.OrderNotFoundException;
import com.example.HealFitNest.Model.Address;
import com.example.HealFitNest.Model.Cart;
import com.example.HealFitNest.Model.*;
import com.example.HealFitNest.Repository.CartRepo;
import com.example.HealFitNest.Repository.UserRepo;
import com.example.HealFitNest.Service.*;
import com.example.HealFitNest.Handler.CartNotFoundException;
import com.example.HealFitNest.Handler.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.example.HealFitNest.Repository.OrderRepo;

@Service
public class OrderServiceImp implements OrderService{
    @Autowired
    private CartRepo cartRepo;
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
    private EmailSenderService emailSenderService;

     @Autowired
     private InventoryService inventoryService;

     @Autowired
     private ItemService itemService;

    //Get all the orders
    public List<Order> showOrder() {
        return orderRepo.findAll();
    }

    //Get order details by orderId
    public Order showOrderbyId(String orderId) {
        return orderRepo.findById(orderId).orElseThrow(()-> new OrderNotFoundException("Order Does Not Exist"));
    }

    //Get order deatils of a particular user
    public List<Order> showOrderByUserId(String userId){
        return orderRepo.findAllByUserId(userId);
    }


    public Order statusChange(String orderId,String userId) {
        Order order =orderRepo.findById(orderId).orElseThrow(() -> new OrderNotFoundException("OrderId not found"));
        order.setOrderStatus(true);
        orderRepo.save(order);
        String cartId=order.getCartId();
        String totalPrice=order.getTotalPrice().toString();
        Cart cart=cartRepo.findById(cartId).orElseThrow(() -> new CartNotFoundException("Cart does not exsists."));;
        List<CartItem> cartItems=cart.getCartItems();



        for(CartItem eachCartItem:cartItems){
            inventoryService.amountVariation(eachCartItem.getItemId(), eachCartItem.getItemQuantity());
            boolean avail = inventoryService.itemAvailability(eachCartItem.getItemId());
            Item item=itemService.findItemById(eachCartItem.getItemId());
            item.setItemAvailable(avail);
            itemService.saveItem(item);
        }




        Users users=userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User  not found"));;
//        String email=users.getEmail();
////        String email=users.getEmail();
  emailSenderService.sendEmail("ananyapriya1003@gmail.com","Order Summary",userId,cartItems,orderId,totalPrice);
cartService.clearCart(cartId);
        return order;
    }




    //Adding order into the database
    public Order addOrderBycartId(String cartId) {
        try{
            Cart cart=cartService.showCartofId(cartId);
            cart.setCartStatus(false);
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

