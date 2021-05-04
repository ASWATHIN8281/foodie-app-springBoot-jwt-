package com.stackroute.UserService.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.UserService.exception.CustomerUnknownException;
import com.stackroute.UserService.exception.GlobalException;
import com.stackroute.UserService.model.User;
import com.stackroute.UserService.model.UserDto;
import com.stackroute.UserService.service.JwtUserDetailsService;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private JwtUserDetailsService service;
    private User user;
    private UserDto userDto;
    private List<User> userList;

    @InjectMocks
    private UserController controller;

    @BeforeEach
    void setUp() {
        user=new User(1,"aswathi","nair","awa@a.com","9988776655","calicut","password1");
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new GlobalException()).build();
        userList=new ArrayList<>();
        userList.add(user);
//        userDto.setAddress(user.getAddress());
//        userDto.setUsername(user.getUsername());
//        userDto.setContactNum(user.getContactNum());
//        userDto.setFirstName(user.getFirstName());
//        userDto.setLastName(user.getLastName());
//        userDto.setUId(user.getUId());
//        userDto.setPassword(user.getPassword());
    }

    @AfterEach
    void tearDown() {
        user=null;
    }

    @Test
    public void  givenCustomerToUpdateAndReturnCustomer() throws Exception {
        when(service.updateUser(any(),anyInt())).thenReturn(user);
        mockMvc.perform(put("/api/v1/customer?id=1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isCreated());
        verify(service,times(1)).updateUser(any(),anyInt());
    }
    @Test
    void givenCustomerToUpdateThenShouldNotReturnUpdatedBlog() throws Exception {
        when(service.updateUser((User) any(),anyInt())).thenThrow(CustomerUnknownException.class);
        mockMvc.perform(put("/api/v1/customer?id=90")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public  void givenCustomerIdShouldReturnCustomer() throws Exception {
        when(service.getUserById(user.getUId())).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customer/1"))
                .andExpect(MockMvcResultMatchers.status()
                        .isFound())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void givenCustomerIdToDeleteThenShouldNotReturnDeletedCustomer() throws Exception {
        when(service.deleteUser(anyInt())).thenThrow(CustomerUnknownException.class);
        mockMvc.perform(delete("/api/v1/customer/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isConflict())
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