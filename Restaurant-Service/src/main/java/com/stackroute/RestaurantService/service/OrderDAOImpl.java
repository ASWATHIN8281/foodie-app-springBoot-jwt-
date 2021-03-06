package com.stackroute.RestaurantService.service;
import com.stackroute.RestaurantService.exception.OrderAlreadyPlacedByCustomerException;
import com.stackroute.RestaurantService.exception.OrderNotFoundException;
import com.stackroute.RestaurantService.model.Order;
import com.stackroute.RestaurantService.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor

/**
 * @Service indicates annotated class is a service which hold business logic in the Service layer
 */

@Service
public class OrderDAOImpl implements  OrderDAO {
    /**
     * Constructor based Dependency injection to inject Order Repository here
     */

    @Autowired
    private OrderRepository repository;
    private static final Logger logger = (Logger) LoggerFactory.getLogger(OrderDAOImpl.class);

    /**
     * Implementation of addOrder method
     */
    @Override
    public Order addOrder(Order order) throws OrderAlreadyPlacedByCustomerException {
        if (repository.existsById(order.getOrderId())) {
            logger.error("Order already exist");
            throw new OrderAlreadyPlacedByCustomerException();
        }
        LocalTime localTime=LocalTime.now();
        order.setOrderTime(localTime);
        order.setPrice(order.getItemQuantityList().stream().mapToDouble(e->e.getPrice()*e.getQuantity()).sum());
        Order order1 = repository.save(order);
        logger.info("Order created");
        return order1;
    }

    /**
     * Implementation of getAllOrders method
     */
    @Override
    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    /**
     * Implementation of findByOrderId method
     */
    @Override
    public Order findByOrderId(int id) throws OrderNotFoundException {
        //Optional optional = repository.findById(id);
        if (!repository.existsById(id)) {
            logger.error("Order not found");
            throw new OrderNotFoundException();
        }
        Order order = repository.findById(id).get();
        logger.info("Order found");
        return order;
    }
    /**
     * Implementation of deleteOrder method
     */
    @Override
    public Order deleteOrder(int id) throws OrderNotFoundException {
        Optional optional=repository.findById(id);
        if(!optional.isPresent()){
            logger.error("Order not found");
            throw new OrderNotFoundException();
        }
        Order order=repository.findById(id).get();
        repository.deleteById(id);
        logger.info("Order deleted by id");
        return order;
    }
}
