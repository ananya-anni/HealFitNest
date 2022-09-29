package com.example.HealFitNest.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "RegisteredUsers")

public class Users  {

    @Id
    private String userId;
    @NotBlank(message = "First Name cannot be null!")
    private String firstName;
    private String lastName;
    @Indexed(unique = true)
    private String email;
    private String password;
    private long contact;
}