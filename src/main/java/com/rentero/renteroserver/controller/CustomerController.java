package com.rentero.renteroserver.controller;

import com.rentero.renteroserver.payload.request.CustomerReqDto;
import com.rentero.renteroserver.payload.response.CustomerDto;
import com.rentero.renteroserver.repository.CustomerRepository;
import com.rentero.renteroserver.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/customers")
public class CustomerController {

    private CustomerService customerService;
    private CustomerRepository customerRepository;

    public CustomerController(CustomerService customerService, CustomerRepository customerRepository) {
        this.customerService = customerService;
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public List<CustomerDto> getAll() {
        return customerService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getById(@PathVariable(name = "id") long id) {
        return new ResponseEntity<>(customerService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(@Valid @RequestBody CustomerReqDto customerReqDto) {

        if (customerRepository.existsByEmail(customerReqDto.getEmail())) {
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        if (customerRepository.existsByPhoneNumber(customerReqDto.getPhoneNumber())) {
            return new ResponseEntity<>("Phone number is already taken!", HttpStatus.BAD_REQUEST);
        }

        customerService.createCustomer(customerReqDto);

        return new ResponseEntity<>("Customer registered successfully.", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable(name = "id") long id) {
        customerService.deleteCustomer(id);

        return new ResponseEntity<>("Course deleted successfully.", HttpStatus.OK);
    }

}