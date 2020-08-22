package pl.sda.todoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.todoapp.dto.TodoDto;
import pl.sda.todoapp.entity.TodoEntity;
import pl.sda.todoapp.entity.UserEntity;
import pl.sda.todoapp.mapper.TodoMapper;
import pl.sda.todoapp.repository.TodoRepository;
import pl.sda.todoapp.repository.UserRepository;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    public List<TodoDto> getOpenTodos(String username) {

        return getTodos(false, username);
    }

    public List<TodoDto> getCloseTodos(String username) {

        return getTodos(true, username);
    }

    private List<TodoDto> getTodos(boolean closed, String username) {

        UserEntity user = userRepository.findByEmail(username);

        List<TodoEntity> todoEntities = todoRepository.findAllByUserAndClosed(user, closed);
        List<TodoDto> todoDtos = TodoMapper.mapFromEntityToDto(todoEntities);

        return todoDtos;
    }

    public TodoDto addTodo(TodoDto todo) {

        UserEntity user = userRepository.findByEmail(todo.getOwner());

        // TodoDto
        // username
        TodoEntity entity = new TodoEntity();
        entity.setDescription(todo.getDescritpion());
        entity.setUser(user);
        entity.setClosed(todo.isClosed());
        entity.setId(todo.getId());

        entity = todoRepository.save(entity);

        return TodoMapper.mapFromEntityToDto(entity);
    }

    public void completeTodo(Long id) {
        TodoEntity todo = todoRepository.getById(id);

        if (todo != null) {
            todo.setClosed(true);
            todoRepository.save(todo);
        }
    }
}
