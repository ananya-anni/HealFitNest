package com.example.HealFitNest.Model;

import lombok.*;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Items")
public class Item {

    @Id
    @NotBlank(message = "ItemId cannot be null!")
    private String itemId;

    @NotBlank(message = "CategoryId cannot be null!")
    private String categoryId;

    @NotBlank(message = "Sub-categoryId cannot be null!")
    private String subCategoryId;

    @NotBlank(message = "Item Name cannot be null!")
    private String itemName;

    @NotBlank(message = "Item description cannot be null!")
    private String itemDescription;

    @NotNull(message = "Item price cannot be null!")
    private BigDecimal itemPrice;

    private Boolean itemAvailable;

    @NotBlank(message = "Item image cannot be null!")
    private String itemImage;
}
