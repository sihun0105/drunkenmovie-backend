package com.example.drunkenmoviebackend.dto.movie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class MovieListResponse {

    private List<MovieDto> movies;
    private int size;        // 응답 개수
    private boolean hasNext; // 추후 페이징 대비 (지금은 false 고정 가능)
}