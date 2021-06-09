package com.rentero.renteroserver.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class ReviewReqDto {

    private int mark;
    @NotBlank(message = "Morate uneti komentar.")
    @Size(min = 10, max = 255, message = "Broj karaktera mora biti izmedju 10 i 255")
    private String comment;

}
