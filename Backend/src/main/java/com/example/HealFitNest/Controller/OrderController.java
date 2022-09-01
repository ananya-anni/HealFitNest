package com.example.HealFitNest.Controller;

import java.util.List;

import com.example.HealFitNest.Handler.CartNotFoundException;
import com.example.HealFitNest.Handler.OrderNotFoundException;
import com.example.HealFitNest.Repository.OrderRepo;
import com.example.HealFitNest.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.HealFitNest.Model.Order;
import com.example.HealFitNest.Service.OrderService;
import com.example.HealFitNest.Model.Cart;
import com.example.HealFitNest.Repository.CartRepo;

@RestController
@RequestMapping("/api/v6")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderRepo orderRepo;

    // Show All Order
    @GetMapping("/order")
    public ResponseEntity<List<Order>> allOrder() {
        List<Order> orders = orderService.showOrder();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    //Get Order details via orderId
    @GetMapping("/order/{orderId}")
    public ResponseEntity<Order> showOrderWithId(@PathVariable String orderId) {
        Order order = orderService.showOrderbyId(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    //Show Order using UserId
    @GetMapping("/orderUser/{userId}")
    public ResponseEntity<List<Order>> showOrderUserId(@PathVariable String userId) {
        List<Order> orderList = orderService.showOrderByUserId(userId);
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }


    //@PostMapping("/addToOrder/{cartId}")
    //public ResponseEntity<?> addOrder(@PathVariable String cartId) {
    //    Cart cart = cartRepo.findById(cartId).orElseThrow(() -> new CartNotFoundException("CartId not Valid"));
    //    cart.setCartStatus(false);
    //    cartRepo.save(cart);
    //    orderService.addOrderBycartId(cartId);
    //    return new ResponseEntity<>(null, HttpStatus.CREATED);
    //}
    //Add order when click proceed
    @PostMapping("/addToOrder/{cartId}")
    public ResponseEntity<?> addOrder(@PathVariable String cartId) {
        Cart cart = cartRepo.findById(cartId).orElseThrow(() -> new CartNotFoundException("CartId not Valid"));
        cart.setCartStatus(false);
        cartRepo.save(cart);
        Order order=orderService.addOrderBycartId(cartId);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    // Show Orders in "My Orders"
    @GetMapping("/showOrderHistory/{orderId}")
    public ResponseEntity<Cart> showOrderHistory(@PathVariable String orderId) {
        Order order = orderRepo.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Order not found"));
        String cartId = order.getCartId();
        Cart cart  = cartService.showCartofId(cartId);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }


    // Order Status Change ( when order placed)
    @CrossOrigin
    @PutMapping("/orderStatusChange/{orderId}")
    public ResponseEntity<?> statusChange(@PathVariable String orderId) {

        Order order = orderRepo.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Order not found"));
        String userId = order.getUserId();

        orderService.statusChange(orderId, userId);

        return new ResponseEntity<>(orderId, HttpStatus.CREATED);
    }

    //@PutMapping("/orderStatusChange/{orderId}")
    //public ResponseEntity<?> statusChange(@PathVariable String orderId) {
    //    Order order = orderRepo.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Order not found"));
    //    String userId=order.getUserId();
    //    orderService.statusChange(orderId,userId);
    //    return new ResponseEntity<>(null, HttpStatus.CREATED);
    //}
}

