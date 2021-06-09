package com.rentero.renteroserver.service;

import com.rentero.renteroserver.exception.ResourceNotFoundException;
import com.rentero.renteroserver.model.Company;
import com.rentero.renteroserver.payload.response.CompanyDto;
import com.rentero.renteroserver.repository.CompanyRepository;
import com.rentero.renteroserver.service.mapper.DtoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    private CompanyRepository companyRepository;

    private DtoMapper dtoMapper;

    public CompanyService(CompanyRepository companyRepository, DtoMapper dtoMapper) {
        this.companyRepository = companyRepository;
        this.dtoMapper = dtoMapper;
    }

    public List<CompanyDto> getAll() {
        List<Company> companies = companyRepository.findAll();

        return companies.stream().map(company -> dtoMapper.mapToCompanyDto(company)).collect(Collectors.toList());
    }

    public CompanyDto getById(long id) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Company", "id", String.valueOf(id)));

        return dtoMapper.mapToCompanyDto(company);
    }

}