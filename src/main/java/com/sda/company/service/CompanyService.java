package com.sda.company.service;

import com.sda.company.dto.CompanyCreateDTO;
import com.sda.company.dto.CompanyDisplayDTO;
import com.sda.company.dto.CompanyUpdateDTO;
import com.sda.company.model.Company;

import java.util.List;

public interface CompanyService {

    CompanyDisplayDTO createCompany(CompanyCreateDTO company);

    List<CompanyDisplayDTO> findAll(Integer pageNumber, Integer pageSize, String sortBy);

    void saveAll(List<Company> companies);

    CompanyDisplayDTO findByName(String name);

    CompanyDisplayDTO findByNameAndRegistrationNumber(String name, Long registrationNumber);

    CompanyDisplayDTO updateCompany(CompanyUpdateDTO companyUpdateDTO);

    void deleteCompanyById(Integer id);
}
