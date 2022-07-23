package com.example.HealFitNest.Service;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.HealFitNest.Model.Item;

@Service
public interface CartService {
    void addItem(Item item);
    void removeItem(Item item);
    void clearItem();
    int countItem();
    BigDecimal totalPrice();
    void cartCheckout();
    Map<Item, Integer> itemsInCart();
}
