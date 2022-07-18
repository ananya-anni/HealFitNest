package com.example.HealFitNest.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HealFitNest.Model.Item;
import com.example.HealFitNest.Service.CartService;
import com.example.HealFitNest.Service.ItemService;

@RestController
@RequestMapping("/api/v3")
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

    @GetMapping("/addItem/{id}")
    public String addProductToCart(@PathVariable String id){
        Item item = itemService.findById(id);
        if (item != null){
            cartService.addItem(item);
            return "Added";
        } else {
            return "Error: Id not present";
        }
    }
    @GetMapping("/removeItem/{id}")
    public String removeProductFromCart(@PathVariable String id){
        Item item = itemService.findById(id);
        if (item != null){
            cartService.removeItem(item);
        }
        return "Deleted";
    }

    @GetMapping("/clearCart")
    public String clearProductsInCart(){
        cartService.clearItem();
        return "Cleared";
    }

    @GetMapping("/checkout")
    public String cartCheckout(){
        cartService.cartCheckout();
        return "Proceeding to checkout";
    }
    
}
