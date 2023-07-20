package com.MovieCatalog.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Movies {
	 @Id
	 private String movieId;
		 private String image;
		 private String title;
		 private String description;
		 private LocalDate releaseDate;
		 private LocalDateTime createdAt;
		 private String genres;
		 private String director;
		 private double rating;
		 private String trailer;
		 private List<String> cast;
		 
		 
		 
		 	
		@Override
		public String toString() {
			return "Movies [movieId=" + movieId + ", image=" + image + ", title=" + title + ", description="
					+ description + ", releaseDate=" + releaseDate + ", createdAt=" + createdAt + ", genres=" + genres
					+ ", director=" + director + ", rating=" + rating + ", trailer=" + trailer + ", cast=" + cast + "]";
		}


		public Movies() {
			super();
			// TODO Auto-generated constructor stub
		}


		public Movies(String image, String title, String description, LocalDate releaseDate, LocalDateTime createdAt,
				String genres, String director, double rating, String trailer, List<String> cast) {
			super();
			this.image = image;
			this.title = title;
			this.description = description;
			this.releaseDate = releaseDate;
			this.createdAt = createdAt;
			this.genres = genres;
			this.director = director;
			this.rating = rating;
			this.trailer = trailer;
			this.cast = cast;
		}
		
		
		public String getMovieId() {
			return movieId;
		}
		public void setMovieId(String movieId) {
			this.movieId = movieId;
		}
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public LocalDate getReleaseDate() {
			return releaseDate;
		}
		public void setReleaseDate(LocalDate releaseDate) {
			this.releaseDate = releaseDate;
		}
		public LocalDateTime getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}
		public String getGenres() {
			return genres;
		}
		public void setGenres(String genres) {
			this.genres = genres;
		}
		public String getDirector() {
			return director;
		}
		public void setDirector(String director) {
			this.director = director;
		}
		public double getRating() {
			return rating;
		}
		public void setRating(double rating) {
			this.rating = rating;
		}
		public String getTrailer() {
			return trailer;
		}
		public void setTrailer(String trailer) {
			this.trailer = trailer;
		}
		public List<String> getCast() {
			return cast;
		}
		public void setCast(List<String> cast) {
			this.cast = cast;
		}
		 
		 
		 
		 
		 
		
	  
	  
	    

}
