package com.stackroute.RestaurantService.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.common.aliasing.qual.Unique;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "UserInfo")
public class UserInfo {
    @Id
    @Unique
    private int userId;
    @NotNull(message = "userFirstName cannot be null")
    @Size(min = 3,max=30)
    private  String userFirstName;
    @NotNull(message = "userLastName cannot be null")
    @Size(min = 3,max=30)
    private  String userLastName;
    private  long userPhone;
    @NotNull(message = "userAddress cannot be null")
    @Size(min = 3,max=100)
    private String userAddress;

}
