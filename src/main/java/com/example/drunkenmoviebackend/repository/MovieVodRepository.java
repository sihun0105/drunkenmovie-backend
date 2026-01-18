package com.example.drunkenmoviebackend.repository;

import com.example.drunkenmoviebackend.domain.MovieVod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieVodRepository extends JpaRepository<MovieVod, Integer> {

    List<MovieVod> findByMovieCdAndDeletedAtIsNull(Integer movieCd);
}
