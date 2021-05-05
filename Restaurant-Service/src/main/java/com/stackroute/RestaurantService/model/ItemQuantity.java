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
    /**
     * @Id annotation makes id variable as Primary key
     * @UniqueConstraint annotation to specify that a unique constraint is to be included in the generated DDL for a primary or secondary table
     */

    @Id
    @Unique
    private int itemId;

    /**  To store -food item name**/
    @NotNull
    @Size(min = 3,max = 30,message = "itemName cannot be null and must contain 3 minimum character")
    private String itemName;


    /**  To store price of food item**/
    private double price;


    /**  To store quantity of food item needed**/
    private  int quantity;
}
