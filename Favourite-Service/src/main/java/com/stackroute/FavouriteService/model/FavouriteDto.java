package com.stackroute.FavouriteService.model;


import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.Setter;
import org.springframework.data.annotation.Id;

/**
 *  @Getter generates getter
 * @Setter generates setter
 *   @Id annotation declares  this field as a unique identifier
 * **/
@Getter
@Setter
public class FavouriteDto {
    @Id
    private int id;

    private  String restaurantName;

    private String category;

    private String foodItem;
    private double price;
    private String username;

}
