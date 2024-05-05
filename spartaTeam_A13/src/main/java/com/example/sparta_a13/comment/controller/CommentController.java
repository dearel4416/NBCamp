package com.example.sparta_a13.comment.controller;

import com.example.sparta_a13.comment.dto.CommentRequestDto;
import com.example.sparta_a13.comment.dto.CommentResponseDto;
import com.example.sparta_a13.comment.service.CommentService;
import com.example.sparta_a13.user.UserDetailsImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/posts/{postId}")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comments")
    private String createComment(@PathVariable Long postId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletResponse response) {
        if (userDetails == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return "error";
        } else {
            commentService.createComment(postId, requestDto, userDetails);
            return "redirect:/api/posts/" + postId + "/comments";
        }
    }

    @ResponseBody
    @GetMapping("/comments")
    private List<CommentResponseDto> getComments(@PathVariable Long postId) {
        return commentService.getComments(postId);
    }

    @PostMapping("/comments/{commentId}")
    private String updateComment(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletResponse response) {
        if (userDetails == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return "error";
        } else {
            commentService.updateComment(postId, commentId, requestDto, userDetails);
            return "redirect:/api/posts/" + postId + "/comments";
        }
    }

    @DeleteMapping("/comments/{commentId}")
    private String deleteComment(@PathVariable Long postId, @PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl memberDetails, HttpServletResponse response) {
        if (memberDetails == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return "error";
        } else {
            commentService.deleteComment(postId, commentId, memberDetails);
            return "redirect:/api/posts/" + postId + "/comments";
        }
    }
}
