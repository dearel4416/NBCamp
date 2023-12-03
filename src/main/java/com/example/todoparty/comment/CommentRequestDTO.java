package com.example.todoparty.comment;

import lombok.Getter;
import org.springframework.stereotype.Service;

@Getter
@Service
public class CommentRequestDTO {
    private Long todoId;
    private String text;
}
