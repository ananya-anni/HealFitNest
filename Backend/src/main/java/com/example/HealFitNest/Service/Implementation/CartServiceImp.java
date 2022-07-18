package com.example.HealFitNest.Service.Implementation;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

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

    private Map<Item, Integer> cart = new LinkedHashMap<>();

    @Override
    public void addItem(Item item) {
        if (cart.containsKey(item)){
            cart.replace(item, cart.get(item) + 1);
        }else{
            cart.put(item, 1);
        }
        cartRepo.save(item);
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
        return cart.entrySet().stream().map(k -> k.getValue())
        .mapToInt(Integer:: intValue)
        .sum();
    }

    @Override
    public BigDecimal totalPrice() {
        return cart.entrySet().stream()
        .map(k -> k.getKey().getItemPrice().multiply(BigDecimal.valueOf(k.getValue()))).sorted()
        .reduce(BigDecimal::add)
        .orElse(BigDecimal.ZERO);
    }

    @Override
    public void cartCheckout() {
        cart.clear();
    }   

    @Override
    public Map<Item, Integer> itemsInCart() {
        return Collections.unmodifiableMap(cart);
    }
}
