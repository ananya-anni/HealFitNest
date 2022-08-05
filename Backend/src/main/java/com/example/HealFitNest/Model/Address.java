package com.example.HealFitNest.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Address")

public class Address {
    @Id
    private String addressId;
    private String userId;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private int postalCode;
}