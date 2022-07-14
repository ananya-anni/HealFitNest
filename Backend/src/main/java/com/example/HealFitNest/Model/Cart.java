package com.example.HealFitNest.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "Cart")

public class Cart {

    @Id
    private int cartId;
    private double totalPrice;
    private int countItem;
    private Address user;
}
