package com.stackroute.RestaurantService.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "Order")
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
