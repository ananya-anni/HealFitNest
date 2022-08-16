package com.example.HealFitNest.Model;

import lombok.Data;

@Data
public class UserProfile {
    private String firstName;
    private String lastName;
    private long contact;
    private String email;

    public UserProfile(String firstName, String lastName, long contact, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.contact = contact;
        this.email = email;
    }
}
