package com.example.drunkenmoviebackend.service;

import com.example.drunkenmoviebackend.domain.Reply;
import com.example.drunkenmoviebackend.dto.reply.MovieRepliesResponse;
import com.example.drunkenmoviebackend.dto.reply.ReplyDto;
import com.example.drunkenmoviebackend.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private ReplyRepository replyRepository;

    public MovieRepliesResponse getReplies(Long movieId, int page) {

        int pageSize = 10;
        PageRequest pageable = PageRequest.of(page - 1, pageSize + 1);

        List<Reply> replies = replyRepository.findReplies(movieId, pageable);

        boolean hasNext = replies.size() > pageSize;

        if (hasNext) {
            replies = replies.subList(0, pageSize);
        }

        List<ReplyDto> replyDtos = replies.stream()
                .map(reply -> ReplyDto.builder()
                        .replyId(reply.getId())
                        .comment(reply.getComment())
                        .email(reply.getUser().getEmail())
                        .nickname(reply.getUser().getNickname())
                        .avatar(
                                reply.getUser().getImage() != null
                                        ? reply.getUser().getImage()
                                        : ""
                        )
                        .userId(reply.getUser().getId())
                        .createdAt(reply.getCreatedAt())
                        .updatedAt(reply.getUpdatedAt())
                        .build()
                )
                .toList();

        return MovieRepliesResponse.builder()
                .replies(replyDtos)
                .hasNext(hasNext)
                .build();
    }
}
