package com.stackroute.RestaurantService.service;

import com.stackroute.RestaurantService.exception.OrderAlreadyPlacedByCustomerException;
import com.stackroute.RestaurantService.exception.OrderNotFoundException;
import com.stackroute.RestaurantService.model.*;
import com.stackroute.RestaurantService.repository.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderDAOImplTest {
    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private OrderDAOImpl orderDAO;
    private Order order,order1;
    private List<Order> orderList;
    private Optional optional;
    private UserInfo userInfo;
    private Restaurant restaurant;
    private ItemQuantity itemQuantity;
    private MenuItems menuItems;

    @AfterEach
    void tearDown() {
        order=null;
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userInfo=new UserInfo(1,"Jenis","Jose",95,"Kan");

        itemQuantity=new ItemQuantity(1,"Biryani",160,1);
        List<ItemQuantity> itemQuantityList=new ArrayList<>(Arrays.asList(itemQuantity));

        menuItems=new MenuItems(1,"Veg","GobiManchurian","vege",120);
        List<MenuItems> menuItemsList=new ArrayList<>(Arrays.asList(menuItems));
        restaurant=new Restaurant(1,"Clarks cafe","janimpur",menuItemsList);
        order=new Order(1,160,30,20,userInfo,restaurant,itemQuantityList);

        optional=Optional.of(order);

    }
    @Test
    public void givenOrderToSaveThenShouldReturnSavedOrder() throws OrderAlreadyPlacedByCustomerException {
        when(orderRepository.save(any())).thenReturn(order);
        orderDAO.addOrder(order);
        verify(orderRepository,times(1)).save(any());
    }
    @Test
    public void givenGetAllOrdersThenShouldReturnListOfAllOrders(){
        orderRepository.save(order);
        when(orderRepository.findAll()).thenReturn(orderList);
        List<Order> orderList=orderDAO.getAllOrders();
        assertEquals(orderList,orderList);
        verify(orderRepository,times(1)).save(order);
        verify(orderRepository,times(1)).findAll();
    }
    @Test
    public void givenOrderIdThenShouldReturnOrder() throws OrderNotFoundException {
        orderRepository.save(order);
        when(orderRepository.existsById(order.getOrderId())).thenReturn(true);
        when(orderRepository.findById(order.getOrderId())).thenReturn(Optional.of(order));
        orderDAO.findByOrderId(1);
        verify(orderRepository, times(1)).findById(order.getOrderId());


    }
}