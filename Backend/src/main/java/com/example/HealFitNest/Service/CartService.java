package com.example.HealFitNest.Service;

import java.math.BigDecimal;
import java.util.List;

import com.example.HealFitNest.Model.Cart;

public interface CartService {
    public Cart createCart(Cart cart);
    public void addFirstItem(String userId, String cartId, String itemId, int quantity);
    public void addItem(String cartId, String itemId, int quantity);
    public List<Cart> showCart();
    public Cart showCartofId(String cartId);
    public int countItem(String cartId);
    public BigDecimal totalPrice(String cartId);
    public void clearCart(String cartId);
    public void removeItem(String cartId, String itemId);
    public void updateItemQuantityAdd(String cartId, String itemId);
    public void updateItemQuantitySub(String cartId, String itemId);
    public String showCurrentStatus(String userId);
}