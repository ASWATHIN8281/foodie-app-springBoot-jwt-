package com.stackroute.RestaurantService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    @Id
    private int restaurantId;
   @NotNull
    @Size(min = 3,max=30,message = "Name cannot be null")
    private String name;
   @NotNull
    @Size(min = 3,max=40,message = "The location cannot be null")
    private String location;
    List<MenuItems> menuItemsList;
}
