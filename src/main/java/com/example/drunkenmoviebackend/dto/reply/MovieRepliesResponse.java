package com.example.drunkenmoviebackend.dto.reply;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieRepliesResponse {
    private List<ReplyDto> replies;
    private boolean hasNext;
}
