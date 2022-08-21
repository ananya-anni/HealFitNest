package com.example.HealFitNest.Handler;

public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(String message){
        super(message);
    }
}
