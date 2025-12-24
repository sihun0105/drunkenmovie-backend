package com.example.drunkenmoviebackend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Long audience;

    @Column(unique = true, nullable = false)
    private Integer movieCd;

    @Column(columnDefinition = "TEXT")
    private String title;

    private Long rank;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Column(length = 1024)
    private String poster;

    @Column(columnDefinition = "TEXT")
    private String plot;

    @Column(length = 10)
    private String rankInten;

    @Column(length = 10)
    private String rankOldAndNew;

    private LocalDateTime openDt;

    @Column(length = 100)
    private String genre;

    @Column(length = 100)
    private String director;

    @Column(length = 100)
    private String ratting;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovieScore> movieScores = new ArrayList<>();
}
