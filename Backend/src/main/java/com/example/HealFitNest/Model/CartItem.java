package com.example.HealFitNest.Model;

import java.math.BigDecimal;

import lombok.*;

@Data
public class CartItem {
    private String itemId;
    private String itemName;
    private BigDecimal itemPrice;
    private int itemQuantity;
    private String itemImage;


    public CartItem(String itemId, String itemName, BigDecimal itemPrice, int itemQuantity, String itemImage){
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
        this.itemImage = itemImage;
    }
}