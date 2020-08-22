package pl.sda.todoapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.sda.todoapp.entity.TodoEntity;
import pl.sda.todoapp.entity.UserEntity;

import java.util.List;

@Repository
public interface TodoRepository extends CrudRepository<TodoEntity, Long> {

//    List<TodoEntity> findAllByClosed(boolean closed);

    List<TodoEntity> findAllByUserAndClosed(UserEntity user, boolean closed);

    TodoEntity getById(Long id);
}
