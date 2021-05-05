package com.stackroute.RestaurantService.controller;
import com.stackroute.RestaurantService.exception.OrderAlreadyPlacedByCustomerException;
import com.stackroute.RestaurantService.exception.OrderNotFoundException;
import com.stackroute.RestaurantService.model.Order;
import com.stackroute.RestaurantService.service.OrderDAO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@Slf4j
@AllArgsConstructor
/**
 * RestController annotation is used to create Restful web services using Spring MVC
 */

@RestController
/**
 * RequestMapping annotation maps HTTP requests to handler methods
 */


@RequestMapping("/api/v1")
public class OrderController {
    @Autowired
    private OrderDAO service;
    private static Logger logger = (Logger) LoggerFactory.getLogger(OrderController.class);
    /**
     * save a new order
     */
    @PostMapping("/order")
    public ResponseEntity<Order> saveOrder(@Valid @RequestBody Order order) throws OrderAlreadyPlacedByCustomerException {
        logger.info("New order added");
        return new ResponseEntity<Order>(service.addOrder(order), HttpStatus.CREATED);
    }
    /**
     * retrieve all orders
     */
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        logger.info("Fetched all orders");
        return new ResponseEntity<List<Order>>((List<Order>) service.getAllOrders(), HttpStatus.OK);
    }
    /**
     * retrieve order by id
     */

    @GetMapping("/order/{id}")
    public ResponseEntity<Order>getOrderById(@PathVariable int id) throws OrderNotFoundException {
        logger.info("Fetched order based on id");
        return new ResponseEntity<Order>(service.findByOrderId(id),HttpStatus.FOUND);
    }
    /**
     * delete order by id
     */
    @DeleteMapping("/order/cancel/{id}")
    public ResponseEntity<Order>deleteOrderById(@PathVariable int id) throws OrderNotFoundException {
        logger.info("Cancelled  order");
        return new ResponseEntity<Order>(service.deleteOrder(id),HttpStatus.FOUND);
    }
}
