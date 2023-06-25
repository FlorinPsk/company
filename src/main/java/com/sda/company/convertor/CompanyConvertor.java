package com.sda.company.convertor;

import com.sda.company.dto.CompanyCreateDTO;
import com.sda.company.dto.CompanyDisplayDTO;
import com.sda.company.dto.CompanyUpdateDTO;
import com.sda.company.model.Company;

public class CompanyConvertor {

    public static Company createDtoToEntity(CompanyCreateDTO companyCreateDTO) {
        Company company = new Company();
        company.setName(companyCreateDTO.getName());
        company.setEmail(companyCreateDTO.getEmail());
        company.setAddress(companyCreateDTO.getAddress());
        company.setRegistrationNumber(companyCreateDTO.getRegistrationNumber());

        return company;
    }

    public static CompanyDisplayDTO entityToDisplayDto(Company company) {
        CompanyDisplayDTO companyDisplayDTO = new CompanyDisplayDTO();
        companyDisplayDTO.setId(company.getId());
        companyDisplayDTO.setName(company.getName());
        companyDisplayDTO.setEmail(company.getEmail());
        companyDisplayDTO.setAddress(company.getAddress());
        companyDisplayDTO.setRegistrationNumber(company.getRegistrationNumber());

        return companyDisplayDTO;
    }

    public static Company updateDtoToEntity(CompanyUpdateDTO companyUpdateDTO) {
        Company company = new Company();
        company.setName(companyUpdateDTO.getName());
        company.setAddress(companyUpdateDTO.getAddress());
        company.setEmail(companyUpdateDTO.getEmail());
        company.setRegistrationNumber(companyUpdateDTO.getRegistrationNumber());

        return company;
    }
}
