package com.stackroute.RestaurantService.repository;

import com.stackroute.RestaurantService.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@DataMongoTest
class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;
    private Order order;
    private UserInfo userInfo;
    private Restaurant restaurant;
    private MenuItems menuItems;
    private ItemQuantity itemQuantity;

    @BeforeEach
    void setUp() {
        itemQuantity=new ItemQuantity(1,"alfam",100,1);
        menuItems=new MenuItems(1,"Arabian","Alfam","spicy food",190.0);
        List<MenuItems>menuItemsList= Arrays.asList(menuItems);
        List<ItemQuantity> itemQuantityList=Arrays.asList(itemQuantity);
        restaurant=new Restaurant(11,"Cafe","calicut",menuItemsList);
        userInfo=new UserInfo(1,"alby","anthony",708877665,"kollam");
        order=new Order(12,120.0,LocalTime.of(12,43,12),12,userInfo,restaurant,itemQuantityList);
    }

    @AfterEach
    void tearDown() {
        order=null;
        //order1=null;
        userInfo=null;
        itemQuantity=null;
        restaurant=null;
        menuItems=null;
    }

    @Test
    public void givenOrderToAddThenShouldReturnAddedOrder() {
    orderRepository.save(order);
    Order order1=orderRepository.findById(order.getOrderId()).get();
    assertEquals(12,order1.getOrderId());

    }
    @Test
    public void givenGetAllOrdersThenShouldReturnListOfAllOrders(){
        orderRepository.save(order);
        //orderRepository.save(order1);
        List<Order>orderList=orderRepository.findAll();
        assertEquals(12,orderList.get(1).getOrderId());
    }
    @Test
    public void givenOrderIdThenShouldReturnOrder(){
        orderRepository.save(order);
        Optional<Order> order2=orderRepository.findById(order.getOrderId());
        assertEquals(12,order2.get().getOrderId());
    }
    @Test
    public void givenOrderIdThenShouldReturnPrice(){
        orderRepository.save(order);
        Optional<Order> order1=orderRepository.findById(order.getOrderId());
        assertEquals(120.0,order1.get().getPrice());

    }
}