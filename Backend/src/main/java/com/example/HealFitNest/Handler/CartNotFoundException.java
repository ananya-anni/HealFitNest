package com.example.HealFitNest.Handler;

public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(String message){
        super(message);
    }
}
