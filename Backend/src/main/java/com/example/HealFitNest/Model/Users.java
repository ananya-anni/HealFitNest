package com.example.HealFitNest.Model;
import lombok.*;
import org.apache.logging.log4j.message.Message;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.index.Indexed;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "RegisteredUsers")

public class Users  {
    @Id
    private String userId;
    @NotNull(message = "First Name cannot be null!")
    private String firstName;
    private String lastName;
    @NotNull(message = "Email cannot be null!")
    @Indexed(unique = true)
    private String email;
    @NotNull(message = "Password cannot be null!")
    private String password;
    @NotNull(message = "Contact cannot be null!")
    private String contact;

}
