package com.example.drunkenmoviebackend.service;

import com.example.drunkenmoviebackend.domain.Member;
import com.example.drunkenmoviebackend.dto.*;
import com.example.drunkenmoviebackend.global.provider.JwtProvider;
import com.example.drunkenmoviebackend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public LoginResponse validateUser(LoginUserDto dto) {
        if (dto.getEmail() == null || dto.getPassword() == null) {
            throw new IllegalArgumentException("아이디와 비밀번호를 확인해주세요.");
        }

        Member member = memberRepository.findByEmailAndDeletedAtIsNull(dto.getEmail())
                .orElse(null);

        if (member == null) {
            return null;
        }

        if (!passwordEncoder.matches(dto.getPassword(), member.getPassword())) {
            return null;
        }

        return LoginResponse.from(member);
    }

    public TokenResponse login(Member member) {
        String accessToken = jwtProvider.createAccessToken(member);
        String refreshToken = jwtProvider.createRefreshToken(accessToken);

        return new TokenResponse(accessToken, refreshToken);
    }

    @Transactional
    public Member oauthLogin(OauthLoginDto dto) {
        return memberRepository.findByEmailAndDeletedAtIsNull(dto.getProviderId())
                .orElseGet(() -> {
                    String randomNickname = UUID.randomUUID().toString().substring(0, 8);
                    String hashedPassword = passwordEncoder.encode("moview" + dto.getProviderId());
                    Member newMember = new Member();
                    newMember.setEmail(dto.getProviderId());
                    newMember.setPassword(hashedPassword);
                    newMember.setNickname("user_" + randomNickname);
                    return memberRepository.save(newMember);
                });
    }

    public ValidationEmailResponse validateEmail(String email) {
        boolean exists = memberRepository.findByEmailAndDeletedAtIsNull(email).isPresent();

        return exists
                ? ValidationEmailResponse.unavailable("이미 사용 중인 이메일입니다.")
                : ValidationEmailResponse.available("사용 가능한 이메일입니다.");
    }

    public ValidationNicknameResponse validateNickname(String nickname) {
        boolean exists = memberRepository.existsByNicknameAndDeletedAtIsNull(nickname);

        return exists
                ? ValidationNicknameResponse.unavailable("이미 사용 중인 닉네임입니다.")
                : ValidationNicknameResponse.available("사용 가능한 닉네임입니다.");
    }
}
