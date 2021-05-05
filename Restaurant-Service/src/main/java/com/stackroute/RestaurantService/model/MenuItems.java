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
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "MenuItems")
public class MenuItems {
    /**
     * @Id annotation makes id variable as Primary key
     * @UniqueConstraint annotation to specify that a unique constraint is to be included in the generated DDL for a primary or secondary table
     */

    @Id
    @Unique
    private int menuId;

    /**  To store category of food item**/
    @NotNull
    @Size(min = 3,max=30,message = "The category cannot be null and must contain minimum 3 characters")
    private String category;


    /**  To store rname of food item**/
    @NotNull
    @Size(min = 3,max=40,message = "The name cannot be null and must contain 3 minimum characters")
    private String name;
    @Size(max=100)


    /**  To store description about food item**/
    private String description;


    /**  To store price of food item**/
    private double price;

}
