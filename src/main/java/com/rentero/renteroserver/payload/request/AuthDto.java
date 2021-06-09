package com.rentero.renteroserver.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
public class AuthDto {

    @Email(message = "Morate uneti ispravnu email adresu.")
    private String email;
    @Min(value = 6, message = "Lozinka mora imati barem 6 karaktera.")
    private String password;

}
