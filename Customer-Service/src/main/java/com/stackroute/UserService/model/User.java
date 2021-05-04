package com.stackroute.UserService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uId;
    @NotNull
    @Size(min = 3,max = 30,message = "The firstname should not blank and must contain minimum 3 character and max 30 character")
    private String firstName;
    private String lastName;
    @Email
    @NotNull(message = "The username cannot be null")
    private String username;
    @NotNull(message = "The contactNum cannot be null")
    private String contactNum;
    @NotNull
    private String address;
    @NotNull
    @Size(min = 5,message = "required")
    private String password;

}
