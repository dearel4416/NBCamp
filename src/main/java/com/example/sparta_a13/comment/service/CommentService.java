package com.example.sparta_a13.comment.service;

import com.example.sparta_a13.comment.dto.CommentRequestDto;
import com.example.sparta_a13.comment.dto.CommentResponseDto;
import com.example.sparta_a13.comment.entity.Comment;
import com.example.sparta_a13.comment.repository.CommentRepository;
import com.example.sparta_a13.post.Post;
import com.example.sparta_a13.post.PostRepository;
import com.example.sparta_a13.user.User;
import com.example.sparta_a13.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public void createComment(Long postId, CommentRequestDto requestDto, UserDetailsImpl memberDetails) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        Comment comment = new Comment(requestDto, memberDetails.getUser(),post);
        commentRepository.save(comment);
    }

    public List<CommentResponseDto> getComments(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        return commentRepository.findAllByPostId(post.getId()).stream().map(CommentResponseDto::new).toList();
    }

    @Transactional
    public void updateComment(Long postId, Long commentId, CommentRequestDto requestDto, UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        Comment comment = validatePostCommentMember(postId,commentId,user);
        comment.update(requestDto);
    }

    public void deleteComment(Long postId, Long commentId, UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        Comment comment = validatePostCommentMember(postId,commentId,user);
        commentRepository.delete(comment);
    }


    private Comment validatePostCommentMember(Long postId, Long commentId, User user) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new IllegalArgumentException("게시글 내 해당 댓글이 존재하지 않습니다.");
        }
        if(!user.getId().equals(comment.getUser().getId())) {
            throw  new IllegalArgumentException("해당 개시글의 작성자가 아닙니다.");
        }

        return comment;
    }

}