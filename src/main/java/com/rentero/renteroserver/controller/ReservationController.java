package com.rentero.renteroserver.controller;

import com.rentero.renteroserver.payload.request.ReservationReqDto;
import com.rentero.renteroserver.payload.response.ReservationDto;
import com.rentero.renteroserver.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/reservations")
public class ReservationController {

    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<ReservationDto> getAll() {
        return reservationService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> getById(@PathVariable(name = "id") long id) {
        return new ResponseEntity<>(reservationService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReservationDto> createReservation(@Valid @RequestBody ReservationReqDto reservationReqDto,
                                                            @RequestParam(name = "carId") long carId) {
        ReservationDto reservationDto = reservationService.createReservation(reservationReqDto, carId);

        return new ResponseEntity<>(reservationDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable(name = "id") long id) {
        reservationService.deleteReservation(id);

        return new ResponseEntity<>("Reservation deleted successfully.", HttpStatus.OK);
    }

}