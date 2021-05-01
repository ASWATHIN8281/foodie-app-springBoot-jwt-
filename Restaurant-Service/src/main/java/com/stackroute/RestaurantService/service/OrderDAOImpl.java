package com.stackroute.RestaurantService.service;

import com.stackroute.RestaurantService.model.Order;
import com.stackroute.RestaurantService.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class OrderDAOImpl implements  OrderDAO{
    @Autowired
    private OrderRepository repository;


    @Override
    public Order addNewOrder(Order order) {
        return repository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    @Override
    public Order findByOrderId(int id) {
        return repository.findByOrderId(id);
    }
}
