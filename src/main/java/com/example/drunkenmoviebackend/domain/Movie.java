package com.example.drunkenmoviebackend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Movie")
@Getter
@Setter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer movieCd;

    private String title;

    private Long audience;

    private Integer rank;

    private Integer rankInten;

    private String rankOldAndNew;

    private String poster;

    @Lob
    private String plot;

    @Column(name = "openDt")
    private LocalDateTime openDt;

    private String genre;

    private String director;

    private String ratting;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 🔥 VOD 목록
    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    private List<MovieVod> movieVods = new ArrayList<>();

    // 🔥 평점 목록
    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    private List<MovieScore> movieScores = new ArrayList<>();
}
