package com.example.drunkenmoviebackend.dto.reply;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class ReplyDto {
    private Long replyId;
    private Long userId;
    private String nickname;
    private String email;
    private String comment;
    private String avatar;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
