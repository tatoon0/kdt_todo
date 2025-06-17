package com.example.todo.Service;

import com.example.todo.DTO.TodoDTO;
import com.example.todo.Entity.Todo;
import com.example.todo.Repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final ModelMapper modelMapper;

    public void create_todo(TodoDTO todoDTO) {
        todoRepository.save(modelMapper.map(todoDTO, Todo.class));
    }

    public TodoDTO read_todo(Integer id) {
        return modelMapper.map(todoRepository.findById(id), TodoDTO.class);
    }

    public Page<TodoDTO> list_todo(Pageable page) {
        int currentPage = page.getPageNumber() - 1;
        int pageSize = 4;

        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by(Sort.Direction.DESC, "id"));

        Page<Todo> todoPage = todoRepository.findAll(pageable);
        Page<TodoDTO> todoDTOPage = todoPage.map(data -> modelMapper.map(data, TodoDTO.class));

        return todoDTOPage;
    }

    public void update_todo(TodoDTO todoDTO) {
        todoRepository.save(modelMapper.map(todoDTO, Todo.class));
    }

    public void delete_todo(Integer id) {
        todoRepository.deleteById(id);
    }
}
