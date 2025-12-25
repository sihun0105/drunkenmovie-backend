package com.example.drunkenmoviebackend.service;

import com.example.drunkenmoviebackend.domain.Gender;
import com.example.drunkenmoviebackend.domain.Member;
import com.example.drunkenmoviebackend.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
@ActiveProfiles("local")
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;

    @Test
    void join() {

        // given
        Member member = new Member();
        member.setEmail(Math.random() + "@test.com");
        member.setGender(Gender.male);
        member.setNickname("TestUser");

        // when
        Long result = memberService.join(member);

        // then
        Member foundMember = memberRepository.findById(result).get();
        assertThat(foundMember.getId()).isEqualTo(member.getId());
    }
}
