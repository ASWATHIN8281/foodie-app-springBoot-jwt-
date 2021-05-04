package com.stackroute.UserService.service;

import com.stackroute.UserService.exception.CustomerAlreadyExistsException;
import com.stackroute.UserService.exception.CustomerUnknownException;
import com.stackroute.UserService.model.User;
import com.stackroute.UserService.model.UserDto;
import com.stackroute.UserService.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
   import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;
    @Autowired
    PasswordEncoder passwordEncoder;
    private static final Logger logger = (Logger) LoggerFactory.getLogger(JwtUserDetailsService.class);
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = repository.findByUsername(username);
            if(user==null){
                logger.info("user not found");
                throw new UsernameNotFoundException("User not found" + username);
            }
            return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),new ArrayList<>());
    }
    public User saveUser(User user) throws CustomerAlreadyExistsException {
            if(repository.existsByusername(user.getUsername())){
                logger.error("Customer account already exists ");
                throw new CustomerAlreadyExistsException();
            }
            //User user=null;
//            user.setAddress(userDto.getAddress());
//            user.setUsername(userDto.getUsername());
//            user.setContactNum(userDto.getContactNum());
//            user.setFirstName(userDto.getFirstName());
//            user.setLastName(userDto.getLastName());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            logger.info("Customer saved");
            return repository.save(user);
    }
    public User deleteUser(int id)throws CustomerUnknownException{
        User user=null;
        Optional optional=repository.findById(id);
        if(!optional.isPresent()){
            logger.error("Customer unknown");
           throw new CustomerUnknownException();
        }
        user=repository.findById(id).get();
        repository.deleteById(id);
        logger.info("Customer information deleted");
        return user;
    }
    public User getUserById(int id) throws CustomerUnknownException {
        User user=null;
        Optional optional=repository.findById(id);
        if(!optional.isPresent()){
            logger.error("customer unknown");
           throw new CustomerUnknownException();
        }
        logger.info("Customer details fetched");
        user=repository.findById(id).get();
        return user;
    }
    public User updateUser(User user,int id) throws CustomerUnknownException{
        User userUp=null;
        Optional optional=repository.findById(id);
        if (!optional.isPresent()){
            logger.error("customer unknown");
//            user.setAddress(userDto.getAddress());
//            user.setUsername(userDto.getUsername());
//            user.setContactNum(userDto.getContactNum());
//            user.setFirstName(userDto.getFirstName());
//            user.setLastName(userDto.getLastName());

              throw new CustomerUnknownException();
        }
        logger.info("customer details updated");
        userUp=repository.findById(id).get();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userUp= repository.save(user);
        return userUp;
    }
}
