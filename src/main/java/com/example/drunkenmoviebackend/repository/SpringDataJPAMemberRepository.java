package com.example.drunkenmoviebackend.repository;

import com.example.drunkenmoviebackend.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJPAMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
}
