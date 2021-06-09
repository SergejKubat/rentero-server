package com.rentero.renteroserver.service;

import com.rentero.renteroserver.exception.ResourceNotFoundException;
import com.rentero.renteroserver.model.Customer;
import com.rentero.renteroserver.payload.request.CustomerReqDto;
import com.rentero.renteroserver.payload.response.CustomerDto;
import com.rentero.renteroserver.repository.CustomerRepository;
import com.rentero.renteroserver.service.mapper.DtoMapper;
import com.rentero.renteroserver.service.mapper.EntityMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    private DtoMapper dtoMapper;
    private EntityMapper entityMapper;

    public CustomerService(CustomerRepository customerRepository, DtoMapper dtoMapper, EntityMapper entityMapper) {
        this.customerRepository = customerRepository;
        this.dtoMapper = dtoMapper;
        this.entityMapper = entityMapper;
    }

    public List<CustomerDto> getAll() {
        List<Customer> customers = customerRepository.findAll();

        return customers.stream().map(customer -> dtoMapper.mapToCustomerDto(customer)).collect(Collectors.toList());
    }

    public CustomerDto getById(long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", String.valueOf(id)));

        return dtoMapper.mapToCustomerDto(customer);
    }

    public CustomerDto createCustomer(CustomerReqDto customerReqDto) {
        Customer customer = entityMapper.mapToCustomerEntity(customerReqDto);

        Customer newCustomer = customerRepository.save(customer);

        return dtoMapper.mapToCustomerDto(newCustomer);
    }

    public void deleteCustomer(long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", String.valueOf(id)));

        customerRepository.delete(customer);
    }

}