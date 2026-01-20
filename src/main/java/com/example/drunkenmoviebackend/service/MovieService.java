package com.example.drunkenmoviebackend.service;

import com.example.drunkenmoviebackend.domain.Movie;
import com.example.drunkenmoviebackend.dto.movie.*;
import com.example.drunkenmoviebackend.repository.MovieRepository;
import com.example.drunkenmoviebackend.repository.MovieScoreRepository;
import com.example.drunkenmoviebackend.repository.MovieVodRepository;
import com.example.drunkenmoviebackend.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final ReplyRepository replyRepository;
    private final MovieVodRepository movieVodRepository;
    private final MovieScoreRepository movieScoreRepository;

    public List<MovieDto> getMovieDatas() {
        LocalDateTime now = LocalDateTime.now();

        LocalDate targetDate =
                (now.getHour() == 0 && now.getMinute() < 10)
                        ? now.toLocalDate().minusDays(2) // 00:00~00:10 → 이틀 전
                        : now.toLocalDate().minusDays(1); // 기본 → 하루 전

        List<MovieSummaryRow> rows =
                movieRepository.findMovieSummaries(targetDate, 10);

        return rows.stream()
                .map(this::toDto)
                .toList();
    }

    private MovieDto toDto(MovieSummaryRow row) {
        double avgScore =
                row.averageScore() == null
                        ? 0.0
                        : Math.round(row.averageScore() * 10) / 10.0;
        return MovieDto.builder()
                .id(row.id())
                .audience(row.audience())
                .title(row.title())
                .createdAt(row.createdAt())
                .updatedAt(row.updatedAt())
                .poster(row.poster())
                .rank(row.rank())
                .rankInten(row.rankInten())
                .rankOldAndNew(row.rankOldAndNew())
                .plot(row.plot())
                .openedAt(LocalDate.from(row.openDt()))
                .genre(row.genre())
                .director(row.director())
                .ratting(row.ratting())
                .commentCount(row.commentCount())
                .scoreCount(row.scoreCount())
                .averageScore(avgScore)
                .build();
    }


    public Movie save(Movie movie) {
        if (movie.getCreatedAt() == null) {
            movie.setCreatedAt(java.time.LocalDateTime.now());
        }
        movie.setUpdatedAt(java.time.LocalDateTime.now());
        return movieRepository.save(movie);
    }

    public void deleteById(Integer id) {
        movieRepository.deleteById(id);
    }

    public MovieDetailResponse getMovieDetail(Integer movieCd) {

        MovieDetailRow row = movieRepository.findMovieDetail(movieCd)
                .orElseThrow(() ->
                        new IllegalArgumentException("Movie not found. movieCd=" + movieCd)
                );

        // vod는 별도 조회
        List<MovieVodDto> vods =
                movieVodRepository.findByMovieCdAndDeletedAtIsNull(movieCd)
                        .stream()
                        .map(MovieVodDto::from)
                        .toList();

        return MovieDetailResponse.builder()
                .id(row.id())
                .movieCd(row.movieCd())
                .title(row.title())
                .audience(row.audience())
                .rank(row.rank())
                .rankInten(row.rankInten())
                .rankOldAndNew(row.rankOldAndNew())
                .poster(row.poster())
                .plot(row.plot())
                .openedAt(row.openDt().toLocalDate())
                .genre(row.genre())
                .director(row.director())
                .ratting(row.ratting())
                .createdAt(row.createdAt())
                .updatedAt(row.updatedAt())
                .commentCount(row.commentCount())
                .scoreCount(row.scoreCount())
                .averageScore(
                        row.averageScore() == null
                                ? 0.0
                                : Math.round(row.averageScore() * 10) / 10.0
                )
                .vods(vods)
                .build();
    }

    public UpdateMovieScoreResponse updateMovieScore(Integer movieCd) {

        long scoreCount = movieScoreRepository.countByMovieCd(movieCd);
        Double averageScore = movieScoreRepository.calculateAverageScoreByMovieCd(movieCd);

        movieScoreRepository.updateMovieScoreStatistics(
                movieCd,
                scoreCount,
                averageScore == null ? 0.0 : Math.round(averageScore * 10) / 10.0
        );

        return UpdateMovieScoreResponse.builder()
                .movieCd(Long.valueOf(movieCd))
                .score((double) scoreCount)
                .averageScore(
                        averageScore == null ? 0.0 : Math.round(averageScore * 10) / 10.0
                )
                .build();
    }

}
