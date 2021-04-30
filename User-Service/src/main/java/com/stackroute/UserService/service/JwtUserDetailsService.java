package com.stackroute.UserService.service;

import com.stackroute.UserService.model.User;
import com.stackroute.UserService.model.UserDto;
import com.stackroute.UserService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = repository.findByUsername(username);
            if(user==null){
                throw new UsernameNotFoundException("User not found" + username);
            }
            return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),new ArrayList<>());
    }
    public User saveUser(UserDto userDto){
            User user=new User();
            user.setAddress(userDto.getAddress());
            user.setUsername(userDto.getUsername());
            user.setContactNum(userDto.getContactNum());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            return repository.save(user);
    }
    public User deleteUser(int id){
        User user=null;
        Optional optional=repository.findById(id);
        if(optional.isPresent()){
            user=repository.findById(id).get();
            repository.deleteById(id);
        }
        return user;
    }
    public User getUserById(int id){
        User user=null;
        Optional optional=repository.findById(id);
        if(optional.isPresent()){
            user=repository.findById(id).get();
        }
        return user;
    }
    public User updateUser(UserDto userDto){
        User user=null;
        Optional optional=repository.findById(userDto.getUId());
        if (optional.isPresent()){
            user.setAddress(userDto.getAddress());
            user.setUsername(userDto.getUsername());
            user.setContactNum(userDto.getContactNum());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
        return user;
    }
}
