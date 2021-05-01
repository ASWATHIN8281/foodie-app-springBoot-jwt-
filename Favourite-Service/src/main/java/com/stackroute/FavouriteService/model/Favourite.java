package com.stackroute.FavouriteService.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;


@Document(collection = "Favourite-List")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Favourite {
    @Id
    private int id;
    @Size(max = 40)
    private  String restaurantName;
    @Size(max = 40)
    private String category;
    @Size(max = 30)
    private String foodItem;
    private double price;



}
