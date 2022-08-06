package com.example.HealFitNest.Model;

import lombok.Data;

import java.util.List;

@Data
public class MailBody {
    private String message;
    private String orderId;
//    private String userId;
    private List<CartItem> cartItems;
//    private String ImageUrl;


//    private String message;
}
