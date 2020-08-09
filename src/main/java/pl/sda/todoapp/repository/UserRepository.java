package pl.sda.todoapp.repository;

import org.springframework.data.repository.CrudRepository;
import pl.sda.todoapp.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);
}
