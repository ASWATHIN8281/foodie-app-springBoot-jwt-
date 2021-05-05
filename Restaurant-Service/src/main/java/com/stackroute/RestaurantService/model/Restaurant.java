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

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "Restaurant")
public class Restaurant {
    /**
     * @Id annotation makes id variable as Primary key
     * @UniqueConstraint annotation to specify that a unique constraint is to be included in the generated DDL for a primary or secondary table
     */

    @Id
    @Unique
    private int restaurantId;

    /**  To store restaurant name**/
    @NotNull
    @Size(min = 3,max=30,message = "Name cannot be null")
    private String name;

    /**  To store restaurant location**/
   @NotNull
    @Size(min = 3,max=40,message = "The location cannot be null")
    private String location;

    /**  To store restaurant menu**/
   @NotNull(message = "The menu list of restaurant cannot be null")
    List<MenuItems> menuItemsList;
}
