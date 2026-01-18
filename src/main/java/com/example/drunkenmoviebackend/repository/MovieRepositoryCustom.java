package com.example.drunkenmoviebackend.repository;

import com.example.drunkenmoviebackend.dto.movie.MovieDetailRow;
import com.example.drunkenmoviebackend.dto.movie.MovieSummaryRow;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovieRepositoryCustom {
    Optional<MovieDetailRow> findMovieDetail(Integer movieCd);

    List<MovieSummaryRow> findMovieSummaries(LocalDate date, int limit);

}