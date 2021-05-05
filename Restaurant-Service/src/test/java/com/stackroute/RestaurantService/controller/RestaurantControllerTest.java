package com.stackroute.RestaurantService.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.RestaurantService.model.Restaurant;
import com.stackroute.RestaurantService.service.RestaurantDAO;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)


public class RestaurantControllerTest {
    private MockMvc mockMvc;
    @Mock
    RestaurantDAO restaurantDAO;
    @InjectMocks
    private RestaurantController restaurantController;

    private Restaurant restaurant;
    private List<Restaurant> restaurantList;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(restaurantController).build();
        restaurant = new Restaurant();
        restaurant.setRestaurantId(1);
        restaurant.setName("Donalds");
        restaurant.setLocation("Mysore");
        restaurantList = new ArrayList<>();
        restaurantList.add(restaurant);
    }
    @AfterEach
    public void tearDown() {
        restaurant = null;
    }

//    @Test
//    public void givenRestaurantToSaveThenShouldReturnSavedRestaurant() throws Exception {
//        when(restaurantDAO.addRestaurant(any())).thenReturn(restaurant);
//        mockMvc.perform(post("/api/v1/restaurant")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(restaurant)))
//                .andExpect(status().isCreated())
//                .andDo(MockMvcResultHandlers.print());
//        verify(restaurantDAO).addRestaurant(any());
//    }
    @Test
    public void givenGetAllRestaurantsThenShouldReturnListOfAllRestaurants() throws Exception {
        when(restaurantDAO.getAllRestaurants()).thenReturn(restaurantList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/restaurants")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(restaurant)))
                .andDo(MockMvcResultHandlers.print());
        verify(restaurantDAO).getAllRestaurants();
        verify(restaurantDAO, times(1)).getAllRestaurants();

    }
    @Test
    void givenRestaurantLocationThenShouldReturnRespectiveRestaurantLocation() throws Exception {
        when(restaurantDAO.findByLocation(restaurant.getLocation())).thenReturn(restaurantList);
        mockMvc.perform(get("/api/v1/restaurant/loc/Mysore"))
                .andExpect(MockMvcResultMatchers.status()
                        .isFound())
                .andDo(MockMvcResultHandlers.print());

    }
    @Test
    void givenRestaurantNAmeThenShouldReturnRespectiveRestaurantName() throws Exception {
        when(restaurantDAO.findByName(restaurant.getName())).thenReturn(restaurant);
        mockMvc.perform(get("/api/v1/restaurant/name/Donalds"))
                .andExpect(MockMvcResultMatchers.status()
                        .isFound())
                .andDo(MockMvcResultHandlers.print());

    }
    @Test
    public void givenRestaurantIdToDeleteThenShouldNotReturnDeletedRestaurant() throws Exception {
        when(restaurantDAO.deleteRestaurant(restaurant.getRestaurantId())).thenReturn(restaurant);
        mockMvc.perform(delete("/api/v1/restaurant/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(restaurant)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }
//    @Test
//    public void givenRestaurantToUpdateThenShouldReturnUpdatedRestaurant() throws Exception {
//        when(restaurantDAO.updateRestaurant(any())).thenReturn(restaurant);
//        mockMvc.perform(put("/api/v1/restaurant").contentType(MediaType.APPLICATION_JSON).content(asJsonString(restaurant)))
//                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
//    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




}
