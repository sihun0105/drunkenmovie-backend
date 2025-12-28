package com.example.drunkenmoviebackend.service;

import com.example.drunkenmoviebackend.domain.Movie;
import com.example.drunkenmoviebackend.repository.MovieRepository;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles("local")
@Transactional
public class MovieServiceTest {

    @Autowired
    MovieService movieService;

    @Autowired
    MovieRepository movieRepository;

    @BeforeAll
    static void loadEnv() {
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();

        dotenv.entries().forEach(e ->
                System.setProperty(e.getKey(), e.getValue())
        );
    }

    @Test
    void getMovieDatas() {
        // given
        List<Movie> top10ByOrderByRankAsc = movieRepository.findTop10ByOrderByRankAsc();

        // when
        // then
    }
}
