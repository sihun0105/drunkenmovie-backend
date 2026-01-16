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
    public CreateMemberResponse create(@RequestBody @Valid CreateMemberRequest dto) {
        Member member = memberService.join(dto);
        return CreateMemberResponse.builder()
                .email(member.getEmail())
                .nickname(member.getNickname())
                .marketingAgreed(member.getMarketingAgreed())
                .gender(member.getGender())
                .build();
    }

    @PatchMapping("/members/update")
    public UpdateMemberResponse update(@RequestBody @Valid UpdateMemberRequest dto) {
        Member member = memberService.updateUser(dto);
        return UpdateMemberResponse.builder()
                .email(member.getEmail())
                .nickname(member.getNickname())
                .id(member.getId())
                .image(member.getImage())
                .build();
    }

    @DeleteMapping("/members/delete")
    public DeleteMemberResponse delete(@RequestBody @Valid DeleteMemberResponse dto) {
        Member member = memberService.remove(dto.getId());
        return DeleteMemberResponse.builder()
                .id(member.getId())
                .build();
    }

    @PostMapping("/members/profile-image")
    public UpdateMemberResponse updateProfileImage(@RequestBody @Valid UpdateUserProfileImageRequest dto) {
        Member member = memberService.updateUserProfileImage(dto);
        return UpdateMemberResponse.builder()
                .id(member.getId())
                .image(member.getImage())
                .build();
    }

    @PostMapping("/members/login")
    public MemberLoginResponse login(@RequestBody @Valid LoginUserDto dto) {
        Member member = memberService.login(dto.getEmail(), dto.getPassword());
        return MemberLoginResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .createdAt(member.getCreatedAt())
                .updatedAt(member.getUpdatedAt())
                .deletedAt((member.getDeletedAt()))
                .provider(member.getProvider())
                .marketingAgreed(member.getMarketingAgreed())
                .gender(member.getGender())
                .image(member.getImage())
                .build();
    }

    @PostMapping("/members/oauth-login")
    public MemberLoginResponse oauthLogin(@RequestBody @Valid OauthLoginDto dto) {

        Member member = authService.oauthLogin(dto);
        return MemberLoginResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .createdAt(member.getCreatedAt())
                .updatedAt(member.getUpdatedAt())
                .deletedAt((member.getDeletedAt()))
                .provider(member.getProvider())
                .marketingAgreed(member.getMarketingAgreed())
                .gender(member.getGender())
                .image(member.getImage())
                .build();
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
