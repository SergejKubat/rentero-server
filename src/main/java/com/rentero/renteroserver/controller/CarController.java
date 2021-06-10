package com.rentero.renteroserver.controller;

import com.rentero.renteroserver.payload.response.CarDto;
import com.rentero.renteroserver.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/cars")
public class CarController {

    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<CarDto> getAll() {
        return carService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getById(@PathVariable(name = "id") long id) {
        return new ResponseEntity<>(carService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    public List<CarDto> getByCompanyId(@RequestParam(name = "companyId", required = false) long companyId) {
        return carService.getAllByCompanyId(companyId);
    }

}
