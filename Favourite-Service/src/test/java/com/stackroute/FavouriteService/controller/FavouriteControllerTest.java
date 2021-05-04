package com.stackroute.FavouriteService.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.FavouriteService.Exception.FoodItemAlreadyExistsException;
import com.stackroute.FavouriteService.model.Favourite;
import com.stackroute.FavouriteService.service.FavouriteService;
import net.bytebuddy.pool.TypePool;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class FavouriteControllerTest {
    @Autowired
    private MockMvc mockMvc;
@Mock
    private FavouriteService favouriteService;
    private Favourite favourite;
    private List<Favourite> favouriteList;
    private Optional optional;
    @InjectMocks
    private FavouriteController favouriteController;

    @BeforeEach
    void setUp() {
        favourite=new Favourite(1,"Tito's Cafe","Junk","Burger",160,"Jenis");
        optional = Optional.of(favourite);
        mockMvc= MockMvcBuilders.standaloneSetup(favouriteController).build();
    }

    @AfterEach
    void tearDown() {
        favourite=null;
    }
@Test
    public void givenGetAllFavouritesThenShouldReturnListOfAllFavourites() throws Exception {
        when(favouriteService.getAll()).thenReturn(favouriteList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/favourites")
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(favourite))).andDo(MockMvcResultHandlers.print());
        verify(favouriteService,times(1)).getAll();

}
@Test
public void givenFavouriteToSaveThenShouldReturnSavedFavourite() throws Exception{
        when(favouriteService.addFavourite(any())).thenReturn(favourite);
        mockMvc.perform(post("/api/v1/favourite")
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(favourite))).andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());

        verify(favouriteService).addFavourite(any());
}
@Test
public void givenFavouriteToSaveThenShouldReturnSavedFoodItem() throws Exception {
       when(favouriteService.addFavourite(any())).thenReturn(favourite);
mockMvc.perform(post("/api/v1/favourite")
        .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(favourite))).andExpect(status().isCreated())
            .andDo(MockMvcResultHandlers.print());

    verify(favouriteService).addFavourite(any());
}
@Test
public void givenFavouriteFoodItemToDeleteThenShouldReturnDeletedFavourite() throws Exception {
        when(favouriteService.deleteFavourite(favourite.getFoodItem())).thenReturn(favourite);
        mockMvc.perform(delete("/api/v1/favourite/Burger")
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(favourite)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
}
    public static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }





}