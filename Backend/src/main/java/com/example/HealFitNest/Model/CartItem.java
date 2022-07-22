package com.example.HealFitNest.Model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class CartItem {
    private String itemId;
    private String itemName;
    private int itemQuantity;
    private BigDecimal itemPrice;
    
}
