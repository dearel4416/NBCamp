package com.example.todoparty.todo;

import com.example.todoparty.user.User;
import com.example.todoparty.user.UserDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.RejectedExecutionException;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoResponseDTO createPost(TodoRequestDTO todoRequestDTO, User user){
        Todo todo = new Todo(todoRequestDTO);
        todo.setUser(user);

        todoRepository.save(todo);

        return new TodoResponseDTO(todo);
    }

    public TodoResponseDTO getTodoDto(Long todoId) {
        Todo todo = getTodo(todoId);
        return new TodoResponseDTO(todo);
    }

    public Map<UserDTO, List<TodoResponseDTO>> getUserTodoMap() {
        Map<UserDTO, List<TodoResponseDTO>> userTodoMap = new HashMap<>();

        List<Todo> todoList = todoRepository.findAll(Sort.by(Sort.Direction.DESC, "createDate"));

        todoList.forEach(todo -> {
            var userDto = new UserDTO(todo.getUser());
            var todoDto = new TodoResponseDTO(todo);
            if(userTodoMap.containsKey(userDto)){
                //할일 목록 항목 추가
                userTodoMap.get(userDto).add(todoDto);
            }else {
                //할일 목록 새로 추가
                userTodoMap.put(userDto, new ArrayList<>(List.of(todoDto)));
            }
        });

        return userTodoMap;
    }

    @Transactional
    public TodoResponseDTO updateTodo(Long todoId, TodoRequestDTO todoRequestDTO, User user) {
        Todo todo = getUserTodo(todoId, user);

        todo.setTitle(todoRequestDTO.getTitle());
        todo.setContent(todoRequestDTO.getContent());

        return new TodoResponseDTO(todo);
    }

    @Transactional
    public TodoResponseDTO completeTodo(Long todoId, User user) {
        Todo todo = getUserTodo(todoId, user);

        todo.setIsCompleted();

        return new TodoResponseDTO(todo);
    }

    public Todo getTodo(Long todoId) {
        return todoRepository.findById(todoId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 할일 ID입니다."));
    }

    public Todo getUserTodo(Long todoId, User user) {
        Todo todo = getTodo(todoId);

        if(!user.getId().equals(todo.getUser().getId())){
            throw new RejectedExecutionException("작성자만 수정할 수 있습니다.");
        }
        return todo;
    }
}
