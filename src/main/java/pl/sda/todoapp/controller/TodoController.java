package pl.sda.todoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.todoapp.dto.TodoDto;
import pl.sda.todoapp.mapper.TodoMapper;
import pl.sda.todoapp.service.TodoService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TodoController {

    @Autowired
    private TodoService todoService;

    @RequestMapping("/todos")
    public String todos(Model model) {

        String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        List<TodoDto> todos = todoService.getOpenTodos(username);
        List<TodoDto> completed = todoService.getCloseTodos(username);

        model.addAttribute("todos", todos);
        model.addAttribute("completed", completed);

        return "todos";
    }
}
