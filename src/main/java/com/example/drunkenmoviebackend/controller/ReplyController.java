package com.example.drunkenmoviebackend.controller;

import com.example.drunkenmoviebackend.dto.reply.MovieRepliesResponse;
import com.example.drunkenmoviebackend.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@RestController
@RequestMapping("reply")
public class ReplyController {

    private final ReplyService replyService;

    @GetMapping("/")
    public MovieRepliesResponse getMovieReplies(
            @RequestParam("movieId") Long movieId,
            @RequestParam("page") int page
    ) {
        return replyService.getReplies(movieId, page);
    }
}
