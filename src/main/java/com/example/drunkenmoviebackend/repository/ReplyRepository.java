package com.example.drunkenmoviebackend.repository;

import com.example.drunkenmoviebackend.domain.Reply;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Query("""
            select r
            from Reply r
            join fetch r.user
            where r.movieId = :movieId
              and r.deletedAt is null
            order by r.createdAt desc
            """)
    List<Reply> findReplies(
            @Param("movieId") Long movieId,
            Pageable pageable
    );

    long countByMovieIdAndDeletedAtIsNull(Integer movieId);
}

