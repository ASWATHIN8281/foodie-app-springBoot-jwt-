package com.stackroute.RestaurantService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "MenuItems")
public class MenuItems {
    @Id
    private int menuId;
    @Size(max=30)
    private String category;
    @Size(max=40)
    private String name;
    @Size(max=100)
    private String description;
    private double price;

}
