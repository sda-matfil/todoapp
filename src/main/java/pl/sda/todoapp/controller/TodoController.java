package pl.sda.todoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

        ArrayList<TodoDto> todos = todoService.getOpenTodos();
        ArrayList<TodoDto> completed = todoService.getCloseTodos();

        model.addAttribute("todos", todos);
        model.addAttribute("completed", completed);

        return "todos";
    }
}
