package com.example.HealFitNest.Service;

import org.springframework.stereotype.Service;

import com.example.HealFitNest.Model.Item;

@Service
public interface CartService {
    void addItem(Item item);
    void removeItem(Item item);
    void clearItem();
    int countItem();
    long totalPrice();
    void cartCheckout();
}
