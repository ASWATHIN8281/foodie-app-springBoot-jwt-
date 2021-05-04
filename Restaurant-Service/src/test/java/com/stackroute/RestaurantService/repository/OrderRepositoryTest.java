package com.stackroute.RestaurantService.repository;

import com.stackroute.RestaurantService.model.Order;
import com.stackroute.RestaurantService.model.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@DataMongoTest
class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;
    private Order order,order1;

    @BeforeEach
    void setUp() {
        order=new Order();
        order.setOrderId(1);
        order.setOrderTime(LocalTime.of(12,43,12));
        order.setPrice(180);
        order.setDeliveryTime(30);
        order1=new Order();
        order1.setOrderId(2);
        order1.setOrderTime(LocalTime.of(12,3,45));
        order1.setPrice(250);
        order1.setDeliveryTime(30);
    }




    @Test
    public void givenOrderToAddThenShouldReturnAddedOrder() {
    orderRepository.save(order);
    Order order1=orderRepository.findById(order.getOrderId()).get();
    assertEquals(1,order.getOrderId());

    }
    @Test
    public void givenGetAllOrdersThenShouldReturnListOfAllOrders(){
        orderRepository.save(order);
        //orderRepository.save(order1);
        List<Order>orderList=orderRepository.findAll();
        assertEquals(2,orderList.get(1).getOrderId());
    }
    @Test
    public void givenOrderIdThenShouldReturnOrder(){
        orderRepository.save(order);
        Optional<Order> order2=orderRepository.findById(order.getOrderId());
        assertEquals(1,order2.get().getOrderId());
    }
    @Test
    public void givenOrderIdThenShouldReturnPrice(){
        orderRepository.save(order);
        Optional<Order> order1=orderRepository.findById(order.getOrderId());
        assertEquals(180,order1.get().getPrice());

    }
}