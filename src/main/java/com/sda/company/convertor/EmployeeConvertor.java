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
        employee.setPhoneNumber(employeeCreateDTO.getPhoneNumber());
        employee.setJobTitle(employeeCreateDTO.getJobTitle());
        employee.setSalary(employeeCreateDTO.getSalary());

        return employee;
    }

    public static EmployeeDisplayDTO entityToDisplayDto(Employee employee) {
        EmployeeDisplayDTO employeeDisplayDTO = new EmployeeDisplayDTO();
        employeeDisplayDTO.setId(employee.getId());
        employeeDisplayDTO.setName(employee.getName());
        employeeDisplayDTO.setAddress(employee.getAddress());
        employeeDisplayDTO.setEmail(employee.getEmail());
        employeeDisplayDTO.setNid(employee.getNid());
        employeeDisplayDTO.setPhoneNumber(employee.getPhoneNumber());
        employeeDisplayDTO.setJobTitle(employee.getJobTitle());
        employeeDisplayDTO.setSalary(employeeDisplayDTO.getSalary());

        return employeeDisplayDTO;
    }
}
