package com.rentero.renteroserver.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class ReviewReqDto {

    @Min(value = 1, message = "Ocena ne sme biti manja od 1.")
    @Max(value = 5, message = "Ocena ne sme biti veca od 5.")
    private int mark;
    @NotBlank(message = "Morate uneti komentar.")
    @Size(min = 10, max = 255, message = "Broj karaktera mora biti izmedju 10 i 255")
    private String comment;

}
