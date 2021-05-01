package com.stackroute.RestaurantService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemQuantity {
    @Id
    private int id;
    @Size(max=30)
    private String name;
    private double price;
    private  int quantity;
}
