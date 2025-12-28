package com.example.drunkenmoviebackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserDto {
    private Long id;
    private String email;
    private String nickname;
    private String image;
}
