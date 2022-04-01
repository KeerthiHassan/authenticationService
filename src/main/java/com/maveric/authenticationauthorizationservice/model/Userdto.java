package com.maveric.authenticationauthorizationservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Date;

@Data
public class Userdto {

//    private String userID;

    @NotEmpty(message = "Please provide First Name")
    @Size(min = 2,message = "Length of first name should be more than 2 character ")
    private String firstName;
    private String middleName;
    @NotEmpty(message = "Please provide Last Name")
    @Size(min = 2,message = "Length of Last name should be more than 2 character ")
    private String lastName;
    @NotEmpty(message = "Please provide phone number")
    @Size(min = 10,max=10,message = "Length of phone Number should  be 10")
    private String phoneNumber;
    private LocalDate dateOfBirth;

    private Gender gender;
    @NotEmpty(message = "Please provide Address")
    private String address;

    private String employeeNumber;
    private String bloodGroup;
    @NotBlank(message = "email cannot be null")
    @Email(message = "Please provide valid email adress")
    private String email;
    @NotBlank(message = "password cannot be null")
    private String password;

}
