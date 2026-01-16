package com.example.drunkenmoviebackend.dto.member;

import com.example.drunkenmoviebackend.domain.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CreateMemberRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String nickname;

    @NotNull
    private Boolean marketingAgreed;

    @NotNull
    private Gender gender;
}
