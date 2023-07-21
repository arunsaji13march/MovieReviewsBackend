package com.MovieCatalog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.MovieCatalog.model.Movies;
import com.MovieCatalog.service.MovieService;


@RestController
@RequestMapping("/movies/v1")
@CrossOrigin("*")
//@CrossOrigin(origins = "http://localhost:4200") // Replace with your frontend origin
public class MovieController {
	
	ResponseEntity<?> responseEntity;

	@Autowired
	MovieService movieService;
	
	@GetMapping("/getAllMovies")
	public ResponseEntity<?> getAllMoviesHandler(@RequestParam(defaultValue = "0	") int page , @RequestParam(defaultValue = "10") int size){
		
		Page<Movies> moviePage=this.movieService.getAllMovies(page, size);
		List<Movies> moieList=moviePage.getContent();
		
		Map<String, Object> response=new HashMap<String, Object>();
		response.put("data", moieList);
		response.put("page", page);
		response.put("size", size);
		response.put("totalElements", moviePage.getTotalElements());
		response.put("totalPages", moviePage.getTotalPages());
		
		return responseEntity.ok(response);
	}
	
	
	@PostMapping("/addMovie")
	public ResponseEntity<?> addMovieHandle(@RequestBody Movies newMovie){
		
		this.movieService.addMovie(newMovie);
		return responseEntity.ok(newMovie);		
		
	}
	
	@PatchMapping("/updateMovie/{movieId}")
	public ResponseEntity<?> updateMovieHandler(@PathVariable String movieId, @RequestBody Movies UpdatedMovie){
		 Movies  movie= this.movieService.updateMovie(movieId, UpdatedMovie);
		return responseEntity.ok(movie);
		
	}
	
	@DeleteMapping("/deleteMovie")
	public ResponseEntity<?> deleteMovie(@RequestParam String movieId){
		
		boolean flag=this.movieService.deleteMovie(movieId);
		if(flag==true) {
			return responseEntity.ok("deletion successfull...");
		}
		return new ResponseEntity("not found......",HttpStatus.NOT_FOUND);
		
	}
	@GetMapping("/getMovieById")
	public ResponseEntity<?> getMovieByIdHandler(@RequestParam String movieId){
		Movies movie= this.movieService.getMovieById(movieId);
		return responseEntity.ok(movie);
		
	}
	
	 @GetMapping("/genre/{genre}")
	    public Page<Movies> getMoviesByGenre(
	            @PathVariable String genre,
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "10") int size
	    ) {
			Page<Movies> moviePage=this.movieService.getMoviesByGenre(genre, page, size);
			
			Map<String, Object> response=new HashMap<String, Object>();
			response.put("data", moviePage	);
			response.put("page", page);
			response.put("size", size);
			response.put("totalElements", moviePage.getTotalElements());
			response.put("totalPages", moviePage.getTotalPages());
	        return movieService.getMoviesByGenre(genre, page, size);
	    }

}
	