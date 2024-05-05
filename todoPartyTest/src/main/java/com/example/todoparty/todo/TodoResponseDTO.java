package com.example.todoparty.todo;

import com.example.todoparty.CommonResponseDTO;
import com.example.todoparty.user.UserDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TodoResponseDTO extends CommonResponseDTO {
    private Long id;
    private String title;
    private String content;
    private boolean isCompleted;
    private UserDTO user;
    private LocalDateTime createDate;

    public TodoResponseDTO(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.content = todo.getContent();
        this.isCompleted = todo.getIsCompleted();
        this.user = new UserDTO(todo.getUser());
        this.createDate = todo.getCreateDate();
    }
}
