package com.example.HealFitNest.Service.Implementation;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HealFitNest.Model.Item;
import com.example.HealFitNest.Repository.CartRepo;
import com.example.HealFitNest.Service.CartService;

@Service
public class CartServiceImp implements CartService {

    private final CartRepo cartRepo;

    @Autowired
    public CartServiceImp(CartRepo cartRepo){
        this.cartRepo = cartRepo;
    }

    private Map<Item, Long> cart = new LinkedHashMap<>();

    @Override
    public void addItem(Item item) {
        if (cart.containsKey(item)){
            cart.replace(item, cart.get(item) + 1);
        }else{
            cart.put(item, (long) 1);
        }
    }

    @Override
    public void removeItem(Item item) {
        if (cart.containsKey(item)) {
            if (cart.get(item) > 1)
                cart.replace(item, cart.get(item) - 1);
            else if (cart.get(item) == 1) {
                cart.remove(item);
            }
        }
    }

    @Override
    public void clearItem() {
        cart.clear();
    }

    @Override
    public int countItem() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public long totalPrice() {
        
    }

    private long getItemPrice() {
        return 0;
    }

    @Override
    public void cartCheckout() {
        cart.clear();
    }
    
    
}
