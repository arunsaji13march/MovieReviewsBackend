package com.MovieCatalog.service;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.MovieCatalog.model.Movies;
import com.MovieCatalog.repository.MovieRepository;

@Service
public class MovieServiceImp implements MovieService {
	
	@Autowired
	MovieRepository movieRepository;

	@Override
	public Movies addMovie(Movies movie) {
		// TODO Auto-generated method stub
//		Optional<Movies> optional= this.movieRepository.findById(movie.getMovieId());
		movie.setCreatedAt(LocalDateTime.now());
		Movies newMovies= this.movieRepository.save(movie);
		return newMovies;
	}



	@Override
	public Movies updateMovie(String movieId, Movies updatedMovie) {
		// TODO Auto-generated method stub
		Optional<Movies> optional= this.movieRepository.findById(movieId);
		Movies movie=optional.get();
		
		if(updatedMovie.getImage()!=null) {
			movie.setImage(updatedMovie.getImage());
		}
		if(updatedMovie.getTitle()!=null) {
			movie.setTitle(updatedMovie.getTitle());
		}
		if(updatedMovie.getDescription()!=null) {
			movie.setDescription(updatedMovie.getDescription());
		}
		if(updatedMovie.getReleaseDate()!=null) {
			movie.setReleaseDate(updatedMovie.getReleaseDate());
		}
		if(updatedMovie.getDirector()!=null) {
			movie.setDirector(updatedMovie.getDirector());
		}
		if(updatedMovie.getGenres()!=null) {
			movie.setGenres(updatedMovie.getGenres());
		}
		if(updatedMovie.getCast()!=null) {
			movie.setCast(updatedMovie.getCast());
		}
		if(updatedMovie.getRating()!=0) {
			movie.setRating(updatedMovie.getRating());
		}
		
		
//		this.movieRepository.save(movie);
		return this.movieRepository.save(movie);
	}

	@Override
	public Movies getMovieById(String movieId) {
		// TODO Auto-generated method stub
		Optional<Movies> optional= this.movieRepository.findById(movieId);
		return optional.get();
	}

	@Override
	public boolean deleteMovie(String movieId) {
		// TODO Auto-generated method stub
		Optional<Movies> optional = this.movieRepository.findById(movieId);
		boolean flag=false;
		if(optional.isPresent()) {
			this.movieRepository.delete(optional.get());
			flag= true;
		}
		return flag;
	}



	@Override
	public Page<Movies> getAllMovies(int page, int size) {
		// TODO Auto-generated method stub
		 PageRequest pageable = PageRequest.of(page, size);
		 return this.movieRepository.findAll(pageable);
	}



	@Override
	public Page<Movies> getMoviesByGenre(String genre, int page, int size) {
		// TODO Auto-generated method stub
		PageRequest pageable = PageRequest.of(page, size);
		return this.movieRepository.findByGenres(genre, pageable);
	}



	



	



	

}
