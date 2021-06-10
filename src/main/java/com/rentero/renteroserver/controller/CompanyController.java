package com.rentero.renteroserver.controller;

import com.rentero.renteroserver.payload.response.CompanyDto;
import com.rentero.renteroserver.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/companies")
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<CompanyDto> getAll() {
        return companyService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getById(@PathVariable(name = "id") long id) {
        return new ResponseEntity<>(companyService.getById(id), HttpStatus.OK);
    }

}