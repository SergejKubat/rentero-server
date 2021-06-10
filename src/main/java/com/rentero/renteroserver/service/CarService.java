package com.rentero.renteroserver.service;

import com.rentero.renteroserver.exception.ResourceNotFoundException;
import com.rentero.renteroserver.model.Car;
import com.rentero.renteroserver.payload.response.CarDto;
import com.rentero.renteroserver.repository.CarRepository;
import com.rentero.renteroserver.service.mapper.DtoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private CarRepository carRepository;

    private DtoMapper dtoMapper;

    public CarService(CarRepository carRepository, DtoMapper dtoMapper) {
        this.carRepository = carRepository;
        this.dtoMapper = dtoMapper;
    }

    public List<CarDto> getAll() {
        List<Car> cars = carRepository.findAllByReservedFalse();

        return cars.stream().map(car -> dtoMapper.mapToCarDto(car)).collect(Collectors.toList());
    }

    public CarDto getById(long id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car", "id", String.valueOf(id)));

        return dtoMapper.mapToCarDto(car);
    }

    public List<CarDto> getAllByCompanyId(long companyId) {
        List<Car> cars = carRepository.findAllByCompanyId(companyId);

        return cars.stream().map(car -> dtoMapper.mapToCarDto(car)).collect(Collectors.toList());
    }

}