package com.example.HealFitNest.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Inventory")

public class Inventory implements Comparable <Inventory> {
    @Id
    private String itemId;
    private String itemName;
    private int itemQuantity;
    private int amountPresent;
    private int soldItem;

    @Override
    public int compareTo(Inventory inventory) {
        return inventory.getSoldItem()-this.soldItem;
    }
}
