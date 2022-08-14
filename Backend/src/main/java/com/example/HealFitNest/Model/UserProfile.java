package com.example.HealFitNest.Model;
import lombok.*;
import org.apache.logging.log4j.message.Message;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.index.Indexed;

import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.catalina.User;

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
