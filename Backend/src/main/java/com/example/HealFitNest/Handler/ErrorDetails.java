package com.example.HealFitNest.Handler;

import java.util.Date;

import lombok.Getter;

@Getter
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;

    public ErrorDetails(Date date, String message, String details) {
        super();
        this.timestamp = date;
        this.message = message;
        this.details = details;
    }
}
