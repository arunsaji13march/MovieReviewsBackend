package com.Reviews.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Reviews.model.Review;
import com.Reviews.service.ReviewService;

@RestController
@RequestMapping("/review/v1")
@CrossOrigin("*")
public class ReviewController {
	
	ResponseEntity<?> responseEntity;
	
	@Autowired
	ReviewService reviewService;
		
	@GetMapping("getAllReviews")
	public ResponseEntity<?> getAllReviewsHandler(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size){
		
		Page<Review> reviewPage=this.reviewService.getAllReviews(page, size);
		List<Review> reviewList=reviewPage.getContent();
		
		Map<String,Object> response= new HashMap<String,Object>();
		response.put("data", reviewList);
		response.put("page", page);
		response.put("size", size);
		response.put("totalElements", reviewPage.getTotalElements());
		
		return responseEntity.ok(response);
	}
	
	@PostMapping("/addReview")
	public ResponseEntity<?> addNewReviewHandler(@RequestBody Review review){
//		Review newReview=this.reviewService.addReview(review);
//		System.out.println(newReview);
//		return responseEntity.ok(newReview);
		
		   try {
	            Review addedReview = reviewService.addReview(review);
	            return ResponseEntity.ok(addedReview);
	        } catch (IllegalArgumentException e) {
	            // Return a 409 Conflict status when the user already gave a review for this movie
	        	String errorMessage = "User already gave a review for this movie.";
	            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
	        } catch (Exception e) {
	            // Handle other exceptions, return 500 Internal Server Error status
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
		
	}
	
	@PatchMapping("/updateReview/{reviewId}")
	public ResponseEntity<?> updateReviewHandler(@PathVariable String reviewId, @RequestBody Review review){
		Review oldReview = this.reviewService.updateReview(reviewId, review);
		return responseEntity.ok(oldReview);
		
	}
	
	@GetMapping("/getReviewById")
	public ResponseEntity<?> getReviewById(@RequestParam String reviewId){
		Review review=this.reviewService.getReviewById(reviewId);
		return responseEntity.ok(review);	
	}
	
	@DeleteMapping("/deleteReview")
	public ResponseEntity<?> deleteReviewHandler(@RequestParam String reviewId){
		
		boolean flag=this.reviewService.deleteReview(reviewId);
		if(flag) {
			return responseEntity.ok("Deletion Successfull.....");
		}
		return new ResponseEntity<>("not found...",HttpStatus.NOT_FOUND);
		
	}
	
	
	 @GetMapping("/reviewsByMovieId")
	    public ResponseEntity<List<Review>> getReviewsByMovieId(@RequestParam String movieId) {
	        List<Review> reviews = reviewService.getReviewsByMovieId(movieId);
	        return ResponseEntity.ok(reviews);
	    }

}
