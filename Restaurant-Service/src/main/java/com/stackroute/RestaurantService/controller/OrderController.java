package com.stackroute.RestaurantService.controller;
import com.stackroute.RestaurantService.exception.OrderAlreadyPlacedByCustomerException;
import com.stackroute.RestaurantService.exception.OrderNotFoundException;
import com.stackroute.RestaurantService.model.Order;
import com.stackroute.RestaurantService.service.OrderDAO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class OrderController {
    @Autowired
    private OrderDAO service;

    @PostMapping("/order")
    public ResponseEntity<Order> saveOrder(@Valid @RequestBody Order order) throws OrderAlreadyPlacedByCustomerException {
        return new ResponseEntity<Order>(service.addOrder(order), HttpStatus.CREATED);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<List<Order>>((List<Order>) service.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Order>getOrderById(@PathVariable int id) throws OrderNotFoundException {
        return new ResponseEntity<Order>(service.findByOrderId(id),HttpStatus.FOUND);
    }
    @DeleteMapping("/order/cancel/{id}")
    public ResponseEntity<Order>deleteOrderById(@PathVariable int id) throws OrderNotFoundException {
        return new ResponseEntity<Order>(service.deleteOrder(id),HttpStatus.FOUND);
    }
}
