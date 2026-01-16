package com.example.drunkenmoviebackend.service;

import com.example.drunkenmoviebackend.domain.Gender;
import com.example.drunkenmoviebackend.domain.Member;
import com.example.drunkenmoviebackend.dto.member.CreateMemberRequest;
import com.example.drunkenmoviebackend.dto.member.UpdateMemberRequest;
import com.example.drunkenmoviebackend.dto.member.UpdateUserProfileImageRequest;
import com.example.drunkenmoviebackend.repository.MemberRepository;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("local")
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;


    @BeforeAll
    static void loadEnv() {
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();

        dotenv.entries().forEach(e ->
                System.setProperty(e.getKey(), e.getValue())
        );
    }

    /**
     * 회원 생성 헬퍼
     */
    private Member createMember(String email, String nickname) {
        CreateMemberRequest dto = new CreateMemberRequest();
        dto.setEmail(email);
        dto.setPassword("password123");
        dto.setNickname(nickname);
        dto.setMarketingAgreed(false);
        dto.setGender(Gender.male);

        return memberService.join(dto);
    }

    @Test
    void 회원가입_성공() {
        // given
        CreateMemberRequest dto = new CreateMemberRequest();
        dto.setEmail("test1@test.com");
        dto.setPassword("password123");
        dto.setNickname("testUser1");
        dto.setMarketingAgreed(true);
        dto.setGender(Gender.male);

        // when
        Member saved = memberService.join(dto);

        // then
        Member found = memberRepository.findByIdAndDeletedAtIsNull(saved.getId()).orElseThrow();
        assertThat(found.getEmail()).isEqualTo(dto.getEmail());
        assertThat(found.getNickname()).isEqualTo(dto.getNickname());
        assertThat(found.getGender()).isEqualTo(dto.getGender());
        assertThat(found.getPassword()).isNotEqualTo(dto.getPassword()); // 암호화 검증
    }

    @Test
    void 닉네임_수정_성공() {
        // given
        Member member = createMember("test2@test.com", "oldNick");

        UpdateMemberRequest dto = new UpdateMemberRequest();
        dto.setId(member.getId());
        dto.setNickname("newNick");

        // when
        Member updated = memberService.updateUser(dto);

        // then
        assertThat(updated.getNickname()).isEqualTo("newNick");
    }

    @Test
    void 프로필_이미지_수정_성공() {
        // given
        Member member = createMember("test3@test.com", "profileUser");

        UpdateUserProfileImageRequest dto = new UpdateUserProfileImageRequest();
        dto.setId(member.getId());
        dto.setImage("https://image.test/profile.png");

        // when
        Member updated = memberService.updateUserProfileImage(dto);

        // then
        assertThat(updated.getImage()).isEqualTo(dto.getImage());
    }

    @Test
    void 회원_soft_delete() {

        // given
        Member member = createMember("test3@test.com", "profileUser");

        Member saved = memberRepository.save(member);

        // when
        Member deletedMember = memberService.remove(saved.getId());

        // then
        Member deleted = memberRepository.findById(member.getId()).orElseThrow();
        assertThat(deleted.getDeletedAt()).isNotNull();
    }
}
