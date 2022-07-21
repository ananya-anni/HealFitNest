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
    private String cartId;
    private String userId;
    private String itemId;
    private double totalPrice;
    private int countItem;
}
