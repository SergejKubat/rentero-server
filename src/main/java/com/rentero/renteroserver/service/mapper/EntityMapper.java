package com.rentero.renteroserver.service.mapper;

import com.rentero.renteroserver.model.Customer;
import com.rentero.renteroserver.model.Reservation;
import com.rentero.renteroserver.model.Review;
import com.rentero.renteroserver.payload.request.CustomerReqDto;
import com.rentero.renteroserver.payload.request.ReservationReqDto;
import com.rentero.renteroserver.payload.request.ReviewReqDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EntityMapper {

    private PasswordEncoder passwordEncoder;

    public EntityMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Customer mapToCustomerEntity(CustomerReqDto customerReqDto) {
        Customer customer = new Customer();

        customer.setFullName(customerReqDto.getFullName());
        customer.setEmail(customerReqDto.getEmail());
        customer.setPhoneNumber(customerReqDto.getPhoneNumber());
        customer.setPassword(passwordEncoder.encode(customerReqDto.getPassword()));
        customer.setDateCreated(LocalDate.now());
        customer.setAvatarUrl("http://localhost:8080/img/user.png");
        customer.setEnabled(true);

        return customer;
    }

    public Reservation mapToReservationEntity(ReservationReqDto reservationReqDto) {
        Reservation reservation = new Reservation();

        reservation.setStartDate(reservationReqDto.getStartDate());
        reservation.setEndDate(reservationReqDto.getEndDate());
        reservation.setEnabled(false);

        return reservation;
    }

    public Review mapToReviewEntity(ReviewReqDto reviewReqDto) {
        Review review = new Review();

        review.setMark(reviewReqDto.getMark());
        review.setComment(reviewReqDto.getComment());
        review.setDateCreated(LocalDate.now());

        return review;
    }

}