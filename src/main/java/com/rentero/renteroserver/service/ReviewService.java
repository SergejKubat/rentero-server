package com.rentero.renteroserver.service;

import com.rentero.renteroserver.exception.ResourceNotFoundException;
import com.rentero.renteroserver.model.Company;
import com.rentero.renteroserver.model.Customer;
import com.rentero.renteroserver.model.Review;
import com.rentero.renteroserver.payload.request.ReviewReqDto;
import com.rentero.renteroserver.payload.response.ReviewDto;
import com.rentero.renteroserver.repository.CompanyRepository;
import com.rentero.renteroserver.repository.CustomerRepository;
import com.rentero.renteroserver.repository.ReviewRepository;
import com.rentero.renteroserver.service.mapper.DtoMapper;
import com.rentero.renteroserver.service.mapper.EntityMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;
    private CompanyRepository companyRepository;
    private CustomerRepository customerRepository;

    private DtoMapper dtoMapper;
    private EntityMapper entityMapper;

    public ReviewService(ReviewRepository reviewRepository, CompanyRepository companyRepository, CustomerRepository customerRepository, DtoMapper dtoMapper, EntityMapper entityMapper) {
        this.reviewRepository = reviewRepository;
        this.companyRepository = companyRepository;
        this.customerRepository = customerRepository;
        this.dtoMapper = dtoMapper;
        this.entityMapper = entityMapper;
    }

    public List<ReviewDto> getAll() {
        List<Review> reviews = reviewRepository.findAll();

        return reviews.stream().map(review -> dtoMapper.mapToReviewDto(review)).collect(Collectors.toList());
    }

    public ReviewDto getById(long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ResourceNotFoundException("Review", "id", String.valueOf(reviewId)));

        return dtoMapper.mapToReviewDto(review);
    }

    public ReviewDto createReview(ReviewReqDto reviewReqDto, long companyId) {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new ResourceNotFoundException("Company", "id", String.valueOf(companyId)));
        Customer customer = customerRepository.findById(1l).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", String.valueOf(1)));

        Review review = entityMapper.mapToReviewEntity(reviewReqDto);

        review.setCustomer(customer);
        review.setCompany(company);

        Review newReview = reviewRepository.save(review);

        return dtoMapper.mapToReviewDto(newReview);
    }

    public void deleteReview(long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ResourceNotFoundException("Review", "id", String.valueOf(reviewId)));

        reviewRepository.delete(review);
    }

}