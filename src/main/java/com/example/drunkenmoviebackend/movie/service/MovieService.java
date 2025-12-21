package com.example.drunkenmoviebackend.movie.service;

import com.example.drunkenmoviebackend.movie.domain.Movie;
import com.example.drunkenmoviebackend.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
}

