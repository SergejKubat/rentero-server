package com.rentero.renteroserver.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String avatarUrl;
    private LocalDate dateCreated;

}
