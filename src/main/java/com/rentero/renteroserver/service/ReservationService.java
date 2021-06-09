package com.rentero.renteroserver.service;

import com.rentero.renteroserver.exception.ResourceNotFoundException;
import com.rentero.renteroserver.model.Car;
import com.rentero.renteroserver.model.Company;
import com.rentero.renteroserver.model.Customer;
import com.rentero.renteroserver.model.Reservation;
import com.rentero.renteroserver.payload.request.ReservationReqDto;
import com.rentero.renteroserver.payload.response.ReservationDto;
import com.rentero.renteroserver.repository.CarRepository;
import com.rentero.renteroserver.repository.CompanyRepository;
import com.rentero.renteroserver.repository.CustomerRepository;
import com.rentero.renteroserver.repository.ReservationRepository;
import com.rentero.renteroserver.service.mapper.DtoMapper;
import com.rentero.renteroserver.service.mapper.EntityMapper;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private ReservationRepository reservationRepository;
    private CarRepository carRepository;
    private CustomerRepository customerRepository;

    private DtoMapper dtoMapper;
    private EntityMapper entityMapper;

    public ReservationService(ReservationRepository reservationRepository, CarRepository carRepository, CustomerRepository customerRepository, DtoMapper dtoMapper, EntityMapper entityMapper) {
        this.reservationRepository = reservationRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.dtoMapper = dtoMapper;
        this.entityMapper = entityMapper;
    }

    public List<ReservationDto> getAll() {
        List<Reservation> reservations = reservationRepository.findAll();

        return reservations.stream().map(reservation -> dtoMapper.mapToReservationDto(reservation)).collect(Collectors.toList());
    }

    public ReservationDto getById(long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", String.valueOf(id)));

        return dtoMapper.mapToReservationDto(reservation);
    }

    public ReservationDto createReservation(ReservationReqDto reservationReqDto, long carId) {
        Car car  = carRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundException("Car", "id", String.valueOf(carId)));
        Customer customer = customerRepository.findById(1l).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", String.valueOf(1)));

        Reservation reservation = entityMapper.mapToReservationEntity(reservationReqDto);

        reservation.setCustomer(customer);
        reservation.setCar(car);

        long daysBetween = Duration.between(reservationReqDto.getStartDate().atStartOfDay(), reservationReqDto.getEndDate().atStartOfDay()).toDays();
        Double price = car.getPricePerDay() * daysBetween;
        reservation.setPrice(price);

        Reservation newReservation = reservationRepository.save(reservation);

        return dtoMapper.mapToReservationDto(newReservation);
    }

    public void deleteReservation(long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", String.valueOf(reservationId)));

        reservationRepository.delete(reservation);
    }

}