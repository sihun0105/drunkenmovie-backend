package com.example.drunkenmoviebackend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "`User`",
        uniqueConstraints = {
                @UniqueConstraint(name = "uni_User_email", columnNames = "email")
        }
)
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 191)
    private String email;

    @Lob
    private String nickname;

    @Lob
    private String password;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    @Column(nullable = false, length = 45)
    private String provider;

    @Lob
    private String image;

    @Column(name = "marketing_agreed", nullable = false)
    private Boolean marketingAgreed;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    /* ---------- JPA Lifecycle ---------- */

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
        if (this.provider == null) {
            this.provider = "credentials";
        }
        if (this.marketingAgreed == null) {
            this.marketingAgreed = false;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
