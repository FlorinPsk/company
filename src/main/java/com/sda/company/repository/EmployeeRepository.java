package com.sda.company.repository;

import com.sda.company.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee, Integer>,
        PagingAndSortingRepository<Employee, Integer> {

    Optional<Employee> findByName(String name);
}
