package com.example.drunkenmoviebackend.dto.member;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateMemberResponse {
    private Long id;
    private String email;
    private String nickname;
    private String image;
}
