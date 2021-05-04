package com.stackroute.RestaurantService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.common.aliasing.qual.Unique;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Document(collection = "ItemQuantity")
public class ItemQuantity {
    @Id
    @Unique
    private int itemId;
    @NotNull
    @Size(min = 3,max = 30,message = "itemName cannot be null and must contain 3 minimum character")
    private String itemName;
    private double price;
    private  int quantity;
}
