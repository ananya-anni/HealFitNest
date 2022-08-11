package com.example.HealFitNest.Model;

import org.apache.catalina.User;

import lombok.Data;

@Data
public class UserProfile {
    private String firstName;
    private String lastName;
    private long contact;
    private String email;

    public UserProfile(String firstName, String lasString, long contact, String email){
        this.firstName = firstName;
        this.lastName = lasString;
        this.contact = contact;
        this.email = email;
    }
}
