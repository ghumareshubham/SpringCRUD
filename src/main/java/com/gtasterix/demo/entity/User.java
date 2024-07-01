package com.gtasterix.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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
    private String referenceId;
//    private String referralId;
    private LocalDate createdDate;


}
