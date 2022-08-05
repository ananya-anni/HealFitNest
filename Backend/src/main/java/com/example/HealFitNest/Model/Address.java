package com.example.HealFitNest.Model;

import com.example.HealFitNest.Handler.NotNullException;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Address")

public class Address{
    @Id
    private String addressId;
    private String userId;
    @NotNull(message = "AddressLine1 cannot be null!")
    private String addressLine1;
    private String addressLine2;
    @NotNull(message = "City cannot be null!")
    private String city;
    @NotNull(message = "State cannot be null!")
    private String state;
    @NotNull(message = "Country cannot be null!")
    private String country;
    @NotNull(message = "PostalCode cannot be null!")
    private String postalCode;

//    public Address(String message) {
//        super(message);
//    }
}