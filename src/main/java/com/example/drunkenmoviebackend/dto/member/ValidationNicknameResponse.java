package com.example.drunkenmoviebackend.dto.member;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ValidationNicknameResponse {
    private boolean isAvailable;
    private String message;

    public ValidationNicknameResponse(boolean isAvailable, String message) {
        this.isAvailable = isAvailable;
        this.message = message;
    }

    public static ValidationNicknameResponse available(String msg) {
        return new ValidationNicknameResponse(true, msg);
    }

    public static ValidationNicknameResponse unavailable(String msg) {
        return new ValidationNicknameResponse(false, msg);
    }
}
