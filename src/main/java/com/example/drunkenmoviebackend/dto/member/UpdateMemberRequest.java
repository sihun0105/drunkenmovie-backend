package com.example.drunkenmoviebackend.dto.member;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UpdateMemberRequest {
    private Long id;
    private String email;
    private String nickname;
    private String image;
}
