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
    private Long id;

    @Column(nullable = false)
    private Long movieId;

    @Lob
    private String comment;

    /**
     * Comment.userno → User.id
     * User 엔티티는 Member로 매핑되어 있음
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userno", nullable = false)
    private Member user;

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
