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
    /**
     * @Id annotation makes id variable as Primary key
     * @UniqueConstraint annotation to specify that a unique constraint is to be included in the generated DDL for a primary or secondary table
     */

    @Id
    @Unique
    private int orderId;

    /**  To store total bill of order**/
    private double price;

    /**  To store order time**/
    private LocalTime orderTime;

    /**  To store delivery time**/
    private long deliveryTime;

    /**  To store  customer information**/
    @NotNull(message = "The UserInfo cannot be null.")
    UserInfo userInfo;


    /**  To store restaurant **/
    @NotNull(message = "The Restaurant Info cannot be null")
    Restaurant restaurant;


    /**  To store item quantity list**/
    @NotNull(message = "The item quantity list for order cannot be null")
    List<ItemQuantity>itemQuantityList;
}
