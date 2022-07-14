package com.example.HealFitNest.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "Product")



public class Product {

    @Id
    private int productId;
    private String productName;
    private String categoryId;
    private String SubCategoryId;
    private String quantity;
    private String description;
    private int cost;
    private Boolean isAvailable;
    private String ImageUrl;

}
