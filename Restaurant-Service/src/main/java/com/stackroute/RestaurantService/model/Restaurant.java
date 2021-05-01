package com.stackroute.RestaurantService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "RestaurantDB")
public class Restaurant {
    @Id
    private int id;
    @Size(max=30)
    private String rName;
    @Size(max=40)
    private String location;
}