package com.stackroute.RestaurantService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Size;
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class ItemQuantity {
    @Id
    private int itemId;
    @Size(max=30)
    private String itemName;
    private double price;
    private  int quantity;
}
