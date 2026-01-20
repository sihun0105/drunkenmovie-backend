package com.example.drunkenmoviebackend.dto.movie;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class UpdateMovieScoreResponse {
    Long id;
    Long movieCd;
    Double score;
    Double averageScore;
    Long userId;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

}
