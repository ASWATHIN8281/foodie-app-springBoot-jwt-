package com.stackroute.UserService.service;

import com.stackroute.UserService.exception.CustomerAlreadyExistsException;
import com.stackroute.UserService.exception.CustomerUnknownException;
import com.stackroute.UserService.model.User;
import com.stackroute.UserService.model.UserDto;
import com.stackroute.UserService.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtUserDetailsServiceTest {
    @Mock
    private UserRepository repository;
    @InjectMocks
    private JwtUserDetailsService service;
    private User user,user1;
    private UserDto userDto;
    private List<User> userList;
    private Optional optional;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);
        user=new User(1,"Sreelatha","Nair","sreelatha@s.com","7799887766","kozhikode","password1");
        user1=new User(2,"sajitha","pai","saji@s.com","9988776655","ekm","password2");
        optional=Optional.of(user);
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
        user1=null;
        userDto=null;
    }
//    @Test
//    public void  givenCustomerToRegisterAndReturnCustomer() throws CustomerAlreadyExistsException {
//
//        when(repository.save(any())).thenReturn(user);
//        //service.saveUser(userDto);
//        assertEquals(user, service.saveUser(user));
//        verify(repository,times(1)).save(any());
//    }
    @Test
    void givenCustomerIdToDeleteThenShouldReturnDeletedCustomer() throws CustomerUnknownException {
        when(repository.findById(user.getUId())).thenReturn(optional);
        User deleted=repository.findById(user.getUId()).get();
        service.deleteUser(user.getUId());
        assertEquals(1,deleted.getUId());
        //verify(repository,times(2)).findById(soulmate.getId());
        verify(repository,times(1)).deleteById(user.getUId());
    }
    @Test
    public void givenCustomerToUpdateThenShouldReturnUpdatedCustomer() throws CustomerUnknownException {
        when(repository.existsByusername(user.getUsername())).thenReturn(true);
        when(repository.save(user)).thenReturn(user);
        user.setFirstName("Arjun");
        User user2 = service.updateUser(user);
        assertEquals(user2.getFirstName(), "Arjun");
        verify(repository, times(1)).save(user);
        verify(repository, times(1)).existsByusername(user.getUsername());

    }
    @Test
    public void givenCustomerIdReturnCustomer() throws CustomerUnknownException {
        when(repository.findById(anyInt())).thenReturn(optional);
        User user2=service.getUserById(user.getUId());;
        verify(repository,times(2)).findById(anyInt());
    }
}