package com.stackroute.RestaurantService.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.common.aliasing.qual.Unique;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "Order")
public class Order {
    @Id
    @Unique
    private int orderId;
    private double price;
    private LocalTime orderTime;
    private long deliveryTime;

    @NotNull(message = "The UserInfo cannot be null.")
    UserInfo userInfo;
    @NotNull(message = "The Restaurant Info cannot be null")
    Restaurant restaurant;
    @NotNull(message = "The item quantity list for order cannot be null")
    List<ItemQuantity>itemQuantityList;
}
