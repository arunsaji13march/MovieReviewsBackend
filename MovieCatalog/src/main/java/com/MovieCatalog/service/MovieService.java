package com.MovieCatalog.service;



import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.MovieCatalog.model.Movies;


public interface MovieService {
	
	public Movies addMovie(Movies movie);
	public Page<Movies> getAllMovies(int page, int size);
	public Movies updateMovie(String movieId,Movies updatedMovie);
	public Movies getMovieById(String movieId);
	public boolean deleteMovie(String movieId);
	public Page<Movies> getMoviesByGenre(String genre,int page,int size);
}
