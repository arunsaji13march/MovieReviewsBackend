package com.Reviews.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Review {
	 @Id
	 private String reviewId;
	 private int userId;
	 private String username;
	 private String movieId;
	 private int rating;
	 private String comment;
	 private LocalDateTime timestamp;
	 
	public String getId() {
		return reviewId;
	}
	public void setId(String id) {
		this.reviewId = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Review(int userId, String movieId, int rating, String comment, LocalDateTime timestamp) {
		super();
		this.userId = userId;
		this.movieId = movieId;
		this.rating = rating;
		this.comment = comment;
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", userId=" + userId + ", movieId=" + movieId + ", rating=" + rating
				+ ", comment=" + comment + ", timestamp=" + timestamp + "]";
	}
	

	 
	 
}
