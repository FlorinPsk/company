package com.sda.company.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class CompanyUpdateDTO implements Serializable {

    @NotNull
    private Integer id;

    // For String's we use this annotation instead of @NotNull bc an empty String is not null!
    @NotBlank(message = "Company name is mandatory!")
    private String name;

    @NotNull(message = "Registration number is mandatory!")
    @Min(value = 9999, message = "Registration number must contain at least 5 digits!")
    private Long registrationNumber;

    @NotBlank(message = "Address is mandatory!")
    private String address;

    @NotBlank(message = "Email is mandatory!")
    @Email(message = "Invalid email!")
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(Long registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
