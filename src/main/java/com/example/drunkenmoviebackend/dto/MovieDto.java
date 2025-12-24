package com.example.drunkenmoviebackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MovieDto {
    private Integer id;
    private Long audience;
    private Integer movieCd;
    private String title;
    private Long rank;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String poster;
    private String plot;
    private String rankInten;
    private String rankOldAndNew;
    private LocalDateTime openDt;
    private String genre;
    private String director;
    private String ratting;

    // MovieService에서 사용하는 추가 필드
    private Integer commentCount;
    private Integer scoreCount;
    private Float averageScore;
}
