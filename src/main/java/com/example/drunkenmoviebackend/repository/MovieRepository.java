package com.example.drunkenmoviebackend.repository;

import com.example.drunkenmoviebackend.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Movie findByMovieCd(Integer movieCd);
    List<Movie> findTop10ByUpdatedAtAfterOrderByRankAsc(LocalDateTime updatedAt);

    List<Movie> findTop10ByOrderByRankAsc();
}
