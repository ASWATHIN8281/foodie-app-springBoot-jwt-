package com.stackroute.RestaurantService.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import javax.validation.constraints.Size;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserInfo {
    @Id
    private int userId;
    @Size(max=30)
    private  String userFirstName;
    @Size(max=30)
    private  String userLastName;
    private  long userPhone;
    @Size(max=100)
    private String userAddress;

}
