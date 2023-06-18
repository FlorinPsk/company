package com.sda.company.service.impl;

import com.sda.company.convertor.EmployeeConvertor;
import com.sda.company.dto.EmployeeCreateDTO;
import com.sda.company.dto.EmployeeDisplayDTO;
import com.sda.company.exceptions.EntityException;
import com.sda.company.model.Employee;
import com.sda.company.repository.EmployeeRepository;
import com.sda.company.service.EmployeeService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDisplayDTO createEmployee(EmployeeCreateDTO employee) {
        Employee employeeToSave = EmployeeConvertor.createDtoToEntity(employee);
        Employee savedEmployee = employeeRepository.save(employeeToSave);

        return EmployeeConvertor.entityToDisplayDto(savedEmployee);
    }

    @Override
    public List<EmployeeDisplayDTO> findAll(
            Integer pageNumber, Integer pageSize, String sortBy) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));

        List<EmployeeDisplayDTO> employeeDisplayDTOList = new ArrayList<>();
        employeeRepository.findAll(pageable)
                .forEach(entity ->
                        employeeDisplayDTOList.add(EmployeeConvertor.entityToDisplayDto(entity)));

        return employeeDisplayDTOList;
    }

    @Override
    public void saveAll(List<Employee> employees) {
        employeeRepository.saveAll(employees);
    }

    @Override
    public EmployeeDisplayDTO findByName(String name) {
        Employee employee = employeeRepository.findByName(name)
                .orElseThrow(() -> new EntityException(String.format("Employee %s not found!", name)));

        return EmployeeConvertor.entityToDisplayDto(employee);
    }
}
