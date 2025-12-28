package com.example.drunkenmoviebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidationEmailResponse {
    private boolean isAvailable;
    private String message;

    public static ValidationEmailResponse available(String msg) {
        return new ValidationEmailResponse(true, msg);
    }

    public static ValidationEmailResponse unavailable(String msg) {
        return new ValidationEmailResponse(false, msg);
    }
}
