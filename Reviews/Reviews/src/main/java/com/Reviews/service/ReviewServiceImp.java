package com.Reviews.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.Reviews.model.Review;
import com.Reviews.repository.ReviewRepository;

@Service
public class ReviewServiceImp implements ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;


	    @Override
	    public Review addReview(Review review) {
	    	boolean exists = reviewRepository.existsByUserIdAndMovieId(review.getUserId(), review.getMovieId());
	        
	    	 if (exists) {
	             throw new IllegalArgumentException("User already gave a review for this movie.");
	         }
	    	 
	    	 review.setTimestamp(LocalDateTime.now());
	         return reviewRepository.save(review);
	    }

	    @Override
	    public Page<Review> getAllReviews(int page, int size) {
	        PageRequest pageRequest = PageRequest.of(page, size);
	        return this.reviewRepository.findAll(pageRequest);
	    }

	    @Override
	    public Review updateReview(String reviewId, Review updatedReview) {
	        Optional<Review> optional= this.reviewRepository.findById(reviewId);
	        Review existingReview=optional.get();
	        
	        if(updatedReview.getComment()!=null) {
	        	existingReview.setComment(updatedReview.getComment());
	        }
	        
	        if(updatedReview.getRating()!= 0 ) {
	        	existingReview.setRating(updatedReview.getRating());	        	
	        	
	        }
	        // Update other fields as needed

	        return reviewRepository.save(existingReview);
	    }

	    @Override
	    public Review getReviewById(String reviewId) {
	    	Optional<Review> optional=this.reviewRepository.findById(reviewId);
	        return optional.get();
	    }

	    @Override
	    public boolean deleteReview(String reviewId) {
	        if (reviewRepository.existsById(reviewId)) {
	            reviewRepository.deleteById(reviewId);
	            return true;
	        } else {
	            return false;
	        }
	}

		@Override
		public List<Review> getReviewsByMovieId(String movieId) {
			 return reviewRepository.findByMovieId(movieId);
		}

}
