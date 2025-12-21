package com.example.drunkenmoviebackend.movie.repository;

import com.example.drunkenmoviebackend.movie.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
