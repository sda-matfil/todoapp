package pl.sda.todoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sda.todoapp.dto.TodoDto;
import pl.sda.todoapp.dto.UserDto;
import pl.sda.todoapp.mapper.TodoMapper;
import pl.sda.todoapp.service.TodoService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TodoController {

    @Autowired
    private TodoService todoService;

    @RequestMapping("/todos")
    public String todos(Model model) {

        String username = getCurrentUsername();

        List<TodoDto> todos = todoService.getOpenTodos(username);
        List<TodoDto> completed = todoService.getCloseTodos(username);

        model.addAttribute("todos", todos);
        model.addAttribute("completed", completed);

        return "todos";
    }

    @ResponseBody
    @RequestMapping(value = "/todos/{name}", method = RequestMethod.POST)
    public ResponseEntity<?> addTodos(@PathVariable("name") String name) {

        TodoDto dto = new TodoDto();
        dto.setDescritpion(name);
        dto.setOwner(getCurrentUsername());

        TodoDto todo = todoService.addTodo(dto);

        return ResponseEntity.ok(todo);
    }

    @ResponseBody
    @RequestMapping(value = "/todos/{id}", method = RequestMethod.PUT, produces = MediaType.TEXT_PLAIN_VALUE)
    public String updateCompleted(@PathVariable("id") Long id) {

        todoService.completeTodo(id);

        return "200 OK";
    }

    @RequestMapping(value = "/addTodo", method = RequestMethod.POST)
    public String addTodo(@ModelAttribute("todo") @Valid TodoDto todoDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        todoDto.setOwner(getCurrentUsername());

        todoService.addTodo(todoDto);

        return "redirect:/todos";
    }

    private String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails)principal).getUsername();
        } else {
            return principal.toString();
        }
    }
}
