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
    private String firstName;
    private String lastName;
    @Email
    @NotNull
    private String username;
    @NotNull
    private String contactNum;
    @NotNull
    private String address;
    @NotNull
    @Size(min = 5,message = "required")
    private String password;

}
