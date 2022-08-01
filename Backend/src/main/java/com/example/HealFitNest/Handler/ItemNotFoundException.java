package com.example.HealFitNest.Handler;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String message){
        super(message);
    }
}
