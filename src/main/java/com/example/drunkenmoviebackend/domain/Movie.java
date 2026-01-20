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

    @Column(nullable = false, unique = true)
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

    // 🔥 평점 통계 (JPQL update 대상)
    @Column(nullable = false)
    private Long scoreCount = 0L;

    @Column(nullable = false)
    private Double averageScore = 0.0;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 🔥 VOD 목록
    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    private List<MovieVod> movieVods = new ArrayList<>();

    // 🔥 평점 목록
    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    private List<MovieScore> movieScores = new ArrayList<>();

    // 🔥 생성/수정 시간 자동 관리 (선택이지만 강력 추천)
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
