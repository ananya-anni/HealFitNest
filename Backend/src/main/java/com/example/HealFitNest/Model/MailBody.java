package com.example.HealFitNest.Model;

import lombok.Data;

@Data
public class MailBody {
    private String userId;
    private String ImageUrl;
    private String orderId;
    private String message;
}
