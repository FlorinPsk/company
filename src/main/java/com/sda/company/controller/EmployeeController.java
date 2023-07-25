package com.sda.company.controller;

import com.sda.company.dto.EmployeeCreateDTO;
import com.sda.company.dto.EmployeeDisplayDTO;
import com.sda.company.service.EmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employee")
@ControllerAdvice
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/create")
    public ResponseEntity<EmployeeDisplayDTO> createEmployee(
            @RequestBody @Valid EmployeeCreateDTO employeeCreateDTO) {
        EmployeeDisplayDTO employeeDisplayDTO = employeeService
                .createEmployee(employeeCreateDTO);

        return ResponseEntity.ok(employeeDisplayDTO);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<EmployeeDisplayDTO>> findAll(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {

        return ResponseEntity.ok(employeeService.findAll(pageNumber, pageSize, sortBy));
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<EmployeeDisplayDTO> findByName(@PathVariable String name) {
        EmployeeDisplayDTO employeeDisplayDTO = employeeService.findByName(name);

        return ResponseEntity.ok(employeeDisplayDTO);
    }

    @GetMapping("/assign")
    public ResponseEntity<String> assignEmployee(@RequestParam @NotNull Integer employeeId,
                                                 @RequestParam @NotNull Integer companyId) {

        return ResponseEntity.ok(employeeService.assignEmployee(employeeId, companyId));
    }
}
