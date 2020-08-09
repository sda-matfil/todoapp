package pl.sda.todoapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.sda.todoapp.entity.TodoEntity;

import java.util.ArrayList;

@Repository
public interface TodoRepository extends CrudRepository<TodoEntity, Long> {

    ArrayList<TodoEntity> findAllByClosed(boolean closed);
}
