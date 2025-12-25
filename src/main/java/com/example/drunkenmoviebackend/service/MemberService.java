package com.example.drunkenmoviebackend.service;

import com.example.drunkenmoviebackend.domain.Member;
import com.example.drunkenmoviebackend.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member) {
        return memberRepository.save(member).getId();
    }
}
