package com.example.drunkenmoviebackend.dto.movie;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetGaveMovieScoreResponse {
    Long id;
    Long movieCd;
    Double score;
    Double averageScore;
    Long userId;
}
