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
	private final String basedUrl = "http://localhost:8080/movies";
	private final String username = "username";
	private final String password = "123";
	
	MovieService (RestTemplateBuilder restTemplateBuilder){
		  restTemplate = restTemplateBuilder
                  .basicAuthentication(username, password)
                  .build();
	}

	
	
	public List<Movie> getMovies() throws RestClientException, IOException {
		ResponseEntity<List> response = restTemplate.getForEntity(baseUrl, List.class);
		List<Movie> body = response.getBody();
		return body;
	}

	public Movie getMovieById(Integer id) {
		ResponseEntity<Movie> m = restTemplate.getForEntity(baseUrl+id, Movie.class);
		Movie movie = m.getBody();
		return movie;

	}
	
	public Movie createMovie(Movie m) {
		ResponseEntity<Movie> inDB = restTemplate.postForEntity(basedUrl, m,Movie.class);
		System.out.println(inDB.getBody());
		return m;
	}

}
