package com.example.drunkenmoviebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidationNicknameResponse {
    private boolean isAvailable;
    private String message;

    public static ValidationNicknameResponse available(String msg) {
        return new ValidationNicknameResponse(true, msg);
    }

    public static ValidationNicknameResponse unavailable(String msg) {
        return new ValidationNicknameResponse(false, msg);
    }
}
