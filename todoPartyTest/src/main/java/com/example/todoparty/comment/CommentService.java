package com.example.todoparty.comment;

import com.example.todoparty.todo.Todo;
import com.example.todoparty.todo.TodoService;
import com.example.todoparty.user.User;
import com.example.todoparty.user.UserDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RejectedExecutionException;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final TodoService todoService;
    public CommentResponseDTO createPost(CommentRequestDTO commentRequestDTO, User user){
        Todo todo = todoService.getTodo(commentRequestDTO.getTodoId());

        Comment comment = new Comment(commentRequestDTO);
        comment.setUser(user);
        comment.setTodo(todo);

        commentRepository.save(comment);

        return new CommentResponseDTO(comment);
    }

    public Map<UserDTO, List<CommentResponseDTO>> getUserCommentMap() {
        Map<UserDTO, List<CommentResponseDTO>> userCommentMap = new HashMap<>();

        List<Comment> commentList = commentRepository.findAll(Sort.by(Sort.Direction.DESC, "createDate"));

        commentList.forEach(comment -> {
            var userDto = new UserDTO(comment.getUser());
            var commentDto = new CommentResponseDTO(comment);
            if(userCommentMap.containsKey(userDto)){
                //댓글 목록 항목 추가
                userCommentMap.get(userDto).add(commentDto);
            }else {
                //댓글 목록 새로 추가
                userCommentMap.put(userDto, new ArrayList<>(List.of(commentDto)));
            }
        });

        return userCommentMap;
    }

    @Transactional
    public CommentResponseDTO updateComment(Long commentId, CommentRequestDTO commentRequestDTO, User user) {
        Comment comment = getUserComment(commentId, user);

        comment.setText(commentRequestDTO.getText());

        return new CommentResponseDTO(comment);
    }

    public void deleteComment(Long commentId, User user){
        Comment comment = getUserComment(commentId, user);

        commentRepository.delete(comment);
    }

    private Comment getUserComment(Long commentId, User user) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글 ID입니다."));

        if(!user.getId().equals(comment.getUser().getId())){
            throw new RejectedExecutionException("작성자만 수정할 수 있습니다.");
        }
        return comment;
    }
}
