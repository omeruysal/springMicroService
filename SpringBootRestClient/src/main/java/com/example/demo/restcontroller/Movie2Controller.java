package com.example.demo.restcontroller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.example.demo.model.Movie;
import com.example.demo.service.MovieService;

@RestController
@RequestMapping("/api/1.0")
public class Movie2Controller {
	
	@Autowired
	MovieService movieService;
	
	@GetMapping("/movies")
	public ResponseEntity<?> getMovies() throws RestClientException, IOException {
		
		return ResponseEntity.ok(movieService.getMovies());
		
		
	}
	
	@GetMapping("/movies/{id}")
	public ResponseEntity<?> getMovie(@PathVariable("id") Integer id)  {
		
		return ResponseEntity.ok(movieService.getMovieById(id));
		
		
	}
	
	@PostMapping("/movies")
	public ResponseEntity<?> createMovie(@RequestBody Movie movie){
		
		try {
			movieService.createMovie(movie);

			return ResponseEntity.ok(movie);
		} catch (Exception e) {
			return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
		}
	}

}
