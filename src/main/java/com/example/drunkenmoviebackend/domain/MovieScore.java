package com.example.drunkenmoviebackend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "movieScore")
@Getter
@Setter
@NoArgsConstructor
public class MovieScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "movieCd", nullable = false)
    private Integer movieCd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movieCd", insertable = false, updatable = false)
    private Movie movie;

    @Column
    private Float score;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    @Column(nullable = false)
    private Integer userno;
}
