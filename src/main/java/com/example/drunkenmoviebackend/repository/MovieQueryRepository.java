package com.example.drunkenmoviebackend.repository;

import com.example.drunkenmoviebackend.domain.QMovie;
import com.example.drunkenmoviebackend.domain.QMovieScore;
import com.example.drunkenmoviebackend.domain.QReply;
import com.example.drunkenmoviebackend.dto.movie.MovieSummaryRow;
import com.example.drunkenmoviebackend.dto.movie.QMovieSummaryRow;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MovieQueryRepository {

    private final JPAQueryFactory queryFactory;

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