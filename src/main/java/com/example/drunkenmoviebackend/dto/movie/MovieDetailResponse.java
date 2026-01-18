package com.example.drunkenmoviebackend.dto.movie;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDetailResponse {

    private Integer id;
    private Integer movieCd;
    private Long audience;

    private String title;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String poster;

    private Integer rank;
    private Integer rankInten;
    private String rankOldAndNew;

    private String plot;

    private LocalDate openedAt;

    private String genre;
    private String director;

    private String ratting;

    private List<MovieVodDto> vods;

    // optional
    private Long commentCount;
    private Long scoreCount;
    private Double averageScore;
}
