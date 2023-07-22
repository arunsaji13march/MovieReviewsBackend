package com.Reviews.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.Reviews.model.Review;

@Repository
public interface ReviewRepository  extends MongoRepository<Review, String>{
	
	List<Review> findByMovieId(String movieId);

	boolean existsByUserIdAndMovieId(int userId, String movieId);

}
