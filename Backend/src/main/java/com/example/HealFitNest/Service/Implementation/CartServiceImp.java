package com.example.HealFitNest.Service.Implementation;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HealFitNest.Model.Cart;
import com.example.HealFitNest.Model.CartItem;
import com.example.HealFitNest.Model.Item;
import com.example.HealFitNest.Repository.CartRepo;
import com.example.HealFitNest.Service.CartService;
import com.example.HealFitNest.Service.ItemService;

@Service
public class CartServiceImp implements CartService {
    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private ItemService itemService;

    List<CartItem> addCartItem =  new ArrayList<CartItem>();

    public void addItem(String cartId, String itemId, int quantity) {
        Item item = itemService.findItemById(itemId);
        CartItem cartItem = new CartItem(itemId, item.getItemName(), item.getItemPrice(), quantity);
        addCartItem.add(cartItem);
        Cart cart = new Cart();
        cart.setCartId(cartId);
        cart.setCartItems(addCartItem);
        cartRepo.save(cart);
        int count = countItem(cartId);
        cart.setCountItem(count);
        BigDecimal total = totalPrice(cartId);
        cart.setTotalPrice(total);
        cartRepo.save(cart);
    }
    
    public List<Cart> showCart(){
        return cartRepo.findAll();
    }

    public Cart showCartofId(String cartId){ 
        return cartRepo.findById(cartId).get();
    }

    public void removeCart(String cartId){
        cartRepo.deleteById(cartId);
    }

    public int countItem(String cartId){
        Cart cart = cartRepo.findById(cartId).get();
        List<CartItem> cartItems = cart.getCartItems();
        int count = 0;
        for(CartItem eachCartItem : cartItems){
            count = count + eachCartItem.getItemQuantity();
        }
        return count;
    }

    public BigDecimal totalPrice(String cartId){
        Cart cart = cartRepo.findById(cartId).get();
        List<CartItem> cartItems = cart.getCartItems();
        BigDecimal price = new BigDecimal(0);
        for(CartItem eachCartItem : cartItems){
            price = price.add(eachCartItem.getItemPrice().multiply(BigDecimal.valueOf(eachCartItem.getItemQuantity())));
        }
        return price;
    }

    public void clearCart(String cartId){
        Cart cart = cartRepo.findById(cartId).get();
        List<CartItem> cartItems = cart.getCartItems();
        cartItems.clear();
        cart.setCountItem(0);
        cart.setTotalPrice(null);
        cartRepo.save(cart);
    }

    public void removeItem(String cartId, String itemId){
        Cart cart = cartRepo.findById(cartId).get();
        List<CartItem> cartItems = cart.getCartItems();
        int removeIndex=0;
        for(CartItem eachCartItem : cartItems){
            int index  = cartItems.indexOf(eachCartItem); 
            if(eachCartItem.getItemId().equalsIgnoreCase(itemId)){
                removeIndex = index;
            }
        }
        cartItems.remove(removeIndex);
        cartRepo.save(cart);
        int count = countItem(cartId);
        cart.setCountItem(count);
        BigDecimal total = totalPrice(cartId);
        cart.setTotalPrice(total);
        cartRepo.save(cart);
    }

    // @Override
    // public void cartCheckout() {
    //     cart.clear();
    // }   
}
