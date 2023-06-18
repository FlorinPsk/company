package com.sda.company.convertor;

import com.sda.company.dto.EmployeeCreateDTO;
import com.sda.company.dto.EmployeeDisplayDTO;
import com.sda.company.model.Employee;

public class EmployeeConvertor {

    public static Employee createDtoToEntity(EmployeeCreateDTO employeeCreateDTO) {
        Employee employee = new Employee();
        employee.setName(employeeCreateDTO.getName());
        employee.setAddress(employeeCreateDTO.getAddress());
        employee.setEmail(employeeCreateDTO.getEmail());
        employee.setNid(employeeCreateDTO.getNid());
        employee.setPhoneNumber(employee.getPhoneNumber());
        employee.setJobTitle(employee.getJobTitle());

        return employee;
    }

    public static EmployeeDisplayDTO entityToDisplayDto(Employee employee) {
        EmployeeDisplayDTO employeeDisplayDTO = new EmployeeDisplayDTO();
        employeeDisplayDTO.setName(employee.getName());
        employeeDisplayDTO.setAddress(employee.getAddress());
        employee.setEmail(employee.getEmail());
        employee.setNid(employee.getNid());
        employee.setPhoneNumber(employee.getPhoneNumber());
        employee.setJobTitle(employee.getJobTitle());

        return employeeDisplayDTO;
    }
}
