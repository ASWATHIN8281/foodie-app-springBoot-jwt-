package com.stackroute.RestaurantService.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    private int orderId;
    private double price;
    private long orderTime;
    private long deliveryTime;
    UserInfo userInfo;
    Restaurant restaurant;
    List<ItemQuantity>itemQuantityList;
}
