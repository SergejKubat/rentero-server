package com.rentero.renteroserver.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {

    private long id;
    private String customerName;
    private String customerAvatar;
    private int mark;
    private String comment;
    private LocalDate dateCreated;

}