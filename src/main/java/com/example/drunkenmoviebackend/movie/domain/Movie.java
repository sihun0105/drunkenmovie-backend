package com.example.drunkenmoviebackend.movie.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "Movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Long audience;

    @Column(unique = true, nullable = false)
    private Integer movieCd;

    @Lob
    private String title;

    private Long rank;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(length = 1024)
    private String poster;

    @Column(columnDefinition = "json")
    private String vector;

    @Column(length = 10)
    private String rankInten;

    @Lob
    private String plot;

    @Column(length = 10)
    private String rankOldAndNew;

    private LocalDateTime openDt;

    @Column(length = 100)
    private String genre;

    @Column(length = 100)
    private String director;

    @Column(length = 100)
    private String ratting;
}
