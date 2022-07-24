package com.example.HealFitNest.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HealFitNest.Model.Cart;
import com.example.HealFitNest.Service.CartService;

@RestController
@RequestMapping("/api/v4")
public class CartController {

    @Autowired
    private CartService cartService;
    //private ItemService itemService;

    @PostMapping("/cart")
    public List<Cart> allCart(){
        return cartService.showCart();
    }

    @GetMapping("/cart/{cartId}")
    public Cart showCartWithId(@PathVariable String cartId){
        return cartService.showCartofId(cartId);
    }

    @PostMapping("/addToCart/{cartId}/{itemId}")
    public void addToCart(@PathVariable String cartId, @PathVariable String itemId, int quantity){
        cartService.addItem(cartId, itemId, quantity);
        cartService.showCart();
    }

    @DeleteMapping("/deleteCart/{cartId}")
    public void deleteCart(@PathVariable String cartId){
        cartService.removeCart(cartId);
    }

    @PutMapping("/clearCart/{cartId}")
    public void clearCart(@PathVariable String cartId){
        cartService.clearCart(cartId);
    }

    @DeleteMapping("/deleteCart/{cartId}/{itemId}")
    public void deleteItem(@PathVariable String cartId, @PathVariable String itemId){
        cartService.removeItem(cartId, itemId);
    }

    // @GetMapping("/cart/checkout")
    // public String cartCheckout(){
    //     cartService.cartCheckout();
    //     return "Proceeding to checkout";
    // }
    
}
