package com.rentero.renteroserver.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerReqDto {

    @NotBlank(message = "Morate uneti ime i prezime.")
    private String fullName;
    @Email(message = "Morate uneti ispravnu email adresu,")
    private String email;
    @NotBlank(message = "Morate uneti broj telefona.")
    private String phoneNumber;
    @Min(value = 6, message = "Lozinka mora imati barem 6 karaktera.")
    private String password;

}
