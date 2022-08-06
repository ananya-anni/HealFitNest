package com.example.HealFitNest.Handler;

public class OrderNotFoundException extends RuntimeException  {

    public OrderNotFoundException (String message){
        super(message);
    }


}