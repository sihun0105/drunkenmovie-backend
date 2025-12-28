package com.example.drunkenmoviebackend.repository;

import com.example.drunkenmoviebackend.domain.Member;

import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    Optional<Member> findByNickname(String name);

    Optional<Member> findByEmail(String email);

    Optional<Member> findByIdAndDeletedAtIsNull(Long id);

    Optional<Member> findByEmailAndDeletedAtIsNull(String email);

    Optional<Member> findById(Long id);

    boolean existsByNicknameAndIdNot(String nickname, Long id);

    boolean existsByNicknameAndDeletedAtIsNull(String email);
}
