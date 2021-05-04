package com.stackroute.RestaurantService.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.RestaurantService.model.MenuItems;
import com.stackroute.RestaurantService.model.Order;
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
    private List<Order> orderList;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
        order = new Order();
        order.setOrderId(1);
        order.setPrice(150);
        order.setOrderTime(LocalTime.of(12,23,12));
        order.setDeliveryTime(30);
        orderList = new ArrayList<>();
        orderList.add(order);
    }
    @AfterEach
    public void tearDown() {
        order = null;
    }
    @Test
    public void givenOrderToSaveThenShouldReturnSavedOrder() throws Exception {
        when(orderDAO.addOrder(any())).thenReturn(order);
        mockMvc.perform(post("/api/v1/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(order)))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());
        verify(orderDAO).addOrder(any());
    }
    @Test
    public void givenGetAllOrdersThenShouldReturnListOfAllOrders() throws Exception {
        when(orderDAO.getAllOrders()).thenReturn(orderList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(order)))
                .andDo(MockMvcResultHandlers.print());
        verify(orderDAO).getAllOrders();
        verify(orderDAO, times(1)).getAllOrders();

    }
    @Test
    void givenOrderIdThenShouldReturnRespectiveOrderId() throws Exception {
        when(orderDAO.findByOrderId(order.getOrderId())).thenReturn(order);
        mockMvc.perform(get("/api/v1/order/1"))
                .andExpect(MockMvcResultMatchers.status()
                        .isFound())
                .andDo(MockMvcResultHandlers.print());

    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
