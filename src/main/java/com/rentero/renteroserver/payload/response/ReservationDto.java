package com.rentero.renteroserver.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {

    private long id;
    private String carBrandAndModel;
    private String companyAddress;
    private LocalDate startDate;
    private LocalDate endDate;
    private double price;
    private boolean enabled;

}