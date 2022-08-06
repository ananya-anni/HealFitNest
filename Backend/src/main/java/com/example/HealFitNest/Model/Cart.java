package com.example.HealFitNest.Model;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Cart")

public class Cart {
    @Id
    private String cartId;
    private String userId;
    private List<CartItem> cartItems;
    private int countItem;
    private BigDecimal totalPrice;
    private boolean status;
}
