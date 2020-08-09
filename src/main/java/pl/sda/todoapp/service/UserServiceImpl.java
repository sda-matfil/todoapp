package pl.sda.todoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.todoapp.dto.UserDto;
import pl.sda.todoapp.entity.UserEntity;
import pl.sda.todoapp.mapper.UserMapper;
import pl.sda.todoapp.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean saveUser(UserDto userDto) {

        UserEntity entity = UserMapper.mapDtoToEntity(userDto, passwordEncoder);

        try {
            userRepository.save(entity);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
