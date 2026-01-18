package com.example.drunkenmoviebackend.service;

import com.example.drunkenmoviebackend.domain.Movie;
import com.example.drunkenmoviebackend.domain.MovieScore;
import com.example.drunkenmoviebackend.dto.movie.MovieDetailResponse;
import com.example.drunkenmoviebackend.dto.movie.MovieDto;
import com.example.drunkenmoviebackend.dto.movie.MovieSummaryRow;
import com.example.drunkenmoviebackend.dto.movie.MovieVodDto;
import com.example.drunkenmoviebackend.repository.MovieQueryRepository;
import com.example.drunkenmoviebackend.repository.MovieRepository;
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
    private final MovieQueryRepository movieQueryRepository;
    private final ReplyRepository replyRepository;

    public List<MovieDto> getMovieDatas() {
        LocalDateTime now = LocalDateTime.now();

        LocalDate targetDate =
                (now.getHour() == 0 && now.getMinute() < 10)
                        ? now.toLocalDate().minusDays(2) // 00:00~00:10 → 이틀 전
                        : now.toLocalDate().minusDays(1); // 기본 → 하루 전

        List<MovieSummaryRow> rows =
                movieQueryRepository.findMovieSummaries(targetDate, 10);

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

        Movie movie = movieRepository.findDetailByMovieCd(movieCd)
                .orElseThrow(() ->
                        new IllegalArgumentException("Movie not found. movieCd=" + movieCd)
                );

        // 댓글 수 (soft delete 제외)
        long commentCount =
                replyRepository.countByMovieIdAndDeletedAtIsNull(movie.getMovieCd());

        // 평점 계산
        long scoreCount = movie.getMovieScores().size();

        double averageScore =
                scoreCount == 0
                        ? 0.0
                        : Math.round(
                        movie.getMovieScores()
                                .stream()
                                .mapToDouble(MovieScore::getScore)
                                .average()
                                .orElse(0.0) * 10
                ) / 10.0;

        return MovieDetailResponse.builder()
                .id(movie.getId())
                .movieCd(movie.getMovieCd())
                .title(movie.getTitle())
                .audience(movie.getAudience())
                .rank(movie.getRank())
                .rankInten(movie.getRankInten())
                .rankOldAndNew(movie.getRankOldAndNew())
                .poster(movie.getPoster())
                .plot(movie.getPlot())
                .openedAt(LocalDate.from(movie.getOpenDt()))
                .genre(movie.getGenre())
                .director(movie.getDirector())
                .ratting(movie.getRatting())
                .createdAt(movie.getCreatedAt())
                .updatedAt(movie.getUpdatedAt())
                .vods(
                        movie.getMovieVods().stream()
                                .map(MovieVodDto::from)
                                .toList()
                )
                .commentCount(commentCount)
                .scoreCount(scoreCount)
                .averageScore(averageScore)
                .build();
    }
}
