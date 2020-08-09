package pl.sda.todoapp.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import pl.sda.todoapp.dto.UserDto;
import pl.sda.todoapp.entity.UserEntity;

public class UserMapper {

    public static UserEntity mapDtoToEntity(UserDto userDto, PasswordEncoder passwordEncoder) {

        UserEntity entity = new UserEntity();
        entity.setEmail(userDto.getEmail());
        entity.setUsername(userDto.getUsername());
        entity.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return entity;
    }
}
