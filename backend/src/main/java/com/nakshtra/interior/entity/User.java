package com.nakshtra.interior.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


@Entity
@Table(name = "user_table", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

  //  @NotEmpty(message = "First name cannot be empty")
    private String firstName;

   // @NotEmpty(message = "Last name cannot be empty")
    private String lastName;

  //  @Email(message = "Enter a valid Email Id")
    private String email;

  //  @Pattern(regexp = "\\d{10}", message = "Phone number must contain 10 digits")
    private String phoneNo;

    private String city;
    private String state;

    @Column(name = "pincode", length = 10)
    private String pincode;

    private String userName;

    private String password;

    
    private String role;

    public User() {
        super();
    }

    public User(int id, String firstName, String lastName, String email, String phoneNo, String city, String state,
                String pincode, String userName, String password, String role) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNo() { return phoneNo; }
    public void setPhoneNo(String phoneNo) { this.phoneNo = phoneNo; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getPincode() { return pincode; }
    public void setPincode(String pincode) { this.pincode = pincode; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role =role; }

    @Override
    public String toString() {
        return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
                + ", phoneNo=" + phoneNo + ", city=" + city + ", state=" + state + ", pincode=" + pincode
                + ", userName=" + userName + ", password=" + password + ", role=" + role + "]";
    }

    public enum Role {
        DESIGNER, CLIENT
    }
}
