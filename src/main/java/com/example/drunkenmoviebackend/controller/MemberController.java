package com.example.drunkenmoviebackend.controller;

import com.example.drunkenmoviebackend.domain.Member;
import com.example.drunkenmoviebackend.dto.CreateUserDto;
import com.example.drunkenmoviebackend.dto.RemoveUserDto;
import com.example.drunkenmoviebackend.dto.UpdateUserDto;
import com.example.drunkenmoviebackend.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
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
}
