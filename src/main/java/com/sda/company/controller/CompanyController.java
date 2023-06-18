package com.sda.company.controller;

import com.sda.company.dto.CompanyCreateDTO;
import com.sda.company.dto.CompanyDisplayDTO;
import com.sda.company.service.CompanyService;
import com.sda.company.util.CustomFakerCompany;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/company")
@ControllerAdvice
public class CompanyController {

    private final CompanyService companyService;

    private final CustomFakerCompany customFakerCompany;

    public CompanyController(CompanyService companyService, CustomFakerCompany customFakerCompany) {
        this.companyService = companyService;
        this.customFakerCompany = customFakerCompany;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {

        return ResponseEntity.ok("Hello World");
    }

    @PostMapping("/create")
    public ResponseEntity<CompanyDisplayDTO> createCompany(
            @RequestBody @Valid CompanyCreateDTO companyCreateDTO) {
        CompanyDisplayDTO companyDisplayDTO = companyService
                .createCompany(companyCreateDTO);

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
}
