package com.example.drunkenmoviebackend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateUserProfileImageResponse {
    private Long id;
    private String iamge;
}
