package com.example.demo.service;

import java.io.IOException;
import java.util.List;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Movie;

@Service
public class MovieService {

	RestTemplate restTemplate;
	
	MovieService (RestTemplateBuilder restTemplateBuilder){
		  restTemplate = restTemplateBuilder
                  .basicAuthentication("username", "123")
                  .build();
	}

	
	
	public List<Movie> getMovies() throws RestClientException, IOException {

		String baseUrl = "http://localhost:8080/movies";
		
		

		ResponseEntity<List> response = restTemplate.getForEntity(baseUrl, List.class);

		List<Movie> body = response.getBody();

		return body;
	}

	public Movie getMovieById(Integer id) {
		String baseUrl = "http://localhost:8080/movies/"+id;

		ResponseEntity<Movie> m = restTemplate.getForEntity(baseUrl, Movie.class);
		Movie movie = m.getBody();
		return movie;

	}
	
	public Movie createMovie(Movie m) {
		
		
		
		String basedUrl = "http://localhost:8080/movies";
			ResponseEntity<Movie> inDB =	restTemplate.postForEntity(basedUrl, m,Movie.class);
						
		System.out.println(inDB.getBody());

		
		return m;
	}

}
