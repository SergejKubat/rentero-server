package com.rentero.renteroserver.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ReservationReqDto {

    private LocalDate startDate;
    private LocalDate endDate;

}
