package com.example.drunkenmoviebackend.dto;

import com.example.drunkenmoviebackend.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {

    private Long id;
    private String email;
    private String nickname;
    private String image;
    private String gender;
    private String createdAt;
    private String updatedAt;
    private String deletedAt;

    public static LoginResponse from(Member member) {
        return new LoginResponse(
                member.getId(),
                member.getEmail(),
                member.getNickname(),
                member.getImage(),
                member.getGender().name(),
                member.getCreatedAt().toString(),
                member.getUpdatedAt().toString(),
                member.getDeletedAt() != null ? member.getDeletedAt().toString() : null
        );
    }
}
