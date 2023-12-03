package com.example.todoparty.user;

import com.example.todoparty.todo.Todo;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
}
