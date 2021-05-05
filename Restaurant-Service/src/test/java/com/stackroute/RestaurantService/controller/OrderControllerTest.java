package com.stackroute.RestaurantService.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.RestaurantService.model.*;
import com.stackroute.RestaurantService.service.MenuItemDAO;
import com.stackroute.RestaurantService.service.OrderDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)

public class OrderControllerTest {
    private MockMvc mockMvc;
    @Mock
    OrderDAO orderDAO;
    @InjectMocks
    private OrderController orderController;

    private Order order;
    private UserInfo userInfo;
    private Restaurant restaurant;
    private MenuItems menuItems;
    private ItemQuantity itemQuantity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
        itemQuantity=new ItemQuantity(1,"alfam",100,1);
        menuItems=new MenuItems(1,"Arabian","Alfam","spicy food",190.0);
        List<MenuItems>menuItemsList= Arrays.asList(menuItems);
        List<ItemQuantity> itemQuantityList=Arrays.asList(itemQuantity);
        restaurant=new Restaurant(11,"Cafe","calicut",menuItemsList);
        userInfo=new UserInfo(1,"alby","anthony",708877665,"kollam");
        order=new Order(12,120.0,LocalTime.of(12,43,12),12,userInfo,restaurant,itemQuantityList);

//        order.setOrderId(1);
//        order.setPrice(150);
//        order.setOrderTime(LocalTime.of(12,23,12));
//        order.setDeliveryTime(30);
//        orderList = new ArrayList<>();
//        orderList.add(order);
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
//    @Test
//    public void givenOrderToSaveThenShouldReturnSavedOrder() throws Exception {
//        when(orderDAO.addOrder(any())).thenReturn(order);
//        mockMvc.perform(post("/api/v1/order")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(order)))
//                .andExpect(status().isCreated())
//                .andDo(MockMvcResultHandlers.print());
//        verify(orderDAO).addOrder(any());
//    }
    @Test
    public void givenGetAllOrdersThenShouldReturnListOfAllOrders() throws Exception {
        List<Order> orderList=Arrays.asList(order);
        when(orderDAO.getAllOrders()).thenReturn(orderList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(order)))
                .andDo(MockMvcResultHandlers.print());
        verify(orderDAO).getAllOrders();
        verify(orderDAO, times(1)).getAllOrders();

    }
//    @Test
//    void givenOrderIdThenShouldReturnRespectiveOrderId() throws Exception {
//        when(orderDAO.findByOrderId(order.getOrderId())).thenReturn(order);
//        mockMvc.perform(get("/api/v1/order/cancel/12"))
//                .andExpect(MockMvcResultMatchers.status()
//                        .isFound())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
