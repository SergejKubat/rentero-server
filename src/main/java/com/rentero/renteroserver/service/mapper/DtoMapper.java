package com.rentero.renteroserver.service.mapper;

import com.rentero.renteroserver.model.*;
import com.rentero.renteroserver.payload.response.*;
import org.springframework.stereotype.Service;

@Service
public class DtoMapper {

    public CarDto mapToCarDto(Car car) {
        CarDto carDto = new CarDto();

        carDto.setId(car.getId());
        carDto.setCompanyId(car.getCompany().getId());
        carDto.setBrand(car.getBrand());
        carDto.setModel(car.getModel());
        carDto.setCapacity(car.getCapacity());
        carDto.setDescription(car.getDescription());
        carDto.setDoors(car.getDoors());
        carDto.setEngineSize(car.getEngineSize());
        carDto.setFuel(car.getFuel());
        carDto.setGearStick(car.getGearStick());
        carDto.setHp(car.getHp());
        carDto.setMainImageUrl(car.getMainImageUrl());
        carDto.setPricePerDay(car.getPricePerDay());
        carDto.setYear(car.getYear());

        return carDto;
    }

    public CompanyDto mapToCompanyDto(Company company) {
        CompanyDto companyDto = new CompanyDto();

        companyDto.setId(company.getId());
        companyDto.setName(company.getName());
        companyDto.setDescription(company.getDescription());
        companyDto.setImageUrl(company.getImageUrl());
        companyDto.setEmail(company.getEmail());
        companyDto.setPhoneNumber(company.getPhoneNumber());
        companyDto.setAddress(company.getAddress());
        companyDto.setCity(company.getCity());
        companyDto.setLatitude(company.getLatitude());
        companyDto.setLongitude(company.getLongitude());

        return companyDto;
    }

    public CustomerDto mapToCustomerDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();

        customerDto.setId(customer.getId());
        customerDto.setFullName(customer.getFullName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPhoneNumber(customer.getPhoneNumber());
        customerDto.setDateCreated(customer.getDateCreated());
        customerDto.setAvatarUrl(customer.getAvatarUrl());

        return customerDto;
    }

    public ReservationDto mapToReservationDto(Reservation reservation) {
        ReservationDto reservationDto = new ReservationDto();

        reservationDto.setId(reservation.getId());
        reservationDto.setCarBrandAndModel(reservation.getCar().getBrand() + " " + reservation.getCar().getModel());
        reservationDto.setCompanyAddress(reservation.getCar().getCompany().getAddress());
        reservationDto.setStartDate(reservation.getStartDate());
        reservationDto.setEndDate(reservation.getEndDate());
        reservationDto.setPrice(reservation.getPrice());
        reservationDto.setEnabled(reservation.isEnabled());

        return reservationDto;
    }

    public ReviewDto mapToReviewDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();

        reviewDto.setId(review.getId());
        reviewDto.setCustomerName(review.getCustomer().getFullName());
        reviewDto.setCustomerAvatar(review.getCustomer().getAvatarUrl());
        reviewDto.setMark(review.getMark());
        reviewDto.setComment(review.getComment());
        reviewDto.setDateCreated(review.getDateCreated());

        return reviewDto;
    }

}