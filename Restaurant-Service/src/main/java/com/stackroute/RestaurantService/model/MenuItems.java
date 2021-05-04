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
    @Id
    @Unique
    private int menuId;
    @NotNull
    @Size(min = 3,max=30,message = "The category cannot be null and must contain minimum 3 characters")
    private String category;
    @NotNull
    @Size(min = 3,max=40,message = "The name cannot be null and must contain 3 minimum characters")
    private String name;
    @Size(max=100)
    private String description;
    private double price;

}
