package com.rentero.renteroserver.controller;

import com.rentero.renteroserver.payload.request.CustomerReqDto;
import com.rentero.renteroserver.payload.response.CustomerDto;
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

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
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
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerReqDto customerReqDto) {
        CustomerDto customerDto = customerService.createCustomer(customerReqDto);

        return new ResponseEntity<>(customerDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable(name = "id") long id) {
        customerService.deleteCustomer(id);

        return new ResponseEntity<>("Course deleted successfully.", HttpStatus.OK);
    }

}