package com.example.sparta_a13.comment.service;

import com.example.sparta_a13.comment.dto.CommentRequestDto;
import com.example.sparta_a13.comment.dto.CommentResponseDto;
import com.example.sparta_a13.comment.entity.Comment;
import com.example.sparta_a13.comment.repository.CommentRepository;
import com.example.sparta_a13.global.comment.CommentNotBelongingToPostException;
import com.example.sparta_a13.global.comment.CommentNotFoundException;
import com.example.sparta_a13.global.post.PostNotFoundException;
import com.example.sparta_a13.global.user.UnauthorizedModifyException;
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
        Post post = getPost(postId);
        Comment comment = new Comment(requestDto, memberDetails.getUser(),post);
        commentRepository.save(comment);
    }

    public List<CommentResponseDto> getComments(Long postId) {
        Post post = getPost(postId);
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
        Post post = getPost(postId);
        Comment comment = commentRepository.findById(commentId)
            .orElseThrow(CommentNotFoundException::new);
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new CommentNotBelongingToPostException();
        }
        if(!user.getId().equals(comment.getUser().getId())) {
            throw  new UnauthorizedModifyException();
        }

        return comment;
    }

    // postId에 해당하는 게시글 가져오는 메소드
    private Post getPost(Long postId) {
      return postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
    }

}