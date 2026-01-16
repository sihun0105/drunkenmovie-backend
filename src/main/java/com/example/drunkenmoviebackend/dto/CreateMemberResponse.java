package com.example.drunkenmoviebackend.dto;

import com.example.drunkenmoviebackend.domain.Gender;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateMemberResponse {
    private String email;
    private String nickname;
    private boolean marketingAgreed;
    private Gender gender;

    public CreateMemberResponse(String email, String nickname, boolean marketingAgreed, Gender gender) {
        this.email = email;
        this.nickname = nickname;
        this.marketingAgreed = marketingAgreed;
        this.gender = gender;
    }
}
