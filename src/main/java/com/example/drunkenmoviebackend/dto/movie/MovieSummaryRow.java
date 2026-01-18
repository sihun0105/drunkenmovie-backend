package com.example.drunkenmoviebackend.dto.movie;

import com.querydsl.core.annotations.QueryProjection;

import java.time.LocalDateTime;

public record MovieSummaryRow(Integer id, Integer movieCd, String title, Long audience, Integer rank, Integer rankInten,
                              String rankOldAndNew, String poster, String plot, LocalDateTime openDt, String genre,
                              String director, String ratting, LocalDateTime createdAt, LocalDateTime updatedAt,
                              Long commentCount, Long scoreCount, Double averageScore) {

    @QueryProjection
    public MovieSummaryRow {
    }
}
