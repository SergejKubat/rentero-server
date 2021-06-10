package com.rentero.renteroserver.controller;

import com.rentero.renteroserver.payload.request.ReviewReqDto;
import com.rentero.renteroserver.payload.response.ReviewDto;
import com.rentero.renteroserver.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/reviews")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<ReviewDto> getAll() {
        return reviewService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> getById(@PathVariable(name = "id") long id) {
        return new ResponseEntity<>(reviewService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReviewDto> createReview(@Valid @RequestBody ReviewReqDto reviewReqDto,
                                                  @RequestParam(name = "companyId") long companyId) {
        ReviewDto reviewDto = reviewService.createReview(reviewReqDto, companyId);

        return new ResponseEntity<>(reviewDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable(name = "id") long id) {
        reviewService.deleteReview(id);

        return new ResponseEntity<>("Review deleted successfully.", HttpStatus.OK);
    }

}