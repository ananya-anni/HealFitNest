package com.example.HealFitNest.Model;

import lombok.*;
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
    private long itemId;
    private String categoryId;
    private String SubCategoryId;
    private String itemName;
    private String itemdescription;
    private double itemPrice;
    private Boolean itemAvailable;
    private String itemImage;
    public Object getItemPrice;

}
