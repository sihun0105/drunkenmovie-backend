package com.example.drunkenmoviebackend.service;

import com.example.drunkenmoviebackend.domain.Member;
import com.example.drunkenmoviebackend.dto.CreateMemberRequest;
import com.example.drunkenmoviebackend.dto.UpdateMemberRequest;
import com.example.drunkenmoviebackend.dto.UpdateUserProfileImageRequest;
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

    /* ================= 회원 로그인 ================= */
    public Member login(String email, String password) {

        Member member = memberRepository.findByEmailAndDeletedAtIsNull(email)
                .orElseThrow(() ->
                        new IllegalStateException("존재하지 않는 회원입니다. email=" + email)
                );

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }

        return member;
    }

    /* ================= 회원 가입 ================= */
    public Member join(CreateMemberRequest dto) {

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

    public Member updateUser(UpdateMemberRequest dto) {

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

    public Member updateUserProfileImage(UpdateUserProfileImageRequest dto) {

        Member member = memberRepository.findByIdAndDeletedAtIsNull(dto.getId())
                .orElseThrow(() ->
                        new IllegalStateException("User not found " + dto.getId())
                );

        member.setImage(dto.getImage());
        return member;
    }
}
