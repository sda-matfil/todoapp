package pl.sda.todoapp.mapper;

import pl.sda.todoapp.dto.TodoDto;
import pl.sda.todoapp.entity.TodoEntity;

import java.util.ArrayList;
import java.util.List;

public class TodoMapper {

    public static TodoDto mapFromEntityToDto(TodoEntity entity) {

        TodoDto dto = new TodoDto();
        dto.setDescritpion(entity.getDescription());
        dto.setClosed(entity.isClosed());
        dto.setOwner(entity.getUser().getEmail());
        dto.setId(entity.getId());

        return dto;
    }

    public static List<TodoDto> mapFromEntityToDto(List<TodoEntity> entities) {

        ArrayList<TodoDto> todoDtos = new ArrayList<>();

        for (TodoEntity entity : entities) {
            todoDtos.add(mapFromEntityToDto(entity));
        }

        return todoDtos;
    }

//    public static TodoDto mapFromDtoToEntity(TodoDto dto) {
//
//        TodoEntity entity = new TodoEntity();
//        entity.setDescription(dto.getDescritpion());
//        entity.setUser();
//    }
}
