package pl.sda.todoapp.mapper;

import pl.sda.todoapp.dto.UserDto;
import pl.sda.todoapp.entity.UserEntity;

public class UserMapper {

    public static UserEntity mapDtoToEntity(UserDto userDto) {

        UserEntity entity = new UserEntity();
        entity.setEmail(userDto.getEmail());
        entity.setUsername(userDto.getUsername());
        entity.setPassword(userDto.getPassword());

        return entity;
    }
}
