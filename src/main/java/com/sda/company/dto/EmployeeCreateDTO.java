package com.sda.company.dto;

import com.sda.company.model.JobTitle;
import jakarta.validation.constraints.*;

public class EmployeeCreateDTO {

    @NotBlank(message = "Employee name is mandatory!")
    private String name;

    @NotBlank(message = "Phone number is mandatory!")
    @Size(min = 6, max = 11, message = "Phone number is not valid!")
    private String phoneNumber;

    @NotNull
    @Min(value = 9999, message = "National identification number must contain at least 5 digits!")
    private Long nid;

    @NotBlank(message = "Email is mandatory!")
    @Email(message = "Invalid email!")
    private String email;

    @NotBlank(message = "Address is mandatory!")
    private String address;

    @NotNull(message = "Job title is mandatory!")
    JobTitle jobTitle;

    @NotNull(message = "Salary value is mandatory!")
    Long salary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getNid() {
        return nid;
    }

    public void setNid(Long nid) {
        this.nid = nid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }
}
