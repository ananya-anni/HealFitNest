package com.example.HealFitNest.Model;

import lombok.*;

import java.util.List;

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
    private double totalPrice;
    private int countItem;
    private List<CartItem> cartItem;
}
