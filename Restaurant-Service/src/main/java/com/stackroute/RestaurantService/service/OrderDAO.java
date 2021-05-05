package com.stackroute.RestaurantService.service;

import com.stackroute.RestaurantService.exception.OrderAlreadyPlacedByCustomerException;
import com.stackroute.RestaurantService.exception.OrderNotFoundException;
import com.stackroute.RestaurantService.model.Order;

import java.util.List;

public interface OrderDAO {
    /**
     * AbstractMethod to save a order
     */

    Order addOrder(Order order)throws OrderAlreadyPlacedByCustomerException;
    /**
     * AbstractMethod to  get all orders
     */

    List<Order> getAllOrders();
    /**
     * AbstractMethod to find order by id
     */

    Order findByOrderId(int id)throws OrderNotFoundException;
    /**
     * AbstractMethod to delete order
     */

    Order deleteOrder(int id)throws  OrderNotFoundException;
}
