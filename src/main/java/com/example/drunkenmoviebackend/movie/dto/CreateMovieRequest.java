package com.example.drunkenmoviebackend.movie.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateMovieRequest(

        @NotBlank(message = "제목은 필수입니다")
        String title,

        @NotNull(message = "개봉 연도는 필수입니다")
        @Positive(message = "개봉 연도는 양수여야 합니다")
        Integer year
) {
}
