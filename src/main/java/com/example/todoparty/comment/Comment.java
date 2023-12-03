package com.example.todoparty.comment;

import com.example.todoparty.todo.Todo;
import com.example.todoparty.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String text;

    @Column
    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "todo_id")
    private Todo todo;

    public Comment(CommentRequestDTO commentRequestDTO){
        this.text = commentRequestDTO.getText();
        this.createDate = LocalDateTime.now();
    }

    public void setUser(User user){
        this.user = user;
    }

    public void setTodo(Todo todo){
        this.todo = todo;
        todo.getComments().add(this);
    }

    public void setText(String text) {
        this.text = text;
    }
}
