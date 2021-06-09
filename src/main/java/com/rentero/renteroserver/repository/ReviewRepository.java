package com.rentero.renteroserver.repository;

import com.rentero.renteroserver.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByCustomerId(long customerId);

    List<Review> findAllByCompanyId(long companyId);

}
