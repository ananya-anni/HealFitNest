package com.example.HealFitNest.Service;

import com.example.HealFitNest.Config.UserDetailService;
import com.example.HealFitNest.Repository.CartRepo;
import com.example.HealFitNest.Repository.UserRepo;
import com.example.HealFitNest.Service.Implementation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.example.HealFitNest.Model.Order;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.example.HealFitNest.Repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderServiceTest {


   // @Mock
   // UserService userService;
//
   // @Mock
   // CartService cartService;

    OrderRepo orderRepo=mock(OrderRepo.class);
    @Mock
    CartRepo cartRepo;

    @Mock
    UserRepo userRepo;

    @Mock
    EmailSenderService emailSenderService;

    @Mock
    OrderService orderService;
    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.initMocks(this);
    }


    @InjectMocks
    OrderServiceImp orderServiceImp;

   // @InjectMocks
   // CartServiceImp cartServiceImp;
//
   // @InjectMocks
   // UserDetailService userDetailService;

    @Autowired
    Order order1=new Order();
    @Test
    public void showOrder() {
        List<Order> order=new ArrayList<>();

        order1.setUserId("62ee2d1fec74e75beb7ea5dd");
        order1.setCartId("123");
        order1.setAddressId("123456");
        order1.setTotalPrice(BigDecimal.valueOf(40));

        Order order2=new Order();
        order2.setUserId("62ee2d1fec74e75beb7ea5dd");
        order2.setCartId("62ee9c90a05e8e657c087cc8");
        order2.setAddressId("123456");
        order2.setTotalPrice(BigDecimal.valueOf(120));

        order.add(order1);
        order.add(order2);

        when(orderRepo.findAll()).thenReturn(order);
        List<Order> orders=orderServiceImp.showOrder();
        assertEquals(2,orders.size());


    }

    @Test
    public void showOrderbyId(){
        Order order1=new Order();
        order1.setUserId("62ee2d1fec74e75beb7ea5dd");
        order1.setCartId("62ee9e89a05e8e657c087ccc");
        order1.setAddressId("123456");
        order1.setTotalPrice(BigDecimal.valueOf(20));

        when(orderRepo.findById(any())).thenReturn(Optional.of(order1));
        Order orders=orderServiceImp.showOrderbyId(order1.getOrderId());
        assertEquals("62ee9e89a05e8e657c087ccc",orders.getCartId());

    }

//
//    @Test
//    public void addOrderBycartId(){
//        List<Cart> carts=new ArrayList<>();
//        Cart cart =new Cart();
//        cart.setCartId("567");
//        cart.setCartStatus(false);
//        cart.setTotalPrice(BigDecimal.valueOf(100));
//        cart.setUserId("62ee2d1fec74e75beb7ea5dd");
//        cart.setCountItem(1);
//        List<CartItem> cartItems=new ArrayList<>();
//        CartItem cartItem=new CartItem("100301","Toor Dal",BigDecimal.valueOf(100),1);
//        cartItems.add(cartItem);
//        cart.setCartItems(cartItems);
//        carts.add(cart);
//
//        List<Order> order=new ArrayList<>();
//        Order order1=new Order();
//        order1.setUserId(cart.getUserId());
//        order1.setCartId(cart.getCartId());
//        order1.setAddressId("123456");
//        order1.setOrderStatus(true);
//        order1.setTotalPrice(cart.getTotalPrice());
//
//        order.add(order1);
//        when(orderRepo.findById(any())).thenReturn(Optional.of(order1));
//        Order orders=orderServiceImp.addOrderBycartId(carts.get(0).getCartId());
//        assertNotNull(orders);
//    }


    @Test
    public void statusChange(){
        List<Order> order=new ArrayList<>();
        Order order1=new Order();
        order1.setOrderId("23456");
        order1.setUserId("UI678");
        order1.setCartId("CI004");
        order1.setAddressId("123456");
        order1.setOrderStatus(true);
        order1.setTotalPrice(BigDecimal.valueOf(20));

        order.add(order1);

        when(orderRepo.findById("CI004")).thenReturn(Optional.of(order1));
        when(orderRepo.findById(order1.getOrderId())).thenReturn(Optional.of(order1));
        Order orders=orderServiceImp.statusChange(order1.getOrderId(),order1.getUserId());
        assertEquals(true,orders.getOrderStatus());

    }

    @Test
    public void showOrderByUserId(){
        List<Order> order=new ArrayList<>();
        Order order1=new Order();
        order1.setUserId("62ee2d1fec74e75beb7ea5dd");
        order1.setCartId("123");
        order1.setAddressId("123456");
        order1.setTotalPrice(BigDecimal.valueOf(40));

        Order order2=new Order();
        order2.setUserId("62ee2d1fec74e75beb7ea5dd");
        order2.setCartId("62ee9c90a05e8e657c087cc8");
        order2.setAddressId("123456");
        order2.setTotalPrice(BigDecimal.valueOf(120));

        order.add(order1);
        order.add(order2);

        when(orderRepo.findAllByUserId(any())).thenReturn(order);
        List<Order> orders=orderServiceImp.showOrderByUserId(order1.getUserId());
        assertEquals(2,orders.size());
    }
}