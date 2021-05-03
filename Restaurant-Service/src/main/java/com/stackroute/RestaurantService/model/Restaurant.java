package com.stackroute.RestaurantService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
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
    @Size(max=30)
    private String name;
    @Size(max=40)
    private String location;
    List<MenuItems> menuItemsList;
}
