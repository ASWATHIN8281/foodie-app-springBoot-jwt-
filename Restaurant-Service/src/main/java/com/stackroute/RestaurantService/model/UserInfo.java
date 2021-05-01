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
    private int id;
    @Size(max=30)
    private  String fName;
    @Size(max=30)
    private  String lName;
    private  long phone;
    @Size(max=100)
    private String address;

}
