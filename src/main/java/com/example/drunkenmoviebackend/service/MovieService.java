package com.example.drunkenmoviebackend.service;

import com.example.drunkenmoviebackend.domain.Movie;
import com.example.drunkenmoviebackend.domain.MovieScore;
import com.example.drunkenmoviebackend.dto.MovieDto;
import com.example.drunkenmoviebackend.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieDto> getMovieDatas() {
        // 날짜 조건 없이 랭크순 10개만 조회 (테스트/임시)
        List<Movie> movies = movieRepository.findTop10ByOrderByRankAsc();

        List<MovieDto> result = new java.util.ArrayList<>();
        for (Movie movie : movies) {
            int commentCount = 0;
            // TODO: commentRepository.countByMovieIdAndDeletedAtIsNull(movie.getMovieCd()) 구현 필요
            // int commentCount = commentRepository.countByMovieIdAndDeletedAtIsNull(movie.getMovieCd());

            int scoreCount = 0;
            float averageScore = 0;
            if (movie.getMovieScores() != null && !movie.getMovieScores().isEmpty()) {
                scoreCount = movie.getMovieScores().size();
                averageScore = (float) movie.getMovieScores().stream().filter(s -> s.getScore() != null).mapToDouble(MovieScore::getScore).average().orElse(0);
            }
            MovieDto dto = new MovieDto();
            dto.setId(movie.getId());
            dto.setAudience(movie.getAudience());
            dto.setMovieCd(movie.getMovieCd());
            dto.setTitle(movie.getTitle());
            dto.setRank(movie.getRank());
            dto.setCreatedAt(movie.getCreatedAt());
            dto.setUpdatedAt(movie.getUpdatedAt());
            dto.setPoster(movie.getPoster());
            dto.setPlot(movie.getPlot());
            dto.setRankInten(movie.getRankInten());
            dto.setRankOldAndNew(movie.getRankOldAndNew());
            dto.setOpenDt(movie.getOpenDt());
            dto.setGenre(movie.getGenre());
            dto.setDirector(movie.getDirector());
            dto.setRatting(movie.getRatting());
            // dto.setVods(movie.getMovieVod()); // MovieVod 매핑 필요시
            dto.setCommentCount(commentCount);
            dto.setScoreCount(scoreCount);
            dto.setAverageScore(averageScore);
            result.add(dto);
        }
        return result;
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
}
