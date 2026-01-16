package com.example.drunkenmoviebackend.dto.member;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteMemberResponse {
    private Long id;
}
