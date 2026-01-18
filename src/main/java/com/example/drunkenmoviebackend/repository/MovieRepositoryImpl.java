package com.example.drunkenmoviebackend.repository;

import com.example.drunkenmoviebackend.domain.QMovie;
import com.example.drunkenmoviebackend.domain.QMovieScore;
import com.example.drunkenmoviebackend.domain.QReply;
import com.example.drunkenmoviebackend.dto.movie.MovieDetailRow;
import com.example.drunkenmoviebackend.dto.movie.MovieSummaryRow;
import com.example.drunkenmoviebackend.dto.movie.QMovieDetailRow;
import com.example.drunkenmoviebackend.dto.movie.QMovieSummaryRow;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class MovieRepositoryImpl implements MovieRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<MovieDetailRow> findMovieDetail(Integer movieCd) {

        QMovie movie = QMovie.movie;
        QReply reply = QReply.reply;
        QMovieScore score = QMovieScore.movieScore;

        return Optional.ofNullable(
                queryFactory
                        .select(new QMovieDetailRow(
                                movie.id,
                                movie.movieCd,
                                movie.title,
                                movie.audience,
                                movie.rank,
                                movie.rankInten,
                                movie.rankOldAndNew,
                                movie.poster,
                                movie.plot,
                                movie.openDt,
                                movie.genre,
                                movie.director,
                                movie.ratting,
                                movie.createdAt,
                                movie.updatedAt,

                                // 댓글 수
                                reply.id.countDistinct(),

                                // 평점 수
                                score.id.countDistinct(),

                                // 평균 평점 (소수 1자리)
                                score.score.avg()
                        ))
                        .from(movie)
                        .leftJoin(reply)
                        .on(reply.movieId.eq(movie.movieCd)
                                .and(reply.deletedAt.isNull()))
                        .leftJoin(score)
                        .on(score.movieCd.eq(movie.movieCd))
                        .where(movie.movieCd.eq(movieCd))
                        .groupBy(movie.id)
                        .fetchOne()
        );
    }

    public List<MovieSummaryRow> findMovieSummaries(LocalDate date, int limit) {

        QMovie movie = QMovie.movie;
        QReply reply = QReply.reply;
        QMovieScore score = QMovieScore.movieScore;

        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays(1).atStartOfDay();

        return queryFactory
                .select(
                        new QMovieSummaryRow(
                                movie.id,
                                movie.movieCd,
                                movie.title,
                                movie.audience,
                                movie.rank,
                                movie.rankInten,
                                movie.rankOldAndNew,
                                movie.poster,
                                movie.plot,
                                movie.openDt,
                                movie.genre,
                                movie.director,
                                movie.ratting,
                                movie.createdAt,
                                movie.updatedAt,
                                reply.id.countDistinct(),
                                score.id.countDistinct(),
                                score.score.avg()
                        )
                )
                .from(movie)
                .leftJoin(reply).on(
                        reply.movieId.eq(movie.movieCd)
                                .and(reply.deletedAt.isNull())
                )
                .leftJoin(score).on(score.movie.eq(movie))
                .where(
                        movie.updatedAt.goe(start)
                                .and(movie.updatedAt.lt(end))
                )
                .groupBy(movie.id)
                .orderBy(movie.rank.asc())
                .limit(limit)
                .fetch();
    }
}
