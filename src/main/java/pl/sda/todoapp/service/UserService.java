package pl.sda.todoapp.service;

import pl.sda.todoapp.dto.UserDto;

public interface UserService {

    boolean saveUser(UserDto userDto);
}
