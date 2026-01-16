package com.example.drunkenmoviebackend.dto;

import com.example.drunkenmoviebackend.domain.Gender;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class MemberLoginResponse {
    private Long id;
    private String nickname;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private String provider;
    private String image;
    private Boolean marketingAgreed;
    private Gender gender;
}
