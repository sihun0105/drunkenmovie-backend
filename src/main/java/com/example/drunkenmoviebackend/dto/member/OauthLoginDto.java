package com.example.drunkenmoviebackend.dto.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OauthLoginDto {
    private String provider;
    private String providerId;
}
