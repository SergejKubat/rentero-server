package com.rentero.renteroserver.payload.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class LoginDto {

    @Email(message = "Morate uneti ispravnu email adresu.")
    private String email;
    @Size(min = 6, message = "Lozinka mora imati barem 6 karaktera.")
    private String password;

}