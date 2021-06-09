package com.rentero.renteroserver.repository;

import com.rentero.renteroserver.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByCustomerId(long customerId);

    List<Reservation> findAllByCarId(long carId);

}
