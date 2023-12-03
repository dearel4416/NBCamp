package com.example.todoparty.comment;

import com.example.todoparty.user.UserDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommentListResponseDTO {
    private UserDTO user;
    private List<CommentResponseDTO> commentList;

    public CommentListResponseDTO(UserDTO user, List<CommentResponseDTO> commentList) {
        this.user = user;
        this.commentList = commentList;
    }
}
