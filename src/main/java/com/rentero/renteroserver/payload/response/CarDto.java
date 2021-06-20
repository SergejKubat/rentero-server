package com.rentero.renteroserver.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {

    private long id;
    private long companyId;
    private String brand;
    private String model;
    private String mainImageUrl;
    private int capacity;
    private int doors;
    private int hp;
    private int engineSize;
    private int year;
    private String fuel;
    private String gearStick;
    private String description;
    private double pricePerDay;

}