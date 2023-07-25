package com.sda.company.service.impl;

import com.sda.company.convertor.CompanyConvertor;
import com.sda.company.dto.CompanyCreateDTO;
import com.sda.company.dto.CompanyDisplayDTO;
import com.sda.company.dto.CompanyUpdateDTO;
import com.sda.company.exceptions.EntityException;
import com.sda.company.model.Company;
import com.sda.company.repository.CompanyRepository;
import com.sda.company.service.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public CompanyDisplayDTO createCompany(CompanyCreateDTO company) {
        Company companyToSave = CompanyConvertor.createDtoToEntity(company);
        Company savedCompany = companyRepository.save(companyToSave);

        return CompanyConvertor.entityToDisplayDto(savedCompany);
    }

    @Override
    public List<CompanyDisplayDTO> findAll(
            Integer pageNumber, Integer pageSize, String sortBy) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));

        List<CompanyDisplayDTO> companyDisplayDTOList = new ArrayList<>();
        companyRepository.findAll(pageable).
                forEach(entity ->
                        companyDisplayDTOList.add(CompanyConvertor.entityToDisplayDto(entity)));

        return companyDisplayDTOList;
    }

    @Override
    public void saveAll(List<Company> companies) {
        companyRepository.saveAll(companies);
    }

    @Override
    public CompanyDisplayDTO findByName(String name) {
        Company company = companyRepository.findByName(name)
                .orElseThrow(() -> new EntityException(String.format("Company with name %s not found", name)));

        return CompanyConvertor.entityToDisplayDto(company);
    }

    @Override
    public CompanyDisplayDTO findByNameAndRegistrationNumber(String name, Long registrationNumber) {
        Company company = companyRepository.findByNameAndRegistrationNumber(name, registrationNumber)
                .orElseThrow(() -> new EntityException(
                        String.format("Company with name %s and registration number %s not found",
                                name, registrationNumber))
                );

        return CompanyConvertor.entityToDisplayDto(company);
    }

    @Override
    public CompanyDisplayDTO updateCompany(CompanyUpdateDTO companyUpdateDTO) {
        if (companyRepository.findById(companyUpdateDTO.getId()).isPresent()) {
            throw new EntityException(String.format("Company with id %s was not found.", companyUpdateDTO.getId()));
        }

        Company company = CompanyConvertor.updateDtoToEntity(companyUpdateDTO);
        Company updatedCompany = companyRepository.save(company);

        return CompanyConvertor.entityToDisplayDto(updatedCompany);
    }

    @Override
    public void deleteCompanyById(Integer id) {
        if (companyRepository.findById(id).isEmpty()) {
            throw new EntityException(String.format("Company with id %s was not found.", id));
        }
        companyRepository.deleteById(id);
    }

    @Override
    public Page<CompanyDisplayDTO> findPage(Integer currentPage) {
        Pageable pageable = PageRequest.of(currentPage - 1, 10, Sort.by("id"));
        Page<Company> companyPage = companyRepository.findAll(pageable);
        Page<CompanyDisplayDTO> companyDisplayDTOPage = companyPage.map(CompanyConvertor::entityToDisplayDto);

        return companyDisplayDTOPage;
    }
}
