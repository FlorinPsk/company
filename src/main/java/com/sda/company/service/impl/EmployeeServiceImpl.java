package com.sda.company.service.impl;

import com.sda.company.convertor.EmployeeConvertor;
import com.sda.company.dto.EmployeeCreateDTO;
import com.sda.company.dto.EmployeeDisplayDTO;
import com.sda.company.exceptions.EntityException;
import com.sda.company.model.Company;
import com.sda.company.model.Employee;
import com.sda.company.repository.CompanyRepository;
import com.sda.company.repository.EmployeeRepository;
import com.sda.company.service.EmployeeService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final CompanyRepository companyRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, CompanyRepository companyRepository) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
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

    @Override
    public String assignEmployee(Integer employeeId, Integer companyId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityException(String.format("Employee with id %s not found!", employeeId)));

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityException(String.format("Company with id %s not found!", companyId)));

        employee.setCompany(company);
        employeeRepository.save(employee);

        return String.format("Employee %s has been assigned to company %s", employeeId, companyId);
    }
}
