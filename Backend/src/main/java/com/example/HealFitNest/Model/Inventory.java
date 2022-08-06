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

public class Inventory {
    @Id
    private String itemId;
    private String itemName;
    private int amountPresent;
}
