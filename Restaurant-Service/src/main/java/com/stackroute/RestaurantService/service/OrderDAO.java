package com.stackroute.RestaurantService.service;

import com.stackroute.RestaurantService.exception.OrderAlreadyPlacedByCustomerException;
import com.stackroute.RestaurantService.exception.OrderNotFoundException;
import com.stackroute.RestaurantService.model.Order;

import java.util.List;

public interface OrderDAO {
    Order addNewOrder(Order order)throws OrderAlreadyPlacedByCustomerException;
    List<Order> getAllOrders();
    Order findByOrderId(int id)throws OrderNotFoundException;
}
