package com.example.drunkenmoviebackend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Comment")
@Getter
@Setter
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer movieId; // ✔ Movie.movieCd 와 동일

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userno")
    private Member user;

    @Lob
    private String comment;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
