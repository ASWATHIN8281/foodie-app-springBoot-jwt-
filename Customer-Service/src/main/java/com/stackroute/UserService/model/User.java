package com.stackroute.UserService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
    @NotNull(message = "Name is mandatory")
    private String firstName;
    private String lastName;
    @Email(message = "should be in asw@domain.com format")
    @NotNull(message = "Email is mandatory")
    private String username;
    @NotNull(message = "Phone number is mandatory")
    private String contactNum;
    @NotNull(message = "Address is mandatory")
    private String address;
    @NotNull(message = "required")
    @Size(min = 5)
    private String password;

}
