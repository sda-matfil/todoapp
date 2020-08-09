package pl.sda.todoapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.sda.todoapp.dto.TodoDto;
import pl.sda.todoapp.entity.TodoEntity;
import pl.sda.todoapp.entity.UserEntity;
import pl.sda.todoapp.repository.TodoRepository;
import pl.sda.todoapp.repository.UserRepository;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        UserEntity user = new UserEntity();
        user.setEmail("jnowak@a.pl");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setUsername("jnowak");

        userRepository.save(user);

        for (int i = 1; i <= 15; i++) {
            insertTodo(i, false, user);
            insertTodo(i, true, user);
        }
    }

    private void insertTodo(int index, boolean completed, UserEntity user) {
        TodoEntity todo = new TodoEntity();
        todo.setDescription("Zadanie " + index);
        todo.setClosed(completed);
//        todo.setUser(user);

        todoRepository.save(todo);
    }
}
