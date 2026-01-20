package com.example.drunkenmoviebackend.repository;

import com.example.drunkenmoviebackend.domain.MovieScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface MovieScoreRepository extends JpaRepository<MovieScore, Long> {

    long countByMovieCd(Integer movieCd);

    @Query("""
                select avg(ms.score)
                from MovieScore ms
                where ms.movie.movieCd = :movieCd
            """)
    Double calculateAverageScoreByMovieCd(Integer movieCd);

    // 🔥 핵심
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("""
                update Movie m
                set m.scoreCount = :scoreCount,
                    m.averageScore = :averageScore,
                    m.updatedAt = current_timestamp
                where m.movieCd = :movieCd
            """)
    void updateMovieScoreStatistics(
            Integer movieCd,
            Long scoreCount,
            Double averageScore
    );

}

