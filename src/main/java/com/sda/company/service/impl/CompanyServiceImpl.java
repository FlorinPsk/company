package com.sda.company.service.impl;

import com.sda.company.converter.CompanyConvertor;
import com.sda.company.dto.CompanyCreateDTO;
import com.sda.company.dto.CompanyDisplayDTO;
import com.sda.company.exceptions.CompanyException;
import com.sda.company.model.Company;
import com.sda.company.repository.CompanyRepository;
import com.sda.company.service.CompanyService;
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
    public CompanyDisplayDTO createCompany(CompanyCreateDTO companyCreateDto) {
        Company companyToSave = CompanyConvertor.createDtoToEntity(companyCreateDto);
        Company savedCompany = companyRepository.save(companyToSave);

        return CompanyConvertor.entityToDisplayDto(savedCompany);
    }

    @Override
    public List<CompanyDisplayDTO> findAll(Integer pageNumber, Integer pageSize, String sortBy) {

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
                .orElseThrow(() -> new CompanyException(String.format("Company with name %s not found", name)));

        return CompanyConvertor.entityToDisplayDto(company);
    }

}
