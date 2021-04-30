package com.stackroute.FavouriteService.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Favourite-List")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Favourite {
    @Id
   private  String restaurantName;
    private String category;
    private String foodItem;
    private double price;



}
