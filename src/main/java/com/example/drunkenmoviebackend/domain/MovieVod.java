package com.example.drunkenmoviebackend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "MovieVod",
        indexes = {
                @Index(
                        name = "MovieVod_movieCd_fkey_idx",
                        columnList = "movieCd"
                )
        }
)
@Getter
@Setter
public class MovieVod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 1024)
    private String vodUrl;

    /**
     * FK 컬럼 (DB 기준)
     */
    @Column(nullable = false)
    private Integer movieCd;

    /**
     * 선택적 연관관계
     * - 조회용 join 편의
     * - insert/update 는 movieCd 기준
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "movieCd",
            referencedColumnName = "movieCd",
            insertable = false,
            updatable = false
    )
    private Movie movie;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    /* ---------- JPA Lifecycle ---------- */

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
