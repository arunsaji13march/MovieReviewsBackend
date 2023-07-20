package com.Reviews.service;

import java.util.List;

import org.springframework.data.domain.Page;


import com.Reviews.model.Review;

public interface ReviewService {
	
	Review addReview(Review review);
    
    Page<Review> getAllReviews(int page, int size);
    
    Review updateReview(String reviewId, Review updatedReview);
    
    Review getReviewById(String reviewId);
    
    boolean deleteReview(String reviewId);
    
    public List<Review> getReviewsByMovieId(String movieId);

}
