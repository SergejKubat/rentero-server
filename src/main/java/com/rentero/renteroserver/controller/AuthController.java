package com.rentero.renteroserver.controller;

import com.rentero.renteroserver.payload.request.CustomerReqDto;
import com.rentero.renteroserver.payload.request.LoginDto;
import com.rentero.renteroserver.payload.response.JwtAuthResponse;
import com.rentero.renteroserver.repository.CustomerRepository;
import com.rentero.renteroserver.security.JwtTokenProvider;
import com.rentero.renteroserver.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    private CustomerService customerService;
    private CustomerRepository customerRepository;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, CustomerService customerService, CustomerRepository customerRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.customerService = customerService;
        this.customerRepository = customerRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> authenticateUser(@Valid @RequestBody LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JwtAuthResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> createCustomer(@Valid @RequestBody CustomerReqDto customerReqDto) {

        if (customerRepository.existsByEmail(customerReqDto.getEmail())) {
            return new ResponseEntity<>("Email adresa već postoji.", HttpStatus.BAD_REQUEST);
        }

        if (customerRepository.existsByPhoneNumber(customerReqDto.getPhoneNumber())) {
            return new ResponseEntity<>("Broj telefona već postoji.", HttpStatus.BAD_REQUEST);
        }

        customerService.createCustomer(customerReqDto);

        return new ResponseEntity<>("Uspešno ste se registrovali.", HttpStatus.CREATED);
    }

}