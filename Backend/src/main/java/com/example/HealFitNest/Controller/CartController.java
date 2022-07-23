package com.example.HealFitNest.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HealFitNest.Model.Cart;
import com.example.HealFitNest.Model.Item;
import com.example.HealFitNest.Service.CartService;
import com.example.HealFitNest.Repository.CartRepo;
import com.example.HealFitNest.Service.ItemService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;


@RestController
@RequestMapping("/api/v4")
public class CartController {

    private final CartService cartService;
    private final ItemService itemService;
    @Autowired 
    private CartRepo cartRepo;
    @Autowired
    public CartController(CartService cartService, ItemService itemService) {
        this.cartService = cartService;
        this.itemService = itemService;
    }

    @GetMapping("/cartshow")
    public List<Cart> allCart()
    {
        return cartRepo.findAll();

    }

    @GetMapping("/cart")
    public String cart(Model model){
        model.addAttribute("products", cartService.itemsInCart());
        model.addAttribute("totalPrice", cartService.totalPrice());
        System.out.println(cartService.totalPrice());
        System.out.println(cartService.itemsInCart());
        return "cart";
    }

<<<<<<< HEAD
    @PostMapping("/addcart")
    public void addtocart(@RequestBody Cart cart){
        cartRepo.save(cart);
    }


    @PostMapping("/addItem/{id}")
=======
    @PostMapping("/cart/addItem/{id}")
>>>>>>> 9dba5be9c10f81dc7354df9aaab75f248713aa91
    public String addProductToCart(@PathVariable String id){
        Item item = itemService.findById(id);
        if (item != null){
            cartService.addItem(item);
            return "Added";
        } 
        else {
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
