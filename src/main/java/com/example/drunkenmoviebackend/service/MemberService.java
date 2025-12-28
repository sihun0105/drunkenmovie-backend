package com.example.drunkenmoviebackend.service;

import com.example.drunkenmoviebackend.domain.Member;
import com.example.drunkenmoviebackend.dto.CreateUserDto;
import com.example.drunkenmoviebackend.dto.UpdateUserDto;
import com.example.drunkenmoviebackend.dto.UpdateUserProfileImageDto;
import com.example.drunkenmoviebackend.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {

        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;

    }

    /* ================= 회원 가입 ================= */

    public Member join(CreateUserDto dto) {

        if (memberRepository.findByNickname(dto.getNickname()).isPresent()) {
            throw new IllegalStateException("이미 존재하는 닉네임입니다.");
        }

        if (memberRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }

        Member member = new Member();
        member.setEmail(dto.getEmail());
        member.setPassword(passwordEncoder.encode(dto.getPassword()));
        member.setNickname(dto.getNickname());
        member.setMarketingAgreed(
                dto.getMarketingAgreed() != null && dto.getMarketingAgreed()
        );
        member.setGender(dto.getGender());

        return memberRepository.save(member);
    }

    /* ================= 회원 삭제 ================= */

    public Member remove(Long memberId) {

        Member member = memberRepository.findByIdAndDeletedAtIsNull(memberId)
                .orElseThrow(() ->
                        new IllegalStateException("존재하지 않는 회원입니다. id=" + memberId)
                );

        // 🔥 soft delete
        member.setDeletedAt(LocalDateTime.now());

        return memberRepository.save(member);
    }

    /* ================= 회원 정보 수정 ================= */

    public Member updateUser(UpdateUserDto dto) {

        Member member = memberRepository.findByIdAndDeletedAtIsNull(dto.getId())
                .orElseThrow(() ->
                        new IllegalStateException("User not found " + dto.getId())
                );

        if (dto.getNickname() != null) {
            boolean exists = memberRepository
                    .existsByNicknameAndIdNot(dto.getNickname(), dto.getId());

            if (exists) {
                throw new IllegalStateException("이미 존재하는 닉네임입니다.");
            }

            member.setNickname(dto.getNickname());
        }

        if (dto.getEmail() != null) {
            member.setEmail(dto.getEmail());
        }

        if (dto.getImage() != null) {
            member.setImage(dto.getImage());
        }

        return member;
    }

    /* ================= 프로필 이미지 수정 ================= */

    public Member updateUserProfileImage(UpdateUserProfileImageDto dto) {

        Member member = memberRepository.findByIdAndDeletedAtIsNull(dto.getId())
                .orElseThrow(() ->
                        new IllegalStateException("User not found " + dto.getId())
                );

        member.setImage(dto.getImage());
        return member;
    }
}
