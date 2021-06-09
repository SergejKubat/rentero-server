package com.rentero.renteroserver.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {

    private long id;
    private String name;
    private String description;
    private String imageUrl;
    private String phoneNumber;
    private String email;
    private String address;
    private String city;
    private double latitude;
    private double longitude;

}
