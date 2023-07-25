package com.sda.company.service;

import com.sda.company.dto.EmployeeCreateDTO;
import com.sda.company.dto.EmployeeDisplayDTO;
import com.sda.company.model.Employee;

import java.util.List;

public interface EmployeeService {

    EmployeeDisplayDTO createEmployee(EmployeeCreateDTO employee);

    List<EmployeeDisplayDTO> findAll(Integer pageNumber, Integer pageSize, String sortBy);

    void saveAll(List<Employee> employees);

    EmployeeDisplayDTO findByName(String name);

    String assignEmployee(Integer employeeId, Integer companyId);
}
