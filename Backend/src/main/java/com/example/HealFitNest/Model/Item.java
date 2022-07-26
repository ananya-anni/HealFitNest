package com.example.HealFitNest.Model;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "Items")
public class Item {

    @Id
    private String itemId;
    private String categoryId;
    private String subCategoryId;
    private String itemName;
    private String itemDescription;
    private BigDecimal itemPrice;
    private Boolean itemAvailable;
    private String itemImage;
    public Object getItemPrice;

}
