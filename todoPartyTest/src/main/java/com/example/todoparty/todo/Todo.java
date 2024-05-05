package com.example.todoparty.todo;

import com.example.todoparty.comment.Comment;
import com.example.todoparty.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private LocalDateTime createDate;

    @Column
    private Boolean isCompleted;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    @JoinColumn
    private List<Comment> comments;

    public Todo(TodoRequestDTO todoRequestDTO){
        this.title = todoRequestDTO.getTitle();
        this.content = todoRequestDTO.getContent();
        this.createDate = LocalDateTime.now();
    }

    public void setUser(User user){
        this.user = user;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public void setIsCompleted() {
        this.isCompleted = true;
    }
}