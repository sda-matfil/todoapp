package pl.sda.todoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.todoapp.dto.TodoDto;
import pl.sda.todoapp.entity.TodoEntity;
import pl.sda.todoapp.mapper.TodoMapper;
import pl.sda.todoapp.repository.TodoRepository;

import java.util.ArrayList;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public ArrayList<TodoDto> getOpenTodos() {

        return getTodos(false);
    }

    public ArrayList<TodoDto> getCloseTodos() {

        return getTodos(true);
    }

    private ArrayList<TodoDto> getTodos(boolean closed) {

        ArrayList<TodoEntity> todoEntities = todoRepository.findAllByClosed(closed);
        ArrayList<TodoDto> todoDtos = TodoMapper.mapFromEntityToDto(todoEntities);

        return todoDtos;
    }
}
