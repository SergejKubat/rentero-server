package com.rentero.renteroserver.service;

import com.rentero.renteroserver.exception.RenteroException;
import com.rentero.renteroserver.exception.ResourceNotFoundException;
import com.rentero.renteroserver.model.Customer;
import com.rentero.renteroserver.model.Role;
import com.rentero.renteroserver.payload.request.CustomerReqDto;
import com.rentero.renteroserver.payload.response.CustomerDto;
import com.rentero.renteroserver.repository.CustomerRepository;
import com.rentero.renteroserver.repository.RoleRepository;
import com.rentero.renteroserver.security.SecurityUtils;
import com.rentero.renteroserver.service.mapper.DtoMapper;
import com.rentero.renteroserver.service.mapper.EntityMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;
    private RoleRepository roleRepository;

    private MailService mailService;

    private DtoMapper dtoMapper;
    private EntityMapper entityMapper;

    public CustomerService(CustomerRepository customerRepository, RoleRepository roleRepository, MailService mailService, DtoMapper dtoMapper, EntityMapper entityMapper) {
        this.customerRepository = customerRepository;
        this.roleRepository = roleRepository;
        this.mailService = mailService;
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

    public CustomerDto getByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Customer", "email", email));

        return dtoMapper.mapToCustomerDto(customer);
    }

    public CustomerDto createCustomer(CustomerReqDto customerReqDto) {
        Customer customer = entityMapper.mapToCustomerEntity(customerReqDto);

        Set<Role> roles = new HashSet<>();

        Role customerRole = roleRepository.findById(1).get();

        roles.add(customerRole);

        customer.setRoles(roles);

        Customer newCustomer = customerRepository.save(customer);

        //mailService.sendCreationEmail(customer);

        return dtoMapper.mapToCustomerDto(newCustomer);
    }

    public CustomerDto uploadAvatar(String avatarUrl, long customerId) {
        String customerEmail = SecurityUtils.getCurrentCustomerEmail();

        Customer customer = customerRepository.findByEmail(customerEmail).get();

        if (customer.getId() != customerId) {
            throw new RenteroException(HttpStatus.BAD_REQUEST, "Pogre??an nalog.");
        }

        customer.setAvatarUrl(avatarUrl);

        Customer updatedCustomer = customerRepository.save(customer);

        return dtoMapper.mapToCustomerDto(updatedCustomer);
    }

    public void deleteCustomer(long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", String.valueOf(id)));

        customerRepository.delete(customer);
    }

}