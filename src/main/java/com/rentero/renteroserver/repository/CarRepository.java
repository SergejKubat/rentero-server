package com.rentero.renteroserver.repository;

import com.rentero.renteroserver.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findAllByCompanyId(long companyId);

    List<Car> findAllByPricePerDayIsLessThanEqual(double price);

}
