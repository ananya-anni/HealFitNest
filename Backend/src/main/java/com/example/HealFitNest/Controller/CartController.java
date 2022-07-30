package com.example.HealFitNest.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // Shows all the carts present in the cart table
    @GetMapping("/cart")
    public ResponseEntity<List<Cart>> allCart(){
        List<Cart> carts = cartService.showCart();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    // Shows the cart of a particular ID
    @GetMapping("/cart/{cartId}")
    public ResponseEntity<Cart> showCartWithId(@PathVariable String cartId){
        Cart cart = cartService.showCartofId(cartId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    // Add item which is present in the item as well as inventory to the cart
    @PostMapping("/addToCart/{cartId}/{itemId}")
    public ResponseEntity<?> addToCart(@PathVariable String cartId, @PathVariable String itemId, int quantity){
        cartService.addItem(cartId, itemId, quantity);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    // Delete the cart
    @DeleteMapping("/deleteCart/{cartId}")
    public ResponseEntity<?> deleteCart(@PathVariable String cartId){
        cartService.removeCart(cartId);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    // Clears the cart
    @PutMapping("/clearCart/{cartId}")
    public ResponseEntity<?> clearCart(@PathVariable String cartId){
        cartService.clearCart(cartId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    // Delete item from the cart
    @DeleteMapping("/deleteItem/{cartId}/{itemId}")
    public ResponseEntity<?> deleteItem(@PathVariable String cartId, @PathVariable String itemId){
        cartService.removeItem(cartId, itemId);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    // Updates the quantity of an item already present in the cart
    @PutMapping("/updateCart/{cartId}/{itemId}")
    public ResponseEntity<?> updateCart(@PathVariable String cartId, @PathVariable String itemId, int quantity){
        cartService.updateItemQuantity(cartId, itemId, quantity);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    
    // @GetMapping("/cart/checkout")
    // public String cartCheckout(){
    //     cartService.cartCheckout();
    //     return "Proceeding to checkout";
    // }
    
}
