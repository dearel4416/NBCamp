package com.example.todoparty.todo;

import com.example.todoparty.CommonResponseDTO;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Getter
@Service
public class TodoRequestDTO {
    private String title;
    private String content;
}
