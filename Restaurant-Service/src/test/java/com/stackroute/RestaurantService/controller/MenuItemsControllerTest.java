package com.stackroute.RestaurantService.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.RestaurantService.model.MenuItems;
import com.stackroute.RestaurantService.service.MenuItemDAO;
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


public class MenuItemsControllerTest {
    private MockMvc mockMvc;
    @Mock
    MenuItemDAO menuItemDAO;
    @InjectMocks
    private MenuItemsController menuItemsController;

    private MenuItems menuItems;
    private List<MenuItems> menuItemsList;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(menuItemsController).build();
        menuItems = new MenuItems();
        menuItems.setMenuId(1);
        menuItems.setCategory("NonVeg");
        menuItems.setName("ChikkenTikka");
        menuItems.setDescription("A dish made  of chicken");
        menuItems.setPrice(110);
        menuItemsList = new ArrayList<>();
        menuItemsList.add(menuItems);
    }
    @AfterEach
    public void tearDown() {
        menuItems = null;
    }
    @Test
    public void givenMenuItemToSaveThenShouldReturnSavedMenuItem() throws Exception {
        when(menuItemDAO.addMenuItems(any())).thenReturn(menuItems);
        mockMvc.perform(post("/api/v1/menuitem")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(menuItems)))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());
        verify(menuItemDAO).addMenuItems(any());
    }
    @Test
    public void givenGetAllMenuItemsThenShouldReturnListOfAllMenuItems() throws Exception {
        when(menuItemDAO.getAllMenuItems()).thenReturn(menuItemsList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/menuitems")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(menuItems)))
                .andDo(MockMvcResultHandlers.print());
        verify(menuItemDAO).getAllMenuItems();
        verify(menuItemDAO, times(1)).getAllMenuItems();

    }
    @Test
    void givenMenuItemNameThenShouldReturnRespectiveMenuItemName() throws Exception {
        when(menuItemDAO.findByMenuName(menuItems.getName())).thenReturn(menuItems);
        mockMvc.perform(get("/api/v1/menuitems/ChikkenTikka"))
                .andExpect(MockMvcResultMatchers.status()
                        .isFound())
                .andDo(MockMvcResultHandlers.print());

    }
    @Test
    public void givenMenuitemNameToDeleteThenShouldNotReturnDeletedMenuItemName() throws Exception {
        when(menuItemDAO.deleteMenuItems(menuItems.getName())).thenReturn(menuItems);
        mockMvc.perform(delete("/api/v1/menuitem/ChikkenTikka")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(menuItems)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void givenMenuItemsToUpdateThenShouldReturnUpdatedMenuItems() throws Exception {
        when(menuItemDAO.updateMenuItems(any())).thenReturn(menuItems);
        mockMvc.perform(put("/api/v1/menuitems").contentType(MediaType.APPLICATION_JSON).content(asJsonString(menuItems)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
