package com.gtasterix.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {


    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private String mobileNumber;
    private String gender;




    public boolean isEmpty() {
        return (email == null || email.isEmpty()) &&
                (password == null || password.isEmpty()) &&
                (firstName == null || firstName.isEmpty()) &&
                (lastName == null || lastName.isEmpty()) &&
                (city == null || city.isEmpty()) &&
                (state == null || state.isEmpty()) &&
                (country == null || country.isEmpty()) &&
                (postalCode == null || postalCode.isEmpty()) &&
                (mobileNumber == null || mobileNumber.isEmpty()) &&
                (gender == null || gender.isEmpty());
    }


    @Override
    public String toString() {
        return "UserDTO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
