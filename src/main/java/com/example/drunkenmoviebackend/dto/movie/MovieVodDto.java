package com.example.drunkenmoviebackend.dto.movie;

import com.example.drunkenmoviebackend.domain.MovieVod;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieVodDto {

    private Integer id;
    private String provider;
    private String url;

    public static MovieVodDto from(MovieVod vod) {
        return MovieVodDto.builder()
                .id(vod.getId())
                .url(vod.getVodUrl())
                .build();
    }
}


