package com.stackroute.FavouriteService.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Document(collection = "Favourite-List")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Favourite {
    @Id
    @NotNull

    private int id;

    @NotNull
    @Size(min = 3,max = 40,message = "The RestaurantName should be of minimum 3 character and maximum 40 character.")
    private  String restaurantName;


    @NotNull
    @Size(min = 3,max = 40,message = "The Category should contain min 3 character and max 40 character.")
    private String category;


    @NotNull
    @Size(min = 2,max = 30,message = "The foodItem should contain minimum 2 characters")
    private String foodItem;

    @NotNull
    private double price;

    @NotNull
    @Email
    @Size(min = 3,message = "The Username should contain minimum 3 characters")
    private String username;
  //add user rate


}
