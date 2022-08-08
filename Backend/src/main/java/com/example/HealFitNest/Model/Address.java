package com.example.HealFitNest.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Address")

public class Address{
    @Id
    private String addressId;
    private String userId;

    @NotBlank(message = "AddressLine1 cannot be null!")
    private String addressLine1;
    private String addressLine2;

    @NotBlank(message = "City cannot be null!")
    private String city;

    @NotBlank(message = "State cannot be null!")
    private String state;

    @NotBlank(message = "Country cannot be null!")
    private String country;

    @NotBlank(message = "PostalCode cannot be null!")
    private String postalCode;

}