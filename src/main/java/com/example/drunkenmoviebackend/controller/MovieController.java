package com.example.drunkenmoviebackend.controller;

import com.example.drunkenmoviebackend.dto.movie.MovieDetailResponse;
import com.example.drunkenmoviebackend.dto.movie.MovieDto;
import com.example.drunkenmoviebackend.dto.movie.MovieListResponse;
import com.example.drunkenmoviebackend.dto.movie.UpdateMovieScoreResponse;
import com.example.drunkenmoviebackend.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movie")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("")
    public MovieListResponse getMovieDatas() {
        List<MovieDto> movies = movieService.getMovieDatas();
        return MovieListResponse.builder()
                .movies(movies)
                .size(movies.size())
                .hasNext(false)
                .build();
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Integer id) {
        movieService.deleteById(id);
    }

    @GetMapping("/detail/{movieCd}")
    public MovieDetailResponse getMovieDetail(@PathVariable Integer movieCd) {
        return movieService.getMovieDetail(movieCd);
    }

    @PostMapping("/score/{movieCd}")
    public UpdateMovieScoreResponse updateMovieScore(@PathVariable Integer movieCd) {
        return movieService.updateMovieScore(movieCd);
    }
}
