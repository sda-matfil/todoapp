package pl.sda.todoapp.mapper;

import pl.sda.todoapp.dto.TodoDto;
import pl.sda.todoapp.entity.TodoEntity;

import java.util.ArrayList;

public class TodoMapper {

    public static TodoDto mapFromEntityToDto(TodoEntity entity) {

        TodoDto dto = new TodoDto();
        dto.setDescritpion(entity.getDescription());
        dto.setClosed(entity.isClosed());

        return dto;
    }

    public static ArrayList<TodoDto> mapFromEntityToDto(ArrayList<TodoEntity> entities) {

        ArrayList<TodoDto> todoDtos = new ArrayList<>();

        for (TodoEntity entity : entities) {
            todoDtos.add(mapFromEntityToDto(entity));
        }

        return todoDtos;
    }
}
