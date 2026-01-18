package com.example.drunkenmoviebackend.repository;

import com.example.drunkenmoviebackend.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Movie findByMovieCd(Integer movieCd);

    List<Movie> findTop10ByUpdatedAtAfterOrderByRankAsc(LocalDateTime updatedAt);

    List<Movie> findTop10ByOrderByRankAsc();

    @Query("""
                select distinct m
                from Movie m
                left join fetch m.movieVods
                left join fetch m.movieScores
                where m.movieCd = :movieCd
            """)
    Optional<Movie> findDetailByMovieCd(@Param("movieCd") Integer movieCd);
}
