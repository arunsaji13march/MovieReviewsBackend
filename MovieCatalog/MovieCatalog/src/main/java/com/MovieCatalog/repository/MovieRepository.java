package com.MovieCatalog.repository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.MovieCatalog.model.Movies;

@Repository
public interface MovieRepository extends MongoRepository<Movies, String> {
	
	 Page<Movies> findByGenres(String genre, PageRequest pageable);
	

}
