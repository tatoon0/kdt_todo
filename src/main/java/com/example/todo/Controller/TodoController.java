package com.example.todo.Controller;

import com.example.todo.DTO.TodoDTO;
import com.example.todo.Service.TodoService;
import com.example.todo.Util.PaginationUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;
    private final PaginationUtil paginationUtil;

    @GetMapping("/register")
    public String register_get(Model model) {
        model.addAttribute(new TodoDTO());

        return "todo/register";
    }

    @PostMapping("/register")
    public String register_post(@Valid TodoDTO todoDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "todo/register";
        }

        todoService.create_todo(todoDTO);

        return "redirect:/todo/list";
    }

    @GetMapping("/list")
    public String list_get(@PageableDefault(page = 1) Pageable pageable, Model model) {
        Page<TodoDTO> todoDTOPage = todoService.list_todo(pageable);
        model.addAttribute("todoDTO", todoDTOPage);

        Map<String, Integer> pageInfo = paginationUtil.Pagination(todoDTOPage);
        model.addAllAttributes(pageInfo);

        return "todo/list";
    }

    @GetMapping("/update")
    public String update_get(Integer id, Model model) {
        model.addAttribute(todoService.read_todo(id));

        return "todo/update";
    }

    @PostMapping("/update")
    public String update_post(@Valid TodoDTO todoDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "todo/update";
        }

        todoService.update_todo(todoDTO);

        return "redirect:/todo/list";
    }

    @GetMapping("/delete")
    public String delete_get(Integer id, Integer page) {
        todoService.delete_todo(id);

        return "redirect:/todo/list?page="+page;
    }
}
