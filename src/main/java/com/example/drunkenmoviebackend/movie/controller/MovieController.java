package com.example.drunkenmoviebackend.movie.controller;

import com.example.drunkenmoviebackend.movie.domain.Movie;
import com.example.drunkenmoviebackend.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }
}
