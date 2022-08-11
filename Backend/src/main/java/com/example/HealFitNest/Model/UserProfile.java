package com.example.HealFitNest.Model;
import lombok.*;
import org.apache.logging.log4j.message.Message;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.index.Indexed;

import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "RegisteredUsers")

public class UserProfile {
    @Id
    private String userId;

    @NotBlank(message = "First Name cannot be null!")
    private String firstName;
    private String lastName;

    @Indexed(unique = true)
    private String email;

    private long contact;

    public UserProfile(String firstName, String lasString, long contact, String email){
        this.firstName = firstName;
        this.lastName = lasString;
        this.contact = contact;
        this.email = email;
    }
    
}
