package com.example.drunkenmoviebackend.dto.movie;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetMovieAverageScoreResponse {
    Long movieCd;
    Double averageScore;
    Long scoreCount;
    Double myScore;
}
