package com.example.drunkenmoviebackend.controller;

import com.example.drunkenmoviebackend.dto.MovieDto;
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

    @GetMapping()
    public List<MovieDto> getMovieDatas() {


        var movieDatas = movieService.getMovieDatas();
        System.out.println(movieDatas.toArray().length);
        return movieDatas;
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Integer id) {
        movieService.deleteById(id);
    }

    @GetMapping("/detail/{movieCd}")
    public MovieDto getMovieDetail(@PathVariable Integer movieCd) {
        // TODO: 서비스에서 상세조회 로직 구현
        return null;
    }
}
