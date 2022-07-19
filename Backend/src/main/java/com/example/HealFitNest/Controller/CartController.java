package com.example.HealFitNest.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HealFitNest.Model.Item;
import com.example.HealFitNest.Service.CartService;
import com.example.HealFitNest.Service.ItemService;

@RestController
@RequestMapping("/api/v4")
public class CartController {

    private final CartService cartService;
    private final ItemService itemService;

    @Autowired
    public CartController(CartService cartService, ItemService itemService) {
        this.cartService = cartService;
        this.itemService = itemService;
    }

    @GetMapping("/cart")
    public String cart(Model model){
        model.addAttribute("products", cartService.itemsInCart());
        model.addAttribute("totalPrice", cartService.totalPrice());
        System.out.println(cartService.totalPrice());
        System.out.println(cartService.itemsInCart());
        return "cart";
    }

    @PostMapping("/cart/addItem/{id}")
    public String addProductToCart(@PathVariable String id){
        Item item = itemService.findById(id);
        if (item != null){
            cartService.addItem(item);
            return "Added";
        } else {
            return "Error: Id not present.";
        }
    }
    
    @DeleteMapping("/cart/removeItem/{id}")
    public String removeProductFromCart(@PathVariable  String id){
        Item item = itemService.findById(id);
        if (item != null){
            cartService.removeItem(item);
            return "Deleted";
        } else {
            return "Error: Id not present to delete.";
        } 
    }

    @GetMapping("/cart/clearCart")
    public String clearProductsInCart(){
        cartService.clearItem();
        return "Cleared";
    }

    @GetMapping("/cart/checkout")
    public String cartCheckout(){
        cartService.cartCheckout();
        return "Proceeding to checkout";
    }
    
}
