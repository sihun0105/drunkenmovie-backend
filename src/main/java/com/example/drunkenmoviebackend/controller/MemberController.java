package com.example.drunkenmoviebackend.controller;

import com.example.drunkenmoviebackend.domain.Member;
import com.example.drunkenmoviebackend.dto.*;
import com.example.drunkenmoviebackend.service.AuthService;
import com.example.drunkenmoviebackend.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {

    private final MemberService memberService;
    private final AuthService authService;

    public MemberController(MemberService memberService, AuthService authService) {
        this.authService = authService;
        this.memberService = memberService;
    }

    @PostMapping("/members/new")
    public Member create(@RequestBody @Valid CreateUserDto dto) {
        return memberService.join(dto);
    }

    @PatchMapping("/members/update")
    public Member update(@RequestBody @Valid UpdateUserDto dto) {
        return memberService.updateUser(dto);
    }

    @DeleteMapping("/members/delete")
    public Member delete(@RequestBody @Valid RemoveUserDto dto) {
        return memberService.remove(dto.getId());
    }

    @PostMapping("/members/profile-image")
    public Member updateProfileImage(@RequestBody @Valid UpdateUserProfileImageDto dto) {
        return memberService.updateUserProfileImage(dto);
    }

    //TODO: 로그인 리스폰스는 Omit<User, 'password'>
    @PostMapping("/members/login")
    public TokenResponse login(@RequestBody @Valid LoginUserDto dto) {
        System.out.println(dto);
        Member member = new Member();
        member.setEmail(dto.getEmail());
        member.setPassword(dto.getPassword());
        return authService.login(member);
    }

    @PostMapping("/members/oauth-login")
    public LoginResponse oauthLogin(@RequestBody @Valid OauthLoginDto dto) {
        return authService.oauthLogin(dto);
    }

    @PostMapping("/members/validate-nickname")
    public ValidationNicknameResponse validateNickname(@RequestBody @Valid ValidateNicknameDto dto) {
        return authService.validateNickname(dto.getNickname());
    }

    @PostMapping("/members/validate-email")
    public ValidationEmailResponse validateNickname(@RequestBody @Valid ValidateEmailDto dto) {
        return authService.validateEmail(dto.getEmail());
    }
}
