package com.sda.company.controller;

import com.sda.company.dto.CompanyCreateDTO;
import com.sda.company.dto.CompanyDisplayDTO;
import com.sda.company.dto.CompanyUpdateDTO;
import com.sda.company.service.CompanyService;
import com.sda.company.util.CustomFakerCompany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/company")
@ControllerAdvice
public class CompanyController {

    private final CompanyService companyService;

    private final CustomFakerCompany customFakerCompany;

    public CompanyController(
            @Qualifier("myCompanyServiceImpl"/* camelCase is usually used here*/) CompanyService companyService,
            CustomFakerCompany customFakerCompany) {
        this.companyService = companyService;
        this.customFakerCompany = customFakerCompany;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {

        return ResponseEntity.ok("Hello World");
    }

    @PostMapping("/create")
    public ResponseEntity<CompanyDisplayDTO> createCompany(
            @RequestBody @Valid CompanyCreateDTO companyCreateDTO,
            Principal principal) {      // The principal object is used for user's permissions
        CompanyDisplayDTO companyDisplayDTO = companyService
                .createCompany(companyCreateDTO);
        System.out.printf("Request has been made by %s", principal.getName());

        return ResponseEntity.ok(companyDisplayDTO);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<CompanyDisplayDTO>> findAll(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {

        return ResponseEntity.ok(companyService.findAll(pageNumber, pageSize, sortBy));
    }

    @GetMapping("/generate")
    public ResponseEntity<String> generateCompanies() {
        companyService.saveAll(customFakerCompany.generateCompanies());

        return ResponseEntity.ok("Companies were generated!");
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<CompanyDisplayDTO> findByName(@PathVariable String name) {
        CompanyDisplayDTO companyDisplayDTO = companyService.findByName(name);

        return ResponseEntity.ok(companyDisplayDTO);
    }

    @GetMapping("/findByNameAndRegistrationNumber")
    public ResponseEntity<CompanyDisplayDTO> findByNameAndRegistrationNumber(@RequestParam String name,
                                    @RequestParam Long registrationNumber) {
        CompanyDisplayDTO companyDisplayDTO = companyService
                .findByNameAndRegistrationNumber(name, registrationNumber);

        return ResponseEntity.ok(companyDisplayDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<CompanyDisplayDTO> updateCompany(@RequestBody @Valid CompanyUpdateDTO companyUpdateDTO) {
        CompanyDisplayDTO companyDisplayDTO = companyService.updateCompany(companyUpdateDTO);

        return ResponseEntity.ok(companyDisplayDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable @NotNull Integer id) {
        companyService.deleteCompanyById(id);

        return ResponseEntity.ok(String.format("Company with id %s was successfully deleted!", id));
    }
}
