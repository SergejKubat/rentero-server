package com.rentero.renteroserver.controller;

import com.rentero.renteroserver.payload.response.CustomerDto;
import com.rentero.renteroserver.service.CustomerService;
import com.rentero.renteroserver.service.FileStorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/customers")
public class CustomerController {

    private CustomerService customerService;
    private FileStorageService fileStorageService;

    public CustomerController(CustomerService customerService, FileStorageService fileStorageService) {
        this.customerService = customerService;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping
    public List<CustomerDto> getAll() {
        return customerService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getById(@PathVariable(name = "id") long id) {
        return new ResponseEntity<>(customerService.getById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}/upload")
    public ResponseEntity<CustomerDto> uploadAvatar(@PathVariable(name = "id") long id,
                                                    @RequestParam(name = "file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String avatarUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/img/")
                .path(fileName)
                .toUriString();

        CustomerDto customerDto = customerService.uploadAvatar(avatarUrl, id);

        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable(name = "id") long id) {
        customerService.deleteCustomer(id);

        return new ResponseEntity<>("Nalog je uspešno obrisan.", HttpStatus.OK);
    }

}